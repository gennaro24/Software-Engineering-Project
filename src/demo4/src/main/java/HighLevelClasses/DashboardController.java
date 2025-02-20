package HighLevelClasses;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import LowLevelClasses.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML
    private Button confirmAddButton;
    @FXML
    private Button addButton;
    @FXML
    private Button searchButton;
    @FXML
    private Button confirmButton; //--
    @FXML
    private Label missingFieldMessage; //--
    @FXML
    private Button resetSearchButton;
    @FXML
    private TextField searchField;
    @FXML
    private TableView<Contact> table;
    @FXML
    private TableColumn<Contact , String> nameCol;
    @FXML
    private TableColumn<Contact , String > surnameCol;
    @FXML
    private ObservableList<Contact> allContacts;
    @FXML
    private ObservableList<Contact> filteredContacts;

    @FXML
    private AnchorPane mainDashboard;
    @FXML
    private AnchorPane VMDashboard; // --

    @FXML
    private TextField name; //--
    @FXML
    private TextField surname; //
    /***********************************************************************
    * TextField del menù contestuale di aggiunta / modifica / visualizzazione
    **************************************************************************/
    @FXML
    private TextField email1;
    @FXML
    private TextField email2;
    @FXML
    private TextField email3;
    @FXML
    private TextField number1;
    @FXML
    private TextField number2;
    @FXML
    private TextField number3;
    private TextField[] numberArray;
    private TextField[] emailsArray;
    /*************************************************************************
     label per il messaggio : email non valida (nve) / numero non valido (nvn)
     **************************************************************************/
    @FXML
    private Label nve1;
    @FXML
    private Label nve2;
    @FXML
    private Label nve3;
    @FXML
    private Label nvn1;
    @FXML
    private Label nvn2;
    @FXML
    private Label nvn3;
    /**ARRAY DI LABEL**/
    private Label[] nveLabels;
    private Label[] nvnLabels;
    /**utile per tenere traccia del contatto corrente in modifica; viene istanziato alla chiamata di modifyClick**/
    private Contact currentContact;
    /***************************INIZIALIZZAZIONE SCENA*********************/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainDashboard.setVisible(true);
        mainDashboard.setDisable(false);

        VMDashboard.setDisable(true);
        VMDashboard.setVisible(false);

        resetSearchButton.setVisible(false);

        initializeTable();
        loadResource();
        emailsArray = new TextField[]{email1 , email2 , email3};
        numberArray = new TextField[]{number1 , number2 , number3};
        nveLabels= new Label[]{nve1 , nve2 , nve3};
        nvnLabels = new Label[]{nvn1 , nvn2 , nvn3};
        initBindings();

    }

    /*******************************
     INIZIALIZZAZIONE TABELLA :
     - associazione colonne - proprietà nome e cognome dell'oggetto contact
     - creazione di un contextMenu al click destro sul contatto in lista,
        con possibilità di modifica , eliminazione e visualizzazione.
     - ad ogni item viene associato un metodo a cui viene passato il contatto
       selezionato (row.getItem()).
     *******************************/
    @FXML
    public void initializeTable(){
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
        table.setRowFactory(tv -> {
            TableRow<Contact> row = new TableRow<>();
            row.setOnContextMenuRequested(event ->{
                if (!row.isEmpty()){
                    ContextMenu contextMenu = new ContextMenu();

                    MenuItem viewItem = new MenuItem("Visualizza");
                    viewItem.setOnAction(e -> showClick(row.getItem()));

                    MenuItem deleteItem = new MenuItem("Elimina");
                    deleteItem.setOnAction(e -> deleteClick(row.getItem()));

                    MenuItem modifyItem = new MenuItem("Modifica");
                    modifyItem.setOnAction(e -> modifyClick(row.getItem()));

                    contextMenu.getItems().addAll(viewItem , modifyItem , deleteItem);
                    contextMenu.show(row , event.getScreenX() , event.getScreenY());
                }
            });
            return row;
        });
    }
    /***************************************************
     * CARICAMENTO RISORSE ATTRAVERSO IL METODO loadFileFromResource()
      ************************************************/
    public void loadResource(){
        FileManager fm = new FileManager();
        ContactList c = fm.loadFileFromResource();
        allContacts = FXCollections.observableArrayList(c.getContacts());
        filteredContacts = FXCollections.observableArrayList(ContactListManager.searchContact(c.getContacts() , null));
        table.setItems(allContacts);
        if (allContacts.isEmpty())
                table.setPlaceholder(new Label("Nessun contatto presente. "));
    }
    @FXML
    public void handleLoadButton() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleziona il file dei contatti");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("File JSON (*.json)", "*.json"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            FileManager fm = new FileManager();
            ContactList c =  fm.loadFile(selectedFile.getAbsolutePath());
            allContacts = FXCollections.observableArrayList(c.getContacts());
            table.setItems(allContacts);
            if (allContacts.isEmpty()) {
                table.setPlaceholder(new Label("Nessun contatto presente."));
            }
            showAlert("Contatti caricati con successo da:\n" + selectedFile.getAbsolutePath(),
                    Alert.AlertType.INFORMATION, "Caricamento");
        } else {
            showAlert("Nessun file selezionato.", Alert.AlertType.WARNING, "Caricamento");
        }
    }

    public void handleSaveButton(){
    FileManager fm = new FileManager();
    ContactList c = new ContactList(new ArrayList<>(allContacts));
    fm.saveFile(c);
    showAlert("Contatti salvati con successo!" , Alert.AlertType.CONFIRMATION , "salvataggio");
    }

    @FXML
    public void deleteClick(Contact c) {
        // creazione dei bottoni all'interno dell'alert
        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE); //bottone conferma
        ButtonType backButton = new ButtonType("Indietro", ButtonBar.ButtonData.CANCEL_CLOSE); //bottone indietro

        // Crea l'Alert di conferma
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Vuoi eliminare il contatto:\n" + c.getName() + " " + c.getSurname() + "?",
                okButton, backButton);
        alert.setTitle("Conferma Eliminazione");
        alert.setHeaderText("Eliminazione Contatto");

        alert.getDialogPane().setStyle("-fx-font-size: 14pt; -fx-font-family: 'Arial';");

        // Mostra il dialogo e aspetta la risposta
        alert.showAndWait().ifPresent(response -> {
            if (response == okButton) {
                // Se confermato, rimuovi il contatto dalla lista
                allContacts.remove(c);
                table.refresh();
                refreshFilteredTable();
                showAlert("Contatto eliminato con successo!", Alert.AlertType.INFORMATION, "Eliminato");
            } else {
                showAlert("Eliminazione annullata.", Alert.AlertType.INFORMATION, "Annullato");
            }
        });
    }


    public void modifyClick(Contact c){
        currentContact = c;
        System.out.println("funziona modifica sul contatto : " + c.getName() + " " + c.getSurname());
        openContextDashboard();
        loadFields(c);
        setConfirmButton(true);
        setConfirmAddButton(false);
        enableModifyContextDashboard();

    }
    @FXML
    public void addClick(){

        openContextDashboard();
        enableModifyContextDashboard();
        clearContextDashboard();
        setConfirmButton(false);
        setConfirmAddButton(true);

    }

    public void showClick(Contact c){
        System.out.println("funziona visualizza sul contatto : " + c.getName() + " " + c.getSurname());
        loadFields(c);
        openContextDashboard();
        disableModifyContextDashboard();
        setConfirmButton(false);
        setConfirmAddButton(false);
        loadFields(c);
    }
    @FXML
    public void handleSearchButton(){
        if (searchField.getText() == null)
            return;

        String query = searchField.getText().trim();

        if (!query.isEmpty()) {
            ArrayList<Contact> filteredList = ContactListManager.searchContact(new ArrayList<>(allContacts), query);
            filteredContacts = FXCollections.observableArrayList(filteredList);
            table.setItems(filteredContacts);
            resetSearchButton.setVisible(true);
        }

    }

    @FXML
    public void handleResetSearchButton(){
        table.setItems(allContacts);
        searchField.clear();
        resetSearchButton.setVisible(false);
    }

    @FXML
    public void handleResetVM(){
        closeContextDashboard();
        clearContextDashboard();

    }

    @FXML
    public void handleConfirmEditButton(){

       setConfirmAddButton(false);
       setConfirmButton(true);
       setErrorLabelsVisible(false);

        int i = 0;
        /**creazione di liste per salvare le modifiche alle e-mail utili per l'aggiornamento del contatto**/
        ArrayList<String> emails = new ArrayList<>();
        ArrayList<String> numbers = new ArrayList<>();

        for (TextField t : emailsArray){
            if (!t.getText().isEmpty()) {
                Boolean validMail = ContactChecker.isValidEmail(t.getText());
                if (!validMail) {
                    nveLabels[i].setVisible(true);
                    return;
                }

            }
            i++;
            emails.add(t.getText());
        }
        i = 0;
        for (TextField n : numberArray) {
            if (!n.getText().isEmpty()) {
                Boolean validNumber = ContactChecker.isValidNumber(n.getText());
                if (!validNumber) {
                    nvnLabels[i].setVisible(true);
                    return;
                }
            }
            i++;
            numbers.add(n.getText());
        }

        Contact modifiedContact = new Contact(name.getText() , surname.getText() , emails , numbers);
        ContactListManager.updateContact(allContacts , currentContact , modifiedContact);
        table.refresh();
        refreshFilteredTable();
        showAlert("modifica avvenuta con successo!" , Alert.AlertType.INFORMATION , "modifica");
        clearContextDashboard();
        closeContextDashboard();

    }

    @FXML
    public void handleConfirmAddButton(){
        setConfirmAddButton(true);
        setConfirmButton(false);
        setErrorLabelsVisible(false);

        int i = 0;
        /**creazione di liste per salvare le modifiche alle e-mail utili per l'aggiornamento del contatto**/
        ArrayList<String> emails = new ArrayList<>();
        ArrayList<String> numbers = new ArrayList<>();

        for (TextField t : emailsArray){
            if (!t.getText().isEmpty()) {
                Boolean validMail = ContactChecker.isValidEmail(t.getText());
                if (!validMail) {
                    nveLabels[i].setVisible(true);
                    return;
                }

            }
            i++;
            emails.add(t.getText());
        }
        i = 0;
        for (TextField n : numberArray) {
            if (!n.getText().isEmpty()) {
                Boolean validNumber = ContactChecker.isValidNumber(n.getText());
                if (!validNumber) {
                    nvnLabels[i].setVisible(true);
                    return;
                }
            }
            i++;
            numbers.add(n.getText());
        }

        Contact addedContact = new Contact(name.getText() , surname.getText() , emails , numbers);
        allContacts.add(addedContact);
        table.refresh();
        refreshFilteredTable();
        showAlert("è stato aggiunto   \n" + addedContact.getName() + "   " + addedContact.getSurname() + "\n alla lista dei contatti!" , Alert.AlertType.INFORMATION , "modifica");
        clearContextDashboard();
        closeContextDashboard();




    }

    /**UTILITIES METHODS : richiamabili per modifiche visive all'interno della dashboard.**/


    /************************************************************************************
    Apertura Dashboard di contesto per la modifica, visualizzazione e aggiunta di contatti.
     **************************************************************************************/
    public void openContextDashboard(){
        for (Label l : nvnLabels) {
            l.setVisible(false);
            l.setManaged(false);
        }
        for (Label l : nveLabels) {
            l.setVisible(false);
            l.setManaged(false);
        }
        mainDashboard.setDisable(true);
        mainDashboard.setVisible(false);
        VMDashboard.setVisible(true);
        VMDashboard.setDisable(false);
    }
    /*****************************************************************************************
     Chiusura Dashboard di contesto per la modifica, visualizzazione e aggiunta di contatti.
     *****************************************************************************************/
    public void closeContextDashboard(){
        mainDashboard.setDisable(false);
        mainDashboard.setVisible(true);
        VMDashboard.setVisible(false);
        VMDashboard.setDisable(true);
    }
    /***********************************************************************************************************
      Pulizia dei campi della Dashboard di contesto per la modifica, visualizzazione e aggiunta di contatti.
     ***********************************************************************************************************/
    public void clearContextDashboard(){
        name.clear();
        surname.clear();
        email1.clear();
        email2.clear();
        email3.clear();
        number1.clear();
        number2.clear();
        number3.clear();
    }
    /***********************************************************************************************************
                Disabilita la modifica dei campi all'interno della Dashboard di contesto.
     ***********************************************************************************************************/
    public void disableModifyContextDashboard(){
        name.setEditable(false);
        surname.setEditable(false);
        email3.setEditable(false);
        email2.setEditable(false);
        email1.setEditable(false);
        number1.setEditable(false);
        number2.setEditable(false);
        number3.setEditable(false);
    }
    /***********************************************************************************************************
     Abilita la modifica dei campi all'interno della Dashboard di contesto.
     ***********************************************************************************************************/
    public void enableModifyContextDashboard(){
        name.setEditable(true);
        surname.setEditable(true);
        email3.setEditable(true);
        email2.setEditable(true);
        email1.setEditable(true);
        number1.setEditable(true);
        number2.setEditable(true);
        number3.setEditable(true);
    }

    /***********************************************************************************************************
     Carica i campi della Dashboard di Contesto prendendo come parametro il contatto c selezionato dall'utente
     ***********************************************************************************************************/

    public void loadFields (Contact c){
        name.setText(c.getName());
        surname.setText(c.getSurname());
        int i = 0;
        for (String s : c.getEmails()){
            if (s != null)
                emailsArray[i].setText(s);
            i++;
        }
        i = 0;
        for (String s : c.getNumbers()){
            if ( s != null)
                numberArray[i].setText(s);
            i++;
        }

    }
    /***********************************************************************************************************
     Mostra un alert passando come parametri:
     text -> il testo da mostrare nell'alert.
     tipo -> il tipo di alert da mostrare.
     titolo -> il titolo della finestra di alert.
     ***********************************************************************************************************/
    public void showAlert(String text , Alert.AlertType tipo ,String titolo) {
        Alert alert = new Alert(tipo);
        alert.getDialogPane().getStyleClass().add("error-dialog");
        alert.setTitle(titolo);
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }
    /***********************************************************************************************************
     Permette di abilitare (con x = true) o disabilitare (con x = false) il bottone di conferma.
     ***********************************************************************************************************/

    public void setConfirmButton(boolean x){
        confirmButton.setVisible(x);
        confirmButton.setManaged(x);
        confirmButton.setMouseTransparent(!x);
    }
    /***********************************************************************************************************
     Permette di abilitare (con x = true) o disabilitare (con x = false) il bottone di conferma di aggiunta.
     ***********************************************************************************************************/
    public void setConfirmAddButton(boolean x){
        confirmAddButton.setVisible(x);
        confirmAddButton.setManaged(x);
        confirmAddButton.setMouseTransparent(!x);

    }

    public void setErrorLabelsVisible(boolean x){
        for (Label l : nvnLabels) {
            l.setVisible(x);
            l.setManaged(x);
        }
        for (Label l : nveLabels) {
            l.setVisible(x);
            l.setManaged(x);
        }
    }

    public void initBindings(){
        BooleanBinding missingFields = Bindings.createBooleanBinding(() ->
                ContactChecker.checkNameSurname(name.getText() , surname.getText()) , name.textProperty() , surname.textProperty() );
        confirmButton.disableProperty().bind(missingFields);
        missingFieldMessage.visibleProperty().bind(missingFields);
        confirmAddButton.disableProperty().bind(missingFields);
    }
    /***********************************************************************************************************
     riflette i cambiamenti della lista filtrata di contatti durante la ricerca nel caso in cui l'utente modifica elimina o
     aggiunge un contatto che potrebbe corrispondere ai criteri di ricerca correnti.
     ***********************************************************************************************************/
    private void refreshFilteredTable() {
        String query = searchField.getText().trim();
        if (!query.isEmpty()) {
            ArrayList<Contact> filteredList = ContactListManager.searchContact(new ArrayList<>(allContacts), query);
            filteredContacts = FXCollections.observableArrayList(filteredList);
            table.setItems(filteredContacts);
        } else {
            table.setItems(allContacts);
        }
        table.refresh();
    }

}
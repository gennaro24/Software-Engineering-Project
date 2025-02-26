# Software Engineering Project - Contact List 
 
Sviluppato da: _Foschillo Gennaro_

# Obiettivi di progetto
Il progetto ha come obiettivo la realizzazione di una rubrica per la gestione di contatti telefonici. In particolare, dovranno essere gestiti un insieme di contatti a cui sono associati:
- **nome** , **Cognome**
- **Numero di telefono** *(Max 3 occorrenze)*
- **Indirizzo e-mail** *(Max 3 occorrenze)* 
- Dovrà essere possibile la **Creazione , Modifica , Cancellazione , Ricerca** dei contatti.
- Il **salvataggio** e il **caricamento** della rubrica dovrà essere gestito attraverso **File** .
- I contatti dovranno essere mostrati in ordine alfabetico e la ricerca potrà avvenire attraverso l'inserimento di una sottostringa del nome o del cognome.

L'applicativo deve essere sviluppato in linguaggio **Java** e dotato di interfaccia grafica realizzata con framework **JavaFX** Utilizzando il pattern MVC. Inoltre dovranno essere effettuati dei test per verificarne il corretto funzionamento, fatti attraverso **JUnit**


## Struttura Repository 

- `/docs`               # Documentazione del progetto (Planning ,Requirements , Design)
- `/docs/Planning`      # Pianificazione del progetto in aggiornamento continuo con task sempre più dettagliate.
- `/docs/Requirements`  # Cartella contenente il documento dei requisiti, codice uml del diagramma dei casi d'uso e codice latex per la realizzazione del documento dei requisiti.
- `/docs/Design`        # Progettazione e diagrammi di progetto
- `/src/demo4/src/main/resources`     # Eventuali file utility (.css , .fxml , .json) 
- `/src/demo4/src/main/java`          # Codice sorgente
- `/src/demo4/src/tests`              # Contiene test automatizzati delle classi LowLevel
- `**2 branch**`:
  1. main: contiene il codice sviluppato e approvato
  2. dev: contiene il codice in sviluppo / non approvato




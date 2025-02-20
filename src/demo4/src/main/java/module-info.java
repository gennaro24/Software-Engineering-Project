module foschillogennaro.demo4 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.google.gson;

    opens LowLevelClasses to javafx.fxml , com.google.gson;
    exports LowLevelClasses;
    exports HighLevelClasses;
    opens HighLevelClasses to javafx.fxml;

}
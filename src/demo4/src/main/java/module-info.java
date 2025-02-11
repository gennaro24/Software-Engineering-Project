module foschillogennaro.demo4 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens LowLevelClasses to javafx.fxml;
    exports LowLevelClasses;
    exports HighLevelClasses;
    opens HighLevelClasses to javafx.fxml;
}
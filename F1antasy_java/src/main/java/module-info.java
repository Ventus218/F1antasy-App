module f1antasy.f1antasy {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens f1antasy.f1antasy to javafx.fxml;
    exports f1antasy.f1antasy;
}
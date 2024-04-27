module com.first.liederverwaltung {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.first.liederverwaltung to javafx.fxml;
    exports com.first.liederverwaltung;
}
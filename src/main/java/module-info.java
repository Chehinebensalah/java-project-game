module com.mycompany.mavenproject1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    requires java.desktop;
    opens com.mycompany.mavenproject1 to javafx.fxml;
    //opens com.mycompany.mavenproject1.game to javafx.fxml;
    exports com.mycompany.mavenproject1;
    //exports com.mycompany.mavenproject1.game;
}

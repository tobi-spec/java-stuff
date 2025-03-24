module org.example.springstuff {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.springstuff to javafx.fxml;
    exports org.example.springstuff;
}
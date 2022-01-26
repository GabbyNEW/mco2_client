module com.example.mysql_client_javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.mysql_client_javafx to javafx.fxml;
    exports com.example.mysql_client_javafx;
}
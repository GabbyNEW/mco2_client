module com.example.mysql_client_javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires org.json;


    opens com.example.mysql_client_javafx to javafx.fxml;
    exports com.example.mysql_client_javafx;
}
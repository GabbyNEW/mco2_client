package com.example.mysql_client_javafx;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.json.*;

public class DeleteController {
    @FXML
    private Button deleteBtn;
    @FXML
    private CheckBox doRollbackCheck;
    @FXML
    private TextField rankInput, secondsInput, yearInput;
    @FXML
    private Text whatsThisClick;

    Alert a = new Alert(Alert.AlertType.NONE);

    @FXML
    void sendDeleteRequest(MouseEvent event) {

    }

    @FXML
    void showSecondsInfoPrompt(MouseEvent event) {
        a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Information");
        a.setContentText("Before a transaction is committed by the web server, you may optionally add a timeout before it does so. " +
                "This is to test isolation levels.");
        a.showAndWait();
    }
}

package com.example.mysql_client_javafx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class WriteController {
    public TextField titleInput, yearInput, rankInput, secondsInput;
    public Button postBtn;
    public Text whatsThisClick;
    public CheckBox doRollbackCheck;

    Alert a = new Alert(Alert.AlertType.NONE);

    public void showSecondsInfoPrompt(MouseEvent mouseEvent) {
        a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Information");
        a.setContentText("Before a transaction is commited by the web server, you may optionally add a timeout before it does so. " +
                "This is to test isolation levels.");
        a.showAndWait();
    }
}

package com.example.mysql_client_javafx;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class ReadController {
    public TextField idInput, titleInput, yearInput;
    public Text infoTextBtn;
    public Button getBtn;
    public Button spamBtn;
    public Button stopBtn;

    Alert a = new Alert(Alert.AlertType.NONE);

    public void whatsThisInfoPromptClick(MouseEvent mouseEvent) {
        a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Information");
        a.setContentText("You may send consecutive GET requests with the same filter information. "
        + "This is to monitor data stored on the database for the purpose of testing isolation levels.");
        a.showAndWait();
    }

    // TODO: Create a separate window with a TableView container to show the returned GET data.
}

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
    private TextField idInput, secondsInput, titleInput;

    public Boolean isRollback;
    public String isRollbackText;

    Alert a = new Alert(Alert.AlertType.NONE);

    HttpClient client;
    HttpRequest request; boolean debug = true;

    @FXML
    void sendDeleteRequest(MouseEvent event) {
        client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
        String seconds = secondsInput.getText().compareTo("") == 0 ? "0" : secondsInput.getText();
        isRollback = doRollbackCheck.isSelected();
        isRollbackText = (isRollback) ? "true" : "false";
        if (debug)
            request = HttpRequest.newBuilder()
                    .uri(URI.create("http://206.189.148.74:5001/")) // Replication server
                    .DELETE()
                    .header("_id", "1100")
                    .header("title", titleInput.getText())
                    .header("seconds", "0")
                    .header("rollback",isRollbackText)
                    .build();
        else
            request = HttpRequest.newBuilder()
                    .uri(URI.create("http://206.189.148.74:5001/")) // Replication server
                    .DELETE()
                    .header("_id", idInput.getText())
                    .header("title", titleInput.getText())
                    .header("seconds", seconds)
                    .header("rollback",isRollbackText)
                    .build();

        try {
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

            showOutputPrompt("Status code: " + response.statusCode() + "\nResponse body: "  + response.body().toString());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showSecondsInfoPrompt(MouseEvent event) {
        a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Information");
        a.setContentText("Before a transaction is committed by the web server, you may optionally add a timeout before it does so. " +
                "This is to test isolation levels.");
        a.showAndWait();
    }

    void showOutputPrompt(String output) {
        a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Information");
        a.setContentText(output);
        a.showAndWait();
    }
}

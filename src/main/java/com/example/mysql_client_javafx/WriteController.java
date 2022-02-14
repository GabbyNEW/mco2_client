package com.example.mysql_client_javafx;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.json.*;

public class WriteController {
    public TextField titleInput, yearInput, rankInput, secondsInput;
    public Button postBtn;
    public Text whatsThisClick;
    public CheckBox doRollbackCheck;
    public TextArea outputArea;
    public Boolean isRollback;
    public String isRollbackText;

    Alert a = new Alert(Alert.AlertType.NONE);

    HttpClient client;
    HttpRequest request; boolean debug = false;

    public String preparePostHeader() {
        // TODO: Add chosenIsolationLevel, isRollback fields
        isRollback = doRollbackCheck.isSelected();
        isRollbackText = (isRollback) ? "true" : "false";

        String seconds = secondsInput.getText().compareTo("") == 0 ? "0" : secondsInput.getText();
        String yearInputHeader = (yearInput.getText().compareTo("") == 0) ? "-1" : yearInput.getText();
        if (debug)
            return new JSONObject()
                    .put("title", "TITLE HERE")
                    .put("year", "1989")
                    .put("rank", "69")
                    .put("seconds", "0")
                    .put("rollback",isRollbackText)
                    .toString();

        return new JSONObject()
                .put("title", titleInput.getText())
                .put("year", yearInput.getText())
                .put("rank", rankInput.getText())
                .put("seconds", seconds)
                .put("rollback",isRollbackText)
                .toString();
    }

    // Send POST Request to Replication Server
    public void sendPostRequest() {
        String headers = preparePostHeader();
        client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
        request = HttpRequest.newBuilder()
                .uri(URI.create("http://206.189.148.74:5001/")) // Replication server
                .POST(HttpRequest.BodyPublishers.ofString(headers))
                .header("Accept", "application/json")
                .build();

        try {
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

            outputArea.setText( "Status code: " + response.statusCode() + "\nResponse body: "  + response.body().toString());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void showSecondsInfoPrompt(MouseEvent mouseEvent) {
        a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Information");
        a.setContentText("Before a transaction is committed by the web server, you may optionally add a timeout before it does so. " +
                "This is to test isolation levels.");
        a.showAndWait();
    }
}

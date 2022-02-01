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

    Alert a = new Alert(Alert.AlertType.NONE);

    HttpClient client;
    HttpRequest request; boolean debug = true;

    public String preparePostHeader() {
        if (debug)
            return new JSONObject()
                    .put("title", "TITLE HERE")
                    .put("year", "1989")
                    .put("rank", "69")
                    .put("seconds", "0")
                    .toString();

        return new JSONObject()
                .put("title", titleInput.getText())
                .put("year", yearInput.getText())
                .put("rank", rankInput.getText())
                .put("seconds", secondsInput.getText())
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
            System.out.println(response.statusCode());
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

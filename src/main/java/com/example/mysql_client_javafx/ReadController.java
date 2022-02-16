package com.example.mysql_client_javafx;

import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;

public class ReadController {
    public TextField idInput, titleInput, yearInput;
    public Text infoTextBtn;
    public Button getBtn, spamBtn, stopBtn;
    public TextArea outputArea;

    HttpClient client;
    HttpRequest request; boolean debug = false;

    private boolean runSpam = false;

    Alert a = new Alert(Alert.AlertType.NONE);

    public void sendGetRequest() {
        // TODO: Add chosenIsolationLevel field
        client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
        String yearInputHeader = (yearInput.getText().compareTo("") == 0) ? "-1" : yearInput.getText();
        if (debug)
            request = HttpRequest.newBuilder()
                    .uri(URI.create("http://206.189.148.74:5001/")) // Replication server
                    .header("_id", "4")
                    .header("title", "")
                    .header("year", "")
                    .GET()
                    .build();
        else
            request = HttpRequest.newBuilder()
                    .uri(URI.create("http://206.189.148.74:5001/")) // Replication server
                    .header("_id", idInput.getText())
                    .header("title", titleInput.getText())
                    .header("year", yearInputHeader)
                    .GET()
                    .build();
        try {
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            outputArea.setText(response.body().toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void spamGetRequest() throws InterruptedException {
        runSpam = true;
        spamBtn.setDisable(true);
        stopBtn.setDisable(false);

        Task task = new Task<Void>() {
            @Override
            public Void call() throws InterruptedException {
                while (runSpam) {
                    outputArea.setText("Sending GET Request...");
                    System.out.println("Sending GET Request...");
                    sendGetRequest();
                    Thread.sleep(1000);
                }
                return null;
            }
        };

        new Thread(task).start();
    }

    public void stopSpamRequest() {
        runSpam = false;
        spamBtn.setDisable(false);
        stopBtn.setDisable(true);
    }

    public void whatsThisInfoPromptClick(MouseEvent mouseEvent) {
        a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Information");
        a.setContentText("You may send consecutive GET requests with the same filter information. "
        + "This is to monitor data stored on the database for the purpose of testing data consistency across multiple isolation levels.");
        a.showAndWait();
    }

    // TODO: Create a separate window with a TableView container to show the returned GET data.
}

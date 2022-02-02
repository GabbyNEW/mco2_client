package com.example.mysql_client_javafx;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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

    HttpClient client;
    HttpRequest request; boolean debug = true;

    Alert a = new Alert(Alert.AlertType.NONE);

    public void sendGetRequest() {
        client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
        if (debug)
            request = HttpRequest.newBuilder()
                    .uri(URI.create("http://206.189.148.74:5001/")) // Replication server
                    .header("key", "value")
                    .GET()
                    .build();
        else
            request = HttpRequest.newBuilder()
                    .uri(URI.create("http://206.189.148.74:5001/")) // Replication server
                    .header("key", "value")
                    .GET()
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

    public void whatsThisInfoPromptClick(MouseEvent mouseEvent) {
        a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Information");
        a.setContentText("You may send consecutive GET requests with the same filter information. "
        + "This is to monitor data stored on the database for the purpose of testing isolation levels.");
        a.showAndWait();
    }

    // TODO: Create a separate window with a TableView container to show the returned GET data.
}

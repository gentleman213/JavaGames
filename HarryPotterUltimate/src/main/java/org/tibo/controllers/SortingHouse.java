package org.tibo.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


import java.io.IOException;

import static org.tibo.controllers.SetUp.wizard;

public class SortingHouse {
    @FXML private Label house;
    @FXML private Button houseButton;
    @FXML private Label dialogHouse;



    public void initialize() {
        dialogHouse.setText("Ahh, another young mind to sort,\n" +
                "Let me see what where \n to place you...");

    }


    public void tellMe(){
        dialogHouse.setText("");
        house.setText(wizard.getHouse().name());
        houseButton.setText("Begin my Adventure");
        houseButton.setOnAction(event2 -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/tibo/Fight.fxml"));
            Stage stage = (Stage) ((Node) event2.getSource()).getScene().getWindow();
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen(); // Loads the stage in the middle
            stage.setResizable(false);
            stage.show();
        });

    }

}

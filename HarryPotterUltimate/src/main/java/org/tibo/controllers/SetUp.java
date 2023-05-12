package org.tibo.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.tibo.character.SortingHat;
import org.tibo.character.Wizard;
import org.tibo.object.Core;
import org.tibo.object.House;
import org.tibo.object.Wand;

import java.io.IOException;

public class SetUp {

    static Wizard wizard;
    @FXML private Slider length;
    @FXML private Label lengthLabel;
    @FXML private Label errorText;
    @FXML private TextField nameField;
    @FXML private ComboBox animalBox;
    @FXML private ComboBox matiereBox;

    public void initialize() {
        length.valueProperty().addListener((obs, oldVal, newVal) -> {
            int value = (int) Math.round(newVal.intValue());
            length.setValue(value);
            lengthLabel.setText(Integer.toString(value));
        });
    }

    public void validate(ActionEvent event) throws IOException {
        String name = nameField.getText();
        String animal = (String) animalBox.getValue();
        String coreType = (String) matiereBox.getValue();
        int lengthWand = (int) length.getValue();


        if (name.isEmpty() || animal == null || coreType == null) {
            errorText.setText("Please complete all the fields!");
        } else {
            Core coreType2 = Core.valueOf(coreType);
            SortingHat sortingHat = new SortingHat();
            House house = sortingHat.assignHouse();
            wizard = new Wizard(name, house);
            Wand wand = new Wand( coreType2, lengthWand);
            wizard.equip(wand);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/tibo/SortingHouse.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen(); // Loads the stage in the middle
            stage.setResizable(false);
            stage.show();

        }
    }



}








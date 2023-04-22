package org.tibo.controllers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.tibo.character.SortingHat;
import org.tibo.character.Wizard;
import org.tibo.object.House;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Test {

    private List<String> textes = new ArrayList<>();
    private int texteActuelIndex = 0;

    static Wizard wizard;
    @FXML private Label questionText;
    @FXML private Label resultLabel;
    @FXML private TextField textInput;

    public void initialize() {
        questionText.setText("What's your name?");
        textes.add("What's your name?");
        textes.add("Please, choose an animal:");
        textes.add("Texte 3");
    }



    @FXML
    public void confirm() throws IOException {
        String name = textInput.getText();
        questionText.setText("");
        resultLabel.setText("Your name is "+name);
        textInput.setVisible(false);

        if(texteActuelIndex == 0) {
            SortingHat sortingHat = new SortingHat();
            House house = sortingHat.assignHouse();
            wizard = new Wizard(name, house);
        }
        texteActuelIndex++; // Incrémenter l'indice de la valeur actuelle
        if (texteActuelIndex >= textes.size()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/tibo/SetUp.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        }
        String texteActuel = textes.get(texteActuelIndex); // Obtenir la valeur de texte correspondante dans la liste
        questionText.setText(texteActuel);// Mettre à jour le texte du TextField
    }

}
package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.example.model.Player;
import java.io.IOException;
import java.util.*;

@Setter
@Getter
public class SetUp {
    public static ArrayList<Player> players  = new ArrayList<Player>();

    public static boolean playWithAi = false;

    @FXML private Label numberOfPlayersLabel;
    @FXML private Label errorMessage;
    @FXML private Label name;
    @FXML private TextField nameField;
    @FXML private HBox plusMinus;
    @FXML private CheckBox aiCheckbox;

    private int minPlayers = 2;
    private int maxPlayers = 10;
    private boolean error = false;
    private int clics = 0;

    String[] prenoms = {"Alice", "Bob", "Charlie", "David", "Emma", "Frank", "Gina", "Hank", "Iris", "Jack"};
    Random random = new Random();
    public void initialize(){
        players.clear();
        playWithAi=false;
        nameField.setVisible(false);
        name.setVisible(false);

    }

    //confirm the name of each player / then start the game
    public void confirm(ActionEvent event)throws IOException {
        plusMinus.setVisible(false);
        aiCheckbox.setVisible(false);
        nameField.setVisible(true);
        name.setVisible(true);

        int numberOfPlayers = Integer.parseInt(numberOfPlayersLabel.getText());

        if (nameField.getText().isEmpty() && error) {
            errorMessage.setText("Please complete the field!");
        }else if(!nameField.getText().isEmpty()){
            clics++;
            errorMessage.setText("");


            if(clics<=numberOfPlayers && clics>0){
                String namePlayer = nameField.getText();
                Player player = new Player(namePlayer);
                addPlayer(player);
                nameField.clear();}
            if(aiCheckbox.isSelected() && playWithAi ==  false){
                playWithAi = true;
                name.setText("Player 1 name");
                clics = numberOfPlayers;
                int n = numberOfPlayers;
                Set<String> prenomsSelectionnes = new HashSet<>();
                while (players.size() < n) {
                    int index = random.nextInt(prenoms.length);
                    String prenom = prenoms[index];
                    if (prenomsSelectionnes.contains(prenom)) {
                        continue;
                    }
                    prenomsSelectionnes.add(prenom);
                    players.add(new Player(prenom+" (BOT)"));
                }
            }
                if (clics>=numberOfPlayers){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/cardGame.fxml"));
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.centerOnScreen(); // Loads the stage in the middle
                    stage.setResizable(false);
                    stage.show();
        }
        }
        name.setText("Player "+(clics+1)+" name");
        error = true;



    }

    // add a player with its name to the list of player;
    public void addPlayer(Player player) {
        players.add(player);
    }


    // remove a player when the button - is clicked
    public void decrementNumberOfPlayers() {
        int numPlayers = Integer.parseInt(numberOfPlayersLabel.getText());
        if (numPlayers > minPlayers) {
            errorMessage.setText("");
            numPlayers--;
            numberOfPlayersLabel.setText(Integer.toString(numPlayers));
        }else{
            errorMessage.setText("You must be at least 2 players to play this game");
        }
    }
    // add a player when the button + is clicked
    public void incrementNumberOfPlayers() {
        int numPlayers = Integer.parseInt(numberOfPlayersLabel.getText());
        if (numPlayers < maxPlayers) {
            errorMessage.setText("");
            numPlayers++;
            numberOfPlayersLabel.setText(Integer.toString(numPlayers));
        }else{
            errorMessage.setText("You have reached the maximum number of players");
        }
    }

}

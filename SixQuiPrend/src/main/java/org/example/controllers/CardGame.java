package org.example.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import lombok.Setter;
import org.example.model.Card;
import org.example.model.Pile;
import org.example.model.Player;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.example.controllers.SetUp.players;
import static org.example.controllers.SetUp.playWithAi;
@Setter
public class CardGame {
    @FXML private HBox pile1;
    @FXML private HBox pile2;
    @FXML private HBox pile3;
    @FXML private HBox pile4;
    @FXML private Button menu;
    @FXML private HBox playerCard;
    @FXML private Label scoreBoard;
    @FXML private Label roundLabel;
    @FXML private Label playerLabel;
    @FXML private MediaView mediaView;

    private ArrayList<Pile> pile;
    //private Label labelCard;
    private Map<Integer, Card> tempCard = new TreeMap<>();
    int nRound = 1;
    Image image = new Image(String.valueOf(getClass().getResource("../images/beef.png"))); // Replace "card_image.png" with the filename of your image
    ImageView imageView = new ImageView(image);


    //create a list of pile
    public CardGame() {
        pile = new ArrayList<Pile>();
    }

    public void initialize() {
        readAudio("shuffle");
        refreshScore();
        menu.setVisible(false);
        addPile();
        addRectangle(0);
        hideCard(0);
        roundLabel.setText("ROUND 1");
        playerLabel.setText("It's your turn "+players.get(0).getName().toUpperCase());
    }


    /**
     * refresh the Card of the four pile
     * @param playerN
     */
    public void refreshCard(int playerN){
        pile1.getChildren().clear();
        pile2.getChildren().clear();
        pile3.getChildren().clear();
        pile4.getChildren().clear();
        for (int i = 0; i < 4; i++) {
            HBox currentPile = null;
            switch (i) {
                case 0:
                    currentPile = pile1;
                    break;
                case 1:
                    currentPile = pile2;
                    break;
                case 2:
                    currentPile = pile3;
                    break;
                case 3:
                    currentPile = pile4;
                    break;
            }
            for (int y = 0; y < pile.get(i).getCards().size(); y++) {
                Rectangle rectangle = new Rectangle(50, 90);
                rectangle.setArcHeight(5.0);
                rectangle.setArcWidth(5.0);
                rectangle.setFill(Color.web("#d0d1c5"));
                rectangle.setStroke(Color.BLACK);
                rectangle.setStrokeType(StrokeType.OUTSIDE);
                Label label = new Label(String.valueOf(pile.get(i).getCard(y)));
                Label heads = new Label(String.valueOf(pile.get(i).getCard(y).getHeads())+ " bulls");
                label.setStyle("-fx-font-size: 30px;");
                heads.setStyle("-fx-font-size: 15px; -fx-translate-y: 20px;");
                heads.setTextFill(Color.RED);
                label.setTextFill(Color.WHITE);
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(50);
                imageView.setFitHeight(90);
                StackPane stackPane = new StackPane(rectangle,imageView, label,heads);
                currentPile.getChildren().add(stackPane);
            }
        }
    }

    /**
     * give or/and refresh each cards of a player
     * @param playerN
     */
    public void addRectangle(int playerN) {
        refreshCard(playerN);
        playerCard.getChildren().clear();
        for (int i = 0; i < players.get(playerN).getCards().size(); i++) {
            Rectangle rectangle = new Rectangle(50, 90);
            rectangle.setId("rectangle"+i);
            rectangle.getId();
            rectangle.setArcHeight(5.0);
            rectangle.setArcWidth(5.0);
            rectangle.setFill(Color.web("#d0d1c5"));
            rectangle.setStroke(Color.BLACK);
            rectangle.setStrokeType(StrokeType.OUTSIDE);
            Label labelCard = new Label(String.valueOf(players.get(playerN).getCard(i)));
            Label heads = new Label(String.valueOf(players.get(playerN).getCard(i).getHeads())+ " bulls");
            labelCard.setStyle("-fx-font-size: 30px;");
            heads.setStyle("-fx-font-size: 15px; -fx-translate-y: 20px;");
            heads.setTextFill(Color.RED);
            labelCard.setTextFill(Color.WHITE);
            StackPane stackPane = new StackPane(rectangle, labelCard,heads);
            playerCard.getChildren().add(stackPane);

            // Ajouter des EventHandlers pour les événements de la souris
            stackPane.setOnMouseEntered(e -> {
                stackPane.setTranslateY(-10);
                stackPane.setEffect(new DropShadow());
            });
            stackPane.setOnMouseExited(e -> {
                stackPane.setTranslateY(0);
                stackPane.setEffect(null);
            });
            final int indexCard = i;

            if(!playWithAi || playerN == 0){
                stackPane.setOnMouseClicked(event -> chooseCard(indexCard, playerN));

            }
        }     if (playWithAi && playerN != 0) {
                final int randomIndex = (int) (Math.random() * players.get(playerN).getCards().size());
                chooseCard(randomIndex,playerN);
        }

    }


    /**
     * add four Pile and create each card  of the game with their value and heads
     */
    public void addPile() {
        ArrayList<Card> cards = new ArrayList<Card>();
        pile.clear();
        cards.clear();
        // Créer les tas
        for (int i = 0; i < 4; i++) {
            pile.add(new Pile());
        }
        // Créer les cartes
        for (int i = 1; i <= 104; i++) {
            int value = i;
            int heads = 0;
            if (i % 10 == 5) {
                heads = i == 55 ? 7 : 2;
            } else if (i % 10 == 0) {
                heads = 3;
            } else if (i == 11 || i == 22 || i == 33 || i == 44 || i == 55 || i == 66 || i == 77 || i == 88 || i == 99) {
                heads = 5;
            } else {
                heads = 1;
            }
            cards.add(new Card(value, heads));
        }
        Collections.shuffle(cards);

        // give the card to the player
        int index = 0;
        for (Player player : players) {
            for (int i = 0; i < 10; i++) {
                player.addCard(cards.get(index));
                index++;
            }
        }
        // add one card for each pile
        for (int i = 0; i < 4; i++) {
            pile.get(i).addCard(cards.get(index));
            index++;
        }
    }


    /**
     * for each card played,compare the value and sort them in ascending order
     * @param indexCard
     * @param playerN
     */
    public void chooseCard(int indexCard, int playerN) {
        Card card = players.get(playerN).getCard(indexCard);
        tempCard.put(playerN, card);
        players.get(playerN).removeCard(indexCard);
        if (playerN < players.size() - 1) {
            playerN++;
        } else {
            List<Card> sortedCards = new ArrayList<>(tempCard.values());
            Collections.sort(sortedCards, new Comparator<Card>() {
                @Override
                public int compare(Card c1, Card c2) {
                    return Integer.compare(c1.getValue(), c2.getValue());
                }
            });
            for (int i = 0; i < players.size(); i++) {
                play(sortedCards.get(i),getKeyByValue(tempCard,sortedCards.get(i)));
            }
            playerN = 0;
            tempCard.clear();
        }
        hideCard(playerN);
    }

    public <K, V> K getKeyByValue(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }


    //play the card
    public void play(Card card, int playerN) {
        int indexPile = findPile(card.getValue(),playerN);
        pile.get(indexPile).addCard(card);
        refreshCard(playerN);
    }


    /**
     *find the pile associated to a card / replace a pile by the card if its value is too low
     * @param value check if the value of the card played is under the value of the last card of each pile
     * @param playerN to remove the card from the player who played it
     * @return the index of the pile where the card goes
     */
    private int findPile(int value,int playerN) {
        int indexPile = -1;
        int differenceMin = Integer.MAX_VALUE;
        for (int i = 0; i < pile.size(); i++) {
            int pileValue = pile.get(i).getCard(pile.get(i).getCardsNumber() - 1).getValue();
            int difference = value - pileValue;
            if (difference > 0 && difference < differenceMin) {
                indexPile = i;
                differenceMin = difference;
            }
        }
        if (indexPile < 0) {
            // Choose pile with dialog box
            if (playerN==0||!playWithAi) {
                hideCard(playerN);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Choose a pile, " + players.get(playerN).getName().toUpperCase());
                alert.setHeaderText("No available pile for card value " + value);
                alert.setContentText("Please choose a pile to add the card to:");
                ToggleGroup toggleGroup = new ToggleGroup();
                RadioButton pile1RadioButton = new RadioButton("Pile 1");
                pile1RadioButton.setToggleGroup(toggleGroup);
                RadioButton pile2RadioButton = new RadioButton("Pile 2");
                pile2RadioButton.setToggleGroup(toggleGroup);
                RadioButton pile3RadioButton = new RadioButton("Pile 3");
                pile3RadioButton.setToggleGroup(toggleGroup);
                RadioButton pile4RadioButton = new RadioButton("Pile 4");
                pile4RadioButton.setToggleGroup(toggleGroup);

                VBox vbox = new VBox(10);
                vbox.getChildren().addAll(pile1RadioButton, pile2RadioButton, pile3RadioButton, pile4RadioButton);
                alert.getDialogPane().setContent(vbox);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    if (pile1RadioButton.isSelected()) {
                        indexPile = 0;
                    } else if (pile2RadioButton.isSelected()) {
                        indexPile = 1;
                    } else if (pile3RadioButton.isSelected()) {
                        indexPile = 2;
                    } else if (pile4RadioButton.isSelected()) {
                        indexPile = 3;
                    } else {
                        indexPile = 0;
                    }
                } else {
                    indexPile = 0;
                }
            }else{
                //AI
                int maxHeads = pile.get(0).totalHeads();
                for (int i = 1; i < pile.size()-1; i++) {
                    int currentHeads = pile.get(i).totalHeads();
                    if (currentHeads < maxHeads) {
                        maxHeads = currentHeads;
                        System.out.println(pile.get(i).totalHeads());
                        indexPile = i;
                    }else{indexPile = 0;}
                }
            }
            int addPoint= addPoint(indexPile);
            players.get(playerN).score(addPoint);
            refreshScore();
            pile.get(indexPile).getCards().clear();
        }
        if (pile.get(indexPile).getCardsNumber() >= 5) {
            int addPoint= addPoint(indexPile);
            players.get(playerN).score(addPoint);
            refreshScore();
            pile.get(indexPile).getCards().clear();
        }
        return indexPile;
    }

    /**
     * add the total number of bull heads of a pile to addPoint
     * @param indexPile the index of the pile where we are adding the bull heads of each card
     * @return the number of bull heads
     */
    public int addPoint(int indexPile){
        int addPoint = 0;
        for (int i = 0; i < pile.get(indexPile).getCards().size(); i++){
            addPoint += pile.get(indexPile).getCards().get(i).getHeads();
        }
        return addPoint;
    }

    /**
     * display the last score of each player
     */
    public void refreshScore(){
        String scoreString="";
        for(int i=0;i<players.size();i++) {

            scoreString += players.get(i).getName().toUpperCase()+ " : " + players.get(i).getScore()+" beef heads\n";
        }
        scoreBoard.setText(scoreString);
    }


    /**
     * hide the card of a player + check if the game is finished (if not give new cards for each player and start a new round)
     * @param playerN hide the card of the playerN
     */
    private void hideCard(int playerN) {
        int checkEnd = 0;
        if (players.get(playerN).getCards().size()==0){
            for(int i=0;i<players.size();i++) {
                if(players.get(i).getScore()>=66){
                    checkEnd++;
                }
            }if (checkEnd>0){
                int minScore = Integer.MAX_VALUE;
                Player winner = null;
                for (Player p : players) {
                    int score = p.getScore();
                    if (score < minScore) {
                        minScore = score;
                        winner = p;
                    }
                }
                roundLabel.setText("GAME OVER");
                menu.setVisible(true);
                playerLabel.setText("WINNER: "+winner.getName().toUpperCase());
            }else{
                nRound++;
                roundLabel.setText("ROUND "+nRound);
                addPile();
            }
        }
     if (checkEnd==0){
         playerLabel.setText("It's your turn "+players.get(playerN).getName().toUpperCase());
     }
     if(playerN!=0 && playWithAi) {
         addRectangle(playerN);
     }else{
        for (Node node : playerCard.getChildren()) {
            if (node instanceof StackPane) {
                StackPane stackPane = (StackPane) node;
                if (stackPane.getChildren().size() > 1) {
                    Rectangle rectangle = (Rectangle) ((StackPane) node).getChildren().get(0);
                    rectangle.setFill(Color.rgb(125,0,0));
                    Label label = (Label) stackPane.getChildren().get(1);
                    Label heads = (Label) stackPane.getChildren().get(2);// Récupérer le label
                    label.setVisible(false);// Cacher le label
                    heads.setVisible(false);
                    stackPane.setOnMouseClicked(event -> addRectangle(playerN)); // Ajouter un événement de clic pour révéler le label
                }
            }
            }
        }
    }

 //Go to the menu
    public void menu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/MainWindow.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

    }

    /**
     * read an audio file
     * @param audioName name of the mp3 file
     */
    public void readAudio(String audioName){
        String path = "src/main/resources/org/example/audio/"+audioName+".mp3"; // remplacer par le chemin du fichier audio à jouer
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
    }

}





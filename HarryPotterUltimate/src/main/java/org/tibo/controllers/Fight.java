package org.tibo.controllers;

import javafx.animation.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import org.tibo.character.AbstractEnemy;
import org.tibo.character.Boss;
import org.tibo.character.Enemy;
import org.tibo.object.Core;
import org.tibo.object.House;
import org.tibo.object.Potion;
import org.tibo.object.Wand;
import org.tibo.spell.AbstractSpell;
import org.tibo.spell.ForbiddenSpell;
import org.tibo.spell.Spell;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



import static org.tibo.Story.*;
import static org.tibo.controllers.SetUp.wizard;


public class Fight {

    @FXML private ChoiceBox<String> spellSelection;
    private List<String> enemy = new ArrayList<>();
    @FXML
    private ImageView enemyImage;

    @FXML private ImageView wizardImage;
    @FXML private  Label dialog;
    @FXML private  Button next;
    @FXML private  Button Menu;
    @FXML private  Button fight;
    @FXML private  Button lifeUpgrade;
    @FXML private  Button attackUpgrade;
    @FXML private  Button ok;
    @FXML private  Button potion;
    @FXML private  Label healthLabel;
    @FXML private  Label EnemyHealthLabel;
    @FXML private ProgressBar EnemyHealthBar;
    @FXML private ProgressBar healthBar;

    public static double successRate = 0;

    private static String[] enemies = {"Troll","Basilic","Dementors","Voldemort & Peter Portolion","Dolores Umbrage","The Death Eaters","Bellatrix Lestrange","Voldemort"};
    private int place = 0, act = 1;
    private int index = 0;
    private int clics = 0;

    @FXML private Rectangle lightning;
    @FXML private ImageView foudre;
    @FXML private ImageView foudre2;
    @FXML private ImageView fail;
    @FXML
    private MediaView mediaView;



    private void playAnimation(Node node, int duration) {
        FadeTransition ft = new FadeTransition(Duration.millis(duration), node);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.setCycleCount(1);
        ft.play();
    }



    public void initialize() {
        // Ajout des titres dans la liste
        enemy.add("../images/troll.png");
        enemy.add("../images/Basilic.png");
        enemy.add("../images/dementors.png");
        enemy.add("../images/Petter.png");
        enemy.add("../images/Dolores.png");
        enemy.add("../images/DeathEaters.png");
        enemy.add("../images/Bellatrix.png");
        enemy.add("../images/Voldemort.png");
        enemy.add("../images/Harry.png");
        setImage(index);



        if (wizard.getHouse()== House.RAVENCLAW) {
            successRate = 0.9;
        }else{
            successRate = 0.82;
        }
        wizard.learnSpell(new Spell("Expelliarmus", 5,successRate));
        dialog.setText(print1FirstActIntro());
        Menu.setVisible(false);
        visibleFightButtons(false,false,false);
        potion.setText("Potion ("+wizard.getPotions().size()+")");

    }


    public void visibleFightButtons(boolean Fightvisibility,boolean spellVisibility,boolean upgradeVisibility) {
        fight.setVisible(Fightvisibility);
        potion.setVisible(Fightvisibility);
        spellSelection.setVisible(spellVisibility);
        ok.setVisible(spellVisibility);
        lifeUpgrade.setVisible(upgradeVisibility);
        attackUpgrade.setVisible((upgradeVisibility));
    }



    public void next(ActionEvent event)throws IOException {
        clics++;
        if(clics == 1) {
            continueJourney();
        } else if(clics == 2) {
            continueJourney();
            dialog.setText(print1SecondActIntro());
        }else if(clics == 3) {
            continueJourney();
            dialog.setText(print1ThirdActIntro());
        }else if(clics == 4) {
            continueJourney();
            dialog.setText(print1FourthActIntro());
        }else if(clics == 5) {
            continueJourney();
            dialog.setText(print1FiveActIntro());
        }else if(clics == 6) {
            continueJourney();
            dialog.setText(print1SixActIntro());
        }else if(clics == 7) {
            continueJourney();
            dialog.setText(print1SevenActIntro());
        }else if(clics == 8) {
            continueJourney();
            dialog.setText(print1SevenActOutro());
        }else if(clics == 9) {
            dialog.setText(printEnd());
            next.setText("Receive my diploma");
        }else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/tibo/end.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen(); // Loads the stage in the middle
            stage.show();
        }
    }
    public void continueJourney(){
        switchEnemy();
        next.setVisible(false);
        visibleFightButtons(true,false,false);
        //changes acts
        if(act == 1) {
            //increment act and place
            act = 2;
            place = 1;
            battle(new Enemy(enemies[0],10,1));
        } else if (act == 2) {
            //let the player "level up"
            wizard.learnSpell(new Spell("Accio", 10, successRate-0.1));
            wizard.addPotion(new Potion(5));
            //story
            //increment act and place
            act = 3;
            place = 2;
            battle(new Enemy(enemies[1],15,1));
            //story
        } else if (act == 3){
            //let the player "level up"
            wizard.addPotion(new Potion(5));
            wizard.learnSpell(new Spell("Expecto Patronum", 15, successRate-0.15));
            //increment act and place
            act = 4;
            place = 3;
            battle(new Enemy(enemies[2],20,2));
            //let the player "level up"
            potion.setText("Potion ("+wizard.getPotions().size()+")");
        } else if (act == 4) {
            //increment act and place
            act = 5;
            place = 4;
            battle(new Enemy(enemies[3],25,2));
        }else if (act == 5) {
            //let the player "level up"
            wizard.learnSpell(new Spell("Sectumsempra", 25,  successRate-0.25));
            wizard.addPotion(new Potion(5));
            //increment act and place
            act = 6;
            place = 5;
            battle(new Enemy(enemies[4],30,3));
            //let the player "level up"
            potion.setText("Potion ("+wizard.getPotions().size()+")");
            //story
        }else if (act == 6) {
            //increment act and place
            act = 7;
            place = 6;
            battle(new Enemy(enemies[5],35,3));
            //let the player "level up"
            potion.setText("Potion ("+wizard.getPotions().size()+")");
        }else if (act == 7) {
            //increment act and place
            act = 8;
            place = 7;
            readAudio("Belatrix");
            battle(new Enemy(enemies[6], 50, 4));
        }else{
            wizard.hp = wizard.maxHp;
            healthbar();
            //calling the final battle.
            finalBattle();
        }
    }

    public void battle(AbstractEnemy enemy){
            List<String> spellList = wizard.getKnownSpells();
            ObservableList<String> observableList = FXCollections.observableArrayList(spellList);
            spellSelection.setItems(observableList);

            double health = (double) enemy.hp / enemy.maxHp;
            EnemyHealthBar.setProgress(health);
            EnemyHealthLabel.setText("HP:" + String.valueOf(enemy.hp)+"/"+String.valueOf(enemy.maxHp));
            potion.setText("Potion ("+wizard.getPotions().size()+")");
            spellSelection.getSelectionModel().selectFirst();
            dialog.setText("choose an action \n (1) Fight\n(2) Use Potion");
            fight.setOnAction(actionEvent -> {
                visibleFightButtons(true,true,false);
                //FIGHT
                dialog.setText("Choose a Spell :"+wizard.KnownSpells());
            });
                ok.setOnAction(actionEvent -> {



                int dmg = 0;
                int selectedIndex = spellSelection.getSelectionModel().getSelectedIndex();
                AbstractSpell spellName = wizard.getSpells().get(selectedIndex);
                int dmgSpell = wizard.attackWithSpell(spellName.getName());
                if (dmgSpell != 0){
                    dmg = wizard.attack() - enemy.defend() + dmgSpell;
                }
                //calculate dmg and dmgTook
                int dmgTook = enemy.attack() - wizard.defend();
                //check that dmg isn't negative
                if (dmgTook < 0){
                    dmgTook = 0;
                }
                if(dmg <= 0) {
                    dmg = 0;
                    playAnimation(fail,500);
                    readAudio("fail");
                }else {
                    readAudio("attack");
                    playAnimation(foudre,1000);
                    playAnimation(foudre2,1000);
                    playAnimation(lightning,300);
                    TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.1), enemyImage);
                    translateTransition.setCycleCount(10);
                    translateTransition.setAutoReverse(true);
                    translateTransition.setByX(-5);
                    translateTransition.play();
                }
                //deal damage to both parties
                wizard.hp -= dmgTook;
                enemy.hp -= dmg;
                healthbar();

                double health2 = (double) enemy.hp / enemy.maxHp;
                EnemyHealthBar.setProgress(health2);
                EnemyHealthLabel.setText("HP:" + String.valueOf(enemy.hp)+"/"+String.valueOf(enemy.maxHp));
                //print the info of this battle round
                dialog.setText("You dealt " + dmg + " damage to the " + enemy.name + ".\nThe" + enemy.name + " dealt " + dmgTook + " damage to you.");
                visibleFightButtons(true,false,false);
                //check if player is still alive
                if(wizard.hp <=0){
                    wizardDied();
                } else if (enemy.hp <= 0 ){
                    dialog.setText("You defeated the " + enemy.name + "!\n\n(1) Attack upgrade\n(2) Life upgrade");
                    EnemyHealthLabel.setText("HP:" + String.valueOf(0)+"/"+String.valueOf(enemy.maxHp));
                    visibleFightButtons(false,false,false);
                    if (enemy.name=="VOLDEMORT"){
                        dialog.setText("You defeated the " + enemy.name );
                        next.setVisible(true);
                    }else {
                        chooseTrait();
                    }
                }
                });
            potion.setOnAction(actionEvent -> {
                //USE POTION
                if (wizard.getPotions().size() > 0 && wizard.hp < wizard.maxHp) {
                    //player CAN take a potion
                        if (wizard.getHouse() == House.HUFFLEPUFF) {
                            wizard.usePotion(new Potion(60));
                        } else {
                            wizard.usePotion(new Potion(50));
                        }
                    wizardColorFilter(-0.90);
                    readAudio("potion");
                    dialog.setText("You drank a magic potion. You have now HP: " + wizard.hp);
                        healthbar();
                    potion.setText("Potion ("+wizard.getPotions().size()+")");
                    }else if(wizard.getPotions().size() <= 0){
                    dialog.setText("You don't have any potion.");
                }else{
                    dialog.setText("You're already at full health.");
                }
            });
    }

    private void healthbar() {
        double health = (double) wizard.hp / wizard.maxHp;
        healthBar.setProgress(health);
        healthLabel.setText("HP:" + String.valueOf(wizard.hp)+"/"+String.valueOf(wizard.maxHp));
    }


    public void finalBattle(){
        readAudio("Voldemort");
        wizard.learnForbiddenSpell(new ForbiddenSpell("Forbidden Spell: Avada Kedavra", 1000,0.5,10));
        wizard.addPotion(new Potion(5));
        potion.setText("Potion ("+wizard.getPotions().size()+")");
        //Story.printSevenActIntro();
        Wand wandBoss = new Wand( Core.WOOD, 22);

        if (wizard.wand.getCore() == wandBoss.getCore()){
            dialog.setText(print1SpecialEvent());
            wizard.numAtkUpgrades += 3;
        }
            battle(new Boss("VOLDEMORT", 100, 5, wandBoss));
        }


    public void wizardDied(){
        readAudio("gameOver");
        dialog.setText("You died...\n\n\nGAME OVER");
        Menu.setVisible(true);
        visibleFightButtons(false,false,false);
        wizardImage.setImage(null);
    }

    public void switchEnemy()  {
        index = (index + 1) % enemy.size();
        setImage(index-1);
    }

    private void setImage(int index) {
        // Chargement de l'image à partir de l'URL
        Image image = new Image(getClass().getResource(enemy.get(index)).toString());

        // Mise à jour de l'image dans l'ImageView
        enemyImage.setImage(image);
    }
    public void chooseTrait() {
        visibleFightButtons(false,false,true);

        lifeUpgrade.setOnAction(actionEvent -> {
            wizard.addHealthPoint();
            dialog.setText("You chose Life upgrade!");
            visibleFightButtons(false,false,false);
            healthbar();
            next.setVisible(true);
            wizardColorFilter(0.4);
            readAudio("levelUp");
        });
        attackUpgrade.setOnAction(actionEvent -> {
            wizard.numAtkUpgrades++;
            dialog.setText("You chose Attack upgrade!");
            visibleFightButtons(false,false,false);
            next.setVisible(true);
            wizardColorFilter(-0.5);
            readAudio("levelUp");
        });
    }

    public void menu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/tibo/MainWindow.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen(); // Loads the stage in the middle
        stage.show();
    }


    public void wizardColorFilter(double color){
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setHue(color);
        wizardImage.setEffect(colorAdjust);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> {
                    wizardImage.setEffect(null);
                })
        );
        timeline.play();
    }

    public void readAudio(String audioName){
        String path = "/Users/thibaudbarberon/Desktop/JavaISEP2023/HarryPotterUltimate/src/main/resources/org/tibo/audio/"+audioName+".mp3"; // remplacer par le chemin du fichier audio à jouer
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
    }


}

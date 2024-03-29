package org.example;
import org.example.character.*;
import org.example.object.*;
import org.example.spell.AbstractSpell;
import org.example.spell.ForbiddenSpell;
import org.example.spell.Spell;
import java.util.Scanner;

public class GameLogic {
    static Scanner scanner = new Scanner(System.in);
    static Wizard wizard;
    public static boolean isRunning;
    //enemey names
    public static String[] enemies = {"Troll","Basilic","Dementors","Voldemort & Peter Portolion","Dolores Umbrage","The Death Eaters","Voldemort and Bellatrix Lestrange"};
    //Story elements
    public static int place = 0, act = 1;
    public static String[] places = {"Bathroom Dungeon","The Chamber of Secrets","Lake in the Forbidden Forest","Little Hangleton cemetery","Hogwarts examination room","Astronomy tower","Hogwart"};
    public static double successRate = 0;
    public static int readInt(String prompt, int userChoices) {
        int input;
        do {
            System.out.println(prompt);
            try {
                input = Integer.parseInt(scanner.next());

            }catch(Exception e) {
                input = -1;
                System.out.println("Please choose a number");
            }
        }while(input <1 || input > userChoices);
        return input;
    }

    //method to simulate clearing out the console
    public  static void clearConsole() {
        for(int i = 0; i<100; i++)
            System.out.println();
    }

    //method to print a seperator with length n
    public static void printSeperator(int n) {
        for(int i = 0; i < n;i++)
            System.out.print("-");
        System.out.println();
    }

    //method to print a heading
    public static void printHeading(String title) {
        printSeperator(title.length());
        System.out.println(title);
        printSeperator(title.length());
    }

    //method to stop the game until user enters anything
    public static void anythingToContinue() {
        System.out.println("\nPress Enter to continue...");
        try{
            System.in.read();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //method to start the game
    public static void startgame() {
        boolean nameSet = false;
        String name;
        //print title Screen
        clearConsole();
        printSeperator(40);
        printSeperator(30);
        System.out.println("HARRY POTTER");
        printSeperator(30);
        printSeperator(40);
        anythingToContinue();

        //getting the name player
        do {
            clearConsole();
            printHeading("What's your name?");
            name = scanner.next();
            clearConsole();
            printHeading("Your name is " + name + ".\nIs that correct?");
            System.out.println("(1) Yes!");
            System.out.println("(2) No, I want to change my name!");
            int input = readInt("->", 2);
            if (input == 1)
                nameSet = true;
        } while(!nameSet);

        SortingHat sortingHat = new SortingHat();
        House house = sortingHat.assignHouse();

        //create new player object with the name
        wizard = new Wizard(name, house);

        //print story intro
        Story.printIntro();

        // Demander à l'utilisateur de saisir un animal
        clearConsole();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, choose an animal:");
        printSeperator(20);
        for (Pet pet : Pet.values()) {
            System.out.println("("+ (int)(pet.ordinal()+1) + ") " + pet.toString());
        }
        int petTypeIndex= readInt("->",4);
        Pet petType = Pet.values()[petTypeIndex-1];

        // Afficher les informations de la baguette du sorcier
        clearConsole();
        printHeading("You chose the " + petType  );
        anythingToContinue();



        // Demander à l'utilisateur de saisir le type de core pour la baguette
        clearConsole();
        System.out.println("Please, choose a  core type for you wand:");
        printSeperator(20);
        for (Core core : Core.values()) {
            System.out.println("("+ (int)(core.ordinal()+1) + ") " + core.toString());
        }
        int coreTypeIndex = readInt("->",4);

        // Équiper le sorcier d'une baguette avec une longueur de 12 pouces et un core personnalisé
        Core coreType = Core.values()[coreTypeIndex-1];

        Wand wand = new Wand( coreType, 12);
        wizard.equip(wand);


        if (wizard.getHouse()== House.RAVENCLAW) {
            successRate = 0.9;
        }else{
            successRate = 0.82;
        }

        wizard.learnSpell(new Spell("Expelliarmus", 5,successRate));

        // Afficher les informations de la baguette du sorcier
        clearConsole();
        printHeading("The wizard " + name + "'s wand has a core in " + wand.getCore() + " and a length of " + wand.getLength() + " inches.");
        anythingToContinue();

        // Appel au Choipeau Magique pour assigner une maison
        clearConsole();
        printHeading(name + " was sorted into " + wizard.getHouse().name() + " House by the Sorting Hat");
        System.out.println("You are now ready to begin your first year in Hogwart!");
        anythingToContinue();
        //setting is running to true, so the game loop can continue

        isRunning = true;
        //start main game loop
        gameLoop();
    }
    //method that changes the game's value base on player xp




    //printing out the most important information about the player character
    public static void characterInfo(){
        clearConsole();

        printHeading("CHARACTER INFO");
        System.out.println(wizard.getHouse());
        printSeperator(20);
        System.out.println(wizard.name+"\tHP:"+wizard.hp+"/"+wizard.maxHp);
        printSeperator(20);
        // # of pots
        System.out.println("# of Potions: " + wizard.getPotions().size());
        printSeperator(20);
        //printing the chosen traits
        if(wizard.numAtkUpgrades > 0){
            System.out.println("Offensive upgrade: " + wizard.numAtkUpgrades);
            printSeperator(20);
        }
        anythingToContinue();
    }





    //the main BATTLE method
    public static void battle(AbstractEnemy enemy){
        //main battle loop
        while(true){
            clearConsole();
            printHeading(enemy.name + "\nHP: " + enemy.hp + "/" +enemy.maxHp);
            printHeading(wizard.name + "\nHP: " + wizard.hp + "/" + wizard.maxHp);
            System.out.println("Choose an action");
            printSeperator(20);
            System.out.println("(1) Fight\n(2) Use Potion");
            int input = readInt("->",2);
            //react accordingly to player input
            if(input == 1){
                //FIGHT
                clearConsole();
                System.out.println("Choose a Spell");
                printSeperator(20);
                int dmg = 0;
                int inputSpell = readInt("->", wizard.KnownSpells());
                AbstractSpell spellName = wizard.getSpells().get(inputSpell-1);
                int dmgSpell = wizard.attackWithSpell(spellName.getName());
                if (dmgSpell != 0){
                    dmg = wizard.attack() - enemy.defend() + dmgSpell;
                }
                anythingToContinue();
                //calculate dmg and dmgTook
                int dmgTook = enemy.attack() - wizard.defend();
                //check that dmg isn't negative
                if (dmgTook < 0){
                    dmgTook = 0;
                }
                if(dmg < 0)
                    dmg = 0;
                //deal damage to both parties
                wizard.hp -= dmgTook;
                enemy.hp -= dmg;
                //print the info of this battle round
                clearConsole();
                printHeading("BATTLE");
                System.out.println("You dealt " + dmg + " damage to the " + enemy.name + ".");
                printSeperator(15);
                System.out.println("The" + enemy.name + " dealt " + dmgTook + " damage to you.");
                anythingToContinue();
                //check if player is still alive
                if(wizard.hp <=0){
                    wizardDied();
                    break;
                } else if (enemy.hp <= 0 ){
                    clearConsole();
                    printHeading("You defeated the " + enemy.name + "!");
                    anythingToContinue();
                    wizard.chooseTrait();
                    break;
                }
            }else if(input == 2){
                //USE POTION
                clearConsole();
                if (wizard.getPotions().size() > 0 && wizard.hp < wizard.maxHp) {
                    //player CAN take a potion
                    //make sure player wants to drink the potion
                    printHeading("do you want to drink a potion?("+wizard.getPotions().size() + " left).");
                    System.out.println("(1) Yes \n(2) No");
                    input = readInt("->", 2);
                    if (input == 1) {
                        //player actually took it
                        if (wizard.getHouse() == House.HUFFLEPUFF){
                            wizard.usePotion(new Potion(60));
                        }else {
                            wizard.usePotion(new Potion(50));
                        }
                        clearConsole();
                        printHeading("You drank a magic potion. You have now HP: " + wizard.hp);
                        anythingToContinue();
                    }
                }else if(wizard.getPotions().size() <= 0){
                    //player CANNOT take a potion
                    printHeading("You don't have any potion.");
                    anythingToContinue();
                }else{
                    //player CANNOT take a potion
                    printHeading("You're already at full health.");
                    anythingToContinue();
                }
            }
        }
    }



    public static void printMenu() {
        clearConsole();
        printHeading(places[place]);
        System.out.println("Choose an action:");
        printSeperator(20);
        System.out.println("(1)Continue on your journey");
        System.out.println("(2)Character Info");
        System.out.println("(3)Exit Game");
    }

    //method to continue the jouney (more next part)
    public static void continueJourney(){
        //changes acts
        if(act == 1){
            //increment act and place
            act = 2;
            place = 1;
            Story.printFirstActIntro();
            battle(new Enemy(enemies[0],10,1));
            if (isRunning) {
                Story.printFirstActOutro();
            }
        } else if (act == 2) {
            //let the player "level up"
            wizard.learnSpell(new Spell("Accio", 10, successRate-0.1));
            //story
            //increment act and place
            act = 3;
            place = 2;
            Story.printSecondActIntro();
            battle(new Enemy(enemies[1],15,1));
            //story
            if (isRunning) {
                Story.printSecondActOutro();;
            }
        } else if (act == 3){
            //let the player "level up"
            wizard.addPotion(new Potion(5));
            wizard.learnSpell(new Spell("Expecto Patronum", 15, successRate-0.15));
            //increment act and place
            act = 4;
            place = 3;
            Story.printThirdActIntro();
            battle(new Enemy(enemies[2],20,2));
            //let the player "level up"
            wizard.addPotion(new Potion(5));
            //story
            if (isRunning) {
                Story.printThirdActOutro();
            }
        } else if (act == 4) {
            //increment act and place
            act = 5;
            place = 4;
            Story.printFourthActIntro();
            battle(new Enemy(enemies[3],25,2));
            //story
            if (isRunning) {
                Story.printFourthActOutro();
            }
        }else if (act == 5) {
            //let the player "level up"
            wizard.learnSpell(new Spell("Sectumsempra", 25,  successRate-0.25));
            wizard.addPotion(new Potion(5));
            //increment act and place
            act = 6;
            place = 5;
            Story.printFiveActIntro();
            battle(new Enemy(enemies[4],30,3));
            //let the player "level up"
            wizard.addPotion(new Potion(5));
            //story
            if (isRunning) {
                Story.printFiveActOutro();
            }
        }else if (act == 6) {
            //increment act and place
            act = 7;
            place = 6;
            Story.printSixActIntro();
            battle(new Enemy(enemies[5],35,3));
            //let the player "level up"
            //story
            if (isRunning) {
                Story.printSixActOutro();
                //fully heal the player
                wizard.hp = wizard.maxHp;
                //calling the final battle.
                finalBattle();
            }
        }
    }

    //method when player is dead
    public static void wizardDied(){
        clearConsole();
        printHeading("You died...");
        System.out.println("GAME OVER");
        isRunning=false;
    }

    //the final battle
    public static void finalBattle(){
        wizard.learnForbiddenSpell(new ForbiddenSpell("Forbidden Spell: Avada Kedavra", 1000,0.5,10));
        wizard.addPotion(new Potion(5));
        Story.printSevenActIntro();
        Wand wandBoss = new Wand( Core.WOOD, 22);

        if (wizard.wand.getCore() == wandBoss.getCore()){
            Story.printSpecialEvent();
            wizard.numAtkUpgrades += 10;
        }
        battle(new Boss("VOLDEMORT",100,5,wandBoss));
        if (isRunning) {
            Story.printSevenActOutro();
        }
        isRunning = false;
    }



    //main game loop
    public static void gameLoop(){
        while(isRunning){
            printMenu();
            int input = readInt("->",3);
            if(input == 1)
                continueJourney();
            else if (input == 2)
                characterInfo();
            else
                isRunning = false;
        }
    }


}



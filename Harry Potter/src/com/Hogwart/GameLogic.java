package com.Hogwart;
import java.util.Scanner;

public class GameLogic {
    static Scanner scanner = new Scanner(System.in);
    static Wizard wizard;
    public static boolean isRunning;

    int nombreDePotions = wizard.getPotions().size();



    //enemey names
    public static String[] enemies = {"Ogre","Ogre","Gobelin","Ggobelin","Stone",};
    //Story elements
    public static int place = 0, act = 1;
    public static String[] places = {"Bathroom","Mansion","Castle","throne"};

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



        //create new player object with the name
        wizard = new Wizard(name);

        //print story intro
        Story.printIntro();

        // Demander à l'utilisateur de saisir le type de core pour la baguette
        clearConsole();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, choose a  core type for you wand:");
        for (Core core : Core.values()) {
            System.out.println("("+ core.ordinal() + ") " + core.toString());
        }
        int coreTypeIndex = scanner.nextInt();

        // Équiper le sorcier d'une baguette avec une longueur de 11 pouces et un core personnalisé
        Core coreType = Core.values()[coreTypeIndex];

        Wand wand = new Wand( coreType, 11);
        wizard.equip(wand);


        // Afficher les informations de la baguette du sorcier
        clearConsole();
        printHeading("The wizard " + name + "'s wand has a core in " + wand.getCore() + " and a length of " + wand.getLength() + " inches.");
        anythingToContinue();

        // Appel au Choipeau Magique pour assigner une maison
        SortingHat sortingHat = new SortingHat();
        House assignedHouse = sortingHat.assignHouse(wizard);
        clearConsole();
        printHeading(name + " was sorted into " + assignedHouse.getName() + " House by the Sorting Hat");
        System.out.println("You are now ready to begin your first year in Hogwart!");
        anythingToContinue();
        //print first story act intro
        Story.printFirstActIntro();

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
        System.out.println(wizard.name+"\tHP:"+wizard.hp+"/"+wizard.maxHp);
        printSeperator(20);
        // # of pots
        wizard.addPotion(new Potion(30));
        System.out.println("# of Potions: " + wizard.getPotions().size());
        printSeperator(20);


        //printing the chosen traits
        if(wizard.numAtkUpgrades > 0){
            System.out.println("Offensive trait: " + wizard.atkUpgrades[wizard.numAtkUpgrades -1]);
            printSeperator(20);
        }
        if(wizard.numDefUpgrades > 0){
            System.out.println("Defensive trait: " + wizard.defUpgrades[wizard.numDefUpgrades -1]);

        }
        anythingToContinue();
    }





    //creating a random battle
    public static void randomBattle(){
        clearConsole();
        printHeading("Tou encountered an ennemy");
        anythingToContinue();
        //creating new ennemy with random name
        battle(new Enemy(enemies[(int)(Math.random()* enemies.length)],10));
    }

    //the main BATTLE method
    public static void battle(Enemy enemy){
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
                //calculate dmg and dmgTook
                int dmg = wizard.attack() - enemy.defend();
                int dmgTook = enemy.attack() - wizard.defend();
                //check that dmh isn't negative
                if (dmgTook < 0){
                    //add some dmg if player defends very well
                    dmg -=dmgTook/2;
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
                System.out.println("You dealt" + dmg + " damage to the " + enemy.name + ".");
                printSeperator(15);
                System.out.println("The" + enemy.name + "dealt " + dmgTook + "damage to you.");
                anythingToContinue();
                //check if player is still alive
                if(wizard.hp <=0){
                    wizardDied();
                    break;
                } else if (enemy.hp <= 0 ){
                    clearConsole();
                    printHeading("You defeated the " + enemy.name + "!");
                    anythingToContinue();
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
                        wizard.hp = wizard.maxHp;
                        clearConsole();
                        printHeading("You drank a magic potion. You have now HP: " + wizard.hp);
                        anythingToContinue();
                    }
                }else{
                    //player CANNOT take a potion
                    printHeading("You don't have any potion or you're at full health.");
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
        //changes acts based on xp
        if(act == 1){
            //increment act and place
            act = 2;
            place = 1;
            //story
            Story.printFirstActOutro();
            //let the player "level up"
            wizard.chooseTrait();
            //story
            Story.printSecondActIntro();
            //Assign new values to enemies
            enemies[0] = "Evil Mercenary";
            enemies[1] = "Goblin";
            enemies[2] = "Wolve";
            enemies[3] = "Henchman";
            enemies[4] = "Scary";

            //fully heal the player
            wizard.hp = wizard.maxHp;


        } else if (act == 2) {
            //increment act and place
            act = 3;
            place = 2;
            //story
            Story.printSecondActOutro();
            //let the player "level up"
            wizard.chooseTrait();
            //story
            Story.printThirdActIntro();

            enemies[0] = "Evil Mercenary";
            enemies[1] = "Goblin";
            enemies[2] = "Wolve";
            enemies[3] = "Henchman";
            enemies[4] = "Scary";


        } else if (act == 3){
            //increment act and place
            act = 4;
            place = 3;
            //story
            Story.printThirdActOutro();
            //let the player "level up"
            wizard.chooseTrait();
            //story
            Story.printFourthActIntro();
            //fully heal the player
            wizard.hp = wizard.maxHp;
            //calling the final battle.
            finalBattle();
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
        battle(new Enemy("VOLDEMORT",300));
        //Story.printEnd(wizard);
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



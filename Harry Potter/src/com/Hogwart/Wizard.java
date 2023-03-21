package com.Hogwart;

public class Wizard extends Character{

    public int numAtkUpgrades, numDefUpgrades;
    public Wand wand;
    //additional player stats
    int gold, restsLeft, pots;

    public String[] atkUpgrades = {"strength","Power","Might","Godlike Strength"};
    public String[] defUpgrades = {"Heavy Bones","PowerB","MightB","Godlike StrengthB"};
    public Wizard(String name, Wand wand){

        super(name, 100, 0);
        this.wand = null;
        this.numAtkUpgrades = 0;
        this.numDefUpgrades = 0;
        //set additional stats
        this.gold = 5;
        this.restsLeft = 1;
        this.pots = 0;
        //let the player choose a trait when creating a new character
        chooseTrait();
        wand.chooseWand();
    }



    @Override
    public int attack(){
        return (int) (Math.random()*(xp/4 + numAtkUpgrades*3 + 3) + xp/10 + numAtkUpgrades*2 + numDefUpgrades + 1);
    }

    @Override
    public int defend(){
        return (int) (Math.random()*(xp/4 + numDefUpgrades*3 + 3) + xp/10 + numDefUpgrades*2 + numAtkUpgrades + 1);
    }

    //let the player choose a trait
    public void chooseTrait() {
        GameLogic.clearConsole();
        GameLogic.printHeading("Choose a trait:");
        System.out.println("(1)"+atkUpgrades[numAtkUpgrades]);
        System.out.println("(2)"+defUpgrades[numDefUpgrades]);
        //get the player choice:
        int input = GameLogic.readInt("->",2);
        GameLogic.clearConsole();
        if(input ==1){
            GameLogic.printHeading("You chose"+atkUpgrades[numAtkUpgrades] + "!");
            numAtkUpgrades++;
        }else{
            GameLogic.printHeading("You chose"+defUpgrades[numDefUpgrades]+ "!");
            numDefUpgrades++;
        }
        GameLogic.anythingToContinue();
    }

}

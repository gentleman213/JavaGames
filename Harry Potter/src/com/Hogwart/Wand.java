package com.Hogwart;

public class Wand {
    private String wandName;


    public Wand(String wandName){
        this.wandName = wandName;

    }

    public String getWandName(){
        return this.wandName;
    }

    public void chooseWand() {
        GameLogic.clearConsole();
        GameLogic.printHeading("Choose a wand:");
        System.out.println("(1) Attack Wand");
        System.out.println("(2) Defend Wand");
        //get the player choice:
        int input = GameLogic.readInt("->",2);
        GameLogic.clearConsole();
        if(input ==1){
            GameLogic.printHeading("You chose Bla!");
        }else{
            GameLogic.printHeading("You chose"+ "Boulou!");
        }
        GameLogic.anythingToContinue();

        }



}

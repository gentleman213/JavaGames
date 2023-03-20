package com.Hogwart;

public class Story {

    public static void printIntro(){
        GameLogic.clearConsole();
        GameLogic.printSeperator(30);
        System.out.println("WELCOMING");
        GameLogic.printSeperator(30);
        System.out.println("Welcome to Harry Potter at Home, the RPG for wizards.");
        System.out.println("Do you have what it takes to become one of the greatest wizards? Will you be able to overcome all the challenges? Let's go!");
        System.out.println("Your goal is to complete your studies at Hogwarts. Each year will be filled with obstacles..");
        GameLogic.anythingToContinue();
    }

    public static void printFirstActIntro() {
        GameLogic.clearConsole();
        GameLogic.printSeperator(30);
        System.out.println("THE PHILOSOPHER'S STONE");
        GameLogic.printSeperator(30);
        System.out.println("You are walking to the dungeon .");
        System.out.println("On your way you see an evil troll.");
        System.out.println("You must confront the evil troll that is in the bathroom next to the dungeon.");
        GameLogic.anythingToContinue();


    }


    public static void printFirstActOutro() {
        GameLogic.clearConsole();
        GameLogic.printSeperator(30);
        System.out.println("THE PHILOSOPHER'S STONE");
        GameLogic.printSeperator(30);
        System.out.println("You have defeated the evil troll");
        System.out.println("Congratulation you can now enter the second year");
        GameLogic.anythingToContinue();


    }


    public static void printSecondActIntro() {
        GameLogic.clearConsole();
        GameLogic.printSeperator(30);
        System.out.println("THE CHAMBER OF SECRETS");
        GameLogic.printSeperator(30);
        System.out.println("You are facing the terrible basilisk. ");
        System.out.println("If you are from Gryffindor, you can call upon the legendary sword of Godric Gryffindor to defeat it. ");
        System.out.println("Otherwise, you will need to extract one of its fangs and use it to destroy Tom Riddle's diary.");
        GameLogic.anythingToContinue();


    }


    public static void printSecondActOutro() {
        GameLogic.clearConsole();
        GameLogic.printSeperator(30);
        System.out.println("THE CHAMBER OF SECRETS");
        GameLogic.printSeperator(30);
        System.out.println("You have defeated the terrible basilisk");
        System.out.println("Congratulation you can now enter the third year");
        GameLogic.anythingToContinue();


    }

    public static void printThirdActIntro() {
        GameLogic.clearConsole();
        GameLogic.printSeperator(30);
        System.out.println("THE PRISONNER OF AZKABAN");
        GameLogic.printSeperator(30);
        System.out.println("The Dementors are on the loose. They are wandering through the streets and countryside. ");
        System.out.println("Fortunately, you have heard of a spell to repel them... Expect... Expecta... Ah yes, Expecto Patronum.");
        System.out.println("Your objective is to learn the spell and then use it to defeat the Dementors.");
        GameLogic.anythingToContinue();


    }


    public static void printThirdActOutro() {
        GameLogic.clearConsole();
        GameLogic.printSeperator(30);
        System.out.println("THE PRISONNER OF AZKABAN");
        GameLogic.printSeperator(30);
        System.out.println("You have defeated the Dementors");
        System.out.println("Congratulation you can now enter the fourth year");
        GameLogic.anythingToContinue();


    }


    public static void printFourthActIntro() {
        GameLogic.clearConsole();
        GameLogic.printSeperator(30);
        System.out.println("THE GOBLET OF FIRE");
        GameLogic.printSeperator(30);
        System.out.println("Unfortunately, you have won the Triwizard Tournament... and the right to die.");
        System.out.println("You are in a cemetery where Voldemort and Peter Pettigrew are also present. ");
        System.out.println("Your only chance of escape is to get closer to the Portkey and attract it to you.");
        GameLogic.anythingToContinue();


    }


    public static void printFourthActOutro() {
        GameLogic.clearConsole();
        GameLogic.printSeperator(30);
        System.out.println("THE GOBLET OF FIRE");
        GameLogic.printSeperator(30);
        System.out.println("You have managed to escape Voldemort ");
        System.out.println("Congratulation you can now enter the fifth year");
        GameLogic.anythingToContinue();
    }

    public static void printFiveActIntro() {
        GameLogic.clearConsole();
        GameLogic.printSeperator(30);
        System.out.println("THE ORDER OF THE PHOENIX");
        GameLogic.printSeperator(30);
        System.out.println("Time for the BUSE! Dolores Umbridge is keeping a close eye. ");
        System.out.println("Your goal is to distract her while the fireworks are being prepared.");
        GameLogic.anythingToContinue();


    }
    public static void printFiveActOutro() {
        GameLogic.clearConsole();
        GameLogic.printSeperator(30);
        System.out.println("THE ORDER OF THE PHOENIX");
        GameLogic.printSeperator(30);
        System.out.println("You have defeated the Dolores Umbridge");
        System.out.println("Congratulation you can now enter the seven and last year");
        GameLogic.anythingToContinue();


    }

    public static void printSixActIntro() {
        GameLogic.clearConsole();
        GameLogic.printSeperator(30);
        System.out.println("THE HALF-BLOOD PRINCE");
        GameLogic.printSeperator(30);
        System.out.println("The Death Eaters are attacking Hogwarts. Are you ready to defend yourself?  ");
        System.out.println("You must attack them head-on (with Sectumsempra). ");
        System.out.println("If you're from Slytherin, you can choose to join the ranks of the Death Eaters.");
        GameLogic.anythingToContinue();


    }
    public static void printSixActOutro() {
        GameLogic.clearConsole();
        GameLogic.printSeperator(30);
        System.out.println("THE HALF-BLOOD PRINCE");
        GameLogic.printSeperator(30);
        System.out.println("You have defeated the Death Eaters.");
        System.out.println("Congratulation you can now enter the seven and last year!");
        GameLogic.anythingToContinue();


    }
    public static void printSevenActIntro() {
        GameLogic.clearConsole();
        GameLogic.printSeperator(30);
        System.out.println("THE DEATHLY HALLOWS");
        GameLogic.printSeperator(30);
        System.out.println("No more beating around the bush, we need to attack the problem at its source.");
        System.out.println("You are facing Voldemort and Bellatrix Lestrange. ");
        System.out.println("Be careful, they can kill you instantly with Avada Kedavra if you're not prepared.");
        GameLogic.anythingToContinue();
    }

    public static void printSevenActOutro() {
        GameLogic.clearConsole();
        GameLogic.printSeperator(30);
        System.out.println("THE DEATHLY HALLOWS");
        GameLogic.printSeperator(30);
        System.out.println("You have defeated Voldemort and Bellatrix Lestrange.");;
        System.out.println("Congratulation, you have finished your studies in Hogwart!!!");
        GameLogic.anythingToContinue();
    }

    public static void printSpecialEvent() {
        GameLogic.clearConsole();
        GameLogic.printSeperator(30);
        System.out.println("Your wand has the same core than Voldemort's wand.");;
        System.out.println("Your spell are  now much more powerfull");
        GameLogic.anythingToContinue();

    }



}

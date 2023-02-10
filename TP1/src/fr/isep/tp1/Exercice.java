package fr.isep.tp1;

import java.util.Scanner;

public class Exercice {
    public static void main(String[] args) {
     //maFonction();
     //division();
    	volume();
}
    // Attention a bien recopier ‘public static void’
    public static void maFonction() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez saisir le premier entier");
    	  int premierEntier = scanner.nextInt();
          System.out.println("Veuillez saisir le deuxieme entier");
          int deuxiemeEntier = scanner.nextInt();
          int somme = premierEntier + deuxiemeEntier;
        System.out.println("La somme de " + premierEntier + " avec " + deuxiemeEntier + " est egale a " + somme);
            
} 
    public static void division() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez saisir le premier entier");
    	  int premierEntier = scanner.nextInt();
          System.out.println("Veuillez saisir le deuxieme entier");
          int deuxiemeEntier = scanner.nextInt();
          int somme = premierEntier / deuxiemeEntier;
        System.out.println("La somme de " + premierEntier + " avec " + deuxiemeEntier + " est egale a " + somme);
    }
    
    public static void volume() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez saisir la largeur");
    	  int l = scanner.nextInt();
          System.out.println("Veuillez saisir la longueur");
          int L = scanner.nextInt();
          System.out.println("Veuillez saisir la Hauteur");
          int h = scanner.nextInt();
          int somme = l*L*h;
        System.out.println("Le volume est "+somme);
    }

}
package javaisep;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Rename {
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Quel exercice ? Saisissez: ");
		int choice = scanner.nextInt();
		switch (choice) {
		case 1:
			discriminant();
			break;
		case 2:
			parite();
			break;
		case 3:
			max();
			break;
		case 4:
			egaliteStr();
			break;
		case 5:
			factorielle();
			break;
		case 6:
			try {
				countdown();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 7:
			regle();

			break;
		case 8:
			// tableMultiplication();

			break;
		}

		// initialisationTableau();
	}

	public static void discriminant() {
		System.out.println("Quelle est la valeur de a ?");
		int a = scanner.nextInt();
		System.out.println("Quelle est la valeur de b ?");
		int b = scanner.nextInt();
		System.out.println("Quelle est la valeur de c ?");
		int c = scanner.nextInt();
		if (a != 0) {
			int delta = (int) (Math.pow(b, 2) - 4 * a * c);

			if (delta < 0) {
				System.out.println("Ce polynome n’a pas de racine reelle");

			} else if (delta > 0) {
				double racine1 = (-b - Math.sqrt(delta)) / (2 * a);
				double racine2 = (-b + Math.sqrt(delta)) / (2 * a);
				System.out.println("les racines sont :" + racine1 + ", " + racine2);

			} else {
				double racine1 = (-b) / (2 * a);
				System.out.println("la racine est : " + racine1);
			}
		}
	}

	public static void parite() {
		System.out.println("Entrer un entier?");
		int x = scanner.nextInt();
		if (x % 2 == 0) {
			System.out.println(x + " est pair");
		} else {
			System.out.println(x + " est impair");
		}
		// ou
		System.out.println("le nombre est " + ((x % 2 == 0) ? "pair" : "impair"));
	}

	public static void max() {
		System.out.println("Entrer un entier");
		int x = scanner.nextInt();
		System.out.println("Entrer un deuxième entier");
		int y = scanner.nextInt();
		System.out.println("le maximum est " + ((x > y) ? x : y));
		System.out.println("le minimum est " + ((x < y) ? x : y));

	}

	public static void egaliteStr() {
		System.out.println("Entrer un mot");
		String x = scanner.nextLine();
		System.out.println("Entrer un deuxième mot");
		String y = scanner.nextLine();

		if (x.equals(y)) {
			System.out.println("identiqe");
		} else {
			System.out.println("différent");
		}
	}

	public static void factorielle() {
		System.out.println("Saisir un entier positif ou nul");
		int n = scanner.nextInt();
		int factorielle = 1;
		for (int i = 1; i <= n; i++) {
			factorielle *= i;
		}
		System.out.println(n + "! = " + factorielle);
	}

	public static void countdown() throws InterruptedException {
		int n = 10;
		for (int i = 0; i <= 10; i++) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(n);
			n = n - 1;
		}
		System.out.println("BOOM!");
	}

	public static void tableMultiplication() {
		for (int i = 1; i <= 10; i++) {
			for (int j = 1; j <= 10; j++) {

				System.out.print(i * j);
				System.out.print(" ");
			}
			System.out.print("\n");
		}
	}

	public static void regle() {
		for (int i = 0; i <= 30; i++) {
			char a = i % 10 == 0 ? '|' : '-';
			System.out.print(a);
		}
		System.out.println();
		for (int i = 0; i <= 30; i++) {
			if (i % 10 == 0) {
				System.out.print(i);
				i += (" " + 1).length() - 1;
			} else {
				System.out.print(" ");
			}
		}
	}

//	public static void nombrePremier() {
//		// TODO Auto-generated method stub
//		System.out.println("entrer un nombre premier");
//		int n = scanner.nextInt();
//		int sqrt = (int)Math.sqrt(n)+1;
//		for (int i = 2;i<=sqrt(n);i++){
//			if(n%i==0) {
//				isPrime = false;
//				break;
//			}
//		}
//		if(isPrime) {
//			System.out.println("Le nombre est premier");
//		}
//		else {
//			System.out.println("Le nombre n'est pas premier");
//		}
//
//	}

	public static void initialisationTableau() {
		int[] tableau = new int[4];
		for (int i = 0; i < tableau.length; i++) {
			System.out.println("Saisir un entier");
			int entier = scanner.nextInt();
			tableau[i] = entier;
		}

	}

}

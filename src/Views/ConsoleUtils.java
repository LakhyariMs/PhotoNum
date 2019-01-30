package views;

import java.util.Scanner;

public class ConsoleUtils {
	
	private static Scanner sc = new Scanner(System.in);

	public static void pauseExecution() {
		System.out.print("Appuyez sur Entrer pour Continuer... ");	
		sc.nextLine();
	}

	public static boolean requestConfirmation() {
		while (true) {
			System.out.print("Confirmer l'opération (o/n)... ");
			String in = "";
			if(sc.hasNextLine())
			    in = sc.nextLine().toLowerCase();
			
			if (in.equals("o") || in.equals("oui"))
				return true;
			else if (in.equals("n") || in.equals("non"))
				return false;
			else
				return false;
		}
	}
	
}
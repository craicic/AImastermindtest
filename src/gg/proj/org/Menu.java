package gg.proj.org;

import java.util.Scanner;

public class Menu {

	public Menu() {

		System.out.println("***********************************************");
		System.out.println("**                                           **");
		System.out.println("**                MasterMind                 **");
		System.out.println("**                AI Tester                  **");
		System.out.println("**                                           **");
		System.out.println("***********************************************");
		System.out.println("");

		System.out.println("Quel mode d'AI voulez vous tester?");
		System.out.println("");
		System.out.println("1 - Methode de Knuth avec Minimax");
		System.out.println("2 - Methode de Knuth sans Minimax");
		System.out.println("3 - Calcule du nombre de tour moyen avec Minimax");
		Scanner scanner = new Scanner(System.in);
		System.out.print("Choix : ");
		int i = scanner.nextInt();
		if (i != 3) {
			Launcher launcher = new Launcher(i);
			launcher.runTest();

		} if (i == 3) {
			MultipleGamesLauncher mgl = new MultipleGamesLauncher();
			mgl.runTest();
			mgl.analyseData();
			mgl.displayResult();
		}
	}
}

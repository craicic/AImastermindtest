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

		Scanner scanner = new Scanner(System.in);
		int i = scanner.nextInt();
		System.out.println(i);
		Launcher launcher = new Launcher(i);
		launcher.runTest();
	}

}

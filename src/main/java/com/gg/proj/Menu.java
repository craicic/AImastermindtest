package com.gg.proj;

import java.util.Scanner;

public class Menu {

	
	public Menu() {
		this.displayMenuHeader();
	}
	
	public int getMenuChoice() {
		Scanner scanner = new Scanner(System.in);
		this.displayMainMenu();
		return scanner.nextInt();
	}
	private void displayMenuHeader() {
		System.out.println("***********************************************");
		System.out.println("**                                           **");
		System.out.println("**                MasterMind                 **");
		System.out.println("**                AI Tester                  **");
		System.out.println("**                                           **");
		System.out.println("***********************************************");

	}
	private void displayMainMenu() {
		System.out.println("");
		System.out.println("Quel mode d'AI voulez vous tester?");
		System.out.println("");
		System.out.println("1 - Methode de Knuth avec Minimax");
		System.out.println("2 - Methode de Knuth sans Minimax");
		System.out.println("3 - Calcul du nombre de tour moyen avec Minimax");
		System.out.print("Choix : ");
	}
}

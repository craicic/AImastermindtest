package com.gg.proj.players;

import java.util.Scanner;

import com.gg.proj.Configuration;

public class Player {
	public String giveAnswer(int turnCounter) {
		// if (turnCounter == 0)
		// return "";
		System.out.print("correction : ");
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}

	public String initialize(Configuration config) {
		System.out.print("Entrez le code solution : ");
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}
}

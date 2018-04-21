package com.gg.proj;

import java.util.InputMismatchException;

public class Main {

	public static void main(String[] args) {
		// NbDigit, NbColor
		Menu menu = new Menu();
		boolean ok = false;
		int param = 0;

		while (!ok) {
			try {
				param = menu.getMenuChoice();
				if (param > 0 && param < 4)
					ok = true;
				else throw new IllegalArgumentException("veuillez saisir un nombre entre 1 et 3") ;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			} catch (InputMismatchException e) {
				System.out.println("veuillez saisir un entier");
			}
		}

		if (param != 3) {
			Launcher launcher = new Launcher(param);
			launcher.runTest();
		} else if (param == 3) {
			LoopedGamesLauncher mgl = new LoopedGamesLauncher();
			mgl.runTest();
			mgl.analyseData();
			mgl.displayResult();
		}

	}

}

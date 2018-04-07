package gg.proj.org;

import java.util.ArrayList;

public class MultipleGamesLauncher {

	private Configuration config;
	private ArrayList<Integer> numberOfTurnsList;
	private double averageGameLength;

	public MultipleGamesLauncher() {
		config = new Configuration(3, 5, 12);
		numberOfTurnsList = new ArrayList<Integer>();
		averageGameLength = 0;
	}

	public void runTest() {

		ListBuilder lb = new ListBuilder(config);
		ArrayList<String> fullCodeList = lb.getList();

		for (String solutionCode : fullCodeList) {
			AutomatedGame ag = new AutomatedGame(solutionCode, config);
			ag.runGame();
			numberOfTurnsList.add(ag.getTurnCounter());
			System.out.println(ag.toString());
		}

	}

	public void analyseData() {
		double numberCodeInList = Math.pow(config.getNumberColors(), config.getNumberDigits());
		double grandTotalNumberOfTurns = 0;
		for (Integer turns : numberOfTurnsList) {
			grandTotalNumberOfTurns += turns;
		}
		averageGameLength = grandTotalNumberOfTurns / numberCodeInList;
		System.out.println(grandTotalNumberOfTurns + " / " + numberCodeInList);
	}

	public void displayResult() {

		System.out.println("Average nb Turn : " + averageGameLength);
	}

}

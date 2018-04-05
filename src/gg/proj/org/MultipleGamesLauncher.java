package gg.proj.org;

import java.util.ArrayList;

public class MultipleGamesLauncher {

		private Configuration config = new Configuration(6, 4, 12);
		private ArrayList<Integer> numberOfTurnsList = new ArrayList<Integer>();

		public void runTest(int i) {


		ListBuilder lb = new ListBuilder(config);
		ArrayList<String> fullCodeList = lb.getList();
		
		for (String solutionCode : fullCodeList) {
			AutomatedGame ag = new AutomatedGame(solutionCode);
			ag.runGame();
			numberOfTurnsList.add(ag.getTurnCounter());
		}

	}

}

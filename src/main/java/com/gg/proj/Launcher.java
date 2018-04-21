package com.gg.proj;

import com.gg.proj.players.AIPlayer;
import com.gg.proj.players.KnuthWithMinMax;
import com.gg.proj.players.KnuthWithNoMinMax;
import com.gg.proj.players.Player;

public class Launcher {

	private AIPlayer aiPlayer;
	private Player player;
	private int turnCounter;

	public Launcher(int modeChoice) {
		turnCounter = 0;
		player = new Player();
		switch (modeChoice) {
		case 1:
			aiPlayer = new KnuthWithMinMax();
			break;
		case 2:
			aiPlayer = new KnuthWithNoMinMax();
			break;
		}
	}

	public void runTest() {
		Configuration config = new Configuration(4, 6, 12);
		String code = player.initialize(config);
		String firstguess = aiPlayer.initialize(config);
		boolean ok = true;
		turnCounter++;
		if (firstguess.equals(code))
			ok = false;
		while (ok && nextTurn(code)) {	}
		
		System.out.println("AI " + aiPlayer.getClass() + " gagne en " + turnCounter + " tour(s)");
	}

	private boolean nextTurn(String codeSolution) {
		
		String guess;
		String correction = player.giveAnswer(turnCounter);
		turnCounter++;
//		long timeStart = System.currentTimeMillis();
		aiPlayer.checkMemory(correction);
//		long timeEstimated = System.currentTimeMillis() - timeStart;
//		System.out.println(timeEstimated);
		guess = aiPlayer.makeAGuess();
		return !guess.equals(codeSolution);
	}
}

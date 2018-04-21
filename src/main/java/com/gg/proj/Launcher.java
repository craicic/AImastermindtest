package com.gg.proj;

import com.gg.proj.players.IAPlayer;
import com.gg.proj.players.KnuthWithMinMax;
import com.gg.proj.players.KnuthWithNoMinMax;
import com.gg.proj.players.Player;

public class Launcher {

	private IAPlayer ia;
	private Player pl;
	private int turnCounter;

	public Launcher(int modeChoice) {
		turnCounter = 0;
		pl = new Player();
		switch (modeChoice) {
		case 1:
			ia = new KnuthWithMinMax();
			break;
		case 2:
			ia = new KnuthWithNoMinMax();
			break;
		}
	}

	public void runTest() {
		Configuration config = new Configuration(4, 6, 12);
		String code = pl.initialize(config);
		String firstguess = ia.initialize(config);
		boolean ok = true;
		turnCounter++;
		if (firstguess.equals(code))
			ok = false;
		while (ok && nextTurn(code)) {	}
		
		System.out.println("AI " + ia.getClass() + " gagne en " + turnCounter + " tour(s)");
	}

	private boolean nextTurn(String codeSolution) {
		
		String guess;
		String correction = pl.giveAnswer(turnCounter);
		turnCounter++;
		long timeStart = System.currentTimeMillis();
		ia.checkMemory(correction);
		long timeEstimated = System.currentTimeMillis() - timeStart;
		System.out.println(timeEstimated);
		guess = ia.makeAGuess();
		return !guess.equals(codeSolution);
	}
}

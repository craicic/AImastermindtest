package gg.proj.org;

import gg.player.org.IAPlayer;
import gg.player.org.KnuthWithNoMinMax;
import gg.player.org.KnuthWithMinMax;
import gg.player.org.Player;

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
		ia.checkMemory(correction);
		guess = ia.makeAGuess();
		return !guess.equals(codeSolution);
	}
}

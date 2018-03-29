package gg.player.org;

import gg.proj.org.Configuration;

public interface IAPlayer {

	abstract String initialize(Configuration config);

	abstract void checkMemory(String correction);

	abstract String makeAGuess();

}

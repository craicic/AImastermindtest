package com.gg.proj.players;

import com.gg.proj.Configuration;

public interface IAPlayer {

	abstract String initialize(Configuration config);

	abstract void checkMemory(String correction);

	abstract String makeAGuess();

}

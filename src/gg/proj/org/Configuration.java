package gg.proj.org;

public class Configuration {
	private int numberDigits;
	private int numberColors;
	private int numberTurns;

	public Configuration(int numberDigits, int numberColors, int numberTurns) {
		this.numberDigits = numberDigits;
		this.numberColors = numberColors;
		this.numberTurns = numberTurns;
	}
	public int getNumberDigits() {
		return numberDigits;
	}
	public int getNumberColors() {
		return numberColors;
	}
	public int getNumberTurns() {
		return numberTurns;
	}
}

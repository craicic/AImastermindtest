package gg.proj.org;

import java.util.HashMap;
import java.util.Map;

import gg.player.org.KnuthWithMinMax;

public class AutomatedGame {

	String solutionCode;
	Configuration config;
	int turnCounter;
	
	public AutomatedGame(String solutionCode, Configuration config) {
		this.solutionCode = solutionCode;
		this.config = config;
		turnCounter = 0;
	}

	public void runGame() {
		String guess;
		String correction;
		KnuthWithMinMax knuth = new KnuthWithMinMax();
		guess = knuth.initialize(config);
		turnCounter++;
		while(!guess.equals(solutionCode)) {
			correction = AutomatedGame.Corrector(guess, solutionCode);
			knuth.checkMemory(correction);
			guess = knuth.makeAGuess();
			turnCounter++;
		}
	}

	public int getTurnCounter() {
		return turnCounter;
	}
	
	public String toString() {
		return "Code : "+ solutionCode + " - Nb de tours : " + turnCounter;
	}

	public static String Corrector(String guess, String code) {
	
		String str = "";
		int wellPlaced = 0;
		int correct = 0;
	
		Map<Character, Integer> propositionMap;
		Map<Character, Integer> solutionMap;
	
		for (int i = 0; i < code.length(); i++) {
	
			for (int j = 0; j < code.length(); j++) {
	
				if (guess.charAt(i) == code.charAt(j)) {
					if (i == j) {
						wellPlaced++;
					}
				}
			}
		}
	
		/*
		 * Creation d'une map du qui associe chiffre et occurence du chiffre [
		 * 0,1,1,1,2,0] devient l'ensemble (clé,valeur) (0,2)(1,3)(2,1)
		 * 
		 */
		solutionMap = AutomatedGame.createMap(code);
		propositionMap = AutomatedGame.createMap(guess);
	
		/*
		 * On test chaque entrée de la map solution, si on retrouve un chiffre en commun
		 * on va chercher les valeurs associées on garde la plus petite des deux valeurs
		 * (qui correspond au nombre de correspondances solution / proposition)
		 * 
		 */
		for (Map.Entry<Character, Integer> entry : solutionMap.entrySet()) {
			if (propositionMap.containsKey(entry.getKey())) {
				correct += getTheLesser(propositionMap.get(entry.getKey()), entry.getValue());
			}
		}
	
		/*
		 * La boucle si dessus nous a renvoyé tout le nombre de chiffre present dans les
		 * deux solutions, il faut les distingué des valeurs bien placé, déterminée plus
		 * haut [0,0,0,2] [4,4,0,0] (0,3) (0,2)
		 * 
		 * nombre presents... ... le plus petit des deux valeur 2
		 * 
		 */
		correct -= wellPlaced;
	
		str = correct + " " + wellPlaced;
		return str;
	}
	private static Map<Character, Integer> createMap(String str) {
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < str.length(); i++) {
			if (!map.containsKey(str.charAt(i))) {
				map.put(str.charAt(i), 1);
			} else {
				map.put(str.charAt(i), map.get(str.charAt(i)) + 1);
			}
		}
		return map;
	}

	private static int getTheLesser(int a, int b) {
		return (a > b) ? b : a;
	}

}

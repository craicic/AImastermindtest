package gg.player.org;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import gg.proj.org.Configuration;
import gg.proj.org.ListBuilder;

public class KnuthWithMinMax implements IAPlayer {

	private Configuration config;
	private String guess;
	private ArrayList<String> candidateList;
	// Variables dédiées à makeAGuess()
	private ArrayList<String> fullCodeList;
	private Map<String, Integer> minimumEliminationMap;
	private ArrayList<String> possibleGuessesList;

	private ArrayList<String> pegsList;

	@Override
	public String initialize(Configuration config) {
		this.config = config;
		ListBuilder list = new ListBuilder(config);
		candidateList = list.getList();
		// Spécifique a WithMinMAx
		fullCodeList = new ArrayList<String>(candidateList);
		createPegsList();
		minimumEliminationMap = new HashMap<String, Integer>();
		possibleGuessesList = new ArrayList<String>();
		//
		guess = makeStartGuess();
		System.out.println("Première proposition : " + guess);
		return guess;
	}

	public String makeStartGuess() {
		guess = "11";
		for (int i = 2; i < config.getNumberDigits(); i++)
			guess += "2";
		return guess;
	}

	@Override
	public void checkMemory(String correction) {

		int correct = Integer.parseInt(correction.substring(0, 1));
		int wellPlaced = Integer.parseInt(correction.substring(2, 3));

		for (Iterator<String> iter = candidateList.listIterator(); iter.hasNext();) {
			String candidateCode = iter.next();
			if (!matchWithGuess(guess, candidateCode, correct, wellPlaced)) {
				iter.remove();
			}
		}
		// Affichage de la liste
//		System.out.println("**** candidateList   ****");
//		for (String string : candidateList) {
//			System.out.println(string);
//		}
	}

	private boolean matchWithGuess(String guess, String code, int correct, int wellPlaced) {

		int areWellPlaced = 0;
		int areCorrect = 0;

		Map<Character, Integer> guessMap;
		Map<Character, Integer> candidateCodeMap;

		for (int i = 0; i < config.getNumberDigits(); i++) {

			for (int j = 0; j < config.getNumberDigits(); j++) {

				if (code.charAt(i) == guess.charAt(j)) {
					if (i == j) {
						areWellPlaced++;
					}
				}
			}
		}

		/*
		 * Creation d'une map du qui associe chiffre et occurence du chiffre [
		 * 0,1,1,1,2,0] devient l'ensemble (clé,valeur) (0,2)(1,3)(2,1)
		 * 
		 */
		guessMap = this.createMap(guess);
		candidateCodeMap = this.createMap(code);

		/*
		 * On test chaque entrée de la map solution Si on retrouve un chiffre en commun
		 * on va chercher les valeurs associés on garde la plus petite des deux valeur
		 * (qui correspond au nombre de correspondances solution / proposition)
		 * 
		 */
		for (Map.Entry<Character, Integer> entry : guessMap.entrySet()) {
			if (candidateCodeMap.containsKey(entry.getKey())) {
				areCorrect += getTheLesser(candidateCodeMap.get(entry.getKey()), entry.getValue());
			}
		}

		/*
		 * La boucle si dessus nous a renvoyé tout le nombre de chiffre present dans les
		 * deux solutions, il faut les distingué des valeurs bien placé, déterminée plus
		 * haut [0,0,0,2] [4,4,0,0] (0,3) (0,2)
		 * 
		 * nombres presents... ... la plus petite des deux valeur 2
		 */
		areCorrect -= areWellPlaced;

		return ((areCorrect == correct) && (areWellPlaced == wellPlaced));
	}

	public Map<Character, Integer> createMap(String str) {
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < config.getNumberDigits(); i++) {
			if (!map.containsKey(str.charAt(i))) {
				map.put(str.charAt(i), 1);
			} else {
				map.put(str.charAt(i), map.get(str.charAt(i)) + 1);
			}
		}
		return map;
	}

	public int getTheLesser(int a, int b) {
		return (a > b) ? b : a;
	}

	public int getTheLesser(ArrayList<Integer> list) {
		// minimum très grand
		int minimum = 10000;
		for (Integer amount : list) {
			if (amount < minimum)
				minimum = amount;
		}
		return minimum;
	}

	public int getTheBigger(ArrayList<Integer> list) {
		int maximum = -1;
		for (Integer amount : list) {
			if (amount > maximum)
				maximum = amount;
		}
		return maximum;
	}

	@Override
	public String makeAGuess() {
		resetMapsAndStuff();
		setMinimumEliminationMap();
		setPossibleGuessesList();
		pickTheBestGuess();
		System.out.println("Nouvelle proposition : " + guess);
		return guess;
	}

	private void resetMapsAndStuff() {
		// minimumEliminationMap = new HashMap<String, Integer>();
		// possibleGuessesList = new ArrayList<String>();
		minimumEliminationMap.clear();
		possibleGuessesList.clear();
	}

	public void createPegsList() {

		int index = 0;
		pegsList = new ArrayList<String>();
		for (int i = 0; i <= config.getNumberDigits(); i++)
			for (int j = 0; j <= config.getNumberDigits(); j++)
				if ((i + j) <= config.getNumberDigits()) {
					pegsList.add(index, i + " " + j);
					index++;
				}
		pegsList.remove("0 " + config.getNumberDigits());
		// affiche pegs liste
		// for (String string : pegsList) {
		// System.out.println(string);
		// }
	}

	public void setMinimumEliminationMap() {

		int amountOfElimitatedCodes = 0;
		ArrayList<Integer> listOfAmounts = new ArrayList<Integer>();
		// suppression du dernier guess
		fullCodeList.remove(guess);
		for (String code : fullCodeList) {
			for (String pegs : pegsList) {
				amountOfElimitatedCodes = 0;
				for (String scode : candidateList) {
					if (!matchWithGuess(code, scode, Integer.parseInt(pegs.substring(0, 1)),
							Integer.parseInt(pegs.substring(2, 3)))) {
						amountOfElimitatedCodes++;
					}
				}
				listOfAmounts.add(amountOfElimitatedCodes);
			}
			minimumEliminationMap.put(code, getTheLesser(listOfAmounts));
			listOfAmounts.clear();
		}
	}

	public void setPossibleGuessesList() {
		int minimax = 0;
		ArrayList<Integer> minimumEliminationList = new ArrayList<Integer>();

		for (Map.Entry<String, Integer> entry : minimumEliminationMap.entrySet()) {
			// System.out.println(entry.getKey() + "/" + entry.getValue());
			minimumEliminationList.add(entry.getValue());
		}
		// minimumEliminationSet = new
		// ArrayList<Integer>(minimumEliminationMap.values());

		minimax = getTheBigger(minimumEliminationList);

		for (Map.Entry<String, Integer> entry : minimumEliminationMap.entrySet()) {
			if (entry.getValue() == minimax)
				possibleGuessesList.add(entry.getKey());
		}
	}

	public void pickTheBestGuess() {
		ArrayList<String> bestGuessesList = new ArrayList<String>();
		for (String code : possibleGuessesList) {
			if (candidateList.contains(code)) {
				bestGuessesList.add(code);
//				System.out.println("a solution from the candidate list will be played");
			}
		}
		if (bestGuessesList.isEmpty()) {
			bestGuessesList = new ArrayList<String>(possibleGuessesList);
//			System.out.println("a solution from full code list will be played");
		}

//		System.out.println("**** possibleList ****");
//		for (String string : possibleGuessesList) {
//			System.out.println(string);
//		}
//		System.out.println("**** minimaxedList ****");
//		for (String string : bestGuessesList) {
//			System.out.println(string);
//		}
		// erreur
		// guess = possibleGuessesList.get(0);
		guess = bestGuessesList.get(0);
	}
}

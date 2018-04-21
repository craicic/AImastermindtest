package com.gg.proj.players;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import com.gg.proj.Configuration;
import com.gg.proj.ListBuilder;

public class KnuthWithNoMinMax implements AIPlayer {
	private Configuration config;
	private String guess;
	private ArrayList<String> candidateList;

	@Override
	public String initialize(Configuration config) {
		this.config = config;
		ListBuilder list = new ListBuilder(config);
		candidateList = list.getList();
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
			if (!matchWithCorrection(guess, candidateCode, correct, wellPlaced)) {
				iter.remove();
			}
		}

		
//		Stream<String> ss = candidateList.stream();
//
//		List<String> result = ss.filter(x -> matchWithCorrection(guess, x, correct, wellPlaced)).collect(Collectors.toList());
//		candidateList = (ArrayList<String>) result;
		
		// Affichage de la liste
		// for (String string : candidateList) {
		// System.out.println(string);
		// }
	}

	private boolean matchWithCorrection(String guess, String code, int correct, int wellPlaced) {

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

	@Override
	public String makeAGuess() {
		Random random = new Random();
//		System.out.println("rand : " + random.nextInt(candidateList.size()));
//		System.out.println("list : " + candidateList.size());
		guess = candidateList.get(random.nextInt(candidateList.size()));
		// Une autre méthode consiste à prendre simplement le premier code de la liste
		// guess = candidateList.get(0);
		System.out.println("Nouvelle proposition : " + guess);
		return guess;
	}
}

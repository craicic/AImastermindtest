package com.gg.proj;

import java.util.ArrayList;

public class ListBuilder {

	private ArrayList<String> candidateList;

	public ListBuilder(Configuration config) {

		String combination = "";
		int numberOfCombination = (int) Math.pow(config.getNumberColors(), config.getNumberDigits());
		String blank = "";

		candidateList = new ArrayList<String>();

		for (int i = 0; i < config.getNumberDigits(); i++)
			blank += '0';

		for (int i = 0; i < numberOfCombination; i++) {
			// Utilisation de radix = config.getNumberColor();
			combination = Integer.toString(i, config.getNumberColors());
			if (combination.length() < config.getNumberDigits())
				combination = blank.substring(0, config.getNumberDigits() - combination.length()) + combination;
			candidateList.add(combination);
		}
	}

	public void display() {
		for (String string : candidateList) {
			System.out.println(string);
		}
	}

	public ArrayList<String> getList() {
		return candidateList;
	}
}

package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Main {
	public static int[] numArray;

	public static void main(String[] args) {
		solve("/Users/Alex/Desktop/numbers.txt");
	}

	public static void readData(String file) {
		int total = 0;
		try {
			FileReader r = new FileReader(file);
			BufferedReader br = new BufferedReader(r);
			@SuppressWarnings("unused")
			String line = null;
			while ((line = br.readLine()) != null) {
				total++;
			}
			numArray = new int[total];
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		populate(file);
	}

	private static void populate(String file) {
		try {
			int counter = 0;
			FileReader r = new FileReader(file);
			BufferedReader br = new BufferedReader(r);
			String line = null;
			while ((line = br.readLine()) != null) {
				numArray[counter] = Integer.parseInt(line);
				counter++;
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void solve(String file) {
		readData(file);
		System.out.println("Mode: " + findMode(numArray));
		System.out.println("Average: " + findAverage(numArray));
		System.out.println("Standard Deviation: "
				+ findStandardDeviation(numArray));
	}

	public static String findMode(int[] array) {
		int frequency = 0;
		String mode = "";
		boolean hasMode = false;
		HashMap<Integer, Integer> modeMap = new HashMap<Integer, Integer>();
		for (int i = 0; i <= findBiggest(array); i++) {
			modeMap.put(i, 0);
		}
		for (int i : array) {
			for (int num : modeMap.keySet()) {
				if (num == i) {
					modeMap.put(i, (modeMap.get(i) + 1));
				}
			}
		}
		for (int i : modeMap.keySet()) {
			if (modeMap.get(i) > frequency) {
				frequency = modeMap.get(i);
				mode = (i+",  Frequency: "+modeMap.get(i)+" | ");
				if (frequency > 1)
					hasMode = true;
			}else if (frequency == modeMap.get(i)){
				mode+=(i+",  Frequency: "+modeMap.get(i)+" | ");
			}
		}
		mode = mode.substring(0, mode.lastIndexOf("|"));
		if (hasMode)
			return mode;
		else
			return "No mode, as all numbers have a frequency of 1";
	}

	public static int findBiggest(int[] array) {
		int biggest = 0;
		for (int i : array) {
			if (i > biggest) {
				biggest = i;
			}
		}
		return biggest;
	}

	public static double findAverage(int[] array) {
		double total = 0, average = 0;
		for (int i : array) {
			total += i;
		}
		average = (total / array.length);
		return average;
	}

	public static double findStandardDeviation(int[] array) {
		double sd = 0, average = findAverage(array), total = 0;
		for (int i : array) {
			total += Math.pow((i - average), 2);
		}
		sd = Math.sqrt(total / (array.length - 1));
		return sd;
	}
}

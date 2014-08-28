package main;

import java.util.HashMap;

public class Main {

	public static void main(String[] args) {
		int[] test = new int[] { 7, 4, 5, 9, 10 };
		solve(test);
	}

	public static void solve(int[] array) {
		System.out.println("Mode: " + findMode(array));
		System.out.println("Average: " + findAverage(array));
		System.out.println("Standard Deviation: "
				+ findStandardDeviation(array));
	}

	public static int findMode(int[] array) {
		int frequency = 0;
		int mode = 0;
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
				mode = i;
				if (frequency > 1)
					hasMode = true;
			}
		}
		if (hasMode)
			return mode;
		else
			return -1;
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

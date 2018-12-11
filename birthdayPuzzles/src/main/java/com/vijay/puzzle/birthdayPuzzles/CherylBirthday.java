package com.vijay.puzzle.birthdayPuzzles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CherylBirthday {

	private static String cherylBirthday = null;
	protected static Map<String, Integer> datesWithOccurences = new HashMap<String, Integer>();
	protected static Map<String, Integer> datesWithoutSingleOccurence = new HashMap<String, Integer>();
	protected static Map<String, Integer> datesWithSingleOccurence = new HashMap<String, Integer>();
	private static List<String> inputDates = new ArrayList<String>(10);
	protected static List<String> filteredInputDates = new ArrayList<String>(10);
	protected static List<String> possibleDates = new ArrayList<String>(10);
	protected static String[] removedMonths = new String[5];
	protected static String[] removeEntry = new String[5];
	private static Scanner scanner;

	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		for (int i = 0; i < 10; i++) {
			System.out.println("Please enter " + (i + 1) + " date and month:");
			inputDates.add(scanner.nextLine());
		}
		// dates and their count
		getDatesWithOccurence(inputDates);
		System.out.println("Dates with occurence: "+datesWithOccurences);
		
		// unique dates are removed
		datesWithoutSingleOccurence = getFilteredDates(datesWithOccurences);
		System.out.println("Coming out of getFilteredDates, Dates without single occurence: "+datesWithoutSingleOccurence);
		
		possibleDates = removeSameDateOfMonths(inputDates);
		possibleDates.forEach(System.out::println);
		
		cherylBirthday = getResult(possibleDates);
		
		System.out.println("Cheryl Birthdate is "+cherylBirthday);
	}
	
	public static String getResult(List<String> inputDates){
		datesWithOccurences.clear();
		getMonthsWithOccurence(inputDates);
		// filter months
		datesWithOccurences = removeAmbiguousDates(datesWithOccurences);
		for(Map.Entry<String, Integer> map : datesWithOccurences.entrySet()){
			inputDates = inputDates.stream().filter(predicate -> predicate.contains(map.getKey())).collect(Collectors.toList());
		}
		
		return inputDates.get(0);
	}

	public static List<String> getPossibleDateList(List<String> inputDates) {
		int index = 0;
		for (Map.Entry<String, Integer> date : datesWithSingleOccurence.entrySet()) {
			for (String inputDate : inputDates) {
				if (!inputDate.contains(date.getKey())) {
					inputDates.remove(index);
					index++;
				}
			}
		}
		return inputDates;
	}

	public static List<String> removeSameDateOfMonths(List<String> filteredInputDates) {
		datesWithOccurences.clear();
		System.out.println("date with occurence after clear: "+datesWithOccurences);
		getDatesWithOccurence(filteredInputDates);
		System.out.println("Again date with occurence: "+datesWithOccurences);
		datesWithSingleOccurence = removeAmbiguousDates(datesWithOccurences);
		System.out.println("datesWithSingleOccurence "+datesWithSingleOccurence);
		inputDates = getRefinedDateList(inputDates);

		return filteredInputDates;
	}
	
	public static List<String> getRefinedDateList(List<String> inputDates) {
		List<List<String>> removeDateList = new ArrayList<List<String>>();
		for (Map.Entry<String, Integer> date : datesWithSingleOccurence.entrySet()) {
				removeDateList.add(inputDates.stream().filter(predicate -> predicate.contains(date.getKey())).collect(Collectors.toList()));
		}
		System.out.println("removeDateList before inputDates clear "+removeDateList);
		inputDates.clear();
		System.out.println("Inputdates after clear "+inputDates);
		int p=0;
		for(Map.Entry<String, Integer> date : datesWithSingleOccurence.entrySet()){
				inputDates.addAll(removeDateList.get(p));
				p++;
		}

		return inputDates;
	}



	public static List<String> getDatesWithOccurence(List<String> inputDates) {

		for (String date : inputDates) {
			String[] dateAndMonth = date.split(" ");
			if (datesWithOccurences.containsKey(dateAndMonth[0])) {
				int count = datesWithOccurences.get(dateAndMonth[0]);
				datesWithOccurences.put(dateAndMonth[0], ++count);
			} else {
				datesWithOccurences.put(dateAndMonth[0], 1);
			}
		}

		return inputDates;
	}
	
	public static List<String> getMonthsWithOccurence (List<String> inputDates) {
		for (String date : inputDates) {
			String[] dateAndMonth = date.split(" ");
			if (datesWithOccurences.containsKey(dateAndMonth[1])) {
				int count = datesWithOccurences.get(dateAndMonth[1]);
				datesWithOccurences.put(dateAndMonth[1], ++count);
			} else {
				datesWithOccurences.put(dateAndMonth[1], 1);
			}
		}

		return inputDates;
	}

	// refine the map and remove entries having value = 1
	public static Map<String, Integer> getFilteredDates(Map<String, Integer> datesWithOccurences) {
		// remove months for dates having count = 1
		datesWithOccurences = removeMonthsForSingleOccurence(datesWithOccurences);
		Map<String, Integer> filteredDates = datesWithOccurences.entrySet().stream().filter(p -> p.getValue() != 1)
				.collect(Collectors.toMap(mapper -> mapper.getKey(), mapper -> mapper.getValue()));

		System.out.println("After removing months, filtered dates are: "+filteredDates);
		return filteredDates;
	}

	public static Map<String, Integer> removeMonthsForSingleOccurence(Map<String, Integer> datesWithOccurences) {
		int i = 0;
		datesWithSingleOccurence = datesWithOccurences.entrySet().stream().filter(p -> p.getValue() == 1)
				.collect(Collectors.toMap(mapper -> mapper.getKey(), mapper -> mapper.getValue()));
		System.out.println("Inside removeMonthsForSingleOccurence: "+datesWithSingleOccurence);
		for (Map.Entry<String, Integer> map : datesWithSingleOccurence.entrySet()) {
			for (String date : inputDates) {
				if (date.contains(map.getKey())) {
					String[] dateAndMonth = date.split(" ");
					removedMonths[i] = dateAndMonth[1];
					removeEntry[i] = date;
					i++;
				}
			}
		}
		System.out.println("Remove months: "+removedMonths);
		System.out.println("Remove removeEntry: "+removeEntry);
		
		List<String> removeDates = new ArrayList<String>();
		List<List<String>> removeDateArray = new ArrayList<List<String>>();
		removeDates = inputDates;
		for(String entry : removeEntry){
			inputDates = inputDates.stream().filter(predicate -> predicate != entry).collect(Collectors.toList());
		}
		
		System.out.println("removeDates date intial stage: "+removeDates);
		for(String entry : removedMonths){
			if(entry!= null ){
				System.out.println("entry is: "+entry);
				removeDateArray.add(removeDates.stream().filter(predicate -> predicate.contains(entry)).collect(Collectors.toList()));
				System.out.println("Inside if, removeDate list: "+removeDates);
			}
		}
		int p=0;
		for(String entry : removedMonths){
			if(entry!= null ){
				inputDates.removeAll(removeDateArray.get(p));
				p++;
			}
		}
		
		System.out.println("Input date changed to: "+inputDates);
		System.out.println("removeDates date changed to: "+removeDates);
		return datesWithOccurences;
	}

	public static Map<String, Integer> removeAmbiguousDates(Map<String, Integer> datesWithOccurences) {
		Map<String, Integer> filteredDates = datesWithOccurences.entrySet().stream().filter(p -> p.getValue() == 1)
				.collect(Collectors.toMap(mapper -> mapper.getKey(), mapper -> mapper.getValue()));
		return filteredDates;
	}

	public static String concat(String s, String p) {
		return s + p;
	}

	public static List<String> getList() {
		String arr[] = { "a", "b", "c" };
		return Arrays.asList(arr);
	}
}

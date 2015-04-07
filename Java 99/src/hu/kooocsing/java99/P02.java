package hu.kooocsing.java99;

import static hu.kooocsing.java99.util.InputListFactory.inputList;

import java.util.List;

/**
 * <b>P02</b> (*) Find the last but one element of a list.
 * 
 * @author Dani
 */
public class P02 {
	
	public static void main(String[] args) {
		List<Integer> list = inputList();
		
		Integer[] store = new Integer[2];
		list.stream().mapToInt(Integer::intValue).forEachOrdered(i -> {
			store[0] = store[1];
			store[1] = i;
		});
		System.out.println("Penultimate element of " + list + " is " + store[0] + ".");
	}
}

package hu.kooocsing.java99;

import static hu.kooocsing.java99.util.InputListFactory.inputList;

import java.util.List;



/**
 * <b>P01</b> (*) Find the last element of a list.
 * 
 * @author Dani
 */
public class P01 {

	public static void main(String[] args) {
		List<Integer> list = inputList();
		
		final Integer[] result  = new Integer[1];
		list.stream().mapToInt(Integer::intValue).forEachOrdered(i -> result[0] = i);
		System.out.println("Last element of " + list + " is " + result[0] + ".");
	}
}

package fr.epita.quiz.tests;

import java.util.Arrays;
import java.util.Scanner;

public class StrArrTest {
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter  Topics :");
		String[] topics = scanner.nextLine().split(" ");
		System.out.println("topics::"+Arrays.toString(topics));
	}

}

package src.employment.frame;

import java.util.List;
import java.util.Scanner;

import src.employment.frame.buttons.*;


import src.util.Callable;

public class Frame {
	
	private static Frame bf = null;
	
	public static Frame getBoardFrame() {
		if (bf == null) {
			bf = new Frame();
			return bf;
		}
		return bf;
	}
	
	// 메서드를 인자로 받음. -> 람다 형태로 주어져야 함.
	public void printFrame(String frameTitle, Callable callable, List<Button> buttons) {
		
		System.out.println("#########################################################################");
		System.out.printf("##########\t\t\t%s\t\t\t##########\n", frameTitle);
		System.out.println("#########################################################################");
		System.out.println("-------------------------------------------------------------------------");
		callable.execute();
		System.out.println("-------------------------------------------------------------------------");
		for (Button button: buttons) {
			button.printButton();
		}
		System.out.println("#########################################################################");
		
	}
}

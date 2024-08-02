package src.employment.test;


import java.util.List;
import java.util.ArrayList;

import src.employment.frame.Frame;
import src.employment.frame.contents.*;
import src.employment.frame.buttons.*;


public class TestMain {
	
	public static void main(String[] args) {
		// 메뉴 초기화
		String userInputCommand = null;
		Frame bf = Frame.getBoardFrame();
		AllBoards ab = new AllBoards();
		List<Button> buttonList = new ArrayList<>();
		ReadDetail rd = new ReadDetail();
		Delete d = new Delete();
		Update u = new Update();
		Back b = new Back();
		buttonList.add(rd);
		buttonList.add(u);
		buttonList.add(d);
		buttonList.add(b);
		
		while (true) {
			if (userInputCommand == null) {				
				bf.printFrame("메인 게시판 입니다.", () -> ab.printAllBoards(), buttonList);
			}
		}

	}

}

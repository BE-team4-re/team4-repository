package src.employment.test;


import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

import src.employment.board.BoardDTO;
import src.employment.command.CommandInterpreter;
import src.employment.command.FrameSet;
import src.employment.frame.Frame;
import src.employment.frame.Stack;
import src.employment.frame.contents.*;
import src.employment.frame.buttons.*;
import src.util.Callable;


public class TestMain {
	public static List<BoardDTO> BoardList = null;
	public static String allowedCommands = "uRJ";
	public static void main(String[] args) {
		CommandInterpreter commandInterpreter = CommandInterpreter.getInterpreter(); // 명령어 인터프리터 가져옴.
		String userInputCommand = "";
		// 메인 게시판 첫 진입을 위한 프레임 초기화 작업.
		Frame bf = Frame.getBoardFrame();
		// 프레임 타이틀.
		String title = "메인 게시판 입니다.";
		// 프레임 내용.
		PrintBoard ab = new PrintBoard();
		Callable method = () -> ab.printAllBoards();
		// 프레임 버튼 리스트.
		List<Button> buttonList = new ArrayList<>();

		Update u = new Update();
		ByRegion r = new ByRegion();
		ByJob j = new ByJob();

		buttonList.add(u);
		buttonList.add(r);
		buttonList.add(j);

		FrameSet frameSet = new FrameSet(title, method, buttonList);
		FrameSet initialFrameSet = frameSet;

		Scanner sc = new Scanner(System.in);

		while (!userInputCommand.equals("Q")) {
			FrameSet nextFrameSet = null;
			bf.printFrame(frameSet);
			System.out.print("입력하세요 (Q. 종료하기): ");
			userInputCommand = sc.nextLine();
			nextFrameSet = commandInterpreter.interpret(userInputCommand, allowedCommands);
			if (nextFrameSet == null) {
				if (userInputCommand.equals("Q")) {
					break;
				} else {
					System.out.println("잘못된 입력입니다.");
					frameSet = initialFrameSet; // 잘못된 입력이 올 경우 첫 페이지로 이동.
				}
			} else {
				// 올바른 입력이라면 새로운 frameset 으로 교체하고 페이지 스택에 담음.
				frameSet = nextFrameSet;
			}
		}
	}
}

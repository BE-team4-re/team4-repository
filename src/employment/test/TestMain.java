package src.employment.test;


import java.util.List;

import src.employment.Controller;
import src.employment.board.BoardDTO;


public class TestMain {
	public static List<BoardDTO> BoardList = null;
	public static void EmploymentBoardMainEntrance() {
		Controller controller = new Controller();
		controller.printMain();
	}
}

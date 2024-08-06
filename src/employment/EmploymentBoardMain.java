package src.employment;


import java.util.List;

import src.employment.board.BoardDTO;


public class EmploymentBoardMain {
	public static List<BoardDTO> BoardList = null;
	public static void entrance() {
		Controller controller = new Controller();
		controller.printMain();
	}
}

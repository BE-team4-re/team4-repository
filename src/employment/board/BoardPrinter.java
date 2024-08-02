package src.employment.board;

import src.employment.board.BoardObject;
import src.employment.recordDAO.*;

import java.util.List;
import java.util.ArrayList;

public class BoardPrinter {
	
	// console 틀을 보여주는 메서드
	public void printBoard() {
		EmploymentBoardDAO dao = new EmploymentBoardDAO();
		List<BoardObject> daoList = dao.selectAllEmploymentBoardsFromDB();
		for (BoardObject board: daoList) {			
			System.out.printf(""
					+ "글번호:%d | "
					+ "제목:%s | "
					+ "근무형태:%s | "
					+ "요구학력:%s | "
					+ "채용절차:%s | "
					+ "자격요건:%s | "
					+ "우대사항:%s | "
					+ "메인카테고리:%d | "
					+ "지역:%d | "
					+ "서브카테고리:%d | "
					+ "직무:%d\n",
					board.getEmploymentBoardId(),
					board.getTitle(),
					board.getJobType(),
					board.getCareer(),
					board.getHiringProcess(),
					board.getQualifications(),
					board.getPreferred(),
					board.getMainCategory1Id(),
					board.getMainCategory2Id(),
					board.getSubCategory1Id(),
					board.getSubCategory2Id()
					);
		}
	}
}

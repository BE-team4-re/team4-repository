package src.employment.frame.contents;

import java.util.List;

import src.employment.board.BoardObject;
import src.employment.recordDAO.employmentBoard.read.DAO;

public class AllBoards {
	
	// 채용 공고를 조건없이 모두 보여줌
	public void printAllBoards() {
		DAO dao = new DAO();
		List<BoardObject> daoList = dao.read();
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

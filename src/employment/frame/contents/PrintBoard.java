package src.employment.frame.contents;


import src.employment.board.BoardDTO;
import src.employment.recordDAO.employmentBoard.read.DAO;
import src.util.Response;

import java.util.List;

import static src.employment.test.TestMain.BoardList;


public class PrintBoard {
	
	// 채용 공고를 조건없이 모두 보여줌
	public void printAllBoards() {
		DAO dao = new DAO();
		if (BoardList == null) { // null 인 경우는 페이지를 맨 처음 켰을때만,
			Response<List<BoardDTO>> response = dao.readAll();
			BoardList = response.getData();
		}
		for (BoardDTO board: BoardList) {
			System.out.printf(""
					+ "글번호:%d | "
					+ "제목:%s | "
					+ "근무형태:%s | "
					+ "요구학력:%s | "
					+ "채용절차:%s | "
					+ "자격요건:%s | "
					+ "우대사항:%s | "
					+ "메인카테고리:%s | "
					+ "지역:%s | "
					+ "서브카테고리:%s | "
					+ "직무:%s\n",
					board.getEmploymentBoardId(),
					board.getTitle(),
					board.getJobType(),
					board.getCareer(),
					board.getHiringProcess(),
					board.getQualifications(),
					board.getPreferred(),
					board.getCompanyName(),
					board.getMainCategory1Id(),
					board.getMainCategory2Id(),
					board.getSubCategory1Id(),
					board.getSubCategory2Id()
					);
		}
	}

	public void printOneBoard(int bid) {
		DAO dao = new DAO();
		Response<BoardDTO> response = dao.read(bid);
		BoardDTO board = response.getData();
		System.out.printf(""
						+ "글번호:%d | "
						+ "제목:%s | "
						+ "근무형태:%s | "
						+ "요구학력:%s | "
						+ "채용절차:%s | "
						+ "자격요건:%s | "
						+ "우대사항:%s | "
						+ "회사명:%s | "
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
				board.getCompanyName(),
				board.getMainCategory1Id(),
				board.getMainCategory2Id(),
				board.getSubCategory1Id(),
				board.getSubCategory2Id()
		);
	}
}

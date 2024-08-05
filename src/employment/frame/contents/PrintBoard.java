package src.employment.frame.contents;


import src.employment.board.BoardCategoryEnum;
import src.employment.board.BoardDTO;
import src.employment.recordDAO.employmentBoard.read.ReadDAO;
import src.util.Response;

import java.util.*;

import static src.employment.test.TestMain.BoardList;


public class PrintBoard {

	private List<BoardDTO> printBoardOnePage(int pageIdx, List<BoardDTO> boardList) {
		List<BoardDTO> onePage = new ArrayList<>();

		for (int i = 0; i < 10 && !boardList.isEmpty(); i++) {
			int lastIndex = boardList.size() - 1;
			onePage.add(boardList.remove(lastIndex));
		}

	}

	public String convertCategoryIdToName(int id) {
		BoardCategoryEnum[] values = BoardCategoryEnum.values();
		for (BoardCategoryEnum value: values) {
			if (id == value.getSubId()) {
				return value.getCategoryName();
			}
		}
		return null;
	}

	// 채용 공고를 조건없이 모두 보여줌
	public int printAllBoards(int pageIdx) {
		int pageSize = 0;
		ReadDAO readDao = new ReadDAO();
		if (BoardList == null) { // null 인 경우는 페이지를 맨 처음 켰을때만,
			Response<List<BoardDTO>> response = readDao.readAll();
			BoardList = response.getData();
		}
		if (BoardList.isEmpty()) {
			System.out.println("등록된 채용 게시물이 없습니다.");
		} else {
			List<BoardDTO> onePage = printBoardOnePage(pageIdx, BoardList);
			for (BoardDTO board: onePage) {
				System.out.printf(""
								+ "글번호:%d | "
								+ "제목:%s | "
								+ "근무형태:%s | "
								+ "요구학력:%s | "
								+ "채용절차:%s | "
								+ "자격요건:%s | "
								+ "우대사항:%s | "
								+ "회사명:%s | "
								+ "메인카테고리:%s | "
								+ "상세 지역:%s | "
								+ "메인카테고리:%s | "
								+ "상세 직무:%s\n",
						board.getEmploymentBoardId(),
						board.getTitle(),
						board.getJobType(),
						board.getCareer(),
						board.getHiringProcess(),
						board.getQualifications(),
						board.getPreferred(),
						board.getCompanyName(),
						convertCategoryIdToName(board.getMainCategory1Id()),
						convertCategoryIdToName(board.getSubCategory1Id()),
						convertCategoryIdToName(board.getMainCategory2Id()),
						convertCategoryIdToName(board.getSubCategory2Id())
				);
			}
		}
		return pageSize;
	}

	// id로 얻어온 단일 채용 공고를 보여줌.
	public void printOneBoard(int bid) {
		ReadDAO readDao = new ReadDAO();
		Response<BoardDTO> response = readDao.read(bid);
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
						+ "메인카테고리:%s | "
						+ "상세 지역:%s | "
						+ "메인카테고리:%s | "
						+ "상세 직무:%s\n",
				board.getEmploymentBoardId(),
				board.getTitle(),
				board.getJobType(),
				board.getCareer(),
				board.getHiringProcess(),
				board.getQualifications(),
				board.getPreferred(),
				board.getCompanyName(),
				convertCategoryIdToName(board.getMainCategory1Id()),
				convertCategoryIdToName(board.getSubCategory1Id()),
				convertCategoryIdToName(board.getMainCategory2Id()),
				convertCategoryIdToName(board.getSubCategory2Id())
		);
	}

	// `지역별` 선택시 조회.
	public int printAllBoardsByRegion(int pageIdx) {
		int pageSize = 0;
		ReadDAO readDao = new ReadDAO();
		Response<List<BoardDTO>> response = readDao.readByRegion();
		List<BoardDTO> boardList = response.getData();
		if (boardList.isEmpty()) {
			System.out.println("관련된 채용 게시물이 없습니다.");
		} else {
			List<BoardDTO> onePage = printBoardOnePage(pageIdx, boardList);
			for (BoardDTO board: onePage) {
				System.out.printf(""
								+ "글번호:%d | "
								+ "제목:%s | "
								+ "근무형태:%s | "
								+ "요구학력:%s | "
								+ "채용절차:%s | "
								+ "자격요건:%s | "
								+ "우대사항:%s | "
								+ "회사명:%s | "
								+ "메인카테고리:%s | "
								+ "상세 지역:%s | "
								+ "메인카테고리:%s | "
								+ "상세 직무:%s\n",
						board.getEmploymentBoardId(),
						board.getTitle(),
						board.getJobType(),
						board.getCareer(),
						board.getHiringProcess(),
						board.getQualifications(),
						board.getPreferred(),
						board.getCompanyName(),
						convertCategoryIdToName(board.getMainCategory1Id()),
						convertCategoryIdToName(board.getSubCategory1Id()),
						convertCategoryIdToName(board.getMainCategory2Id()),
						convertCategoryIdToName(board.getSubCategory2Id())
				);
			}
		}
		return pageSize;
	}

	// 상세 지역으로 조회.
	public int printAllBoardsByRegionDetail(int pageIdx, int subCategory1Id) {
		int pageSize = 0;
		ReadDAO readDao = new ReadDAO();
		Response<List<BoardDTO>> response = readDao.readByRegionDetail(subCategory1Id);
		List<BoardDTO> boardList = response.getData();
		if (boardList.isEmpty()) {
			System.out.println("관련된 채용 게시물이 없습니다.");
		} else {
			List<BoardDTO> onePage = printBoardOnePage(pageIdx, boardList);
			for (BoardDTO board: onePage) {
				System.out.printf(""
								+ "글번호:%d | "
								+ "제목:%s | "
								+ "근무형태:%s | "
								+ "요구학력:%s | "
								+ "채용절차:%s | "
								+ "자격요건:%s | "
								+ "우대사항:%s | "
								+ "회사명:%s | "
								+ "메인카테고리:%s | "
								+ "상세 지역:%s | "
								+ "메인카테고리:%s | "
								+ "상세 직무:%s\n",
						board.getEmploymentBoardId(),
						board.getTitle(),
						board.getJobType(),
						board.getCareer(),
						board.getHiringProcess(),
						board.getQualifications(),
						board.getPreferred(),
						board.getCompanyName(),
						convertCategoryIdToName(board.getMainCategory1Id()),
						convertCategoryIdToName(board.getSubCategory1Id()),
						convertCategoryIdToName(board.getMainCategory2Id()),
						convertCategoryIdToName(board.getSubCategory2Id())
				);
			}
		}
		return pageSize;
	}
	
	// `직무별` 선택시 조회.
	public int printAllBoardsByJob(int pageIdx) {
		int pageSize = 0;
		ReadDAO readDao = new ReadDAO();
		Response<List<BoardDTO>> response = readDao.readByJob();
		List<BoardDTO> boardList = response.getData();
		if (boardList.isEmpty()) {
			System.out.println("관련된 채용 게시물이 없습니다.");
		} else {
			List<BoardDTO> onePage = printBoardOnePage(pageIdx, boardList);
			for (BoardDTO board: onePage) {
				System.out.printf(""
								+ "글번호:%d | "
								+ "제목:%s | "
								+ "근무형태:%s | "
								+ "요구학력:%s | "
								+ "채용절차:%s | "
								+ "자격요건:%s | "
								+ "우대사항:%s | "
								+ "회사명:%s | "
								+ "메인카테고리:%s | "
								+ "상세 지역:%s | "
								+ "메인카테고리:%s | "
								+ "상세 직무:%s\n",
						board.getEmploymentBoardId(),
						board.getTitle(),
						board.getJobType(),
						board.getCareer(),
						board.getHiringProcess(),
						board.getQualifications(),
						board.getPreferred(),
						board.getCompanyName(),
						convertCategoryIdToName(board.getMainCategory1Id()),
						convertCategoryIdToName(board.getSubCategory1Id()),
						convertCategoryIdToName(board.getMainCategory2Id()),
						convertCategoryIdToName(board.getSubCategory2Id())
				);
			}
		}
		return pageSize;
	}

	// 상세 직무로 조회.
	public int printAllBoardsByJobDetail(int pageIdx, int subCategory2Id) {
		int pageSize = 0;
		ReadDAO readDao = new ReadDAO();
		Response<List<BoardDTO>> response = readDao.readByJobDetail(subCategory2Id);
		List<BoardDTO> boardList = response.getData();
		if (boardList.isEmpty()) {
			System.out.println("관련된 채용 게시물이 없습니다.");
		} else {
			List<BoardDTO> onePage = printBoardOnePage(pageIdx, boardList);
			for (BoardDTO board: onePage) {
				System.out.printf(""
								+ "글번호:%d | "
								+ "제목:%s | "
								+ "근무형태:%s | "
								+ "요구학력:%s | "
								+ "채용절차:%s | "
								+ "자격요건:%s | "
								+ "우대사항:%s | "
								+ "회사명:%s | "
								+ "메인카테고리:%s | "
								+ "상세 지역:%s | "
								+ "메인카테고리:%s | "
								+ "상세 직무:%s\n",
						board.getEmploymentBoardId(),
						board.getTitle(),
						board.getJobType(),
						board.getCareer(),
						board.getHiringProcess(),
						board.getQualifications(),
						board.getPreferred(),
						board.getCompanyName(),
						convertCategoryIdToName(board.getMainCategory1Id()),
						convertCategoryIdToName(board.getSubCategory1Id()),
						convertCategoryIdToName(board.getMainCategory2Id()),
						convertCategoryIdToName(board.getSubCategory2Id())
				);
			}
		}
		return pageSize;
	}

	// 상세 지역과 상세 직무로 조회.
	public int printAllBoardsByRegionDetailAndJobDetail(int pageIdx, int subCategory1Id, int subCategory2Id) {
		int pageSize = 0;
		ReadDAO readDao = new ReadDAO();
		Response<List<BoardDTO>> response = readDao.readByRegionDetailAndJobDetail(subCategory1Id, subCategory2Id);
		List<BoardDTO> boardList = response.getData();
		if (boardList.isEmpty()) {
			System.out.println("관련된 채용 게시물이 없습니다.");
		} else {
			List<BoardDTO> onePage = printBoardOnePage(pageIdx, boardList);
			for (BoardDTO board: onePage) {
				System.out.printf(""
								+ "글번호:%d | "
								+ "제목:%s | "
								+ "근무형태:%s | "
								+ "요구학력:%s | "
								+ "채용절차:%s | "
								+ "자격요건:%s | "
								+ "우대사항:%s | "
								+ "회사명:%s | "
								+ "메인카테고리:%s | "
								+ "상세 지역:%s | "
								+ "메인카테고리:%s | "
								+ "상세 직무:%s\n",
						board.getEmploymentBoardId(),
						board.getTitle(),
						board.getJobType(),
						board.getCareer(),
						board.getHiringProcess(),
						board.getQualifications(),
						board.getPreferred(),
						board.getCompanyName(),
						convertCategoryIdToName(board.getMainCategory1Id()),
						convertCategoryIdToName(board.getSubCategory1Id()),
						convertCategoryIdToName(board.getMainCategory2Id()),
						convertCategoryIdToName(board.getSubCategory2Id())
				);
			}
		}
		return pageSize;
	}
}

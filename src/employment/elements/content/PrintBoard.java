package src.employment.elements.content;


import src.employment.board.BoardCategoryDTO;
import src.employment.board.BoardDTO;
import src.employment.recordDAO.employmentBoard.ReadDAO;
import src.employment.recordDAO.employmentBoardCategory.ReadCategoryDAO;
import src.util.Response;

import java.util.*;


public class PrintBoard {
	public static int maxPage = 0;
	public static int maxPageMod = 0;
	private List<BoardDTO> printBoardOnePage(int pageIdx, List<BoardDTO> boardList) {
		int listSize = boardList.size();
		maxPage = listSize / 10;
		maxPageMod = listSize % 10;
		if (maxPageMod != 0) {
			maxPage ++;
		}
		int startIdx = (pageIdx - 1) * 10;
		int endIdx = (pageIdx * 10) - 1;

		List<BoardDTO> page = new ArrayList<>();
		for (int i = startIdx; i <= endIdx; i++) {
			try {
				BoardDTO board = boardList.get(i);
				page.add(board);
			} catch (IndexOutOfBoundsException e) {
				break;
			}
		}
		return page;
	}

	public String convertCategoryIdToCategoryName(int userInputSubCategoryId) {
		String convertedName = "";
		ReadCategoryDAO readCategoryDAO = new ReadCategoryDAO();
		List<BoardCategoryDTO> boardCategoryDTOList = new ArrayList<>();
		Response<List<BoardCategoryDTO>> response = readCategoryDAO.readAllCategories();
		if (response.isSuccess()) {
			boardCategoryDTOList = response.getData();
		}
		if (boardCategoryDTOList.isEmpty()) {
			System.out.println("카테고리 리스트가 비어있기 때문에 변환할 수 없습니다.");
			// 이 경우 공백이 반환됨.
		} else {
			// 전체 리스트를 돌면서 id가 일치하는 요소를 찾아냄.
			for (BoardCategoryDTO category: boardCategoryDTOList) {
				if (userInputSubCategoryId == category.getSubCategoryId()) {
					convertedName = category.getCategoryName();
					break;
				}
			}
		}
		// 맞는게 없어도 공백이 반환됨.
		return convertedName;
	}

	public void printAllRegionList() {
		ReadCategoryDAO readCategoryDAO = new ReadCategoryDAO();
		List<BoardCategoryDTO> boardCategoryDTOList = new ArrayList<>();
		Response<List<BoardCategoryDTO>> response = readCategoryDAO.readAllCategories();
		if (response.isSuccess()) {
			boardCategoryDTOList = response.getData();
		}
		if (boardCategoryDTOList.isEmpty()) {
			System.out.println("카테고리 리스트가 비어있습니다.");
		} else {
			System.out.println(":::검색 가능 지역 리스트:::");
			for (BoardCategoryDTO category: boardCategoryDTOList) {
				if (category.getMainCategoryId() == 1) {
					System.out.print(category.getCategoryName()+"\t");
				}
			}
			System.out.println();
		}
	}

	public void printAllJobList() {
		ReadCategoryDAO readCategoryDAO = new ReadCategoryDAO();
		List<BoardCategoryDTO> boardCategoryDTOList = new ArrayList<>();
		Response<List<BoardCategoryDTO>> response = readCategoryDAO.readAllCategories();
		if (response.isSuccess()) {
			boardCategoryDTOList = response.getData();
		}
		if (boardCategoryDTOList.isEmpty()) {
			System.out.println("카테고리 리스트가 비어있습니다.");
		} else {
			System.out.println(":::검색 가능 직무 리스트:::");
			for (BoardCategoryDTO category: boardCategoryDTOList) {
				if (category.getMainCategoryId() == 2) {
					System.out.print(category.getCategoryName()+"\t");
				}
			}
			System.out.println();
		}
	}

	public int convertCategoryNameToSubCategoryId(String userInputCategoryName) {
		int convertedSubId = 0;
		ReadCategoryDAO readCategoryDAO = new ReadCategoryDAO();
		List<BoardCategoryDTO> boardCategoryDTOList = new ArrayList<>();
		Response<List<BoardCategoryDTO>> response = readCategoryDAO.readAllCategories();
		if (response.isSuccess()) {
			boardCategoryDTOList = response.getData();
		}
		if (boardCategoryDTOList.isEmpty()) {
			System.out.println("카테고리 리스트가 비어있기 때문에 변환할 수 없습니다.");
			// 이 경우 0이 반환됨.
		} else {
			// 전체 리스트를 돌면서 id가 일치하는 요소를 찾아냄.
			for (BoardCategoryDTO category: boardCategoryDTOList) {
				if (userInputCategoryName.equals(category.getCategoryName())) {
					convertedSubId = category.getSubCategoryId();
					break;
				}
			}
		}
		// 맞는게 없으면 0이 반환됨.
		return convertedSubId;
	}

	// 채용 공고를 조건없이 모두 보여줌
	public void printAllBoards(int pageIdx) {
		ReadDAO readDao = new ReadDAO();
		Response<List<BoardDTO>> response = readDao.readAll();
		List<BoardDTO> boardList = new ArrayList<>();
		if (response.isSuccess()) {
			boardList = response.getData();
		} else {
			System.out.println("불러오기 실패.");
		}
		if (boardList.isEmpty()) {
			System.out.println("등록된 채용 게시물이 없습니다.");
		} else {
			List<BoardDTO> onePage = printBoardOnePage(pageIdx, boardList);
			for (BoardDTO board: onePage) {
				System.out.printf("글번호: %d | "
								+ "제목: %s\n",
//								+ "근무형태:%s | "
//								+ "요구학력:%s | "
//								+ "채용절차:%s | "
//								+ "자격요건:%s | "
//								+ "우대사항:%s | "
//								+ "회사명:%s | "
//								+ "지역:%s | "
//								+ "직무:%s\n",
						board.getEmploymentBoardId(),
						board.getTitle()
//						board.getJobType(),
//						board.getCareer(),
//						board.getHiringProcess(),
//						board.getQualifications(),
//						board.getPreferred(),
//						board.getCompanyName(),
//						convertCategoryIdToName(board.getSubCategory1Id()),
//						convertCategoryIdToName(board.getSubCategory2Id())
				);
			}
		}
	}

	// 자세히보기 -> id로 얻어온 단일 채용 공고를 보여줌.
	public void printDetailBoard(int bid) {
		ReadDAO readDao = new ReadDAO();
		Response<BoardDTO> response = readDao.read(bid);
		BoardDTO board = response.getData();
		if (response.isSuccess()) {
			System.out.printf("글번호: %d\n"
							+ "제목: %s\n"
							+ "근무형태: %s\n"
							+ "요구학력: %s\n"
							+ "채용절차: %s\n"
							+ "자격요건: %s\n"
							+ "우대사항: %s\n"
							+ "회사명: %s\n"
							+ "지역: %s\n"
							+ "직무: %s\n",
					board.getEmploymentBoardId(),
					board.getTitle(),
					board.getJobType(),
					board.getCareer(),
					board.getHiringProcess(),
					board.getQualifications(),
					board.getPreferred(),
					board.getCompanyName(),
					convertCategoryIdToCategoryName(board.getSubCategory1Id()),
					convertCategoryIdToCategoryName(board.getSubCategory2Id())
			);
		} else {
			System.out.println("불러오기 실패.");
		}
	}

	// `지역별` 선택시 조회.
	public void printAllBoardsByRegion(int pageIdx) {
		ReadDAO readDao = new ReadDAO();
		Response<List<BoardDTO>> response = readDao.readByRegion();
		List<BoardDTO> boardList = new ArrayList<>();
		if (response.isSuccess()) {
			boardList = response.getData();
		} else {
			System.out.println("불러오기 실패.");
		}
		if (boardList.isEmpty()) {
			System.out.println("관련된 채용 게시물이 없습니다.");
		} else {
			List<BoardDTO> onePage = printBoardOnePage(pageIdx, boardList);
			for (BoardDTO board: onePage) {
				System.out.printf("글번호: %d | "
								+ "제목: %s\n",
//								+ "근무형태:%s | "
//								+ "요구학력:%s | "
//								+ "채용절차:%s | "
//								+ "자격요건:%s | "
//								+ "우대사항:%s | "
//								+ "회사명:%s | "
//								+ "지역:%s | "
//								+ "직무:%s\n",
						board.getEmploymentBoardId(),
						board.getTitle()
//						board.getJobType(),
//						board.getCareer(),
//						board.getHiringProcess(),
//						board.getQualifications(),
//						board.getPreferred(),
//						board.getCompanyName(),
//						convertCategoryIdToName(board.getSubCategory1Id()),
//						convertCategoryIdToName(board.getSubCategory2Id())
				);
			}
		}
	}

	// 상세 지역으로 조회.
	public void printAllBoardsByRegionDetail(int pageIdx, int subCategory1Id) {
		ReadDAO readDao = new ReadDAO();
		Response<List<BoardDTO>> response = readDao.readByRegionDetail(subCategory1Id);
		List<BoardDTO> boardList = new ArrayList<>();
		if (response.isSuccess()) {
			boardList = response.getData();
		} else {
			System.out.println("불러오기 실패.");
		}
		if (boardList.isEmpty()) {
			System.out.println("관련된 채용 게시물이 없습니다.");
		} else {
			List<BoardDTO> onePage = printBoardOnePage(pageIdx, boardList);
			for (BoardDTO board: onePage) {
				System.out.printf("글번호: %d | "
								+ "제목: %s\n",
//								+ "근무형태:%s | "
//								+ "요구학력:%s | "
//								+ "채용절차:%s | "
//								+ "자격요건:%s | "
//								+ "우대사항:%s | "
//								+ "회사명:%s | "
//								+ "지역:%s | "
//								+ "직무:%s\n",
						board.getEmploymentBoardId(),
						board.getTitle()
//						board.getJobType(),
//						board.getCareer(),
//						board.getHiringProcess(),
//						board.getQualifications(),
//						board.getPreferred(),
//						board.getCompanyName(),
//						convertCategoryIdToName(board.getSubCategory1Id()),
//						convertCategoryIdToName(board.getSubCategory2Id())
				);
			}
		}
	}
	
	// `직무별` 선택시 조회.
	public void printAllBoardsByJob(int pageIdx) {
		ReadDAO readDao = new ReadDAO();
		Response<List<BoardDTO>> response = readDao.readByJob();
		List<BoardDTO> boardList = new ArrayList<>();
		if (response.isSuccess()) {
			boardList = response.getData();
		} else {
			System.out.println("불러오기 실패.");
		}
		if (boardList.isEmpty()) {
			System.out.println("관련된 채용 게시물이 없습니다.");
		} else {
			List<BoardDTO> onePage = printBoardOnePage(pageIdx, boardList);
			for (BoardDTO board: onePage) {
				System.out.printf("글번호: %d | "
								+ "제목: %s\n",
//								+ "근무형태:%s | "
//								+ "요구학력:%s | "
//								+ "채용절차:%s | "
//								+ "자격요건:%s | "
//								+ "우대사항:%s | "
//								+ "회사명:%s | "
//								+ "지역:%s | "
//								+ "직무:%s\n",
						board.getEmploymentBoardId(),
						board.getTitle()
//						board.getJobType(),
//						board.getCareer(),
//						board.getHiringProcess(),
//						board.getQualifications(),
//						board.getPreferred(),
//						board.getCompanyName(),
//						convertCategoryIdToName(board.getSubCategory1Id()),
//						convertCategoryIdToName(board.getSubCategory2Id())
				);
			}
		}
	}

	// 상세 직무로 조회.
	public void printAllBoardsByJobDetail(int pageIdx, int subCategory2Id) {
		ReadDAO readDao = new ReadDAO();
		Response<List<BoardDTO>> response = readDao.readByJobDetail(subCategory2Id);
		List<BoardDTO> boardList = new ArrayList<>();
		if (response.isSuccess()) {
			boardList = response.getData();
		} else {
			System.out.println("불러오기 실패.");
		}
		if (boardList.isEmpty()) {
			System.out.println("관련된 채용 게시물이 없습니다.");
		} else {
			List<BoardDTO> onePage = printBoardOnePage(pageIdx, boardList);
			for (BoardDTO board: onePage) {
				System.out.printf("글번호: %d | "
								+ "제목: %s\n",
//								+ "근무형태:%s | "
//								+ "요구학력:%s | "
//								+ "채용절차:%s | "
//								+ "자격요건:%s | "
//								+ "우대사항:%s | "
//								+ "회사명:%s | "
//								+ "지역:%s | "
//								+ "직무:%s\n",
						board.getEmploymentBoardId(),
						board.getTitle()
//						board.getJobType(),
//						board.getCareer(),
//						board.getHiringProcess(),
//						board.getQualifications(),
//						board.getPreferred(),
//						board.getCompanyName(),
//						convertCategoryIdToName(board.getSubCategory1Id()),
//						convertCategoryIdToName(board.getSubCategory2Id())
				);
			}
		}
	}

	// 상세 지역과 상세 직무로 조회.
	public void printAllBoardsByRegionDetailAndJobDetail(int pageIdx, int subCategory1Id, int subCategory2Id) {
		ReadDAO readDao = new ReadDAO();
		Response<List<BoardDTO>> response = readDao.readByRegionDetailAndJobDetail(subCategory1Id, subCategory2Id);
		List<BoardDTO> boardList = new ArrayList<>();
		if (response.isSuccess()) {
			boardList = response.getData();
		} else {
			System.out.println("불러오기 실패.");
		}
		if (boardList.isEmpty()) {
			System.out.println("관련된 채용 게시물이 없습니다.");
		} else {
			List<BoardDTO> onePage = printBoardOnePage(pageIdx, boardList);
			for (BoardDTO board: onePage) {
				System.out.printf("글번호: %d | "
								+ "제목: %s\n",
//								+ "근무형태:%s | "
//								+ "요구학력:%s | "
//								+ "채용절차:%s | "
//								+ "자격요건:%s | "
//								+ "우대사항:%s | "
//								+ "회사명:%s | "
//								+ "지역:%s | "
//								+ "직무:%s\n",
						board.getEmploymentBoardId(),
						board.getTitle()
//						board.getJobType(),
//						board.getCareer(),
//						board.getHiringProcess(),
//						board.getQualifications(),
//						board.getPreferred(),
//						board.getCompanyName(),
//						convertCategoryIdToName(board.getSubCategory1Id()),
//						convertCategoryIdToName(board.getSubCategory2Id())
				);
			}
		}
	}
}

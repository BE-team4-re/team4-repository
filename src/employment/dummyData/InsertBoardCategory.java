package src.employment.dummyData;


import src.employment.boardDTO.BoardCategoryEnum;
import src.employment.recordDAO.employmentBoardCategory.CrateCategoryDAO;


public class InsertBoardCategory {
	
	public static void main(String[] args) {
		
		CrateCategoryDAO empCateCrateCategoryDAO = new CrateCategoryDAO();
		
		BoardCategoryEnum[] values = BoardCategoryEnum.values();
		for (BoardCategoryEnum value: values) {
			empCateCrateCategoryDAO.create(value.getMainId(), value.getSubId(), value.getCategoryName()); // 메인
		}
	}
}

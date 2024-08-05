package src.employment.test;


import src.employment.board.BoardCategoryEnum;
import src.employment.recordDAO.employmentBoardCategory.create.*;


public class TestInsertBoardCategory<T> {
	
	public static void main(String[] args) {
		
		CrateCategoryDAO empCateCrateCategoryDAO = new CrateCategoryDAO();
		
		BoardCategoryEnum[] values = BoardCategoryEnum.values();
		for (BoardCategoryEnum value: values) {
			empCateCrateCategoryDAO.create(value.getMainId(), value.getSubId(), value.getCategoryName()); // 메인
		}
	}
}

package src.employment.test;

import java.util.List;
import java.util.ArrayList;

import src.employment.recordDAO.EmploymentBoardCategoryDAO;

public class TestInsertBoardCategory<T> {
	private List<T>[] DummyDataArray = new List[18];
	private List<T> DummyDataList = new ArrayList<>();
	
	public static void main(String[] args) {
//		(1, 1, '지역별'),
//		(1, 2, '서울'),
//		(1, 3, '경기'),
//		(1, 4, '강원'),
//		(1, 5, '충북'),
//		(1, 6, '충남'),
//		(1, 7, '전북'),
//		(1, 8, '전남'),
//		(1, 9, '경북'),
//		(1, 10, '경남'),
//		(1, 11, '제주'),
//		(12, 12, '직무별'),
//		(12, 13, '프론트엔드'),
//		(12, 14, '백엔드'),
//		(12, 15, '풀스택'),
//		(12, 16, '데이터분석'),
//		(12, 17, '임베디드'),
//		(12, 18, '기타');
		EmploymentBoardCategoryDAO empCateDAO = new EmploymentBoardCategoryDAO();
		empCateDAO.insertEmploymentBoardCategoryToDB(9999, 9999, "테스트");
	}
}

package src.employment.test;

import src.employment.recordDAO.*;

public class TestInsertEmploymentBoard {

	public static void main(String[] args) {
		// 테스트용 더미 데이터를 넣는 코드.
		EmploymentBoardDAO dao = new EmploymentBoardDAO();
		// 제목, 근무형태, 요구학력, 채용절차, 자격요건, 우대사항, 메인1, 메인2, 서브1, 서브2
		dao.insertEmploymentBoardToDB("제목입니다.", "정규직", "무관", "무관", "없음", "없음", 1, 2, 12, 15);

	}

}

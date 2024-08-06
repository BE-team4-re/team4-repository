package src.employment;


public class EmploymentBoardMain {
	// 첫 조회작업 수행시 전체 채용 게시글 데이터가 BoardList 에 들어가고 프로그램이 끝날 때까지 활용됨.
	public static void entrance() {
		Controller controller = new Controller();
		controller.printMain();
	}
}

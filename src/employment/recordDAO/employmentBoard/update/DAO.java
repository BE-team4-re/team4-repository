package src.employment.recordDAO.employmentBoard.update;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Scanner;

import src.database.Database;
import src.employment.board.BoardDTO;
import src.util.Response;

import static src.employment.test.TestMain.BoardList;


public class DAO {
	
private Database db = new Database();
	// 업데이트 타겟 지정 시 여기서 그 타겟이 설정됨.
	// 내 구현력의 한계에 화난다.
	public static int updateTarget = 0;
	private Connection conn = null;
	private PreparedStatement pstmt = null;

	// switch, case 여러번 쓰기 싫어서 만든 변수들.
	private String title = null;
	private String jobType = null;
	private String career = null;
	private String hiringProcess = null;
	private String qualifications = null;
	private String preferred = null;
	private String companyName = null;
	private int mainCategory1Id = 0;
	private int mainCategory2Id = 0;
	private int subCategory1Id = 0;
	private int subCategory2Id = 0;

	public Response<BoardDTO> update(int bid, String userInput) {

		Response<BoardDTO> response = null;

		String target = null;
		String content = null;
		String baseSql = "update employment_board set ";

		Scanner sc = new Scanner(System.in);
		boolean breakFlag = false;

		while (!breakFlag) {
			System.out.print("바꿀 내용을 입력해주세요: ");
			content = sc.nextLine();

			switch (userInput) {
				case "t" -> {
					target = "title=?";
					title = content;
					breakFlag = true;
				}
				case "j" -> {
					target = "jobType=?";
					jobType = content;
					breakFlag = true;
				}
				case "c" -> {
					target = "career=?";
					career = content;
					breakFlag = true;
				}
				case "h" -> {
					target = "hiringProcess=?";
					hiringProcess = content;
					breakFlag = true;
				}
				case "q" -> {
					target = "qualifications=?";
					qualifications = content;
					breakFlag = true;
				}
				case "p" -> {
					target = "preferred=?";
					preferred = content;
					breakFlag = true;
				}
				case "1" -> {
					target = "main_category1_id=?";
					mainCategory1Id = Integer.parseInt(content);
					breakFlag = true;
				}
				case "2" -> {
					target = "main_category2_id=?";
					mainCategory2Id = Integer.parseInt(content);
					breakFlag = true;
				}
				case "3" -> {
					target = "sub_category1_id=?";
					subCategory1Id = Integer.parseInt(content);
					breakFlag = true;
				}
				case "4" -> {
					target = "sub_category2_id=?";
					subCategory2Id = Integer.parseInt(content);
					breakFlag = true;
				}
				case "n" -> {
					target = "company_name=?";
					companyName = content;
					breakFlag = true;
				}
				case "b" -> {
					breakFlag = true;
				}
				default -> {
					System.out.println("다시 입력해주세요.");
				}
			};
		}

		String fullSql = baseSql + target + "where employment_board_id=?;";

		try {
			
			conn = db.connect();
			pstmt = conn.prepareStatement(fullSql);

			pstmt.setString(1, content);
			pstmt.setInt(2, bid);

			int rows = pstmt.executeUpdate();
			BoardDTO resultBoardDTO = null;
			if (rows == 1) { // 성공적으로 db의 값이 바뀌면,
				for (BoardDTO board: BoardList) {
					if (board.getEmploymentBoardId() == bid) {
						board.setTitle(title);
						board.setJobType(jobType);
						board.setCareer(career);
						board.setHiringProcess(hiringProcess);
						board.setQualifications(qualifications);
						board.setPreferred(preferred);
						board.setCompanyName(companyName);
						board.setMainCategory1Id(mainCategory1Id);
						board.setMainCategory2Id(mainCategory2Id);
						board.setSubCategory1Id(subCategory1Id);
						board.setSubCategory2Id(subCategory2Id);
						resultBoardDTO = board;
					}
				}
				// System.out.println("성공적으로 저장되었습니다.");
				response = new Response<BoardDTO>(true, "성공적으로 저장되었습니다.", resultBoardDTO);
			} else {
				// System.out.println("저장에 실패하였습니다.");
				response = new Response<BoardDTO>(false, "저장에 실패하였습니다.", resultBoardDTO);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {					
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return response;
	}
}

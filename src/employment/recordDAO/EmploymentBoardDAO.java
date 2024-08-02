package src.employment.recordDAO;


import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import java.util.List;
import java.util.ArrayList;

import src.employment.board.BoardObject;

import src.database.Database;


public class EmploymentBoardDAO {

	private Database db = new Database();
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public EmploymentBoardDAO() {
		conn = db.connect();
	}
	
	// select * from employment_board
	public List<BoardObject> selectAllEmploymentBoardsFromDB() {
		
		List<BoardObject> EmploymentBoardList = new ArrayList<>();
		String sql = ""+
		"select * from employment_board";

		try {
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				int employmentBoardId = rs.getInt("employment_board_id");
				String title = rs.getString("title");
				String jobType = rs.getString("job_type");
				String career = rs.getString("career");
				String hiringProcess = rs.getString("hiring_process");
				String qualifications = rs.getString("qualifications");
				String preferred = rs.getString("preferred");
				int mainCategory1Id = rs.getInt("main_category1_id");
				int mainCategory2Id = rs.getInt("main_category2_id");
				int subCategory1Id = rs.getInt("sub_category1_id");
				int subCategory2Id = rs.getInt("sub_category2_id");
				
				BoardObject board = new BoardObject(
					employmentBoardId, title, jobType, career, hiringProcess,
					qualifications, preferred, mainCategory1Id,
					mainCategory2Id, subCategory1Id, subCategory2Id
				);
				
				EmploymentBoardList.add(board);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
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
		
		return EmploymentBoardList;
	
	}
	
	// insert into employment_board ... values ...
	public void insertEmploymentBoardToDB(
			String title, String jobType, String career,
			String hiringProcess, String qualifications, String preferred,
			int mainCategory1Id, int mainCategory2Id, int subCategory1Id, int subCategory2Id) {
		// employment_board_id -> 자동 증가 컬럼.
		String sql = ""+
		"insert into employment_board ("
		+ "title,"
		+ "job_type,"
		+ "career,"
		+ "hiring_process,"
		+ "qualifications,"
		+ "preferred,"
		+ "main_category1_id,"
		+ "main_category2_id,"
		+ "sub_category1_id,"
		+ "sub_category2_id"
		+ ") values ("
		+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?"
		+ ");";
		
		try {
					
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1, title);
			pstmt.setString(2, jobType);
			pstmt.setString(3, career);
			pstmt.setString(4, hiringProcess);
			pstmt.setString(5, qualifications);
			pstmt.setString(6, preferred);
			pstmt.setInt(7, mainCategory1Id);
			pstmt.setInt(8, mainCategory2Id);
			pstmt.setInt(9, subCategory1Id);
			pstmt.setInt(10, subCategory1Id);
			
			int rows = pstmt.executeUpdate();
			if (rows == 1) {
				if (rows == 1) {
					System.out.println("성공적으로 저장되었습니다.");
				} else {
					System.out.println("저장에 실패하였습니다.");
				}
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
	}
	
	// 구현중 -> 빨간줄 싫어서 주석처리함
	// update
//	public int updateEmploymentBoardInDB(
//			int bno, String title, String jobType, String career,
//			String hiringProcess, String qualifications, String preferred,
//			int mainCategory1Id, int mainCategory2Id, int subCategory1Id, int subCategory2Id) {
//		
//		String sql = ""+
//		"update employment_board set "
//				
//		
//		return 1;
//	}
//	
//	// delete
//	public int deleteEmploymentBoardInDB(int bno) {
//		
//	}
	
}

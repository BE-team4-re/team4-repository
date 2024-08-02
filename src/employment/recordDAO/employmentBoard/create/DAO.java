package src.employment.recordDAO.employmentBoard.create;


import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import src.database.Database;


public class DAO {

	private Database db = new Database();
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	
	// insert into employment_board ... values ...
	public void create(
			String title, String jobType, String career,
			String hiringProcess, String qualifications, String preferred,
			int mainCategory1Id, int mainCategory2Id, int subCategory1Id,
			int subCategory2Id, String companyName) {
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
		+ "sub_category2_id,"
		+ "company_name"
		+ ") values ("
		+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?"
		+ ");";
		
		try {
			
			conn = db.connect();
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
			pstmt.setInt(10, subCategory2Id);
			pstmt.setString(11, companyName);
			
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
}

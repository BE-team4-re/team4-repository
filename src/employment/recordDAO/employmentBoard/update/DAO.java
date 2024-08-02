package src.employment.recordDAO.employmentBoard.update;


import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import src.database.Database;


public class DAO {
	
private Database db = new Database();
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	
	public int update(
			int bno, String title, String jobType, String career,
			String hiringProcess, String qualifications, String preferred,
			int mainCategory1Id, int mainCategory2Id, int subCategory1Id,
			int subCategory2Id, String companyName) {
		
		int responseCode = 0;
		
		String sql = ""+
				"update employment_board set "
				+ "title=?,"
				+ "jobType=?,"
				+ "career=?,"
				+ "hiringProcess=?,"
				+ "qualifications=?,"
				+ "preferred=?,"
				+ "main_category1_id=?,"
				+ "main_category2_id=?,"
				+ "sub_category1_id=?,"
				+ "sub_category2_id=?"
				+ "company_name=?"
				+ "where employment_board_id=?;";
		
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
			pstmt.setInt(12, bno);
			
			int rows = pstmt.executeUpdate();
			if (rows == 1) {
				if (rows == 1) {
					// System.out.println("성공적으로 저장되었습니다.");
					responseCode = 1;
				} else {
					// System.out.println("저장에 실패하였습니다.");
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
		return responseCode;
	}
}

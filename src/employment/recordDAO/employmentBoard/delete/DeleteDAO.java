package src.employment.recordDAO.employmentBoard.delete;


import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import src.database.Database;


public class DeleteDAO {

	private Database db = new Database();
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	
	// delete
	public int delete(int bno) {
		
		int responseCode = 0;
		
		String sql = "delete from employment_board where employment_board_id=?";
		
		try {
			
			conn = db.connect();
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setInt(1, bno);
			
			int rows = pstmt.executeUpdate();

			if (rows == 1) {
				// System.out.println("성공적으로 저장되었습니다.");
				responseCode = 1;
			} else {
				// System.out.println("저장에 실패하였습니다.");
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

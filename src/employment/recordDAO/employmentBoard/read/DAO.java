package src.employment.recordDAO.employmentBoard.read;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import java.util.List;
import java.util.ArrayList;

import src.employment.board.BoardObject;

import src.database.Database;


public class DAO {

	private Database db = new Database();
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	// select * from employment_board
	public List<BoardObject> read() {
		
		List<BoardObject> EmploymentBoardList = new ArrayList<>();
		String sql = ""+
		"select * from employment_board";

		try {
			conn = db.connect();
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
				String companyName = rs.getString("company_name");
				
				BoardObject board = new BoardObject(
					employmentBoardId, title, jobType, career, hiringProcess,
					qualifications, preferred, mainCategory1Id, mainCategory2Id,
					subCategory1Id, subCategory2Id, companyName
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

}
package src.employment.recordDAO.employmentBoardCategory.read;


import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import java.util.ArrayList;
import java.util.List;

import src.database.Database;

import src.employment.board.BoardCategory;


public class DAO {
	
	private Database db = new Database();
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	// select * from employment_board_category
	public List<BoardCategory> read() {
		
		List<BoardCategory> EmploymentBoardCategoryList = new ArrayList<>();
		String sql = ""+
		"select * from employment_board_category";

		try {
			
			conn = db.connect();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				int categoryId = rs.getInt("category_id");
				int maincategoryId = rs.getInt("maincategory_id");
				int subcategoryId = rs.getInt("subcategory_id");
				String categoryName = rs.getString("category_name");
				
				BoardCategory boardCategory = new BoardCategory(
					categoryId, maincategoryId, subcategoryId, categoryName
				);
				
				EmploymentBoardCategoryList.add(boardCategory);
				
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
		return EmploymentBoardCategoryList;		
	}
}
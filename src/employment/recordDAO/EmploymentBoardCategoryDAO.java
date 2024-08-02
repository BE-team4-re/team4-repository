package src.employment.recordDAO;


import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import java.util.ArrayList;
import java.util.List;

import src.database.Database;

import src.employment.board.BoardCategory;


public class EmploymentBoardCategoryDAO {
	
	private Database db = new Database();
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public EmploymentBoardCategoryDAO() {
		conn = db.connect();
	}
	
	// select * from employment_board_category
	public List<BoardCategory> selectAllEmploymentBoardCategoryFromDB() {
		
		List<BoardCategory> EmploymentBoardCategoryList = new ArrayList<>();
		String sql = ""+
		"select * from employment_board_category";

		try {
			
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
	
	// insert into employment_board_category ... values ...
	public void insertEmploymentBoardCategoryToDB(int maincategoryId, int subcategoryId, String categoryName) {
		
		// employment_board_id -> 자동 증가 컬럼.
		String sql = ""+
		"insert into employment_board_category ("
		+ "maincategory_id,"
		+ "subcategory_id,"
		+ "category_name"
		+ ") values ("
		+ "?, ?, ?"
		+ ");";
		
		try {
					
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, maincategoryId);
			pstmt.setInt(2, subcategoryId);
			pstmt.setString(3, categoryName);
			
			int rows = pstmt.executeUpdate();
			if (rows == 1) {
				System.out.println("성공적으로 저장되었습니다.");
			} else {
				System.out.println("저장에 실패하였습니다.");
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

package src.employment.recordDAO.employmentBoardCategory;


import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import java.util.ArrayList;
import java.util.List;

import src.database.Database;

import src.employment.boardDTO.BoardCategoryDTO;


public class ReadCategoryDAO {
	
	private Database db = new Database();
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	// select * from employment_board_category
	public List<BoardCategoryDTO> read() {
		
		List<BoardCategoryDTO> employmentBoardCategoryDTOList = new ArrayList<>();
		String sql = "select * from employment_board_category";

		try {
			
			conn = db.connect();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				int categoryId = rs.getInt("category_id");
				int maincategoryId = rs.getInt("maincategory_id");
				int subcategoryId = rs.getInt("subcategory_id");
				String categoryName = rs.getString("category_name");
				
				BoardCategoryDTO boardCategoryDTO = new BoardCategoryDTO(
					categoryId, maincategoryId, subcategoryId, categoryName
				);
				
				employmentBoardCategoryDTOList.add(boardCategoryDTO);
				
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
		return employmentBoardCategoryDTOList;
	}
}
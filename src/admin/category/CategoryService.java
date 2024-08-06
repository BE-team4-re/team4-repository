package src.admin.category;

import src.database.Database;
import src.employment.board.BoardCategoryDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryService {
    private final Database db = new Database();

    //전체 채용 카테고리 리스트로 가져오기
    public List<BoardCategoryDTO> getCategoryList(){
        List<BoardCategoryDTO> boardCategoryDTOList = new ArrayList<>();
        try(Connection connection = db.connect();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM employment_board_category");)
        {
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                BoardCategoryDTO boardCategoryDTO = new BoardCategoryDTO(
                        rs.getInt("category_id"),
                        rs.getInt("mainCategory_id"),
                        rs.getInt("subCategory_id"),
                        rs.getString("category_name")
                );
                boardCategoryDTOList.add(boardCategoryDTO);
            }
            rs.close();
        }
        catch (Exception e ){
            e.printStackTrace();
        }
        return boardCategoryDTOList;
    }

    //카테고리 업데이트
    public int updateCategory(int categoryId, String categoryName ){
        int count = 0;
        try(Connection connection = db.connect();
            PreparedStatement ps = connection.prepareStatement("UPDATE employment_board_category SET category_name = ? WHERE category_id = ?");)
        {
            ps.setString(1, categoryName);
            ps.setInt(2, categoryId);

            count = ps.executeUpdate();
            if (count == 1 ){
                System.out.println("수정에 성공하였습니다.");
            }else{
                System.out.println("존재하지 않는 category_id입니다.");
            }

            return count;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }

    //카테고리 추가
    public int insertCategory(String categoryName, int mainCategoryId){
        int newSubCategoryId = 0;

        String selectAutoIncrementQuery = "SELECT AUTO_INCREMENT FROM information_schema.TABLES " +
                "WHERE TABLE_SCHEMA = 'miniProject4' " +
                "AND TABLE_NAME = 'employment_board_category'";

        String insertQuery = "INSERT INTO employment_board_category (maincategory_id, subcategory_id, category_name) VALUES (?,?,?)";

        try (Connection connection = db.connect();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(selectAutoIncrementQuery)) {

            if (rs.next()) {
                newSubCategoryId = rs.getInt("AUTO_INCREMENT");
            }

            try (PreparedStatement ps = connection.prepareStatement(insertQuery)) {
                ps.setInt(1, mainCategoryId);
                ps.setInt(2, newSubCategoryId);
                ps.setString(3, categoryName);

                int rows = ps.executeUpdate();
                if (rows == 1) {
                    System.out.println("정상적으로 삽입되었습니다.");
                }
                return rows;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}

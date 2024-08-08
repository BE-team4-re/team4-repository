package src.admin.category;

import src.database.Database;
import src.employment.board.BoardCategoryDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryService {
    private final Database db = new Database();


    //채용 카테고리 리스트로 가져오기
    public List<BoardCategoryDTO> getDetailCategoryList(int categoryNum){
        List<BoardCategoryDTO> boardCategoryDTOList = new ArrayList<>();
        try(Connection connection = db.connect();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM employment_board_category WHERE mainCategory_id = ?");)
        {
            ps.setInt(1, categoryNum);
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
    public void insertCategory(String categoryName, int mainCategoryId) {
        String maxCategoryIdQuery = "SELECT MAX(category_id) AS max_id FROM employment_board_category";
        String insertQuery = "INSERT INTO employment_board_category (maincategory_id, subcategory_id, category_name) VALUES (?, ?, ?)";
        int maxId = 0;

        try (Connection connection = db.connect();
             PreparedStatement psMax = connection.prepareStatement(maxCategoryIdQuery);
             ResultSet rs = psMax.executeQuery()) {

            // Move cursor to the first row of the result set
            if (rs.next()) {
                maxId = rs.getInt("max_id") + 1;
            }

            try (PreparedStatement psInsert = connection.prepareStatement(insertQuery)) {
                psInsert.setInt(1, mainCategoryId);
                psInsert.setInt(2, maxId);
                psInsert.setString(3, categoryName);
                psInsert.executeUpdate();
            }
            System.out.println("카테고리 추가가 완료되었습니다.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

package src.communication.communicationCategory;

import src.communication.communicationCategory.dto.FindCommunicationBoardCategoryDto;
import src.communication.communicationCategory.repository.CommunicationBoardCategoryRepository;
import src.database.Database;
import src.util.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CommunicationCategoryService {
    private final Database database = new Database();
    private final CommunicationBoardCategoryRepository sql = new CommunicationBoardCategoryRepository();

    // 커뮤니티 게시물 카테고리 불러온다.
    public Response<List<FindCommunicationBoardCategoryDto>> findAll(){
        List<FindCommunicationBoardCategoryDto> categoryList = new ArrayList<>();
        boolean success = true;
        String message = "카테고리 조회에 성공했습니다.";
        try(
            Connection conn = database.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql.findAll());
            ResultSet rs = pstmt.executeQuery();
            ){
            if(!rs.isBeforeFirst()){
                throw new RuntimeException("커뮤니티 카테고리가 없습니다. 카테고리 추가해주세요.");
            }
            while(rs.next()){
                int categoryId = rs.getInt("category_id");
                String category = rs.getNString("category");
                categoryList.add(new FindCommunicationBoardCategoryDto(categoryId,category));
            }
        }catch (SQLException e){
            success = false;
            message = "조회가 안됩니다. 다시 시도해주세요.";
        }catch(RuntimeException error) {
            success = false;
            message = error.getLocalizedMessage();
        }
        return new Response<>(success, message, categoryList);
    }

    public boolean create(String category) {
        try(
            Connection conn = database.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql.create());
            ){
            pstmt.setString(1, category);
            int row = pstmt.executeUpdate();
            if(row != 1) throw new SQLException();
            System.out.println("카테고리 등록 성공");
            return true;
        }catch (SQLException e) {
            System.out.println("카테고리 등록 실패");
            return false;
        }
    }

    public boolean update(int categoryId, String updateCategory) {
        try(
            Connection conn = database.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql.update());
            ){
            pstmt.setString(1, updateCategory);
            pstmt.setInt(2, categoryId);
            int row = pstmt.executeUpdate();
            if(row != 1) throw new SQLException();
            System.out.println("카테고리 수정 성공");
            return true;
        }catch (SQLException e){
            System.out.println("카테고리 수정 실패");
            return false;
        }
    }
}

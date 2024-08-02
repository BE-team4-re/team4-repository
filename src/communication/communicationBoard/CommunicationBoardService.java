package src.communication.communicationBoard;

import src.communication.communicationBoard.dto.CreateCommunicationBoardDto;
import src.communication.communicationBoard.dto.UpdateCommunicationBoardDto;
import src.communication.communicationBoard.repository.CommunicationBoardRepository;
import src.database.Database;
import src.util.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CommunicationBoardService {
    private final Database database = new Database();
    private final CommunicationBoardRepository cbSQL = new CommunicationBoardRepository();


    public Response<Integer> create(CreateCommunicationBoardDto createCommunitionBoardDto) {
        boolean success = true;
        String message = "커뮤니케이션 게시물 등록 성공했습니다.";
        try(
            Connection conn = database.connect();
            PreparedStatement pstmt = conn.prepareStatement(cbSQL.create());
        ){
            pstmt.setString(1,createCommunitionBoardDto.title());
            pstmt.setString(2, createCommunitionBoardDto.content());
            pstmt.setInt(3, createCommunitionBoardDto.id());
            pstmt.setInt(4, createCommunitionBoardDto.categodyId());
            int rows = pstmt.executeUpdate();
            if(rows != 1) throw new SQLException();
        }catch (SQLException e){
            success = false;
            message = "커뮤니케이션 게시물 등록에 실패했습니다. 다시 시도해주세요.";
        }
        return new Response<>(success,message,1);
    }
    public  Response<Integer> updateCommunicationBoard(int communicationBoardId, String title, String content){
        boolean success = true;
        String message = "커뮤니케이션 게시물 수정을 성공했습니다.";
        try(
            Connection conn = database.connect();
            PreparedStatement pstmt = conn.prepareStatement(cbSQL.update());
            ){
            pstmt.setString(1,title);
            pstmt.setString(2,content);
            pstmt.setInt(3,communicationBoardId);
            int row = pstmt.executeUpdate();
            if(row == 0) throw new SQLException();
        }catch (SQLException e){
            success = false;
            message = "커뮤니케이션 게시물의 수정을 실패했습니다.";
        }
        return new Response<>(success, message,1);
    }
    public Response<UpdateCommunicationBoardDto> findCommunicationIdByCommunicationBoard(int communicationBoardId){
        boolean success = true;
        String message = "커뮤니케이션 게시물 조회를 성공했습니다.";
        UpdateCommunicationBoardDto updateCommunicationBoardDto = null;
        try(
            Connection conn = database.connect();
            PreparedStatement pstmt = conn.prepareStatement(cbSQL.findOne());
            ){
            pstmt.setInt(1,communicationBoardId);
            ResultSet rs = pstmt.executeQuery();
            if(!rs.isBeforeFirst()){
                throw new SQLException();
            }

            while(rs.next()){
                String title = rs.getString("title");
                String content = rs.getString("content");
                updateCommunicationBoardDto = new UpdateCommunicationBoardDto(title,content);
            }
        }catch (SQLException e){
            message = "커뮤니티 게시물의 조회를 실패했습니다.";
            System.out.println("없다면???");
            success = false;
        }
        return new Response<>(success, message, updateCommunicationBoardDto);
    }

    public Response<Integer> delete(int communicationBoardId) {
        boolean success = true;
        String message = "삭제 성공";
        try(
            Connection conn = database.connect();
            PreparedStatement pstmt = conn.prepareStatement(cbSQL.delete());
            ){
            pstmt.setInt(1, communicationBoardId);
            int row = pstmt.executeUpdate();
            if(row == 0) throw new SQLException();
        }catch (SQLException e){
            success = false;
            message = "삭제에 실패했습니다.";
        }
        return new Response<>(success, message, 1);
    }
}

package src.communication.communicationComment;

import src.communication.communicationComment.dto.CreateCommunicationBoardCommentDto;
import src.communication.communicationComment.dto.CreateCommunicationBoardReCommentDto;
import src.communication.communicationComment.dto.FindCommunicationBoardCommentDto;
import src.communication.communicationComment.repository.CommunicationBoardCommentRepository;
import src.database.Database;
import src.util.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CommunicationBoardCommentService {
    private final CommunicationBoardCommentRepository cbcSQL;
    private final Database database;
    public CommunicationBoardCommentService(){
        this.cbcSQL = new CommunicationBoardCommentRepository();
        this.database = new Database();
    }
    // 댓글 생성
    public Response<Integer> createComment(CreateCommunicationBoardCommentDto createCommunicationBoardComment){
        boolean success = true;
        String message = "등록에 성공했습니다.";
        try(
            Connection conn = database.connect();
            PreparedStatement pstmt = conn.prepareStatement(cbcSQL.createComment());
            ){
            pstmt.setString(1,createCommunicationBoardComment.comment());
            pstmt.setInt(2, createCommunicationBoardComment.communicationBoardId());
            pstmt.setInt(3, createCommunicationBoardComment.id());
            int row = pstmt.executeUpdate();
            if(row != 1) throw new SQLException();
        }catch (SQLException e){
            success = false;
            message = "등록에 실패했습니다.";
        }
        return new Response<Integer>(success,message,1);
    }

    // 대댓글 생성
    public Response<Integer> createReComment(CreateCommunicationBoardReCommentDto createCommunicationBoardReComment){
        boolean success = true;
        String message = "등록에 성공했습니다.";
        try(
            Connection conn = database.connect();
            PreparedStatement pstmt = conn.prepareStatement(cbcSQL.createReComment());
            ){
            pstmt.setString(1, createCommunicationBoardReComment.reComment());
            pstmt.setInt(2,createCommunicationBoardReComment.commentId());
            pstmt.setInt(3,createCommunicationBoardReComment.communicationBoardId());
            pstmt.setInt(4,createCommunicationBoardReComment.id());
            int row = pstmt.executeUpdate();
            if(row != 1) throw new SQLException();
        }catch (SQLException e){
            success = false;
            message = "등록에 실패했습니다.";
        }
        return new Response<Integer>(success, message, 1);
    }

    // 댓글 대댓글 수정
    public Response<Integer> update(int updateCommentId, String updateComment) {
        boolean success = true;
        String message = "댓글 수정 완료";
        try(
            Connection conn = database.connect();
            PreparedStatement pstmt = conn.prepareStatement(cbcSQL.updateComment());
            ){
            pstmt.setString(1, updateComment);
            pstmt.setInt(2,updateCommentId);
            int row = pstmt.executeUpdate();
            if(row != 1) throw new SQLException();
        }catch (SQLException e){
            success = false;
            message = "댓글 수정 실패";
        }
        return new Response<>(success, message, 1);
    }

    // 댓글 대댓글 삭제
    public Response<Integer> delete(int commentId) {
        boolean success = true;
        String message = "삭제성공";
        try(
            Connection conn = database.connect();
            PreparedStatement pstmt = conn.prepareStatement(cbcSQL.deleteComment());
            ){
            pstmt.setInt(1, commentId);
            int row = pstmt.executeUpdate();
            if(row != 1) throw new SQLException();
        }catch(SQLException e){
            success = false;
            message = "삭제 실패";
        }
        return new Response<Integer>(success,message,1);
    }
}

package src.communication.communicationBoard;

import src.communication.communicationBoard.dto.*;
import src.communication.communicationBoard.repository.CommunicationBoardRepository;
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

public class CommunicationBoardService {
    private final Database database = new Database();
    private final CommunicationBoardRepository cbSQL = new CommunicationBoardRepository();
    private final CommunicationBoardCommentRepository cbcSQL = new CommunicationBoardCommentRepository();

    // 커뮤니티 게시물 생성
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
    // 커뮤니티 게시물 수정
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
            if(row != 1) throw new SQLException();
        }catch (SQLException e){
            success = false;
            message = "커뮤니케이션 게시물의 수정을 실패했습니다.";
        }
        return new Response<>(success, message,1);
    }
    // 하나의 게시물 내용을 가져온다.
    public Response<FindOneCommunicationBoardDto> findCommunicationIdByCommunicationBoard(int communicationBoardId){
        boolean success = true;
        String message = "커뮤니케이션 게시물 조회를 성공했습니다.";
        FindOneCommunicationBoardDto findOneCommunicationBoardDto = null;
        List<FindCommunicationBoardCommentDto> commentList = new ArrayList<>();
        try(
            Connection conn = database.connect();
            PreparedStatement pstmt = conn.prepareStatement(cbSQL.findOne());
            ){
            pstmt.setInt(1,communicationBoardId);
            try(ResultSet rs = pstmt.executeQuery()){
                if(!rs.isBeforeFirst()) throw new SQLException();
                while(rs.next()){
                    int findCommunicationBoardId = rs.getInt("communicationboard_id");
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    String userId = rs.getString("userId");
                    int id = rs.getInt("id");
                    int categoryId = rs.getInt("category_id");
                    findOneCommunicationBoardDto = new FindOneCommunicationBoardDto(findCommunicationBoardId, title, content, userId, id, categoryId);
                }
            }
        }catch (SQLException e){
            message = "커뮤니티 게시물의 조회를 실패했습니다.";
            success = false;
        }
        FindOneCommnicationBoardNCommentDto findOneCommnicationBoardNCommentDto = new FindOneCommnicationBoardNCommentDto(findOneCommunicationBoardDto, commentList);
        return new Response<>(success, message, findOneCommunicationBoardDto);
    }

    // 하나의 게시물과 댓글과 대댓글을 가져온다.
    public Response<FindOneCommnicationBoardNCommentDto> findOneCommunicationBoardIdByBoardComment(int communicationBoardId){
        boolean success = true;
        String message = "커뮤니케이션 게시물 조회를 성공했습니다.";
        FindOneCommunicationBoardDto findOneCommunicationBoardDto = null;
        List<FindCommunicationBoardCommentDto> commentList = new ArrayList<>();
        try(
            Connection conn = database.connect();
            PreparedStatement pstmt = conn.prepareStatement(cbSQL.findOne());
            PreparedStatement pstmtComment = conn.prepareStatement(cbcSQL.findComment());
        ){
            pstmt.setInt(1,communicationBoardId);
            pstmtComment.setInt(1, communicationBoardId);
            try(ResultSet rs = pstmt.executeQuery()){
                if(!rs.isBeforeFirst()) throw new SQLException();
                while(rs.next()){
                    int findCommunicationBoardId = rs.getInt("communicationboard_id");
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    String userId = rs.getString("userId");
                    int id = rs.getInt("id");
                    int categoryId = rs.getInt("category_id");
                    findOneCommunicationBoardDto = new FindOneCommunicationBoardDto(findCommunicationBoardId, title, content, userId, id, categoryId);
                }
            }
            try(ResultSet rs = pstmtComment.executeQuery()){
                while (rs.next()){
                    int commentId = rs.getInt("comment_id");
                    String comment = rs.getString("comment");
                    String userId =rs.getString("userid");
                    int parentId = rs.getInt("parent_id");
                    int id = rs.getInt("id");
                    commentList.add(new FindCommunicationBoardCommentDto(commentId,comment,userId, parentId, id));
                }
            }
        }catch (SQLException e){
            message = "커뮤니티 게시물의 조회를 실패했습니다.";
            success = false;
        }
        FindOneCommnicationBoardNCommentDto findOneCommnicationBoardNCommentDto = new FindOneCommnicationBoardNCommentDto(findOneCommunicationBoardDto, commentList);
        return new Response<>(success, message, findOneCommnicationBoardNCommentDto);
    }

    // 커뮤니티 게시물 삭제
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

    // 카테고리만 들어온다. 검색어는 공백.
    // 카테고리랑 검색어가 들어온다.
    // 1. 전체보기 2. 카테고리별 보기
    public Response<PagenationCommunicationBoardDto> searchCommunicationBoard(int communicationCategoryId, int page, String searchWord){
        String sql;
        String countSql;
        boolean success = true;
        String message = "검색성공";
        int communicationBoardCount = 0;
        List<Object> params = new ArrayList<>();
        List<SearchCommunicationBoardDto> searchCommunicationBoardDtoList = new ArrayList<>();
        PagenationCommunicationBoardDto resultCommunicationBoard = null;

        if(communicationCategoryId == 0){
            if(searchWord.isEmpty()){ //전체 게시물 검색 params(offset만)
                sql = cbSQL.findAll();
                countSql = cbSQL.findAllCount();
            }else{ // 전체게시물에서 겅색어로 검색 params(검색어, offset)
                sql = cbSQL.findAllWordByCommunicationBoard();
                countSql = cbSQL.findAllWordByCommunicationBoardCount();
                params.add("%" + searchWord + "%");
            }
        }else{ // 카테고리 선택
            if(searchWord.isEmpty()){ //카테고리별 전체 검색 params(카테고리id, offset)
                sql = cbSQL.findCategoryByCommunicationBoard();
                countSql = cbSQL.findCategoryByCommunicationBoardCount();
                params.add(communicationCategoryId);
            }else{ //카테고리와 검색어로 검색 params(카테고리id, 검색어, offset)
                sql = cbSQL.findCategoryNSearchWordByCommunicationBoard();
                countSql = cbSQL.findCategoryNSearchWordByCommunicationBoardCount();
                params.add(communicationCategoryId);
                params.add("%" + searchWord + "%");
            }
        }
        try(
            Connection conn = database.connect();
            PreparedStatement pstmtS = conn.prepareStatement(sql);
            PreparedStatement pstmtC = conn.prepareStatement(countSql);
            ){
            for(int i = 0; i < params.size(); i++){
                pstmtC.setObject(i+1, params.get(i));
            }

            ResultSet rsC = pstmtC.executeQuery();
            if(!rsC.isBeforeFirst())  throw new SQLException();
            // 조건의 맞는 커뮤니케이션 게시물의 총갯수
            if(rsC.next()) communicationBoardCount = rsC.getInt("count");
            int totalPage = (int)Math.ceil((double) communicationBoardCount / 5);
            boolean nextPageCheck = page < totalPage;
            params.add((page - 1) * 5);
            for(int i = 0; i < params.size(); i++){
                pstmtS.setObject(i + 1, params.get(i));
            }
            ResultSet rsS = pstmtS.executeQuery();
            if(!rsS.isBeforeFirst()) throw new SQLException();
            while(rsS.next()){
                searchCommunicationBoardDtoList.add(new SearchCommunicationBoardDto(
                   rsS.getInt("communicationboard_id"), rsS.getString("title"), rsS.getString("userId")
                ));
            }
            resultCommunicationBoard = new PagenationCommunicationBoardDto(totalPage,searchCommunicationBoardDtoList);
        }catch (SQLException e){
            success = false;
            message = "검색실패";
        }
       return new Response<PagenationCommunicationBoardDto>(success, message, resultCommunicationBoard);
    }
}

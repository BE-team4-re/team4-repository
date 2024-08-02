package src.communication.communicationBoard;

import src.communication.communicationBoard.dto.CreateCommunicationBoardDto;
import src.communication.communicationBoard.dto.PagenationCommunicationBoardDto;
import src.communication.communicationBoard.dto.SearchCommunicationBoardDto;
import src.communication.communicationBoard.dto.UpdateCommunicationBoardDto;
import src.communication.communicationBoard.repository.CommunicationBoardRepository;
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
            try(ResultSet rs = pstmt.executeQuery()){
                if(!rs.isBeforeFirst()) throw new SQLException();
                while(rs.next()){
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    updateCommunicationBoardDto = new UpdateCommunicationBoardDto(title,content);
                }
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
            System.out.println(sql);
            for(Object pp : params){
                System.out.println( "파람 안 => " +pp);
            }
            ResultSet rsS = pstmtS.executeQuery();
            if(!rsS.isBeforeFirst()) throw new SQLException();
            while(rsS.next()){
                searchCommunicationBoardDtoList.add(new SearchCommunicationBoardDto(
                   rsS.getInt("communicationboard_id"), rsS.getString("title"), rsS.getString("userId")
                ));
            }
            searchCommunicationBoardDtoList
                .forEach(list -> System.out.println(list.title()));
            resultCommunicationBoard = new PagenationCommunicationBoardDto(totalPage,searchCommunicationBoardDtoList);
        }catch (SQLException e){
            success = false;
            message = "검색실패";
            e.printStackTrace();
        }
       return new Response<PagenationCommunicationBoardDto>(success, message, resultCommunicationBoard);
    }
}

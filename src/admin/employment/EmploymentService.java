package src.admin.employment;

import src.database.Database;
import src.employment.board.BoardDTO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmploymentService {
    private final Database db = new Database();

    //채용공고 추가
    public int insertEmpBoard(String companyName, String title, String jobtype, String career, String hiringProcess, String qualifications, String preferred, int location, int empNo){
        try (Connection connection = db.connect();
             PreparedStatement ps = connection.prepareStatement("INSERT INTO employment_board (title, job_type, career, hiring_process, qualifications, preferred, main_category1_id,main_category2_id,sub_category1_id,sub_category2_id,company_name) " +
                             "VALUES (?,?,?,?,?,?,?,?,?,?,?)");)
        {
            ps.setString(1,title);
            ps.setString(2,jobtype);
            ps.setString(3,career);
            ps.setString(4,hiringProcess);
            ps.setString(5,qualifications);
            ps.setString(6,preferred);
            ps.setInt(7,1);
            ps.setInt(8,2);
            ps.setInt(9,location);
            ps.setInt(10,empNo);
            ps.setString(11,companyName);

            int rows = ps.executeUpdate();
            return rows;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    // 기업명으로 채용공고 검색
    public List<BoardDTO> searchEmpBoard(String company_ID) {
        List<BoardDTO> emplist = new ArrayList<>();
        try (Connection connection = db.connect();
             PreparedStatement ps = connection.prepareStatement("select * from employment_board where company_name=?");) {
            ps.setString(1, company_ID);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                BoardDTO empVO = new BoardDTO(
                        rs.getInt("employment_board_id"),
                        rs.getString("title"),
                        rs.getString("job_type"),
                        rs.getString("career"),
                        rs.getString("hiring_process"),
                        rs.getString("qualifications"),
                        rs.getString("preferred"),
                        rs.getInt("main_category1_id"),
                        rs.getInt("main_category2_id"),
                        rs.getInt("sub_category1_id"),
                        rs.getInt("sub_category2_id"),
                        rs.getString("company_name")
                );
                emplist.add(empVO);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emplist;
    }


    //검색한 기업의 채용공고인지 확인
    public boolean containsEmploymentBoardId(List<BoardDTO> list, int userInputId) {
        return list.stream()
                .anyMatch(dto -> dto.getEmploymentBoardId() == userInputId);
    }


    //존재하는 컬럼인지 상수로 저장한 리스트
    private static final List<String> VALID_COLUMNS = Arrays.asList(
            "title", "job_type", "career", "hiring_process", "qualifications", "preferred", "sub_category1_id","sub_category2_id");

    // 컬럼명이 유효한지 검증하는 메서드
    private boolean isValidColumn(String columnName) {
        return VALID_COLUMNS.contains(columnName);
    }


    //채용공고 업데이트
    public <T> int updateEmpBoard(String columnName, T updateContent, int emp_board_id ) {
        if (!isValidColumn(columnName)) {
            throw new IllegalArgumentException("Invalid column name: " + columnName);
        }

        String sql = "UPDATE employment_board SET " + columnName + " = ? WHERE employment_board_id = ?";
        try(
            Connection connection = db.connect();
            PreparedStatement ps = connection.prepareStatement(sql);)
        {
            if (updateContent instanceof String) {
                ps.setString(1,(String) updateContent);
            }
            else if (updateContent instanceof Integer) {
                ps.setInt(1, (Integer) updateContent);
            }
            else{
                throw new IllegalArgumentException("Unsupported type: " + updateContent.getClass().getName());
            }
            ps.setInt(2, emp_board_id);
            int rows = ps.executeUpdate();
            if (rows == 1){
                System.out.println("정상적으로 수정되었습니다.");
            }
            return rows;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    //채용 공고 삭제
    public int deleteEmpBoard(int board_id){
        try (Connection connection = db.connect();
        PreparedStatement ps = connection.prepareStatement("DELETE FROM employment_board WHERE employment_board_id = ?");
        ){
            ps.setInt(1, board_id);

            int rows = ps.executeUpdate();
            if (rows == 1){
                return rows;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    //채용공고 상세 검색
    public BoardDTO viewEmpBoard(int board_id) {
        try (Connection connection = db.connect();
             PreparedStatement ps = connection.prepareStatement("SELECT eb.* , ebc1.category_name AS localName , ebc2.category_name AS jobName FROM employment_board AS eb inner join employment_board_category AS ebc1 ON eb.sub_category1_id = ebc1.subcategory_id inner join employment_board_category AS ebc2 ON eb.sub_category2_id = ebc2.subcategory_id WHERE employment_board_id = ?");) {
            ps.setInt(1, board_id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                BoardDTO boardDTO = new BoardDTO(
                        rs.getInt("employment_board_id"),
                        rs.getString("title"),
                        rs.getString("job_type"),
                        rs.getString("career"),
                        rs.getString("hiring_process"),
                        rs.getString("qualifications"),
                        rs.getString("preferred"),
                        rs.getInt("main_category1_id"),
                        rs.getInt("main_category2_id"),
                        rs.getInt("sub_category1_id"),
                        rs.getInt("sub_category2_id"),
                        rs.getString("company_name"),
                        rs.getString("localName"),
                        rs.getString("jobName")
                );
                return boardDTO;
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

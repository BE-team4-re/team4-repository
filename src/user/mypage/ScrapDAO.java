package src.user.mypage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import src.database.Database;
import src.user.UserMain;

public class ScrapDAO {

    Database db = new Database();

    // 저장한 스크랩 가져오기
    public void printScrap() {
        // SQL 쿼리문
        String sql =
            "SELECT b.employment_Board_id AS employment_Board_id, c.title AS title "
                + "FROM user AS a "
                + "INNER JOIN scrap AS b ON a.id = b.id "
                + "INNER JOIN employment_board AS c ON b.employment_Board_id = c.employment_Board_id "
                + "WHERE a.id = ? "
                + "ORDER BY b.employment_Board_id DESC";

        try (
            Connection conn = db.connect(); // 데이터베이스 연결
            // PreparedStatement 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            // 쿼리문에 전달할 파라미터 설정
            pstmt.setInt(1, UserMain.id);
            // SQL문 실행
            try (ResultSet rs = pstmt.executeQuery()) {
                boolean boolScrap = false;
                // 값 변수에 저장
                while (rs.next()) {
                    boolScrap = true;
                    int boardId = rs.getInt("employment_Board_id");
                    String title = rs.getString("title");
                    // 출력
                    System.out.println("채용게시물 번호 : " + boardId + " 채용제목 :" + title);
                    System.out.println();
                }
                if (!boolScrap) {
                    System.out.println("스크랩된 항목이 없습니다.");
                }
            }
        } catch (SQLException e) {
            System.out.println("스크랩 정보를 가져오는 중 오류가 발생했습니다.1");
        }
    }

}

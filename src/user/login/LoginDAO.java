package src.user.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import src.database.Database;
import src.user.UserDTO;

public class LoginDAO {

    Database db = new Database();

    // 로그인 검증
    public UserDTO login(String id, String pw) {
        // SQL 쿼리문 작성
        String sql = "SELECT id, userId, userPw FROM user WHERE userId = ? AND userPw = ?";

        try (
            Connection conn = db.connect(); // 데이터베이스 연결
            // PreparedStatement 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            // 쿼리문에 전달할 파라미터 설정
            pstmt.setString(1, id);
            pstmt.setString(2, pw);

            // SQL문 실행
            try (ResultSet rs = pstmt.executeQuery()) {
                // 사용자가 입력한 id와 pw가 일치하는 경우
                if (rs.next()) {
                    UserDTO user = new UserDTO();
                    user.setId(rs.getInt("id"));
                    return user; // 로그인 성공 설정
                }
            }
        } catch (SQLException e) {
            System.out.println("오류 발생1");
        }
        // 결과 반환
        return null;
    }
}

package src.user.signup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import src.database.Database;
import src.user.UserDTO;

public class SignUpDAO {

    Database db = new Database();

    // 회원가입
    public int signUp(UserDTO user) {
        // SQL 쿼리문 작성
        String sql =
            "INSERT INTO User (userId, userPw, userName, userGender, "
                + "userPhone, userStatus, userBirth, userEmail) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (
            Connection conn = db.connect(); // 데이터베이스 연결
            // PreparedStatement 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            // 쿼리문에 전달할 파라미터 설정
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getUserPw());
            pstmt.setString(3, user.getUserName());
            pstmt.setString(4, user.getUserGender());
            pstmt.setString(5, user.getUserPhone());
            pstmt.setInt(6, 1);
            pstmt.setString(7, user.getUserBirth());
            pstmt.setString(8, user.getUserEmail());

            return pstmt.executeUpdate(); // SQL문 실행
        } catch (SQLException e) {
            System.out.println("오류 발생");
        }
        return 0;
    }

    // 아이디 중복 체크
    public boolean duplicateId(String id) {
        // SQL 쿼리문 작성
        String sql = "SELECT userId FROM user WHERE userId = ? ";

        try (
            Connection conn = db.connect(); // 데이터베이스 연결
            // PreparedStatement 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            // 쿼리문에 전달할 파라미터 설정
            pstmt.setString(1, id);
            // SQL문 실행
            try (ResultSet rs = pstmt.executeQuery()) {
                // id 중복 체크
                if (rs.next()) {
                    System.out.println("이미 사용중인 아이디입니다 \n다시 입력하세요.1");
                    return true; // 아이디 존재 시
                }
            }
        } catch (SQLException e) {
            System.out.println("오류 발생");
        }
        // 결과 반환
        return false;
    }
}

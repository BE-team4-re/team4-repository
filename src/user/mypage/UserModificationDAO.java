package src.user.mypage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import src.database.Database;
import src.user.UserMain;

public class UserModificationDAO {

    Database db = new Database();

    // 회원 비밀번호 수정
    public int updatePw(String pw) {
        // SQL 쿼리문 작성
        String sql = "UPDATE user SET userPw = ? "
            + "WHERE userId = ?";

        try (
            Connection conn = db.connect(); // 데이터베이스 연결
            // PreparedStatement 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            // 쿼리문에 전달할 파라미터 설정
            pstmt.setString(1, pw);
            pstmt.setString(2, UserMain.loginId);

            return pstmt.executeUpdate(); // SQL문 실행
        } catch (SQLException e) {
            System.out.println("오류 발생");
        }
        // 결과 반환
        return 0;
    }

    // 회원 전화번호 수정
    public int updatePhone(String phone) {
        // SQL 쿼리문 작성
        String sql = "UPDATE user SET userPhone = ? "
            + "WHERE userId = ?";

        try (
            Connection conn = db.connect(); // 데이터베이스 연결
            // PreparedStatement 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            // 쿼리문에 전달할 파라미터 설정
            pstmt.setString(1, phone);
            pstmt.setString(2, UserMain.loginId);

            return pstmt.executeUpdate(); // SQL문 실행
        } catch (SQLException e) {
            System.out.println("오류 발생");
        }
        // 결과 반환
        return 0;
    }

    // 회원 이메일 수정
    public int updateEmail(String email) {
        // SQL 쿼리문 작성
        String sql = "UPDATE user SET userEmail = ? "
            + "WHERE userId = ?";

        try (
            Connection conn = db.connect(); // 데이터베이스 연결
            // PreparedStatement 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            // 쿼리문에 전달할 파라미터 설정
            pstmt.setString(1, email);
            pstmt.setString(2, UserMain.loginId);

            return pstmt.executeUpdate(); // SQL문 실행
        } catch (SQLException e) {
            System.out.println("오류 발생");
        }
        // 결과 반환
        return 0;
    }

    // 회원 탈퇴
    public int deleteUser() {
        // SQL 쿼리문 작성
        String sql =
            "UPDATE user SET userPw = '', userName = '', userGender = '', userPhone = '', "
                + "userStatus = 0, userBirth = '', userEmail = '' "
                + "WHERE userId = ?";

        try (
            Connection conn = db.connect(); // 데이터베이스 연결
            // PreparedStatement 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            // 쿼리문에 전달할 파라미터 설정
            pstmt.setString(1, UserMain.loginId);

            return pstmt.executeUpdate(); // SQL문 실행
        } catch (SQLException e) {
            System.out.println("오류 발생");
        }
        // 결과 반환
        return 0;
    }
}

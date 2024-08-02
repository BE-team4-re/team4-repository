package src.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import src.database.Database;

public class UserDAO {

    Database db = new Database();

    // 회원가입
    public int createUser(UserDTO user) {
        Connection conn = db.connect(); // DB 연동
        int result = 0; // 저장 행수 초기화
        try {
            // sql문 작성
            String sql =
                "INSERT INTO User (userId, userPw, userName, userGender, userPhone, userStatus, userBirth, userEmail) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            // ?에 해당하는 값 지정
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getUserPw());
            pstmt.setString(3, user.getUserName());
            pstmt.setString(4, user.getUserGender());
            pstmt.setString(5, user.getUserPhone());
            pstmt.setInt(6, 1);
            pstmt.setString(7, user.getUserBirth());
            pstmt.setString(8, user.getUserEmail());

            result = pstmt.executeUpdate(); // sql문 실행
            pstmt.close(); // PreparedStatement 닫기
        } catch (Exception e) {
            System.out.println("오류발생");
        }
        return result;
    }

    // 회원 비밀번호 수정
    public int updatePw(String pw) {
        Connection conn = db.connect(); //DB 연동
        int result = 0; // 저장 행수 초기화
        try {
            String sql = "UPDATE user SET userPw = ? "; // sql문 작성

            // ?에 해당하는 값 지정
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, pw);

            result = pstmt.executeUpdate(); // sql 실행
            pstmt.close(); // PreparedStatement 닫기
        } catch (Exception e) {
            System.out.println("오류발생");
        }
        return result;
    }

    // 회원 전화번호 수정
    public int updatePhone(String phone) {
        Connection conn = db.connect(); // DB연동
        int result = 0; // 저장 행수 초기화
        try {
            String sql = "UPDATE user SET userPhone = ?"; // sql문 작성

            // ?에 해당하는 값 지정
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, phone);

            result = pstmt.executeUpdate(); // sql문 실행
            pstmt.close(); // PreparedStatement 닫기
        } catch (Exception e) {
            System.out.println("오류 발생");
        }
        return result;
    }

    // 회원 이메일 수정
    public int updateEmail(String email) {
        Connection conn = db.connect(); // DB연동
        int result = 0; // 저장 행수 초기화

        try {
            String sql = "UPDATE user SET userEmail = ?"; // sql문 작성

            // ?에 해당하는 값 지정
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);

            result = pstmt.executeUpdate(); // sql 실행
            pstmt.close(); // PreparedStatement 닫기
        } catch (Exception e) {
            System.out.println("오류 발생");
        }
        return result;
    }

    // 회원 탈퇴
    public int deleteUser(String id) {
        Connection conn = db.connect(); // DB연동
        int result = 0; // 저장 행수 초기화

        try {
            // sql문 작성
            String sql =
                "UPDATE user SET userPw = '', userName = '', userGender = '', userPhone = '', "
                    + "userStatus = 0, userBirth = '', userEmail = '' "
                    + "WHERE userId = ?";

            // ?에 해당하는 값 지정
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);

            result = pstmt.executeUpdate(); // sql 실행
            pstmt.close(); // PreparedStatement 닫기
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // 로그인 검증
    public boolean login(String id, String pw) {
        Connection conn = db.connect(); // DB연동
        boolean result = false; // 저장 행수 초기화

        try {
            // sql문 작성
            String sql = "SELECT userId FROM user WHERE userId = ? AND userPw = ?";

            // ?에 해당하는 값 지정
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, pw);

            ResultSet rc = pstmt.executeQuery(); // sql문 실행
            if (rc.next()) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}


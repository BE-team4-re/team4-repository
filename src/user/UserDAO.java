package src.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import src.database.Database;

public class UserDAO {

    Database db = new Database();

    // 회원가입
    public int createUser(UserDTO user) {
        int result = 0; // 결과 저장 변수 초기화

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

            result = pstmt.executeUpdate(); // SQL문 실행
        } catch (Exception e) {
            System.out.println("오류발생");
        }
        // 결과 반환
        return result;
    }

    // 회원 비밀번호 수정
    public int updatePw(String pw) {
        int result = 0; // 결과 저장 변수 초기화

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

            result = pstmt.executeUpdate(); // SQL문 실행
        } catch (Exception e) {
            System.out.println("오류발생");
        }
        // 결과 반환
        return result;
    }

    // 회원 전화번호 수정
    public int updatePhone(String phone) {
        int result = 0; // 결과 저장 변수 초기화

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

            result = pstmt.executeUpdate(); // SQL문 실행
        } catch (Exception e) {
            System.out.println("오류 발생");
        }
        // 결과 반환
        return result;
    }

    // 회원 이메일 수정
    public int updateEmail(String email) {
        int result = 0; // 결과 저장 변수 초기화

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

            result = pstmt.executeUpdate(); // SQL문 실행
        } catch (Exception e) {
            System.out.println("오류 발생");
        }
        // 결과 반환
        return result;
    }

    // 회원 탈퇴
    public int deleteUser() {
        int result = 0; // 결과 저장 변수 초기화

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

            result = pstmt.executeUpdate(); // SQL문 실행
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 결과 반환
        return result;
    }

    // 로그인 검증
    public boolean login(String id, String pw) {
        boolean result = false; // 결과 저장 변수 초기화

        // SQL 쿼리문 작성
        String sql = "SELECT userId, userPw FROM user WHERE userId = ? AND userPw = ?";

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
                    result = true; // 로그인 성공 설정
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 결과 반환
        return result;
    }
}


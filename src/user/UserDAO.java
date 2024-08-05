package src.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import src.database.Database;

public class UserDAO {

    Database db = new Database();

    // 회원가입
    public int createUser(UserDTO user) {
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

    // 로그인 검증
    public boolean login(String id, String pw) {
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
                    return true; // 로그인 성공 설정
                }
            }
        } catch (SQLException e) {
            System.out.println("오류 발생");
        }
        // 결과 반환
        return false;
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
                    System.out.println("이미 사용중인 아이디입니다 \n다시 입력하세요.");
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


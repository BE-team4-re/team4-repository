package src.admin.user;

import src.database.Database;
import src.user.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private final Database db = new Database();

    public List<UserDTO> searchUserlist() {
        List<UserDTO> userDTOlist = new ArrayList<>();
        try (
                Connection connection = db.connect();
                PreparedStatement ps = connection.prepareStatement(
                        "SELECT userId, " +
                                "CASE " +
                                "WHEN userStatus = 0 THEN '탈퇴' " +
                                "WHEN userStatus = 1 THEN '활동' " +
                                "ELSE userStatus " +
                                "END AS userStatus " +
                                "FROM user;"
                );
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UserDTO u = new UserDTO(
                        rs.getString("userId"),
                        rs.getString("userStatus")
                );
                userDTOlist.add(u);
            }
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return userDTOlist;
    }

    //계정 존재 여부 (id와 status 검사)
    public Boolean validationID(String user_ID) {
        try (Connection connection = db.connect();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM user WHERE userId = ? ");)
        {
            String userID = "";
            int status = 3;
            ps.setString(1, user_ID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                userID = rs.getString("userId");
                status = rs.getInt("userStatus");
            }
            rs.close();
            return (userID.equals(user_ID) && status == 1) ? true : false;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    //계정 휴면 업데이트
    public int deleteUser(String user_ID) {
        int result = 0;
        try (Connection connection = db.connect();
             PreparedStatement pstmt = connection.prepareStatement(
                     "UPDATE user SET userPw = '', userName = '', userGender = '', userPhone = '', "
                             + "userStatus = 0, userBirth = '', userEmail = '' "
                             + "WHERE userId = ?" );)
        {
            pstmt.setString(1, user_ID);
            result = pstmt.executeUpdate();
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}

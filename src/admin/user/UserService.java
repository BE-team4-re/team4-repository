package src.admin.user;

import src.database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserService {
    private final Database db = new Database();

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
            } else {
                System.out.println("존재하지 않는 유저입니다.");
            }
            rs.close();
            return (userID.equals(user_ID) && status == 1) ? true : false;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

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

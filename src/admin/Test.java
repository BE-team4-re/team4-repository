package src.admin;

import src.database.Database;

import java.sql.*;
import java.util.Scanner;

//비활성화는 0
public class Test {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        askAdminCategory(scanner);
    }

    public static void askAdminCategory(Scanner scanner){
        printAdminCategory();
        System.out.print("이동할 번호를 눌러주세요: ");
        String index = scanner.nextLine();

        switch (index) {
            case "1":
                printSearchUserId();

                String userID = scanner.nextLine();

                if (validationID(userID)){
                    if (askDeleteUser(scanner)) {
                        deleteUser(userID);
                    }else{
                        System.out.println("잘못된 입력입니다.");
                        return;
                    }
                }
                return;
            default:
                System.out.println("잘못 누르셨습니다. 다시 눌러주세요");
                return;
        }
    }

    public static void printAdminCategory(){
        System.out.println("-----------------------------------------------");
        System.out.println("-------------------관리자페이지-------------------");
        System.out.println("1.회원관리(삭제)  2. 카테고리관리  3.기업관리  4.로그아웃");
        System.out.println("-----------------------------------------------");
    }


    public static void printSearchUserId(){
        System.out.println("--------------------------------------------------------");
        System.out.println("-----------------관리자 회원관리(삭제) 페이지------------------");
        System.out.println("--------------------------------------------------------");
        System.out.println("회원의 아이디를 입력해주세요:");
    }

    public static boolean askDeleteUser(Scanner scanner){
        System.out.println("1. 삭제하기 2.관리자 메뉴 이동");
        String index = scanner.nextLine();
        return index.equals("1");
    }


    public static void deleteUser(String userID ){
        try{
            Database db = new Database();
            Connection connection = db.connect();
            String deleteQuery =  "UPDATE user SET userPw='', userName='', userGender='', userPhone='', userBirth='', userEmail='', userStatus=0 WHERE userId=?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setString(1, userID);
            preparedStatement.executeUpdate();
            connection.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    public static Boolean validationID(String user_ID) {
        try {
            String userID = "";
            int status = 3;
            Database db = new Database();
            Connection connection = db.connect();
            String searchIDSQL = "SELECT * FROM user WHERE userId = ? ";
            PreparedStatement pstmt = connection.prepareStatement(searchIDSQL);
            pstmt.setString(1, user_ID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                userID = rs.getString("userId");
                status = rs.getInt("userStatus");
            } else {
                System.out.println("존재하지 않는 유저입니다.");
            }
            rs.close();
            pstmt.close();
            return (userID.equals(user_ID) && status == 1) ? true : false;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

}




//        try {
//            Connection conn = null;
//            //JDBC Driver 등록
//            Class.forName ("com.mysql.cj.jdbc.Driver");
//        //연결하기
//            conn = DriverManager.getConnection ( "jdbc:mysql://localhost:3306/miniProject4", "root",
//                "10041004");
//            String sql = "SELECT * FROM user";
//            PreparedStatement ps = conn.prepareStatement(sql);
//
//            ResultSet rs = ps.executeQuery();
//            List<User> users = new ArrayList<>();
//            while (rs.next()){
//                User user = new User();
//                user.setUserId(rs.getString("userId"));
//                user.setUserPw(rs.getString("userPw"));
//                user.setUserName(rs.getString("userName"));
//                user.setUserGender(rs.getString("userGender"));
//                user.setUserEmail(rs.getString("userEmail"));
//                user.setUserPhone(rs.getString("userPhone"));
//                user.setUserStatus(rs.getInt("userStatus"));
//                user.setUserBirth(rs.getString("userBirth"));
//                user.setUserEmail(rs.getString("userEmail"));
//
//                users.add(user);
//            }
//            System.out.println(users.toString());
//            rs.close();
//            ps.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if(conn != null){
//                try {
//                    conn.close();
//                }catch (SQLException e){}
//            }
//        }
////        db.connect().close();
////        System.out.println(users);
//
//
//    }
//}

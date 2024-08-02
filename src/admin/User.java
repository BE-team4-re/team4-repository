package src.admin;

public class User {
    public int getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    private int id;
    private String userId;
    private String userPw;
    private String userName;
    private String userGender;
    private String userPhone;
    private int userStatus;
    private String userBirth;
    private String userEmail;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                "userId=" + userId +
                ", userPw='" + userPw + '\'' +
                ", userName='" + userName + '\'' +
                ", userGender='" + userGender + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userStatus=" + userStatus +
                ", userBirth='" + userBirth + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }


    public void setId(int id) {
        this.id = id;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    public void setUserBirth(String userBirth) {
        this.userBirth = userBirth;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}

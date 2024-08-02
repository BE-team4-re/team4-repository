package src.user;

public class UserDTO {

    private String userId;
    private String userPw;
    private String userName;
    private String userGender;
    private String userPhone;
    private String userBirth;
    private String userEmail;
    private int status;

    public UserDTO(String userId, String userPw, String userName, String userGender,
        String userPhone, String userBirth, String userEmail) {
        this.userId = userId;
        this.userPw = userPw;
        this.userName = userName;
        this.userGender = userGender;
        this.userPhone = userPhone;
        this.userBirth = userBirth;
        this.userEmail = userEmail;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserGender() {
        return userGender;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getUserBirth() {
        return userBirth;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public int getStatus() {
        return status;
    }

}
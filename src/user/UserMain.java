package src.user;

public class UserMain {

    // 로그인 상태 유지
    public static String loginId;
    public static int id;

    public static void main(String[] args) {
        MainController controller = new MainController();
        controller.startMain();
    }
}

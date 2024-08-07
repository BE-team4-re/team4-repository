package src.user.login;

import java.util.Scanner;
import src.admin.AdminController;
import src.user.UserDTO;
import src.user.UserMain;
import src.user.Validation;

public class LoginController {

    Scanner sc = new Scanner(System.in);
    Validation check = new Validation();
    LoginDAO ld = new LoginDAO();
    AdminController adminController = new AdminController();

    // 로그인
    public boolean loginMain() {
        while (true) {
            System.out.println("\n=============================================");
            System.out.println("               ★ 로그인 ★                ");
            System.out.println("=============================================");
            System.out.print("아이디를 입력하세요 (나가기 (q)):");
            String id = sc.nextLine();
            if (id.equals("q")) {
                break;
            }

            System.out.print("비밀번호를 입력하세요 (나가기 (q)):");
            String pw = sc.nextLine();
            if (pw.equals("q")) {
                break;
            }

            if (id.equals("admin") && pw.equals("admin")) {
                UserMain.loginId = "admin";
                UserMain.id = 0;
                adminController.askAdminCategory();
                return false;
                // 관리자 페이지 이동
            } else {
                // db에서 로그인 아이디, 비밀번호 확인
                pw = check.encodingPwd(pw); // 입력한 pw 암호화
                UserDTO result = ld.login(id, pw);

                if (result != null) {
                    System.out.println("로그인 성공");
                    UserMain.loginId = id; // 로그인 상태 유지
                    UserMain.id = result.getId();
                    return true;
                } else {
                    System.out.println("아이디 또는 비밀번호가 올바르지 않습니다. \n다시 입력하세요");
                }
            }
        }
        return false;
    }
}

package src.user.mypage;

import java.util.Scanner;
import src.user.UserMain;
import src.user.Validation;

public class UserModificationController {

    Scanner sc = new Scanner(System.in);
    Validation check = new Validation();
    UserModificationDAO umd = new UserModificationDAO();

    // 비밀번호 수정
    public void pwUpdate() {
        String pw;
        int result;
        while (true) {
            System.out.print("수정할 비밀번호를 입력하세요:");
            pw = sc.nextLine();
            if (check.validationPw(pw)) { // 비밀번호 검증
                result = umd.updatePw(check.encodingPwd(pw));
                break;
            }
        }
        if (result > 0) {
            System.out.println("수정 완료");
        } else {
            System.out.println("수정 실패");
        }
    }

    // 휴대폰 번호 수정
    public void phoneUpdate() {
        String phone;
        int result;
        while (true) {
            System.out.print("수정할 휴대폰 번호를 입력하세요:");
            phone = sc.nextLine();
            if (check.validationPhone(phone)) { // 휴대폰 번호 검증
                result = umd.updatePhone(phone);
                break;
            }
        }
        if (result > 0) {
            System.out.println("수정 완료");
        } else {
            System.out.println("수정 실패");
        }
    }

    // 이메일 수정
    public void emailUpdate() {
        String email;
        int result;
        while (true) {
            System.out.print("수정할 이메일을 입력하세요:");
            email = sc.nextLine();
            if (check.validationEmail(email)) { // 이메일 검증
                result = umd.updateEmail(email); // 이메일 수정
                break;
            }
        }
        if (result > 0) {
            System.out.println("수정 완료");
        } else {
            System.out.println("수정 실패");
        }
    }

    // 회원 탈퇴
    public boolean deleteUser() {
        System.out.print("정말 탈퇴하시겠습니까?(y or n) ");
        String yn = sc.nextLine();

        if (yn.equals("y")) {
            int result = umd.deleteUser(); // 로그인 탈퇴
            UserMain.loginId = null; // 로그아웃
            System.out.println("탈퇴가 완료되었습니다.");
            return true;
        } else {
            System.out.println("탈퇴를 취소하였습니다.");
            return false;
        }
    }

}

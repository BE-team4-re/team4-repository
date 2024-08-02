package src.user;

import java.util.Scanner;

public class Controller {

    Scanner sc = new Scanner(System.in);
    UserDAO uc = new UserDAO();

    // 회원가입
    public void insert() {
        System.out.println("===== 회원가입 =====");
        System.out.print("아이디를 입력하세요 :");
        String id = sc.nextLine();

        System.out.print("비밀번호를 입력하세요 :");
        String pw = sc.nextLine();

        System.out.print("이름을 입력하세요 :");
        String name = sc.nextLine();

        System.out.print("성별을 입력하세요 :");
        String gender = sc.nextLine();

        System.out.print("휴대폰 번호를 입력하세요 :");
        String phone = sc.nextLine();

        System.out.print("생년월일을 입력하세요 :");
        String birth = sc.nextLine();

        System.out.print("이메일을 입력하세요 :");
        String email = sc.nextLine();

        UserDTO user = new UserDTO(id, pw, name, gender, phone, birth, email);
        int result = uc.createUser(user);
        if (result > 0) {
            System.out.println("회원가입 성공");
        } else {
            System.out.println("회원가입 실패");
        }
    }

    // 비밀번호 수정
    public void pwUpdate() {
        System.out.print("수정할 비밀번호를 입력하세요:");
        String pw = sc.nextLine();
        int result = uc.updatePw(pw);
        if (result > 0) {
            System.out.println("수정 완료");
        } else {
            System.out.println("수정 실패");
        }
    }

    // 휴대폰 번호 수정
    public void phoneUpdate() {
        System.out.print("수정할 번호를 입력하세요:");
        String phone = sc.nextLine();
        int result = uc.updatePhone(phone);
        if (result > 0) {
            System.out.println("수정 완료");
        } else {
            System.out.println("수정 실패");
        }
    }

    // 비밀번호 수정
    public void emailUpdate() {
        System.out.print("수정할 이메일을 입력하세요:");
        String email = sc.nextLine();
        int result = uc.updateEmail(email);
        if (result > 0) {
            System.out.println("수정 완료");
        } else {
            System.out.println("수정 실패");
        }
    }

    // 회원 정보 수정
    public void updateUser() {
        System.out.println("===== 회원정보 수정 =====");
        System.out.println("1. 비밀번호 | 2. 휴대폰 번호 3. | 이메일");
        System.out.print("수정 할 항목을 선택하세요 :");
        String menu = sc.nextLine();
        switch (menu) {
            case "1":
                pwUpdate();
                break;
            case "2":
                phoneUpdate();
                break;
            case "3":
                emailUpdate();
                break;
            default:
                System.out.println("잘못 입력하셨습니다.");
        }
    }

    // 회원 탈퇴
    public void deleteUser() {
        System.out.print("정말 탈퇴하시겠습니까?(y or n) ");
        String yn = sc.nextLine();
        if (yn.equals("y")) {
            int result = uc.deleteUser();
            UserMain.loginId = null;
            System.out.println("탈퇴가 완료되었습니다.");
        } else {
            System.out.println("탈퇴를 취소하였습니다.");
        }
    }

    // 로그인
    public void login() {
        System.out.println("===== 로그인 =====");
        System.out.println("아이디를 입력하세요 :");
        String id = sc.nextLine();
        System.out.println("비밀번호를 입력하세요 :");
        String pw = sc.nextLine();
        boolean result = uc.login(id, pw);
        if (result) {
            System.out.println("로그인 성공");
            UserMain.loginId = id;
        } else {
            System.out.println("로그인 실패");
        }
    }

    // 마이페이지
    public void myPage() {
        System.out.println("===== 마이페이지 =====");
        System.out.println("1. 회원 정보 수정 | 2. 회원 탈퇴 | 3. 스크랩");
        String menu = sc.nextLine();
        switch (menu) {
            case "1":
                updateUser();
                break;
            case "2":
                deleteUser();
                break;
            case "3":
                break;
            default:
                System.out.println("잘못 입력하셨습니다.");
        }
    }
}

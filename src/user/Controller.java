package src.user;

import java.util.Scanner;

public class Controller {

    Scanner sc = new Scanner(System.in);
    UserDAO uc = new UserDAO();

    // 회원가입
    public void insert() {
        try {
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
        } catch (Exception e) {
            System.out.println("오류 발생");
        }
    }

    // 비밀번호 수정
    public void pwUpdate() {
        System.out.print("수정할 비밀번호를 입력하세요:");
        String pw = sc.nextLine();
        int result = uc.updatePw(pw);
    }

    // 휴대폰 번호 수정
    public void phoneUpdate() {
        System.out.print("수정할 비밀번호를 입력하세요:");
        String phone = sc.nextLine();
        int result = uc.updatePhone(phone);
    }

    // 비밀번호 수정
    public void emailUpdate() {
        System.out.print("수정할 비밀번호를 입력하세요:");
        String email = sc.nextLine();
        int result = uc.updateEmail(email);
    }

    // 회원 탈퇴
    public void deleteUser() {
        System.out.print("탈퇴할 아이디를 입력하세요:");
        String id = sc.nextLine();
        int result = uc.deleteUser(id);
    }

    // 로그인
    public void login() {
        System.out.println("아이디를 입력하세요 :");
        String id = sc.nextLine();
        System.out.println("비밀번호를 입력하세요 :");
        String pw = sc.nextLine();

        boolean result = uc.login(id, pw);
        if (result) {
            System.out.println("로그인 성공");
        } else {
            System.out.println("로그인 실패");
        }

    }
}

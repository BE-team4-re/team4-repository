package src.user;

import java.util.Scanner;

public class UserMain {

    // 로그인 상태ㄴ 유지
    public static String loginId;

    public static void main(String[] args) {
        Controller mv = new Controller();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("메뉴를 선택해주세요");
            System.out.println("1. 회원가입 | 2. 로그인 | 3. 마이페이지");
            String menu = sc.nextLine();
            switch (menu) {
                case "1":
                    mv.insert(); // 회원가입
                    break;
                case "2":
                    mv.login(); // 로그인
                    break;
                case "3":
                case "4":
                case "5":
                default:
                    System.out.println("잘못 입력하셨습니다.");
            }
        }
    }
}

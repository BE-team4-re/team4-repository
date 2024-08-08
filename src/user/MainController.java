package src.user;

import java.util.Scanner;

import src.communication.communicationBoard.CommunicationBoardController;
import src.employment.EmploymentBoardMain;
import src.user.login.LoginController;
import src.user.mypage.ScrapController;
import src.user.mypage.UserModificationController;
import src.user.signup.SignUpController;

public class MainController {

    Scanner sc = new Scanner(System.in);
    SignUpController suc = new SignUpController();
    LoginController lc = new LoginController();
    UserModificationController umc = new UserModificationController();
    ScrapController scr = new ScrapController();
    private final CommunicationBoardController communicationBoardController = new CommunicationBoardController();

    // 메인
    public void startMain() {
        while (true) {
            System.out.println("\n=============================================");
            System.out.println("                  ★ JobabaYou ★                 ");
            System.out.println("=============================================");
            System.out.println("   1. 회원가입    |    2. 로그인    |    3. 종료   ");
            System.out.println("=============================================");
            System.out.print("메뉴를 선택하세요 -> ");
            String menu = sc.nextLine();
            switch (menu) {
                case "1":
                    suc.signUpMain(); // 회원가입
                    break;
                case "2":
                    if (lc.loginMain()) {
                        userMain();
                        break;
                    }
                    break;
                case "3":
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                default:
                    System.out.println("잘못 입력하셨습니다.");
            }
        }
    }

    // 사용자 메인 화면
    public void userMain() {
        while (true) {
            System.out.println("\n====================================================");
            System.out.println("                     ★ User Main ★                        ");
            System.out.println("====================================================");
            System.out.println("   1. 채용 공고 게시판    |    2. 커뮤니티    ");
            System.out.println("   3. 마이페이지         |    4. 로그아웃   ");
            System.out.println("====================================================");
            System.out.print("메뉴를 선택하세요 -> ");
            String menu = sc.nextLine();
            switch (menu) {
                case "1":
                    EmploymentBoardMain.entrance(); // 채용공고 게시판 이동
                    break;
                case "2":
                    // 커뮤니티 이동
                    communicationBoardController.searchCommunicationBoard(UserMain.id);
                    break;
                case "3":
                    myPage(); // 마이페이지 이동
                    break;
                case "4":
                    UserMain.loginId = null; // 로그아웃
                    UserMain.id = 0;
                    System.out.println("로그아웃 되셨습니다.");
                    return;
                default:
                    System.out.println("잘못 입력하셨습니다.");
            }
        }
    }

    // 마이페이지
    public void myPage() {
        while (true) {
            System.out.println("\n====================================================");
            System.out.println("                   ★ 마이페이지 ★                    ");
            System.out.println("====================================================");
            System.out.println("   1. 회원 정보 수정    |    2. 회원 탈퇴   ");
            System.out.println("   3. 스크랩 확인       |    4. 뒤로가기   ");
            System.out.println("====================================================");
            System.out.print("메뉴를 선택하세요 -> ");
            String menu = sc.nextLine();
            switch (menu) {
                case "1":
                    updateUser(); // 회원 정보 수정
                    break;
                case "2":
                    boolean isdelete = umc.deleteUser(); // 회원 탈퇴
                    if (isdelete) {
                        startMain();
                    }
                    break;
                case "3":
                    scr.scrapMain(); // 스크랩 이동
                    break;
                case "4":
                    return; // 뒤로 가기
                default:
                    System.out.println("잘못 입력하셨습니다.");
            }
        }
    }

    // 회원 정보 수정
    public void updateUser() {
        System.out.println("\n=============================================");
        System.out.println("               ★ 회원 정보 수정 ★               ");
        System.out.println("=============================================");
        System.out.println("   1. 비밀번호 수정    |    2. 휴대폰 번호 수정  ");
        System.out.println("   3. 이메일 수정      |    4. 뒤로가기   ");
        System.out.println("=============================================");
        System.out.print("수정 할 항목을 선택하세요 -> ");
        String menu = sc.nextLine();
        switch (menu) {
            case "1":
                umc.pwUpdate(); // 비밀번호 수정
                break;
            case "2":
                umc.phoneUpdate(); // 휴대폰번호 수정
                break;
            case "3":
                umc.emailUpdate(); // 이메일 수정
                break;
            case "4":
                break;
            default:
                System.out.println("잘못 입력하셨습니다.");
        }
    }
}

package src.admin;

import src.admin.employment.EmploymentController;
import src.admin.user.UserController;

import java.util.Scanner;

public class AdminController {
    Scanner scanner = new Scanner(System.in);
    UserController userController = new UserController();
    EmploymentController employmentController = new EmploymentController();

    public void askAdminCategory(){
        printAdminCategory();
        String choose = scanner.nextLine();
        switch(choose){
            case "1":
                userController.deactivateAccount();
                break;
            case "2":
                employmentController.askEmploymentMenu();
                break;
            default:
                System.out.println("잘못된 선택입니다.");
        }

    }

    public void printAdminCategory(){
        System.out.println("-----------------------------------------------");
        System.out.println("-------------------관리자페이지-------------------");
        System.out.println("1.회원관리(삭제)  2. 기업관리  3.카테고리관리  4.로그아웃");
        System.out.println("-----------------------------------------------");
        System.out.print("이동할 번호를 눌러주세요: ");
    }
}
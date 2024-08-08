package src.admin;

import src.admin.category.CategoryController;
import src.admin.employment.EmploymentController;
import src.admin.user.UserController;

import java.util.Scanner;
import src.user.UserMain;

public class AdminController {
    Scanner scanner = new Scanner(System.in);
    UserController userController = new UserController();
    EmploymentController employmentController = new EmploymentController();
    CategoryController categoryController = new CategoryController();

    public void askAdminCategory(){
        while (true){
            printAdminCategory();
            String choose = scanner.nextLine();

            switch(choose){
                case "1":
                    userController.deactivateAccount();
                    break;
                case "2":
                    employmentController.askEmploymentMenu();
                    break;
                case "3":
                    categoryController.askCategoryMenu();
                    break;
                case "4":
                    // 로그아웃
                    UserMain.loginId = null;
                    UserMain.id = 0;
                    System.out.println("로그아웃 되셨습니다.");
                    return;
                case "5":
                    System.exit(0);
                default:
                    System.out.println("잘못된 선택입니다.");
                    System.out.println("\n \n \n \n");
                    break;
            }
        }
    }

    public void printAdminCategory(){
        System.out.println("\n \n \n");
        System.out.println("+=============================================================================+");
        System.out.println("                                   관리자 페이지                               ");
        System.out.println("+=============================================================================+");
        System.out.println("|  1. 회원 관리 (삭제)  |  2. 기업 관리  |  3. 카테고리 관리  |  4. 로그아웃  |  5. 종료  |");
        System.out.println("+=============================================================================+");
        System.out.print("이동할 번호를 눌러주세요: ");
    }
}
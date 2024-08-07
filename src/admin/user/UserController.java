package src.admin.user;

import java.util.Scanner;

public class UserController {

    UserService userService = new UserService();
    Scanner scanner = new Scanner(System.in);

    //휴면계정 처리
    public void deactivateAccount(){
        while(true) {
            printSearchUserId();
            String userID = scanner.nextLine();
            if (userService.validationID(userID)) {
                System.out.println(userID + "는 존재하는 아이디입니다.");
                if (askDeleteUser(scanner)) {
                    if (userService.deleteUser(userID) == 1) {
                        System.out.println("휴면계정으로 상태를 변경하였습니다.");

                    }else {
                        System.out.println("휴면계정으로 상태를 변경하지 못하였습니다.");
                    }
                } else {
                    return;
                }
            } else {
                System.out.println("존재하지 않는 아이디입니다.");
                return;
            }
            return;
        }
    }

    //아이디 입력하도록 출력
    public void printSearchUserId(){
        System.out.println("\n \n \n");
        System.out.println("+=============================================================================+");
        System.out.println("                                관리자 회원관리 페이지                               ");
        System.out.println("+=============================================================================+");
        System.out.print("회원의 아이디를 입력해주세요:");
    }

    //삭제 메뉴
    public boolean askDeleteUser(Scanner scanner){
        System.out.println("+=============================================================================+");
        System.out.println("|          1. 삭제하기                  |            2. 관리자 메뉴 이동            |");
        System.out.println("+=============================================================================+");
        System.out.print("번호를 입력해주세요: ");
        String index = scanner.nextLine();
        return index.equals("1");
    }

}

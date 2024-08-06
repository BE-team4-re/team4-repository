package src.admin.user;

import java.util.Scanner;

public class UserController {

    UserService userService = new UserService();
    Scanner scanner = new Scanner(System.in);

    public void deactivateAccount(){
        printSearchUserId();
         String userID = scanner.nextLine();
         if (userService.validationID(userID)){
             System.out.println(userID + "는 존재하는 아이디입니다.");
             if (askDeleteUser(scanner)){
                 if(userService.deleteUser(userID) == 1){
                     System.out.println("휴면계정으로 상태를 변경하였습니다.");
                 }
                 else{
                     System.out.println("휴면계정으로 상태를 변경하지 못하였습니다.");
                 }
             }
             else{
                 System.out.println("잘못된 입력입니다.");
             }
         }else{
             //유저 아이디를 찾을 수 없는 경우
         }
    }

    public void printSearchUserId(){
        System.out.println("--------------------------------------------------------");
        System.out.println("-----------------관리자 회원관리(삭제) 페이지------------------");
        System.out.println("--------------------------------------------------------");
        System.out.println("회원의 아이디를 입력해주세요:");
    }




    public boolean askDeleteUser(Scanner scanner){
        System.out.println("1. 삭제하기 2.관리자 메뉴 이동");
        String index = scanner.nextLine();
        return index.equals("1");
    }

}

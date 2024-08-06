package src.employment.recordDAO.employmentBoard;


import src.employment.elements.content.PrintBoard;
import java.util.Scanner;


public class ReadDetail {
    public void selectTarget(Scanner sc) {
        String userInput = "";
        int userInputNumber = 0;
        boolean breakFlag = false;
        while (!breakFlag) {
            System.out.print("조회할 게시물 번호를 입력하세요 (Q. 돌아가기): ");
            userInput = sc.nextLine();
            if (userInput.equals("Q")) {
                breakFlag = true;
            } else {
                try {
                    userInputNumber = Integer.parseInt(userInput);
                    breakFlag = true;
                    readDetail(userInputNumber);
                } catch (NumberFormatException e) {
                    System.out.println("다시 입력해주세요.");
                }
            }
        }
    }

    public void readDetail(int userInputNumber) {
        PrintBoard pb = new PrintBoard();
        pb.printDetailBoard(userInputNumber);
    }
}

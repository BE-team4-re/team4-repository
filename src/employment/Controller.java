package src.employment;


import src.employment.board.BoardCategoryEnum;
import src.employment.elements.Buttons;
import src.employment.elements.buttons.*;
import src.employment.elements.content.PrintBoard;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Controller {

    private final Scanner sc = new Scanner(System.in);

    // 메인 페이지
    public void printMain() {
        String userInput = "";
        int pageIdx = 1;
        boolean breakFlag = false;
        while (!breakFlag) {
            // 페이지 타이틀.
            System.out.println("########################################################################");
            System.out.println("#################\t\t\t메인 페이지\t\t\t#################");
            System.out.println("########################################################################");
            System.out.println("-------------------------------------------------------------------------");
            // 내용 구역.
            PrintBoard pb = new PrintBoard();
            pb.printAllBoards(pageIdx);
            System.out.println("-------------------------------------------------------------------------");
            // 버튼 구역.
            List<Button> buttonList = new ArrayList<>();
            buttonList.add(Buttons.byJob);
            buttonList.add(Buttons.byRegion);
            buttonList.add(Buttons.prevPage);
            buttonList.add(Buttons.nextPage);
            buttonList.add(Buttons.first);
            for (Button button: buttonList) {
                button.printButton();
            }
            System.out.println("#########################################################################");
            System.out.print("입력하세요 (Q. 종료하기): ");
            userInput = sc.nextLine();
            switch (userInput) {
                case "R" -> {
                    breakFlag = true;
                    printByRegion();
                }
                case "J" -> {
                    breakFlag = true;
                    printByJob();
                }
                case ">" -> {
                    if (PrintBoard.maxPage > pageIdx) {
                        pageIdx++;
                    }
                }
                case "<" -> {
                    if (1 >= pageIdx) {
                        pageIdx = 1;
                    } else {
                        pageIdx--;
                    }
                }
                case "f" -> {
                    pageIdx = 1;
                }
                case "Q" -> {
                    breakFlag = true;
                }
                default -> {
                    System.out.println("다시 입력하세요.");
                }
            }
        }
    }

    // 지역별 조회 페이지.
    public void printByRegion() {
        String userInput = "";
        int pageIdx = 1;
        boolean breakFlag = false;
        while (!breakFlag) {
            // 페이지 타이틀.
            System.out.println("########################################################################");
            System.out.println("#################\t\t\t지역별 조회 페이지\t\t\t#################");
            System.out.println("########################################################################");
            System.out.println("-------------------------------------------------------------------------");
            // 내용 구역.
            PrintBoard pb = new PrintBoard();
            pb.printAllBoardsByRegion(pageIdx);
            System.out.println("-------------------------------------------------------------------------");
            // 버튼 구역.
            List<Button> buttonList = new ArrayList<>();
//            buttonList.add(Buttons.byJob);
//            buttonList.add(Buttons.byJobDetail);
            buttonList.add(Buttons.byRegionDetail);
            buttonList.add(Buttons.byRegionDetailAndJobDetail);
            buttonList.add(Buttons.prevPage);
            buttonList.add(Buttons.nextPage);
            buttonList.add(Buttons.back);
            for (Button button: buttonList) {
                button.printButton();
            }
            System.out.println("#########################################################################");
            System.out.print("입력하세요 (Q. 종료하기): ");
            userInput = sc.nextLine();
            switch (userInput) {
//                case "J" -> {
//                    breakFlag = true;
//                    printByJob();
//                }
//                case "D" -> {
//                    breakFlag = true;
//                    printByJobDetail();
//                }
                case "L" -> {
                    breakFlag = true;
                    printByRegionDetail();
                }
                case "S" -> {
                    breakFlag = true;
                    printByRegionDetailAndJobDetail();
                }
                case ">" -> {
                    if (PrintBoard.maxPage > pageIdx) {
                        pageIdx++;
                    }
                }
                case "<" -> {
                    if (1 >= pageIdx) {
                        pageIdx = 1;
                    } else {
                        pageIdx--;
                    }
                }
                case "b" -> {
                    breakFlag = true;
                    printMain();
                }
                case "Q" -> {
                    breakFlag = true;
                }
                default -> {
                    System.out.println("다시 입력하세요.");
                }
            }
        }
    }

    // 직무별 조회 페이지.
    public void printByJob() {
        String userInput = "";
        int pageIdx = 1;
        boolean breakFlag = false;
        while (!breakFlag) {
            // 페이지 타이틀.
            System.out.println("########################################################################");
            System.out.println("#################\t\t\t직무별 조회 페이지\t\t\t#################");
            System.out.println("########################################################################");
            System.out.println("-------------------------------------------------------------------------");
            // 내용 구역
            PrintBoard pb = new PrintBoard();
            pb.printAllBoardsByJob(pageIdx);
            System.out.println("-------------------------------------------------------------------------");
            // 버튼 구역.
            List<Button> buttonList = new ArrayList<>();
            buttonList.add(Buttons.byJobDetail);
//            buttonList.add(Buttons.byRegion);
//            buttonList.add(Buttons.byRegionDetail);
            buttonList.add(Buttons.byRegionDetailAndJobDetail);
            buttonList.add(Buttons.prevPage);
            buttonList.add(Buttons.nextPage);
            buttonList.add(Buttons.back);
            for (Button button: buttonList) {
                button.printButton();
            }
            System.out.println("#########################################################################");
            System.out.print("입력하세요 (Q. 종료하기): ");
            userInput = sc.nextLine();
            switch (userInput) {
//                case "R" -> {
//                    breakFlag = true;
//                    printByRegion();
//                }
                case "D" -> {
                    breakFlag = true;
                    printByJobDetail();
                }
//                case "L" -> {
//                    breakFlag = true;
//                    printByRegionDetail();
//                }
                case "S" -> {
                    breakFlag = true;
                    printByRegionDetailAndJobDetail();
                }
                case ">" -> {
                    if (PrintBoard.maxPage > pageIdx) {
                        pageIdx++;
                    }
                }
                case "<" -> {
                    if (1 >= pageIdx) {
                        pageIdx = 1;
                    } else {
                        pageIdx--;
                    }
                }
                case "b" -> {
                    breakFlag = true;
                    printMain();
                }
                case "Q" -> {
                    breakFlag = true;
                }
                default -> {
                    System.out.println("다시 입력하세요.");
                }
            }
        }
    }
    
    // 세부 지역 조회 페이지.
    public void printByRegionDetail() {
        String userInput = "";
        int pageIdx = 1;
        boolean breakFlag = false;
        System.out.print("조회할 지역을 입력하세요: ");
        String UserInput = sc.nextLine();
        BoardCategoryEnum[] values = BoardCategoryEnum.values();
        int subId = 0;
        for (BoardCategoryEnum value: values) {
            if (value.getCategoryName().equals(UserInput)) {
                subId = value.getSubId();
            }
        }
        while (!breakFlag) {
            // 페이지 타이틀.
            System.out.println("########################################################################");
            System.out.println("#################\t\t\t세부 지역 조회 페이지\t\t\t#################");
            System.out.println("########################################################################");
            System.out.println("-------------------------------------------------------------------------");
            // 내용 구역.
            PrintBoard pb = new PrintBoard();
            pb.printAllBoardsByRegionDetail(pageIdx, subId);
            System.out.println("-------------------------------------------------------------------------");
            // 버튼 리스트 정의.
            List<Button> buttonList = new ArrayList<>();
//            buttonList.add(Buttons.byJob);
//            buttonList.add(Buttons.byJobDetail);
//            buttonList.add(Buttons.byRegion);
            buttonList.add(Buttons.byRegionDetail);
            buttonList.add(Buttons.byRegionDetailAndJobDetail);
            buttonList.add(Buttons.prevPage);
            buttonList.add(Buttons.nextPage);
            buttonList.add(Buttons.back);
            for (Button button: buttonList) {
                button.printButton();
            }
            System.out.println("#########################################################################");
            System.out.print("입력하세요 (Q. 종료하기): ");
            userInput = sc.nextLine();
            switch (userInput) {
//                case "J" -> {
//                    breakFlag = true;
//                    printByJob();
//                }
//                case "D" -> {
//                    breakFlag = true;
//                    printByJobDetail();
//                }
//                case "R" -> {
//                    breakFlag = true;
//                    printByRegion();
//                }
                case "L" -> {
                    breakFlag = true;
                    printByRegionDetail();
                }
                case "S" -> {
                    breakFlag = true;
                    printByRegionDetailAndJobDetail();
                }
                case ">" -> {
                    if (PrintBoard.maxPage > pageIdx) {
                        pageIdx++;
                    }
                }
                case "<" -> {
                    if (1 >= pageIdx) {
                        pageIdx = 1;
                    } else {
                        pageIdx--;
                    }
                }
                case "b" -> {
                    breakFlag = true;
                    printMain();
                }
                case "Q" -> {
                    breakFlag = true;
                }
                default -> {
                    System.out.println("다시 입력하세요.");
                }
            }
        }
    }

    // 세부 직무 조회 페이지.
    public void printByJobDetail() {
        String userInput = "";
        int pageIdx = 1;
        boolean breakFlag = false;
        System.out.print("조회할 직무를 입력하세요: ");
        String UserInput = sc.nextLine();
        BoardCategoryEnum[] values = BoardCategoryEnum.values();
        int subId = 0;
        for (BoardCategoryEnum value: values) {
            if (value.getCategoryName().equals(UserInput)) {
                subId = value.getSubId();
            }
        }
        while (!breakFlag) {
            // 페이지 타이틀.
            System.out.println("########################################################################");
            System.out.println("#################\t\t\t세부 직무 조회 페이지\t\t\t#################");
            System.out.println("########################################################################");
            System.out.println("-------------------------------------------------------------------------");
            // 내용 구역.
            PrintBoard pb = new PrintBoard();
            pb.printAllBoardsByJobDetail(pageIdx, subId);
            System.out.println("-------------------------------------------------------------------------");
            // 버튼 리스트 정의.
            List<Button> buttonList = new ArrayList<>();
//            buttonList.add(Buttons.byJob);
            buttonList.add(Buttons.byJobDetail);
//            buttonList.add(Buttons.byRegion);
//            buttonList.add(Buttons.byRegionDetail);
            buttonList.add(Buttons.byRegionDetailAndJobDetail);
            buttonList.add(Buttons.prevPage);
            buttonList.add(Buttons.nextPage);
            buttonList.add(Buttons.back);
            for (Button button: buttonList) {
                button.printButton();
            }
            System.out.println("#########################################################################");
            System.out.print("입력하세요 (Q. 종료하기): ");
            userInput = sc.nextLine();
            switch (userInput) {
//                case "J" -> {
//                    breakFlag = true;
//                    printByJob();
//                }
                case "D" -> {
                    breakFlag = true;
                    printByJobDetail();
                }
//                case "R" -> {
//                    breakFlag = true;
//                    printByRegion();
//                }
//                case "L" -> {
//                    breakFlag = true;
//                    printByRegionDetail();
//                }
                case "S" -> {
                    breakFlag = true;
                    printByRegionDetailAndJobDetail();
                }
                case ">" -> {
                    if (PrintBoard.maxPage > pageIdx) {
                        pageIdx++;
                    }
                }
                case "<" -> {
                    if (1 >= pageIdx) {
                        pageIdx = 1;
                    } else {
                        pageIdx--;
                    }
                }
                case "b" -> {
                    breakFlag = true;
                    printMain();
                }
                case "Q" -> {
                    breakFlag = true;
                }
                default -> {
                    System.out.println("다시 입력하세요.");
                }
            }
        }
    }

    // 세부 지역 + 세부 직무로 찾기 페이지.
    public void printByRegionDetailAndJobDetail() {
        String userInput = "";
        int pageIdx = 1;
        boolean breakFlag = false;
        System.out.print("조회할 지역을 입력하세요: ");
        String UserInput1 = sc.nextLine();
        System.out.println();
        System.out.print("조회할 직무를 입력하세요: ");
        String UserInput2 = sc.nextLine();
        BoardCategoryEnum[] values = BoardCategoryEnum.values();
        int subId1 = 1;
        int subId2 = 19;
        for (BoardCategoryEnum value: values) {
            if (value.getCategoryName().equals(UserInput1)) {
                subId1 = value.getSubId();
            }
            if (value.getCategoryName().equals(UserInput2)) {
                subId2 = value.getSubId();
            }
        }
        while (!breakFlag) {
            // 페이지 타이틀.
            System.out.println("########################################################################");
            System.out.println("#################\t\t\t세부 검색 페이지\t\t\t#################");
            System.out.println("########################################################################");
            System.out.println("-------------------------------------------------------------------------");
            // 내용 구역.
            PrintBoard pb = new PrintBoard();
            pb.printAllBoardsByRegionDetailAndJobDetail(pageIdx, subId1, subId2);
            System.out.println("-------------------------------------------------------------------------");
            // 버튼 리스트 정의.
            List<Button> buttonList = new ArrayList<>();
//            buttonList.add(Buttons.byJob);
//            buttonList.add(Buttons.byJobDetail);
//            buttonList.add(Buttons.byRegion);
//            buttonList.add(Buttons.byRegionDetail);
            buttonList.add(Buttons.byRegionDetailAndJobDetail);
            buttonList.add(Buttons.prevPage);
            buttonList.add(Buttons.nextPage);
            buttonList.add(Buttons.back);
            for (Button button: buttonList) {
                button.printButton();
            }
            System.out.println("#########################################################################");
            System.out.print("입력하세요 (Q. 종료하기): ");
            userInput = sc.nextLine();
            switch (userInput) {
//                case "R" -> {
//                    breakFlag = true;
//                    printByRegion();
//                }
//                case "J" -> {
//                    breakFlag = true;
//                    printByJob();
//                }
//                case "L" -> {
//                    breakFlag = true;
//                    printByRegionDetail();
//                }
                case "S" -> {
                    breakFlag = true;
                    printByRegionDetailAndJobDetail();
                }
//                case "D" -> {
//                    breakFlag = true;
//                    printByJobDetail();
//                }
                case ">" -> {
                    if (PrintBoard.maxPage > pageIdx) {
                        pageIdx++;
                    }
                }
                case "<" -> {
                    if (1 >= pageIdx) {
                        pageIdx = 1;
                    } else {
                        pageIdx--;
                    }
                }
                case "b" -> {
                    breakFlag = true;
                    printMain();
                }
                case "Q" -> {
                    breakFlag = true;
                }
                default -> {
                    System.out.println("다시 입력하세요.");
                }
            }
        }
    }
}

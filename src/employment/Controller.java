package src.employment;


import src.employment.elements.Buttons;
import src.employment.elements.buttons.*;
import src.employment.elements.content.PrintBoard;
import src.employment.recordDAO.employmentBoard.ReadDetail;
import src.employment.recordDAO.employmentBoardScrap.Scraper;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Controller {

    private final Scanner sc = new Scanner(System.in);
    private final Scraper scraper = new Scraper();
    private final ReadDetail readDetail = new ReadDetail();

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
            buttonList.add(Buttons.detail);
            buttonList.add(Buttons.scrap);
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
                case "d" -> {
                    // 자세히보기
                    readDetail.selectTarget(sc);
                }
                case "s" -> {
                    // 스크랩
                    scraper.selectScrapTarget(sc);
                }
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
            System.out.println("#################\t\t\t지역별 조회\t\t\t#################");
            System.out.println("########################################################################");
            System.out.println("-------------------------------------------------------------------------");
            // 내용 구역.
            PrintBoard pb = new PrintBoard();
            pb.printAllBoardsByRegion(pageIdx);
            System.out.println("-------------------------------------------------------------------------");
            // 버튼 구역.
            List<Button> buttonList = new ArrayList<>();
            buttonList.add(Buttons.detail);
            buttonList.add(Buttons.scrap);
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
                case "d" -> {
                    // 자세히보기
                    readDetail.selectTarget(sc);
                }
                case "s" -> {
                    // 스크랩
                    scraper.selectScrapTarget(sc);
                }
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
            System.out.println("#################\t\t\t직무별 조회\t\t\t#################");
            System.out.println("########################################################################");
            System.out.println("-------------------------------------------------------------------------");
            // 내용 구역
            PrintBoard pb = new PrintBoard();
            pb.printAllBoardsByJob(pageIdx);
            System.out.println("-------------------------------------------------------------------------");
            // 버튼 구역.
            List<Button> buttonList = new ArrayList<>();
            buttonList.add(Buttons.detail);
            buttonList.add(Buttons.scrap);
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
                case "d" -> {
                    // 자세히보기
                    readDetail.selectTarget(sc);
                }
                case "s" -> {
                    // 스크랩
                    scraper.selectScrapTarget(sc);
                }
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
        PrintBoard pb = new PrintBoard();
        pb.printAllRegionList();
        System.out.print("조회할 지역을 입력하세요: ");
        userInput = sc.nextLine();
        // 문자열로 입력된 세부 지역을 db의 id로 변환하는 과정.
        int subId = pb.convertCategoryNameToSubCategoryId(userInput);
        while (!breakFlag) {
            // 페이지 타이틀.
            System.out.println("########################################################################");
            System.out.println("#################\t\t\t세부 지역 조회\t\t\t#################");
            System.out.println("########################################################################");
            System.out.println("-------------------------------------------------------------------------");
            // 내용 구역.
//            PrintBoard pb = new PrintBoard();
            pb.printAllBoardsByRegionDetail(pageIdx, subId);
            System.out.println("-------------------------------------------------------------------------");
            // 버튼 리스트 정의.
            List<Button> buttonList = new ArrayList<>();
            buttonList.add(Buttons.detail);
            buttonList.add(Buttons.scrap);
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
                case "d" -> {
                    // 자세히보기
                    readDetail.selectTarget(sc);
                }
                case "s" -> {
                    // 스크랩
                    scraper.selectScrapTarget(sc);
                }
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
        PrintBoard pb = new PrintBoard();
        pb.printAllJobList();
        System.out.print("조회할 직무를 입력하세요: ");
        userInput = sc.nextLine();
        // 문자열로 입력된 세부 지역을 db의 id로 변환하는 과정.
        int subId = pb.convertCategoryNameToSubCategoryId(userInput);
        while (!breakFlag) {
            // 페이지 타이틀.
            System.out.println("########################################################################");
            System.out.println("#################\t\t\t세부 직무 조회\t\t\t#################");
            System.out.println("########################################################################");
            System.out.println("-------------------------------------------------------------------------");
            // 내용 구역.
//            PrintBoard pb = new PrintBoard();
            pb.printAllBoardsByJobDetail(pageIdx, subId);
            System.out.println("-------------------------------------------------------------------------");
            // 버튼 리스트 정의.
            List<Button> buttonList = new ArrayList<>();
            buttonList.add(Buttons.detail);
            buttonList.add(Buttons.scrap);
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
                case "d" -> {
                    // 자세히보기
                    readDetail.selectTarget(sc);
                }
                case "s" -> {
                    // 스크랩
                    scraper.selectScrapTarget(sc);
                }
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
        String userInput1 = "";
        String userInput2 = "";
        int pageIdx = 1;
        boolean breakFlag = false;
        PrintBoard pb = new PrintBoard();
        pb.printAllRegionList();
        System.out.print("조회할 지역을 입력하세요: ");
        userInput1 = sc.nextLine();
        System.out.println();
        pb.printAllJobList();
        System.out.print("조회할 직무를 입력하세요: ");
        userInput2 = sc.nextLine();
        // 문자열로 입력된 세부 지역을 db의 id로 변환하는 과정.
        int subId1 = pb.convertCategoryNameToSubCategoryId(userInput1);
        int subId2 = pb.convertCategoryNameToSubCategoryId(userInput2);
        while (!breakFlag) {
            // 페이지 타이틀.
            System.out.println("########################################################################");
            System.out.println("#################\t\t\t지역과 직무 조합 검색\t\t\t#################");
            System.out.println("########################################################################");
            System.out.println("-------------------------------------------------------------------------");
            // 내용 구역.
//            PrintBoard pb = new PrintBoard();
            pb.printAllBoardsByRegionDetailAndJobDetail(pageIdx, subId1, subId2);
            System.out.println("-------------------------------------------------------------------------");
            // 버튼 리스트 정의.
            List<Button> buttonList = new ArrayList<>();
            buttonList.add(Buttons.detail);
            buttonList.add(Buttons.scrap);
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
                case "d" -> {
                    // 자세히보기
                    readDetail.selectTarget(sc);
                }
                case "s" -> {
                    // 스크랩
                    scraper.selectScrapTarget(sc);
                }
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

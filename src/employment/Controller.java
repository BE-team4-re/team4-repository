package src.employment;


import src.employment.board.BoardDTO;
import src.employment.elements.Buttons;
import src.employment.elements.buttons.*;
import src.employment.elements.content.PrintBoard;
import src.employment.recordDAO.employmentBoard.ReadDAO;
import src.employment.recordDAO.employmentBoard.ReadDetail;
import src.employment.recordDAO.employmentBoardScrap.CreateScrapDAO;
import src.employment.recordDAO.employmentBoardScrap.Scraper;
import src.user.UserMain;
import src.util.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Controller {

    private final Scanner sc = new Scanner(System.in);
    private final Scraper scraper = new Scraper();
    private final List<Button> buttonList = Buttons.getButtonList();

    // 채용 공고 메인 페이지.
    public void printMainPage() {
        String userInput = "";
        boolean breakFlag = false;
        List<Button> buttonList = new ArrayList<>();
        buttonList.add(Buttons.all);
        buttonList.add(Buttons.search);
        while (!breakFlag) {
            // 페이지 타이틀.
            System.out.println("#############################################################");
            System.out.println("#################\t\t\t채용 공고 게시판\t\t\t#################");
            System.out.println("#############################################################");
            System.out.println();
            System.out.println();
            System.out.println();
            // 버튼 구역.
            for (int i = 0; i < buttonList.size(); i ++) {
                System.out.printf("%d. ", i + 1);
                buttonList.get(i).printButton();
            }
            System.out.println();
            System.out.println("#############################################################");
            // 입력 구역.
            System.out.print("입력하세요 (M. 메인으로): ");
            userInput = sc.nextLine().strip();
            switch (userInput) {
                case "1" -> {
                    // 전체 게시물
                    breakFlag = true;
                    printAllBoardsPage();
                }
                case "2" -> {
                    // 검색
                    breakFlag = true;
                    printSearchMenu();
                }
                case "M" -> {
                    // 메인으로
                    breakFlag = true;
                }
                default -> {
                    // 잘못 입력 시 아무런 처리를 하지 않음.
                }
            }
        }
    }

    // 전체 게시물.
    public void printAllBoardsPage() {
        String userInput = "";
        int pageIdx = 1;
        boolean breakFlag = false;
        while (!breakFlag) {
            // 페이지 타이틀.
            System.out.println("#############################################################");
            System.out.println("#################\t\t\t전체 게시물\t\t\t#################");
            System.out.println("#############################################################");
            // 내용 구역.
            PrintBoard pb = new PrintBoard();
            pb.printAllBoards(pageIdx);
            System.out.println("-------------------------------------------------------------");
            System.out.printf("-----------\t\t\t%d 페이지 / %d 페이지\t\t\t-----------\n", pageIdx, PrintBoard.maxPage);
            System.out.println("-------------------------------------------------------------");
            // 페이징 구역.
            System.out.print("<. ");
            Buttons.prevPage.printButton();
            System.out.print("\t\t\t");
            System.out.print(">. ");
            Buttons.nextPage.printButton();
            System.out.println();
            System.out.println("-------------------------------------------------------------");
            // 버튼 구역.
            for (int i = 0; i < buttonList.size(); i ++) {
                System.out.printf("%d. ", i + 1);
                buttonList.get(i).printButton();
            }
            System.out.println();
            System.out.println("#############################################################");
            // 입력 구역.
            System.out.print("입력하세요 (M. 메인으로): ");
            userInput = sc.nextLine().strip();
            switch (userInput) {
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
                case "1" -> {
                    // 자세히보기
                    int userInputNumberDetail = isValidateDetail(sc);
                    if (userInputNumberDetail != 0) { // 유저가 취소하지 않았으면서 올바른 입력을 한 경우.
                        breakFlag = true;
                        printDetailBoardPage(userInputNumberDetail);
                    }
                }
                case "2" -> {
                    // 스크랩
                    int userInputNumberScrap = isValidateScrap(sc);
                    if (userInputNumberScrap != 0) { // 유저가 취소하지 않았으면서 올바른 입력을 한 경우.
                        breakFlag = true;
                        scrapBoardPage(userInputNumberScrap);
                    }
                }
                case "3" -> {
                    // 첫 페이지로
                    pageIdx = 1;
                }
                case "4" -> {
                    // 뒤로가기
                    breakFlag = true;
                    printMainPage();
                }
                default -> {
                    // 잘못 입력 시 아무런 처리를 하지 않음.
                }
            }
        }
    }

    // 자세히 보기 (사전 유효성 검증)
    // -> 유효한 입력을 받을 때 까지 입력을 받고, 유효하면 공고를 보여주는 메서드를 실행함.
    public int isValidateDetail(Scanner sc) {
        String userInput = "";
        int userInputNumber = 0;
        boolean breakFlag = false;
        while (!breakFlag) {
            System.out.print("조회할 게시물 번호를 입력하세요 (0. 취소): ");
            userInput = sc.nextLine();
            if (userInput.equals("0")) {
                breakFlag = true;
            } else {
                try {
                    userInputNumber = Integer.parseInt(userInput);
                    breakFlag = true; // 파싱이 성공하면 브레이크.
                } catch (NumberFormatException e) {
                    System.out.println("다시 입력해주세요.");
                }
            }
        }
        return userInputNumber;
    }

    // 자세히 보기 (실제 공고 보여주기)
    public void printDetailBoardPage(int userInputNumber) {
        String userInput = "";
        boolean breakFlag = false;
        PrintBoard pb = new PrintBoard();
        List<Button> buttonList = new ArrayList<>();
        buttonList.add(Buttons.scrap);
        buttonList.add(Buttons.all);
        buttonList.add(Buttons.search);
        while (!breakFlag) {
            // 페이지 타이틀.
            System.out.println("#############################################################");
            System.out.println("#################\t\t\t자세히 보기\t\t\t#################");
            System.out.println("#############################################################");
            // 내용 구역.
            pb.printDetailBoard(userInputNumber);
            System.out.println("-------------------------------------------------------------");
            // 버튼 구역.
            for (int i = 0; i < buttonList.size(); i ++) {
                System.out.printf("%d. ", i + 1);
                buttonList.get(i).printButton();
            }
            System.out.println();
            System.out.println("#############################################################");
            // 입력 구역.
            System.out.print("입력하세요 (M. 메인으로): ");
            userInput = sc.nextLine().strip();
            switch (userInput) {
                case "1" -> {
                    // 스크랩
                    // -> 어차피 상세 보기에서 스크랩 하겠다고 했기 때문에 유효성 검사는 할 필요 없음.
                    breakFlag = true;
                    scrapBoardPage(userInputNumber);
                }
                case "2" -> {
                    // 전체 게시물
                    breakFlag = true;
                    printAllBoardsPage();
                }
                case "3" -> {
                    // 검색
                    breakFlag = true;
                    printSearchMenu();
                }
                case "M" -> {
                    // 메인으로
                    breakFlag = true;
                }
                default -> {
                    // 잘못 입력 시 아무런 처리를 하지 않음.
                }
            }
        }
    }

    // 스크랩 (사전 유효성 검증)
    // -> 유효한 입력을 받을 때 까지 입력을 받고, 유효하면 스크랩 메서드를 실행함.
    public int isValidateScrap(Scanner sc) {
        String userInput = "";
        int userInputNumber = 0;
        boolean breakFlag = false;
        while (!breakFlag) {
            System.out.print("스크랩 할 게시물 번호를 입력하세요 (Q. 돌아가기): ");
            userInput = sc.nextLine();
            if (userInput.equals("Q")) {
                breakFlag = true;
            } else {
                try {
                    userInputNumber = Integer.parseInt(userInput);
                    breakFlag = true; // 파싱이 성공하면 브레이크.
                } catch (NumberFormatException e) {
                    System.out.println("다시 입력해주세요.");
                }
            }
        }
        return userInputNumber;
    }

    // 스크랩 (실제 공고 스크랩)
    public void scrapBoardPage(int userInputNumber) {
        String userInput = "";
        boolean breakFlag = false;
        List<Button> buttonList = new ArrayList<>();
        buttonList.add(Buttons.all);
        buttonList.add(Buttons.search);
        while (!breakFlag) {
            // 페이지 타이틀.
            System.out.println("#############################################################");
            System.out.println("#################\t\t\t스크랩\t\t\t#################");
            System.out.println("#############################################################");
            // 스크랩 구역.
            
            ReadDAO readDAO = new ReadDAO();
            Response<BoardDTO> readResponse = readDAO.read(userInputNumber);
            if (readResponse.isSuccess()) {
                BoardDTO boardDTO = readResponse.getData();
                int bid = boardDTO.getEmploymentBoardId();
                CreateScrapDAO createScrapDAO = new CreateScrapDAO();
                Response<String> createResponse = createScrapDAO.create(UserMain.id, bid);
                if (createResponse.isSuccess()) {
                    System.out.println("성공적으로 스크랩 되었습니다.");
                } else {
                    System.out.println("스크랩에 실패하였습니다.");
                }
            } else {
                System.out.println("게시글 불러오기 실패.");
            }
            System.out.println("-------------------------------------------------------------");
            // 버튼 구역.
            for (int i = 0; i < buttonList.size(); i ++) {
                System.out.printf("%d. ", i + 1);
                buttonList.get(i).printButton();
            }
            System.out.println();
            System.out.println("#############################################################");
            // 입력 구역.
            System.out.print("입력하세요 (M. 메인으로): ");
            userInput = sc.nextLine().strip();
            switch (userInput) {
                case "1" -> {
                    // 전체 게시물
                    breakFlag = true;
                    printAllBoardsPage();
                }
                case "2" -> {
                    // 검색
                    breakFlag = true;
                    printSearchMenu();
                }
                case "M" -> {
                    // 메인으로
                    breakFlag = true;
                }
                default -> {
                    // 잘못 입력 시 아무런 처리를 하지 않음.
                }
            }
        }
    }
    
    // 검색 메뉴.
    public void printSearchMenu() {
        String userInput = "";
        boolean breakFlag = false;
        List<Button> buttonList = new ArrayList<>();
        buttonList.add(Buttons.byRegionDetailAndJobDetail);
        buttonList.add(Buttons.byRegionDetail);
        buttonList.add(Buttons.byJobDetail);
        buttonList.add(Buttons.back);
        while (!breakFlag) {
            // 페이지 타이틀.
            System.out.println("#############################################################");
            System.out.println("#################\t\t\t검색 메뉴\t\t\t#################");
            System.out.println("#############################################################");
            System.out.println();
            System.out.println();
            System.out.println();
            // 버튼 구역.
            for (int i = 0; i < buttonList.size(); i ++) {
                System.out.printf("%d. ", i + 1);
                buttonList.get(i).printButton();
            }
            System.out.println();
            System.out.println("#############################################################");
            // 입력 구역.
            System.out.print("입력하세요 (M. 메인으로): ");
            userInput = sc.nextLine().strip();
            switch (userInput) {
                case "1" -> {
                    // 종합 검색
                    breakFlag = true;
                    printByRegionDetailAndJobDetail();
                }
                case "2" -> {
                    // 지역별
                    breakFlag = true;
                    printByRegionDetail();
                }
                case "3" -> {
                    // 직무별
                    breakFlag = true;
                    printByJobDetail();
                }
                case "4" -> {
                    // 뒤로가기
                    breakFlag = true;
                    printMainPage();
                }
                case "M" -> {
                    // 메인으로
                    breakFlag = true;
                }
                default -> {
                    // 잘못 입력 시 아무런 처리를 하지 않음.
                }
            }
        }
    }
    
    // 지역 검색 페이지.
    public void printByRegionDetail() {
        String userInput = "";
        int pageIdx = 1;
        boolean breakFlag = false;
        PrintBoard pb = new PrintBoard();
        pb.printAllRegionList();
        System.out.print("조회할 지역을 입력하세요: ");
        userInput = sc.nextLine().strip();
        // 문자열로 입력된 세부 지역을 db의 id로 변환하는 과정.
        int subId = pb.convertCategoryNameToSubCategoryId(userInput);
        while (!breakFlag) {
            // 페이지 타이틀.
            System.out.println("#############################################################");
            System.out.println("#################\t\t\t지역으로 검색\t\t\t#################");
            System.out.println("#############################################################");
            // 내용 구역.
            pb.printAllBoardsByRegionDetail(pageIdx, subId);
            System.out.println("-------------------------------------------------------------");
            System.out.printf("-----------\t\t\t%d 페이지 / %d 페이지\t\t\t-----------\n", pageIdx, PrintBoard.maxPage);
            System.out.println("-------------------------------------------------------------");
            // 페이징 구역.
            System.out.print("<. ");
            Buttons.prevPage.printButton();
            System.out.print("\t\t\t");
            System.out.print(">. ");
            Buttons.nextPage.printButton();
            System.out.println();
            System.out.println("-------------------------------------------------------------");
            // 버튼 구역.
            for (int i = 0; i < buttonList.size(); i ++) {
                System.out.printf("%d. ", i + 1);
                buttonList.get(i).printButton();
            }
            System.out.println();
            System.out.println("#############################################################");
            // 입력 구역.
            System.out.print("입력하세요 (M. 메인으로): ");
            userInput = sc.nextLine().strip();
            switch (userInput) {
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
                case "1" -> {
                    // 자세히보기
                    int userInputNumberDetail = isValidateDetail(sc);
                    if (userInputNumberDetail != 0) { // 유저가 취소하지 않았으면서 올바른 입력을 한 경우.
                        breakFlag = true;
                        printDetailBoardPage(userInputNumberDetail);
                    }
                }
                case "2" -> {
                    // 스크랩
                    int userInputNumberScrap = isValidateScrap(sc);
                    if (userInputNumberScrap != 0) { // 유저가 취소하지 않았으면서 올바른 입력을 한 경우.
                        breakFlag = true;
                        scrapBoardPage(userInputNumberScrap);
                    }
                }
                case "3" -> {
                    // 첫 페이지로
                    pageIdx = 1;
                }
                case "4" -> {
                    // 뒤로가기
                    breakFlag = true;
                    printSearchMenu();
                }
                case "M" -> {
                    breakFlag = true;
                }
                default -> {
                    // 잘못 입력 시 아무런 처리를 하지 않음.
                }
            }
        }
    }

    // 직무 검색 페이지.
    public void printByJobDetail() {
        String userInput = "";
        int pageIdx = 1;
        boolean breakFlag = false;
        PrintBoard pb = new PrintBoard();
        pb.printAllJobList();
        System.out.print("조회할 직무를 입력하세요: ");
        userInput = sc.nextLine().strip();
        // 문자열로 입력된 세부 지역을 db의 id로 변환하는 과정.
        int subId = pb.convertCategoryNameToSubCategoryId(userInput);
        while (!breakFlag) {
            // 페이지 타이틀.
            System.out.println("#############################################################");
            System.out.println("#################\t\t\t직무로 검색\t\t\t#################");
            System.out.println("#############################################################");
            // 내용 구역.
            pb.printAllBoardsByJobDetail(pageIdx, subId);
            System.out.println("-------------------------------------------------------------");
            System.out.printf("-----------\t\t\t%d 페이지 / %d 페이지\t\t\t-----------\n", pageIdx, PrintBoard.maxPage);
            System.out.println("-------------------------------------------------------------");
            // 페이징 구역.
            System.out.print("<. ");
            Buttons.prevPage.printButton();
            System.out.print("\t\t\t");
            System.out.print(">. ");
            Buttons.nextPage.printButton();
            System.out.println();
            System.out.println("-------------------------------------------------------------");
            // 버튼 구역.
            for (int i = 0; i < buttonList.size(); i ++) {
                System.out.printf("%d. ", i + 1);
                buttonList.get(i).printButton();
            }
            System.out.println();
            System.out.println("#############################################################");
            System.out.print("입력하세요 (M. 메인으로): ");
            userInput = sc.nextLine().strip();
            switch (userInput) {
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
                case "1" -> {
                    // 자세히보기
                    int userInputNumberDetail = isValidateDetail(sc);
                    if (userInputNumberDetail != 0) { // 유저가 취소하지 않았으면서 올바른 입력을 한 경우.
                        breakFlag = true;
                        printDetailBoardPage(userInputNumberDetail);
                    }
                }
                case "2" -> {
                    // 스크랩
                    int userInputNumberScrap = isValidateScrap(sc);
                    if (userInputNumberScrap != 0) { // 유저가 취소하지 않았으면서 올바른 입력을 한 경우.
                        breakFlag = true;
                        scrapBoardPage(userInputNumberScrap);
                    }
                }
                case "3" -> {
                    // 첫 페이지로
                    pageIdx = 1;
                }
                case "4" -> {
                    // 뒤로가기
                    breakFlag = true;
                    printSearchMenu();
                }
                case "M" -> {
                    breakFlag = true;
                }
                default -> {
                    // 잘못 입력 시 아무런 처리를 하지 않음.
                }
            }
        }
    }

    // 종합 검색 페이지.
    public void printByRegionDetailAndJobDetail() {
        String userInput = "";
        String userInput1 = "";
        String userInput2 = "";
        int pageIdx = 1;
        boolean breakFlag = false;
        PrintBoard pb = new PrintBoard();
        pb.printAllRegionList();
        System.out.print("조회할 지역을 입력하세요: ");
        userInput1 = sc.nextLine().strip();
        System.out.println();
        pb.printAllJobList();
        System.out.print("조회할 직무를 입력하세요: ");
        userInput2 = sc.nextLine().strip();
        // 문자열로 입력된 세부 지역을 db의 id로 변환하는 과정.
        int subId1 = pb.convertCategoryNameToSubCategoryId(userInput1);
        int subId2 = pb.convertCategoryNameToSubCategoryId(userInput2);
        while (!breakFlag) {
            // 페이지 타이틀.
            System.out.println("#############################################################");
            System.out.println("#################\t\t\t종합 검색\t\t\t#################");
            System.out.println("#############################################################");
            // 내용 구역.
            pb.printAllBoardsByRegionDetailAndJobDetail(pageIdx, subId1, subId2);
            System.out.println("-------------------------------------------------------------");
            System.out.printf("-----------\t\t\t%d 페이지 / %d 페이지\t\t\t-----------\n", pageIdx, PrintBoard.maxPage);
            System.out.println("-------------------------------------------------------------");
            // 페이징 구역.
            System.out.print("<. ");
            Buttons.prevPage.printButton();
            System.out.print("\t\t\t");
            System.out.print(">. ");
            Buttons.nextPage.printButton();
            System.out.println();
            System.out.println("-------------------------------------------------------------");
            // 버튼 구역.
            for (int i = 0; i < buttonList.size(); i ++) {
                System.out.printf("%d. ", i + 1);
                buttonList.get(i).printButton();
            }
            System.out.println();
            System.out.println("#############################################################");
            System.out.print("입력하세요 (M. 메인으로): ");
            userInput = sc.nextLine().strip();
            switch (userInput) {
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
                case "1" -> {
                    // 자세히보기
                    int userInputNumberDetail = isValidateDetail(sc);
                    if (userInputNumberDetail != 0) { // 유저가 취소하지 않았으면서 올바른 입력을 한 경우.
                        breakFlag = true;
                        printDetailBoardPage(userInputNumberDetail);
                    }
                }
                case "2" -> {
                    // 스크랩
                    int userInputNumberScrap = isValidateScrap(sc);
                    if (userInputNumberScrap != 0) { // 유저가 취소하지 않았으면서 올바른 입력을 한 경우.
                        breakFlag = true;
                        scrapBoardPage(userInputNumberScrap);
                    }
                }
                case "3" -> {
                    // 첫 페이지로
                    pageIdx = 1;
                }
                case "4" -> {
                    // 뒤로가기
                    breakFlag = true;
                    printSearchMenu();
                }
                case "M" -> {
                    breakFlag = true;
                }
                default -> {
                    // 잘못 입력 시 아무런 처리를 하지 않음.
                }
            }
        }
    }
}
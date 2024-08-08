package src.employment;


import src.employment.board.BoardDTO;
import src.employment.elements.Buttons;
import src.employment.elements.buttons.*;
import src.employment.elements.content.PrintBoard;
import src.employment.recordDAO.employmentBoard.ReadDAO;
import src.employment.recordDAO.employmentBoardScrap.CreateScrapDAO;
import src.employment.recordDAO.employmentBoardScrap.DeleteScrapDAO;
import src.employment.recordDAO.employmentBoardScrap.ReadScrapDAO;
import src.user.UserMain;
import src.util.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Controller {
    private final Scanner sc = new Scanner(System.in);
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
            System.out.println("\n=============================================");
            System.out.println("              ★ 채용 공고 게시판 ★              ");
            System.out.println("=============================================");
            // 버튼 구역.
            for (int i = 0; i < buttonList.size(); i ++) {
                System.out.printf("%d. ", i + 1);
                buttonList.get(i).printButton();
            }
            System.out.println();
            System.out.println("----------------------------------------------");
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

    // 전체 공고.
    public void printAllBoardsPage() {
        String userInput = "";
        int pageIdx = 1;
        boolean breakFlag = false;
        while (!breakFlag) {
            // 페이지 타이틀.
            System.out.println("\n=============================================");
            System.out.println("              ★ 모든 채용공고 ★              ");
            System.out.println("=============================================");
            // 내용 구역.
            PrintBoard pb = new PrintBoard();
            pb.printAllBoards(pageIdx);
            System.out.println("-------------------------------------------------------");
            if (PrintBoard.allMaxPage == 0) {
                System.out.printf("-----------\t\t\t%d 페이지 / 1 페이지\t\t\t-----------\n", pageIdx);
            } else {
                System.out.printf("-----------\t\t\t%d 페이지 / %d 페이지\t\t\t-----------\n", pageIdx, PrintBoard.allMaxPage);
            }
            System.out.println("-------------------------------------------------------");
            // 페이징 구역.
            System.out.print("<. ");
            Buttons.prevPage.printButton();
            System.out.print("\t\t\t\t\t\t\t\t");
            System.out.print(">. ");
            Buttons.nextPage.printButton();
            System.out.println();
            System.out.println("-------------------------------------------------------");
            // 버튼 구역.
            for (int i = 0; i < buttonList.size(); i ++) {
                System.out.printf("%d. ", i + 1);
                buttonList.get(i).printButton();
            }
            System.out.println();
            System.out.println("-------------------------------------------------------");
            // 입력 구역.
            System.out.print("입력하세요 (M. 메인으로): ");
            userInput = sc.nextLine().strip();
            switch (userInput) {
                case ">" -> {
                    if (PrintBoard.allMaxPage > pageIdx) {
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
                        printDetailBoardPage(userInputNumberDetail, "printAllBoardsPage");
                    }
                }
                case "2" -> {
                    // 첫 페이지로
                    pageIdx = 1;
                }
                case "3" -> {
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

    // 자세히 보기 (실제 공고 보여주기.)
    public void printDetailBoardPage(int userInputNumber, String pageName) {
        String userInput = "";
        boolean breakFlag = false;
        PrintBoard pb = new PrintBoard();
        // 유저가 이미 스크랩 한 적 있는 공고인지 아닌지 확인하는 작업.
        ReadScrapDAO readScrapDAO = new ReadScrapDAO();
        Response<Integer> checkResponse = readScrapDAO.checkScrap(userInputNumber);
        boolean isScrapped = checkResponse.isSuccess();
        List<Button> buttonList = new ArrayList<>();
        if (isScrapped) { // 스크랩 한 적 있다면,
            buttonList.add(Buttons.scrapOff);
        } else { // 스크랩 한 적 없다면,
            buttonList.add(Buttons.scrap);
        }
        buttonList.add(Buttons.back);
        while (!breakFlag) {
            // 페이지 타이틀.
            System.out.println("\n=============================================");
            System.out.println("               ★ 자세히 보기 ★               ");
            System.out.println("=============================================");
            // 내용 구역.
            pb.printDetailBoard(userInputNumber);
            System.out.println("-------------------------------------------------------");
            // 버튼 구역.
            for (int i = 0; i < buttonList.size(); i ++) {
                System.out.printf("%d. ", i + 1);
                buttonList.get(i).printButton();
            }
            System.out.println();
            System.out.println("-------------------------------------------------------");
            // 입력 구역.
            System.out.print("입력하세요 (M. 메인으로): ");
            userInput = sc.nextLine().strip();
            switch (userInput) {
                case "1" -> {
                    // 스크랩 혹은 스크랩 해제
                    // -> 이 상황은 상세 보기에서 유저 스스로 스크랩 하겠다고 선택한 상황이기 때문에 선택한 공고 번호의 유효성 검사는 할 필요가 없다.
                    breakFlag = true;
                    if (isScrapped) { // 스크랩 한 적 있다면,
                        scrapOffBoardPage(userInputNumber, pageName);
                    } else { // 스크랩 한 적 없다면,
                        scrapBoardPage(userInputNumber, pageName);
                    }
                }
                case "2" -> {
                    // 뒤로가기
                    breakFlag = true;
                    switch (pageName) {
                        case "printAllBoardsPage" -> {
                            printAllBoardsPage();
                        }
                        case "printByRegionDetail" -> {
                            printByRegionDetail();
                        }
                        case "printByJobDetail" -> {
                            printByJobDetail();
                        }
                        case "printByRegionDetailAndJobDetail" -> {
                            printByRegionDetailAndJobDetail();
                        }
                        default -> {
                            // 입력값 없거나 잘못 들어올 시 채용공고 메인 페이지로 이동함.
                            printMainPage();
                        }
                    }
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

    // 스크랩 (실제 공고 스크랩.)
    public void scrapBoardPage(int userInputNumber, String pageName) {
        String userInput = "";
        boolean breakFlag = false;
        List<Button> buttonList = new ArrayList<>();
        buttonList.add(Buttons.all);
        buttonList.add(Buttons.back);
        while (!breakFlag) {
            // 페이지 타이틀.
            System.out.println("\n=============================================");
            System.out.println("                  ★ 스크랩 ★                   ");
            System.out.println("=============================================");
            // 스크랩 구역.
            ReadDAO readDAO = new ReadDAO();
            Response<BoardDTO> readResponse = readDAO.read(userInputNumber);
            if (readResponse.isSuccess()) { // 채용공고가 존재하는지 확인.
                BoardDTO boardDTO = readResponse.getData();
                int bid = boardDTO.getEmploymentBoardId();
                ReadScrapDAO readScrapDAO = new ReadScrapDAO();
                Response<Integer> checkResponse = readScrapDAO.checkScrap(bid);
                if (!checkResponse.isSuccess()) { // 이미 스크랩 된 공고인지 여부 확인.
                    CreateScrapDAO createScrapDAO = new CreateScrapDAO();
                    Response<String> createResponse = createScrapDAO.create(UserMain.id, bid);
                    if (createResponse.isSuccess()) {
                        System.out.println("성공적으로 스크랩 되었습니다.");
                    } else {
                        System.out.println("스크랩에 실패하였습니다.");
                    }
                } else {
                    System.out.println("이미 스크랩 된 채용공고 입니다.");
                }
            } else {
                System.out.println("채용공고가 더 이상 존재하지 않습니다.");
            }
            System.out.println("-------------------------------------------------------");
            // 버튼 구역.
            for (int i = 0; i < buttonList.size(); i ++) {
                System.out.printf("%d. ", i + 1);
                buttonList.get(i).printButton();
            }
            System.out.println();
            System.out.println("======================================================");
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
                    // 뒤로가기
                    breakFlag = true;
                    switch (pageName) {
                        case "printAllBoardsPage" -> {
                            printAllBoardsPage();
                        }
                        case "printByRegionDetail" -> {
                            printByRegionDetail();
                        }
                        case "printByJobDetail" -> {
                            printByJobDetail();
                        }
                        case "printByRegionDetailAndJobDetail" -> {
                            printByRegionDetailAndJobDetail();
                        }
                        default -> {
                            // 입력값 없거나 잘못 들어올 시 채용공고 메인 페이지로 이동함.
                            printMainPage();
                        }
                    }
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

    // 스크랩 해제.
    public void scrapOffBoardPage(int userInputNumber, String pageName) {
        String userInput = "";
        boolean breakFlag = false;
        List<Button> buttonList = new ArrayList<>();
        buttonList.add(Buttons.all);
        buttonList.add(Buttons.back);
        while (!breakFlag) {
            // 페이지 타이틀.
            System.out.println("\n=============================================");
            System.out.println("                ★ 스크랩 해제 ★                ");
            System.out.println("=============================================");
            // 스크랩 구역.
            ReadDAO readDAO = new ReadDAO();
            Response<BoardDTO> readResponse = readDAO.read(userInputNumber);
            if (readResponse.isSuccess()) {
                BoardDTO boardDTO = readResponse.getData();
                int bid = boardDTO.getEmploymentBoardId();
                ReadScrapDAO readScrapDAO = new ReadScrapDAO();
                Response<Integer> checkResponse = readScrapDAO.checkScrap(bid);
                if (checkResponse.isSuccess()) { // 스크랩 된 적 있는 공고인지 확인.
                    DeleteScrapDAO deleteScrapDAO = new DeleteScrapDAO();
                    Response<String> deleteResponse = deleteScrapDAO.delete(UserMain.id, bid);
                    if (deleteResponse.isSuccess()) {
                        System.out.println("스크랩 해제 되었습니다.");
                    } else {
                        System.out.println("스크랩 해제에 실패하였습니다.");
                    }
                } else {
                    System.out.println("한 번도 스크랩 된 적 없는 채용공고 입니다.");
                }
            } else {
                System.out.println("채용공고가 더 이상 존재하지 않습니다.");
            }
            System.out.println("-------------------------------------------------------");
            // 버튼 구역.
            for (int i = 0; i < buttonList.size(); i ++) {
                System.out.printf("%d. ", i + 1);
                buttonList.get(i).printButton();
            }
            System.out.println();
            System.out.println("-------------------------------------------------------");
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
                    // 뒤로가기
                    breakFlag = true;
                    switch (pageName) {
                        case "printAllBoardsPage" -> {
                            printAllBoardsPage();
                        }
                        case "printByRegionDetail" -> {
                            printByRegionDetail();
                        }
                        case "printByJobDetail" -> {
                            printByJobDetail();
                        }
                        case "printByRegionDetailAndJobDetail" -> {
                            printByRegionDetailAndJobDetail();
                        }
                        default -> {
                            // 입력값 없거나 잘못 들어올 시 채용공고 메인 페이지로 이동함.
                            printMainPage();
                        }
                    }
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
        buttonList.add(Buttons.byKeyword);
        buttonList.add(Buttons.byRegionDetailAndJobDetail);
        buttonList.add(Buttons.byRegionDetail);
        buttonList.add(Buttons.byJobDetail);
        buttonList.add(Buttons.back);
        while (!breakFlag) {
            // 페이지 타이틀.
            System.out.println("\n=============================================");
            System.out.println("                   ★ 검색 ★                    ");
            System.out.println("=============================================");
            // 버튼 구역.
            for (int i = 0; i < buttonList.size(); i ++) {
                System.out.printf("%d. ", i + 1);
                buttonList.get(i).printButton();
            }
            System.out.println();
            System.out.println("-------------------------------------------------------");
            // 입력 구역.
            System.out.print("입력하세요 (M. 메인으로): ");
            userInput = sc.nextLine().strip();
            switch (userInput) {
                case "1" -> {
                    // 전체 검색
                    breakFlag = true;
                    printByKeyword();
                }
                case "2" -> {
                    // 종합 검색
                    breakFlag = true;
                    printByRegionDetailAndJobDetail();
                }
                case "3" -> {
                    // 지역별
                    breakFlag = true;
                    printByRegionDetail();
                }
                case "4" -> {
                    // 직무별
                    breakFlag = true;
                    printByJobDetail();
                }
                case "5" -> {
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

    // 키워드 검색 페이지.
    public void printByKeyword() {
        String userInput = "";
        int pageIdx = 1;
        boolean breakFlag = false;
        PrintBoard pb = new PrintBoard();
        System.out.print("검색할 키워드를 입력하세요 (0. 취소): ");
        userInput = sc.nextLine().strip();
        if (userInput.equals("0")) {
            printSearchMenu();
            return;
        }
        String userInputKeyword = userInput;
        while (!breakFlag) {
            // 페이지 타이틀.
            System.out.println("\n=============================================");
            System.out.println("            ★ 키워드로 검색 ★             ");
            System.out.println("=============================================");
            // 내용 구역.
            pb.printAllBoardsByKeyword(pageIdx, userInputKeyword);
            System.out.println("-------------------------------------------------------");
            if (PrintBoard.keywordMaxPage == 0) {
                System.out.printf("-----------\t\t\t%d 페이지 / 1 페이지\t\t\t-----------\n", pageIdx);
            } else {
                System.out.printf("-----------\t\t\t%d 페이지 / %d 페이지\t\t\t-----------\n", pageIdx, PrintBoard.keywordMaxPage);
            }
            System.out.println("-------------------------------------------------------");
            // 페이징 구역.
            System.out.print("<. ");
            Buttons.prevPage.printButton();
            System.out.print("\t\t\t\t\t\t\t\t");
            System.out.print(">. ");
            Buttons.nextPage.printButton();
            System.out.println();
            System.out.println("-------------------------------------------------------");
            // 버튼 구역.
            for (int i = 0; i < buttonList.size(); i ++) {
                System.out.printf("%d. ", i + 1);
                buttonList.get(i).printButton();
            }
            System.out.println();
            System.out.println("-------------------------------------------------------");
            // 입력 구역.
            System.out.print("입력하세요 (M. 메인으로): ");
            userInput = sc.nextLine().strip();
            switch (userInput) {
                case ">" -> {
                    if (PrintBoard.keywordMaxPage > pageIdx) {
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
                        printDetailBoardPage(userInputNumberDetail, "printByRegionDetail");
                    }
                }
                case "2" -> {
                    // 첫 페이지로
                    pageIdx = 1;
                }
                case "3" -> {
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

    // 지역 검색 페이지.
    public void printByRegionDetail() {
        String userInput = "";
        int pageIdx = 1;
        boolean breakFlag = false;
        PrintBoard pb = new PrintBoard();
        pb.printAllRegionList();
        System.out.print("조회할 지역을 입력하세요 (0. 취소): ");
        userInput = sc.nextLine().strip();
        if (userInput.equals("0")) {
            printSearchMenu();
            return;
        }
        // 문자열로 입력된 세부 지역을 db의 id로 변환하는 과정.
        int subId = pb.interpretUserInputCategoryValue(userInput);
        while (!breakFlag) {
            // 페이지 타이틀.
            System.out.println("\n=============================================");
            System.out.println("               ★ 지역으로 검색 ★               ");
            System.out.println("=============================================");
            // 내용 구역.
            pb.printAllBoardsByRegionDetail(pageIdx, subId);
            System.out.println("-------------------------------------------------------");
            if (PrintBoard.regionMaxPage == 0) {
                System.out.printf("-----------\t\t\t%d 페이지 / 1 페이지\t\t\t-----------\n", pageIdx);
            } else {
                System.out.printf("-----------\t\t\t%d 페이지 / %d 페이지\t\t\t-----------\n", pageIdx, PrintBoard.regionMaxPage);
            }
            System.out.println("-------------------------------------------------------");
            // 페이징 구역.
            System.out.print("<. ");
            Buttons.prevPage.printButton();
            System.out.print("\t\t\t\t\t\t\t\t");
            System.out.print(">. ");
            Buttons.nextPage.printButton();
            System.out.println();
            System.out.println("-------------------------------------------------------");
            // 버튼 구역.
            for (int i = 0; i < buttonList.size(); i ++) {
                System.out.printf("%d. ", i + 1);
                buttonList.get(i).printButton();
            }
            System.out.println();
            System.out.println("-------------------------------------------------------");
            // 입력 구역.
            System.out.print("입력하세요 (M. 메인으로): ");
            userInput = sc.nextLine().strip();
            switch (userInput) {
                case ">" -> {
                    if (PrintBoard.regionMaxPage > pageIdx) {
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
                        printDetailBoardPage(userInputNumberDetail, "printByRegionDetail");
                    }
                }
                case "2" -> {
                    // 첫 페이지로
                    pageIdx = 1;
                }
                case "3" -> {
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
        System.out.print("조회할 직무를 입력하세요 (0. 취소): ");
        userInput = sc.nextLine().strip();
        if (userInput.equals("0")) {
            printSearchMenu();
            return;
        }
        // 문자열로 입력된 세부 지역을 db의 id로 변환하는 과정.
        int subId = pb.interpretUserInputCategoryValue(userInput);
        while (!breakFlag) {
            // 페이지 타이틀.
            System.out.println("\n=============================================");
            System.out.println("               ★ 직무로 검색 ★               ");
            System.out.println("=============================================");
            // 내용 구역.
            pb.printAllBoardsByJobDetail(pageIdx, subId);
            System.out.println("-------------------------------------------------------");
            if (PrintBoard.jobMaxPage == 0) {
                System.out.printf("-----------\t\t\t%d 페이지 / 1 페이지\t\t\t-----------\n", pageIdx);
            } else {
                System.out.printf("-----------\t\t\t%d 페이지 / %d 페이지\t\t\t-----------\n", pageIdx, PrintBoard.jobMaxPage);
            }
            System.out.println("-------------------------------------------------------");
            // 페이징 구역.
            System.out.print("<. ");
            Buttons.prevPage.printButton();
            System.out.print("\t\t\t\t\t\t\t\t");
            System.out.print(">. ");
            Buttons.nextPage.printButton();
            System.out.println();
            System.out.println("-------------------------------------------------------");
            // 버튼 구역.
            for (int i = 0; i < buttonList.size(); i ++) {
                System.out.printf("%d. ", i + 1);
                buttonList.get(i).printButton();
            }
            System.out.println();
            System.out.println("-------------------------------------------------------");
            System.out.print("입력하세요 (M. 메인으로): ");
            userInput = sc.nextLine().strip();
            switch (userInput) {
                case ">" -> {
                    if (PrintBoard.jobMaxPage > pageIdx) {
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
                        printDetailBoardPage(userInputNumberDetail, "printByJobDetail");
                    }
                }
                case "2" -> {
                    // 첫 페이지로
                    pageIdx = 1;
                }
                case "3" -> {
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
        System.out.print("조회할 지역의 번호를 입력하세요 (0. 취소): ");
        userInput1 = sc.nextLine().strip();
        if (userInput1.equals("0")) {
            printSearchMenu();
            return;
        }
        System.out.println();
        pb.printAllJobList();
        System.out.print("조회할 직무의 번호를 입력하세요 (0. 취소): ");
        userInput2 = sc.nextLine().strip();
        if (userInput2.equals("0")) {
            printSearchMenu();
            return;
        }
        // 문자열로 입력된 세부 지역을 db의 id로 변환하는 과정.
        int subId1 = pb.interpretUserInputCategoryValue(userInput1);
        int subId2 = pb.interpretUserInputCategoryValue(userInput2);
        while (!breakFlag) {
            // 페이지 타이틀.
            System.out.println("\n=============================================");
            System.out.println("               ★ 종합 검색 ★               ");
            System.out.println("=============================================");
            // 내용 구역.
            pb.printAllBoardsByRegionDetailAndJobDetail(pageIdx, subId1, subId2);
            System.out.println("-------------------------------------------------------");
            if (PrintBoard.mixMaxPage == 0) {
                System.out.printf("-----------\t\t\t%d 페이지 / 1 페이지\t\t\t-----------\n", pageIdx);
            } else {
                System.out.printf("-----------\t\t\t%d 페이지 / %d 페이지\t\t\t-----------\n", pageIdx, PrintBoard.mixMaxPage);
            }
            System.out.println("-------------------------------------------------------");
            // 페이징 구역.
            System.out.print("<. ");
            Buttons.prevPage.printButton();
            System.out.print("\t\t\t\t\t\t\t\t");
            System.out.print(">. ");
            Buttons.nextPage.printButton();
            System.out.println();
            System.out.println("-------------------------------------------------------");
            // 버튼 구역.
            for (int i = 0; i < buttonList.size(); i ++) {
                System.out.printf("%d. ", i + 1);
                buttonList.get(i).printButton();
            }
            System.out.println();
            System.out.println("-------------------------------------------------------");
            System.out.print("입력하세요 (M. 메인으로): ");
            userInput = sc.nextLine().strip();
            switch (userInput) {
                case ">" -> {
                    if (PrintBoard.mixMaxPage > pageIdx) {
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
                        printDetailBoardPage(userInputNumberDetail, "printByRegionDetailAndJobDetail");
                    }
                }
                case "2" -> {
                    // 첫 페이지로
                    pageIdx = 1;
                }
                case "3" -> {
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
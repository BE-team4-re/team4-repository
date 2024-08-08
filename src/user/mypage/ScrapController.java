package src.user.mypage;

import java.util.Scanner;

import src.employment.recordDAO.employmentBoard.ReadDetail;
import src.employment.recordDAO.employmentBoardScrap.Scraper;

public class ScrapController {

    ScrapDAO sd = new ScrapDAO();
    Scanner sc = new Scanner(System.in);
    ReadDetail rd = new ReadDetail();
    private final Scraper scraper = new Scraper();

    public void scrapMain() {
        System.out.println("\n=============================================");
        System.out.println("               ★ 나의 스크랩 ★                ");
        System.out.println("=============================================");
        if (sd.printScrap()) {
            System.out.println("=============================================");
            System.out.println("1. 상세보기 | 2. 스크랩 해제");
            System.out.println("=============================================");
            System.out.print("메뉴를 선택하세요 -> ");
            String userInput = sc.nextLine();
            switch (userInput) {
                case "1" -> {
                    rd.selectTarget(sc);
                }
                case "2" -> {
                    scraper.selectDeleteTarget(sc);
                }
                default -> {
                    System.out.println("다시 입력하세요.");
                }
            }
        }
    }
}

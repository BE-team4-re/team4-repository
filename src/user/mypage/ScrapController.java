package src.user.mypage;

import java.util.Scanner;

public class ScrapController {

    ScrapDAO sd = new ScrapDAO();
    Scanner sc = new Scanner(System.in);

    public void scrapMain() {
        System.out.println("\n----------------------------------------------");
        System.out.println("------------------ 나의 스크랩 ------------------");
        System.out.println("----------------------------------------------");
        if (sd.printScrap()) {
            System.out.print("보고싶으면 채용게시물 번호를 입력하세요 :");
            String num = sc.nextLine();

        }
    }
}

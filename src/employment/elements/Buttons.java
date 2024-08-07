package src.employment.elements;

import src.employment.elements.buttons.*;

import java.util.ArrayList;
import java.util.List;

public class Buttons {
    // 채용 공고 조회 등의 작업시 공통으로 쓰여지는 버튼 모음 리스트.
    public static List<Button> buttonList = new ArrayList<>();

    // 아래부터는 쓰여지는 개별 버튼들을 객체로 만들어두고 사용함.
    public static ByJobDetail byJobDetail = new ByJobDetail();
    public static ByRegionDetail byRegionDetail = new ByRegionDetail();
    public static ByRegionDetailAndJobDetail byRegionDetailAndJobDetail = new ByRegionDetailAndJobDetail();

    public static PrevPage prevPage = new PrevPage();
    public static NextPage nextPage = new NextPage();

    public static Back back = new Back();
    public static First first = new First();

    public static Scrap scrap = new Scrap();
    public static Detail detail = new Detail();

    public static Search search = new Search();
    public static All all = new All();

    // 이 아래는 공통 버튼 리스트가 비어있을 때 채워놓고 가져오는 메서드들.
    public static void makeButtonList() {
        if (buttonList.isEmpty()) {
            buttonList.add(Buttons.detail);
            buttonList.add(Buttons.scrap);
            buttonList.add(Buttons.first);
            buttonList.add(Buttons.back);
        }
    }

    public static List<Button> getButtonList() {
        Buttons.makeButtonList();
        return buttonList;
    }
}

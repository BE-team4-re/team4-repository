package src.employment.elements;

import src.employment.elements.buttons.*;
import src.employment.elements.buttons.read.*;

public class Buttons {
    public static ByJob byJob = new ByJob();
    public static ByRegion byRegion = new ByRegion();
    public static ByJobDetail byJobDetail = new ByJobDetail();
    public static ByRegionDetail byRegionDetail = new ByRegionDetail();
    public static ByRegionDetailAndJobDetail byRegionDetailAndJobDetail = new ByRegionDetailAndJobDetail();

    public static PrevPage prevPage = new PrevPage();
    public static NextPage nextPage = new NextPage();

    public static Back back = new Back();
    public static First first = new First();
}

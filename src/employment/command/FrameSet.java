package src.employment.command;

import src.employment.frame.buttons.Button;
import src.util.Callable;

import java.util.List;

public class FrameSet {
    private String title;
    private Callable callable;
    private List<Button> buttonList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Callable getCallable() {
        return callable;
    }

    public void setCallable(Callable callable) {
        this.callable = callable;
    }

    public List<Button> getButtonList() {
        return buttonList;
    }

    public void setButtonList(List<Button> buttonList) {
        this.buttonList = buttonList;
    }

    public FrameSet(String title, Callable callable, List<Button> buttonList) {
        this.title = title;
        this.callable = callable;
        this.buttonList = buttonList;
    }

}

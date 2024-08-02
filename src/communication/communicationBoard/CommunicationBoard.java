package src.communication.communicationBoard;

import src.communication.communicationBoard.dto.CreateCommunicationBoardDto;

public class CommunicationBoard {
    private int communicationBoardId;
    private String title;
    private String content;
    private int id;
    private int categoryId;

    public CommunicationBoard(){}
    public CommunicationBoard(String title, String content, int id, int categoryId) {
        this.title = title;
        this.content = content;
        this.id = id;
        this.categoryId = categoryId;
    }
    public CommunicationBoard(int communicationBoardId, String title, String content, int id, int categoryId) {
        this.communicationBoardId = communicationBoardId;
        this.title = title;
        this.content = content;
        this.id = id;
        this.categoryId = categoryId;
    }

    public CommunicationBoard toCreateEntity(CreateCommunicationBoardDto createCommunitionBoardDto){
        System.out.println("=================");
        System.out.println(createCommunitionBoardDto.content());
        System.out.println(createCommunitionBoardDto.title());
        System.out.println(createCommunitionBoardDto.id());
        System.out.println(createCommunitionBoardDto.categodyId());
        System.out.println("=================");

        return new CommunicationBoard(
            createCommunitionBoardDto.title(),
            createCommunitionBoardDto.content(),
            createCommunitionBoardDto.id(),
            createCommunitionBoardDto.categodyId());
    }


    public int getCommunicationBoardId() {
        return communicationBoardId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getId() {
        return id;
    }

    public int getCategoryId() {
        return categoryId;
    }
}

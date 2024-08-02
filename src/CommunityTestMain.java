package src;

import src.communication.communicationBoard.CommunicationBoardController;
import src.communication.communicationBoard.dto.UpdateCommunicationBoardDto;
import src.communication.communicationCategory.CommunicationCategoryService;

public class CommunityTestMain {
    private static final int id = 1;
    private final String userId = "mok119";
    public static void main(String[] args){
        CommunicationBoardController cc = new CommunicationBoardController();
//        cc.createBoard(id);
        cc.updateCommunicationBoard(2);



    }
}

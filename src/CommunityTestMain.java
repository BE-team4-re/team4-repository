package src;

import src.communication.communicationBoard.CommunicationBoardController;
import src.communication.communicationBoard.CommunicationBoardService;
import src.communication.communicationBoard.dto.CreateCommunicationBoardDto;
import src.communication.communicationBoard.dto.UpdateCommunicationBoardDto;
import src.communication.communicationCategory.CommunicationCategoryService;
import src.communication.communicationComment.CommunicationBoardCommentController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommunityTestMain {
    private static final int id = 1;
    private final String userId = "mok119";
    public static void main(String[] args){
        CommunicationBoardController cc = new CommunicationBoardController();
        CommunicationBoardService cs = new CommunicationBoardService();
        CommunicationBoardCommentController ccm = new CommunicationBoardCommentController();
//        cc.createBoard(id);
        cc.updateCommunicationBoard(2);
//        cc.deleteCommunicationBoard(4);



//        cs.searchCommunicationBoard(1,2,"제목");
        cc.searchCommunicationBoard(1);
//        cc.findOneCommunicationBoard(2,1);
//        ccm.createCommunicationBoardComment(2,1);

    }
}

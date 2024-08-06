package src.communication.communicationComment;

import src.communication.communicationComment.dto.CreateCommunicationBoardCommentDto;
import src.communication.communicationComment.dto.CreateCommunicationBoardReCommentDto;
import src.util.Response;

import java.util.Scanner;

public class CommunicationBoardCommentController {
    private final CommunicationBoardCommentService communicationBoardCommentService = new CommunicationBoardCommentService();
    private final Scanner sc = new Scanner(System.in);

    // 댓글을 생성 시작
    public void createCommunicationBoardComment(int communicationBoardId, int id) {
        System.out.println("=======================");
        System.out.print("댓글 -> ");
        String comment = sc.nextLine();
        System.out.println("=======================");
        CreateCommunicationBoardCommentDto createCommunicationBoardCommentDto = new CreateCommunicationBoardCommentDto(comment, communicationBoardId, id);
        Response<Integer> response = communicationBoardCommentService.createComment(createCommunicationBoardCommentDto);
        // 댓글 생성 성공했다면
        if (response.isSuccess()) System.out.println(response.getMessage());
        // 댓글 생성 실패했다면
        else {
            System.out.println(response.getMessage());
            while (true) {
                System.out.println("1. 다시 댓글을 작성하겠습니까? 2. 나가기");
                String selectNum = sc.nextLine();
                if (selectNum.equals("1")) {
                    System.out.println("=======================");
                    System.out.print("댓글 -> ");
                    comment = sc.nextLine();
                    System.out.println("=======================");
                    createCommunicationBoardCommentDto = new CreateCommunicationBoardCommentDto(comment, communicationBoardId, id);
                    response = communicationBoardCommentService.createComment(createCommunicationBoardCommentDto);
                    if (response.isSuccess()) {
                        System.out.println(response.getMessage());
                        break;
                    }
                    System.out.println(response.getMessage());
                } else if (selectNum.equals("2")) break;
            }
        }
    }

    // 대댓글 생성 시작
    public void createCommunicationBoardReComment(int communicationBoardId, int id, int commentId) {
        while (true) {
            System.out.println("=================");
            System.out.print("대댓글 => ");
            String reComment = sc.nextLine();
            System.out.println("=================");
            CreateCommunicationBoardReCommentDto createCommunicationBoardReCommentDto = new CreateCommunicationBoardReCommentDto(reComment, commentId, communicationBoardId, id);
            Response<Integer> response = communicationBoardCommentService.createReComment(createCommunicationBoardReCommentDto);
            if (response.isSuccess()) {
                System.out.println(response.getMessage());
                break;
            }
            else {
                System.out.println(response.getMessage());
                System.out.println("1. 다시 등록하겠습니까? 2. 나가기");
                String selectNum = sc.nextLine();
                if (selectNum.equals("1")) continue;
                else if (selectNum.equals("2")){
                    break;
                }
            }
        }
    }
    // 댓글과 대댓글 수정
    public boolean updateComment(int updateCommentId, String updateComment) {
        Response<Integer> response = communicationBoardCommentService.update(updateCommentId, updateComment);
        return response.isSuccess();
    }

    // 댓글과 대댓글 삭제
    public boolean deleteComment(int commentId){
        Response<Integer> response = communicationBoardCommentService.delete(commentId);
        if(response.isSuccess()){
            System.out.println(response.getMessage());
            return true;
        }else{
            System.out.println(response.getMessage());
            return false;
        }
    }
}
package src.communication.communicationComment.dto;

public record FindCommunicationBoardCommentDto(
    int commentId,
    String comment,
    String userId,
    int parentId,
    int id
) {
}

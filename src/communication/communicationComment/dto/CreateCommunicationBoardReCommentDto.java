package src.communication.communicationComment.dto;

public record CreateCommunicationBoardReCommentDto(
    String reComment,
    int commentId,
    int communicationBoardId,
    int id
) {
}

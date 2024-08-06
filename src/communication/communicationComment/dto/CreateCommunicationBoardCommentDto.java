package src.communication.communicationComment.dto;

public record CreateCommunicationBoardCommentDto(
    String comment,
    int communicationBoardId,
    int id
    ) {
}

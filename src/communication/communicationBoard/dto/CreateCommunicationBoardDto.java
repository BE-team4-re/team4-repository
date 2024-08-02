package src.communication.communicationBoard.dto;

public record CreateCommunicationBoardDto(
    String title,
    String content,
    int id,
    int categodyId
) {
}

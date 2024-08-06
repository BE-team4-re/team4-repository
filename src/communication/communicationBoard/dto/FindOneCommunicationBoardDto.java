package src.communication.communicationBoard.dto;

import src.communication.communicationComment.dto.FindCommunicationBoardCommentDto;

import java.util.List;

public record FindOneCommunicationBoardDto(
    int communicationBoardId,
    String title,
    String content,
    String userId,
    int id
) {
}

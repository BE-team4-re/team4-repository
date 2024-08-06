package src.communication.communicationBoard.dto;

import src.communication.communicationComment.dto.FindCommunicationBoardCommentDto;

import java.util.List;

public record FindOneCommnicationBoardNCommentDto(
    FindOneCommunicationBoardDto communicationBoard,
    List<FindCommunicationBoardCommentDto> commentList
) {
}

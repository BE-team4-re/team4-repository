package src.communication.communicationBoard.dto;

import java.util.ArrayList;
import java.util.List;

public record PagenationCommunicationBoardDto(
    int totalPage,
    List<SearchCommunicationBoardDto> communicationBoardList
) {
}

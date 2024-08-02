package src.communication.communicationCategory.dto;

public record FindCommunicationBoardCategoryDto(
    int communicationBoardCategoryId,
    String communicationBoardCategory
) {
    public FindCommunicationBoardCategoryDto(int communicationBoardCategoryId, String communicationBoardCategory) {
        this.communicationBoardCategoryId = communicationBoardCategoryId;
        this.communicationBoardCategory = communicationBoardCategory;
    }

    @Override
    public int communicationBoardCategoryId() {
        return communicationBoardCategoryId;
    }

    @Override
    public String communicationBoardCategory() {
        return communicationBoardCategory;
    }
}

package src.communication.communicationCategory.repository;

public class CommunicationBoardCategoryRepository {

    public String findAll(){
        return "SELECT category_id, category from communication_category";
    }
}

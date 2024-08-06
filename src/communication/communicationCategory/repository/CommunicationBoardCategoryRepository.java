package src.communication.communicationCategory.repository;

public class CommunicationBoardCategoryRepository {

    public String findAll(){
        return "SELECT category_id, category from communication_category";
    }
    public String create(){
        return "insert into communication_category (category) values (?)";
    }

    public String update(){
        return "UPDATE communication_category  SET category  = ? where category_id  = ?";
    }

}

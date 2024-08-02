package src.communication.communicationBoard.repository;

public class CommunicationBoardRepository {

    public String create(){
        return "insert INTO communication_board (title, content, id,category_id) values " +
            "(?, ?, ?,?)";
    }
    public String findOne(){
        return "select title, content from communication_board where communicationboard_id  = ?";
    }
    public String update(){
        return "UPDATE communication_board SET title = ?, content = ? where communicationboard_id = ?";
    }
    public String delete(){
        return "delete from communication_board where communicationboard_id = ?";
    }
}

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

    public String findAll(){
        return "select communicationboard_id, title, u.userId " +
        "from communication_board cb " +
        "inner join `user` u on cb.id = u.id " +
        "order by cb.communicationboard_id desc " +
         "limit 5 offset ?";
    }
    public String findAllCount(){
        return "select count(*) as count " +
            "from communication_board cb " +
            "inner join `user` u on cb.id = u.id " +
            "order by cb.communicationboard_id desc";
    }

    public String findAllWordByCommunicationBoard(){
        return "select communicationboard_id, title, u.userId " +
            "from communication_board cb " +
            "inner join `user` u on cb.id = u.id " +
            "where title like ? " +
            "limit 5 offset ?";
    }

    public String findAllWordByCommunicationBoardCount(){
        return "select count(*) as count " +
            "from communication_board cb " +
            "inner join `user` u on cb.id = u.id " +
            "where title like ?";
    }

    public String findCategoryByCommunicationBoard(){
        return "select communicationboard_id, title, u.userId " +
            "from communication_board cb " +
            "inner join `user` u on cb.id = u.id " +
            "where cb.category_id  = ? " +
            "order by cb.communicationboard_id desc " +
            "limit 5 offset ?";
    }
    public String findCategoryByCommunicationBoardCount(){
        return "select count(*) as count " +
            "from communication_board cb " +
            "inner join `user` u on cb.id = u.id " +
            "where cb.category_id  = ? " +
            "order by cb.communicationboard_id desc";
    }

    public String findCategoryNSearchWordByCommunicationBoard(){
        return "select communicationboard_id, title, u.userId " +
            "from communication_board cb " +
            "inner join `user` u on cb.id = u.id " +
            "where cb.category_id  = ? and cb.title like ? " +
            "order by cb.communicationboard_id desc " +
            "limit 5 offset ?";
    }

    public String findCategoryNSearchWordByCommunicationBoardCount(){
        return "select count(*) as count " +
            "from communication_board cb " +
            "inner join `user` u on cb.id = u.id " +
            "where cb.category_id  = ? and cb.title like ? " +
            "order by cb.communicationboard_id desc";
    }
}

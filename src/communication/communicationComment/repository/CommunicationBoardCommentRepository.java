package src.communication.communicationComment.repository;

public class CommunicationBoardCommentRepository {
    public String createComment(){
        return "insert into comment (comment, communicationboard_id,id) values (?, ?,?)";
    }
    public String createReComment(){
        return "insert into comment (comment, parent_id, communicationboard_id,id) values (?, ?, ?, ?)";
    }

    public String updateComment(){
        return "UPDATE comment  SET comment = ? where comment_id  = ?";
    }
    public String deleteComment(){
        return "delete from comment  where comment_id = ?";
    }

    public String findComment(){
        return """
            WITH RECURSIVE CommentTree AS (
                SELECT
                    comment_id,
                    parent_id,
                    comment,
                    communicationboard_id,
                    created_at,
                    u.userId,
                    u.id,
                    CAST(comment_id AS CHAR(200)) AS path,
                    comment_id AS level
                FROM
                    comment
                inner join `user` u on u.id = comment.id
                WHERE
                    communicationboard_id = ? AND parent_id IS NULL
                UNION ALL
                SELECT
                    c.comment_id,
                    c.parent_id,
                    c.comment,
                    c.communicationboard_id,
                    c.created_at,
                    u.userId,
                    u.id,
                    CONCAT(ct.path, '-', c.comment_id) AS path,
                    ct.level
                FROM
                    comment c
                INNER JOIN
                    CommentTree ct ON c.parent_id = ct.comment_id
                Inner join `user` u on c.id = u.id
            )
            SELECT
                comment_id,
                parent_id,
                comment,
                communicationboard_id,
                created_at,
                userid,
                id,
                level,
                path
            FROM
                CommentTree
            ORDER BY
                 level DESC , path
            """;
    }
}

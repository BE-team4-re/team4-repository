package src.employment.recordDAO.employmentBoardScrap;


import src.database.Database;
import src.employment.boardDTO.BoardDTO;
import src.util.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DeleteScrapDAO {
    private final Database db = new Database();

    private Connection conn = null;
    private PreparedStatement pstmt = null;

    // 스크랩 취소.
    public Response<String> delete(int uid, int bid) {

        Response<String> response = new Response<>(false, "실패하였습니다.", null);
        String sql = "delete from scrap where id=? and employment_board_id=?;";

        try {

            conn = db.connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, uid);
            pstmt.setInt(2, bid);

            int rows = pstmt.executeUpdate();
            if (rows == 1) {
//                System.out.println("성공적으로 저장되었습니다.");
                response = new Response<>(true, "성공적으로 삭제했습니다.", "성공");
            } else {
//                System.out.println("저장에 실패하였습니다.");
                response = new Response<>(false, "삭제하는데 실패하였습니다.", "실패");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return response;
    }
}

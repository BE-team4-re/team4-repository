package src.employment.recordDAO.employmentBoardScrap;

import src.database.Database;
import src.user.UserMain;
import src.util.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReadScrapDAO {
    private final Database db = new Database();
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    // select * from scrap;
    // -> 스크랩 테이블에서 유저 id 와 스크랩 id 를 동시에 갖는 레코드가 존재하는지 확인.
    public Response<Integer> checkScrap(int bid) {
        Response<Integer> response = new Response<>(false, "스크랩 된 적 없는 채용공고 입니다.", 0);
        String sql = "select * from scrap where id=? and employment_board_id=?";

        try {
            conn = db.connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, UserMain.id);
            pstmt.setInt(2, bid);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                response = new Response<>(true, "이미 스크랩 된 채용공고 입니다.", 1);
            } else {
                response = new Response<>(false, "스크랩 된 적 없는 채용공고 입니다.", 0);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
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

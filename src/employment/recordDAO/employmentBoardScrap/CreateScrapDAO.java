package src.employment.recordDAO.employmentBoardScrap;

import src.database.Database;
import src.util.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateScrapDAO {

    private Database db = new Database();
    private Connection conn = null;
    private PreparedStatement pstmt = null;

    // insert into employment_board ... values ...
    public Response<String> create(int uid, int bid) {
        // scrap_id -> 자동 증가 컬럼.
        String sql = "insert into scrap (id, employment_board_id) values (?, ?);";
        Response<String> response = new Response<>(false, "실패하였습니다.", "실패");

        try {

            conn = db.connect();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, uid);
            pstmt.setInt(2, bid);

            int rows = pstmt.executeUpdate();
            if (rows == 1) {
//                System.out.println("성공적으로 저장되었습니다.");
                response = new Response<>(true, "성공하였습니다.", "성공");
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

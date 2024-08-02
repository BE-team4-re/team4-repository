package src.admin.employment;

import src.database.Database;
import src.employment.recordVO.EmploymentBoardVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmploymentService {
    private final Database db = new Database();

    public List<EmploymentBoardVO> searchEmpBoard(String company_ID){
        List<EmploymentBoardVO> emplist = new ArrayList<>();
        try(Connection connection = db.connect();
            PreparedStatement ps = connection.prepareStatement("select * from employment_board where company_name=?");)
        {
            ps.setString(1, company_ID);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                EmploymentBoardVO empVO = new EmploymentBoardVO(
                    rs.getInt("employment_board_id"),
                    rs.getString("title"),
                    rs.getString("job_type"),
                    rs.getString("career"),
                    rs.getString("hiring_process"),
                    rs.getString("qualifications"),
                    rs.getString("preferred"),
                    rs.getString("company_name"),
                    rs.getInt("main_category1_id"),
                    rs.getInt("main_category2_id"),
                    rs.getInt("sub_category1_id"),
                    rs.getInt("sub_category2_id")
                );
                emplist.add(empVO);
            }
            rs.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return emplist;
    }
}

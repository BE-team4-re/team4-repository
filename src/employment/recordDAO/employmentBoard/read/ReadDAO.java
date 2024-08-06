package src.employment.recordDAO.employmentBoard.read;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import java.util.List;
import java.util.ArrayList;

import src.employment.board.BoardDTO;

import src.database.Database;
import src.util.Response;


public class ReadDAO {

	private final Database db = new Database();
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;


	// select * from employment_board
	// -> 전체 게시물 조회
	public Response<List<BoardDTO>> readAll() {

		Response<List<BoardDTO>> response = null;

		List<BoardDTO> EmploymentBoardList = new ArrayList<>();
		String sql = "select * from employment_board order by employment_board_id desc";

		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {

				int employmentBoardId = rs.getInt("employment_board_id");
				String title = rs.getString("title");
				String jobType = rs.getString("job_type");
				String career = rs.getString("career");
				String hiringProcess = rs.getString("hiring_process");
				String qualifications = rs.getString("qualifications");
				String preferred = rs.getString("preferred");
				int mainCategory1Id = rs.getInt("main_category1_id");
				int mainCategory2Id = rs.getInt("main_category2_id");
				int subCategory1Id = rs.getInt("sub_category1_id");
				int subCategory2Id = rs.getInt("sub_category2_id");
				String companyName = rs.getString("company_name");
				
				BoardDTO board = new BoardDTO(
					employmentBoardId, title, jobType, career, hiringProcess,
					qualifications, preferred, mainCategory1Id, mainCategory2Id,
					subCategory1Id, subCategory2Id, companyName
				);
				
				EmploymentBoardList.add(board);
				
			}

			response = new Response<>(true, "성공적으로 가져왔습니다.", EmploymentBoardList);
			
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

	// select * from employment_board where employment_board_id=?";
	// -> 단일 게시물 조회
	public Response<BoardDTO> read(int bid) {

		Response<BoardDTO> response = null;

		String sql = "select * from employment_board where employment_board_id=? order by employment_board_id desc";

		try {

			conn = db.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bid);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				// System.out.println("성공적으로 가져왔습니다.");
				int employmentBoardId = rs.getInt("employment_board_id");
				String title = rs.getString("title");
				String jobType = rs.getString("job_type");
				String career = rs.getString("career");
				String hiringProcess = rs.getString("hiring_process");
				String qualifications = rs.getString("qualifications");
				String preferred = rs.getString("preferred");
				int mainCategory1Id = rs.getInt("main_category1_id");
				int mainCategory2Id = rs.getInt("main_category2_id");
				int subCategory1Id = rs.getInt("sub_category1_id");
				int subCategory2Id = rs.getInt("sub_category2_id");
				String companyName = rs.getString("company_name");

				BoardDTO board = new BoardDTO(
						employmentBoardId, title, jobType, career, hiringProcess,
						qualifications, preferred, mainCategory1Id, mainCategory2Id,
						subCategory1Id, subCategory2Id, companyName
				);

				response = new Response<>(true, "성공적으로 가져왔습니다.", board);
			} else {
				// System.out.println("가져오는데 실패하였습니다.");
				response = new Response<>(true, "가져오는데 실패하였습니다.", null);
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

	// select * from employment_board where main_category1_id=1";
	// -> 지역별 조회
	public Response<List<BoardDTO>> readByRegion() {

		Response<List<BoardDTO>> response = null;
		List<BoardDTO> EmploymentBoardList = new ArrayList<>();

		String sql = "select * from employment_board where main_category1_id=1 order by employment_board_id desc";

		try {

			conn = db.connect();
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// System.out.println("성공적으로 가져왔습니다.");
				int employmentBoardId = rs.getInt("employment_board_id");
				String title = rs.getString("title");
				String jobType = rs.getString("job_type");
				String career = rs.getString("career");
				String hiringProcess = rs.getString("hiring_process");
				String qualifications = rs.getString("qualifications");
				String preferred = rs.getString("preferred");
				int mainCategory1Id = rs.getInt("main_category1_id");
				int subCategory1Id = rs.getInt("sub_category1_id");
				int mainCategory2Id = rs.getInt("main_category2_id");
				int subCategory2Id = rs.getInt("sub_category2_id");
				String companyName = rs.getString("company_name");

				BoardDTO board = new BoardDTO(
						employmentBoardId, title, jobType, career, hiringProcess,
						qualifications, preferred, mainCategory1Id, mainCategory2Id,
						subCategory1Id, subCategory2Id, companyName
				);
				EmploymentBoardList.add(board);
			}
			response = new Response<>(true, "성공적으로 가져왔습니다.", EmploymentBoardList);

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

	// select * from employment_board where main_category2_id=2";
	// -> 직무별 조회
	public Response<List<BoardDTO>> readByJob() {

		Response<List<BoardDTO>> response = null;
		List<BoardDTO> EmploymentBoardList = new ArrayList<>();

		String sql = "select * from employment_board where main_category2_id=2 order by employment_board_id desc";

		try {

			conn = db.connect();
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// System.out.println("성공적으로 가져왔습니다.");
				int employmentBoardId = rs.getInt("employment_board_id");
				String title = rs.getString("title");
				String jobType = rs.getString("job_type");
				String career = rs.getString("career");
				String hiringProcess = rs.getString("hiring_process");
				String qualifications = rs.getString("qualifications");
				String preferred = rs.getString("preferred");
				int mainCategory1Id = rs.getInt("main_category1_id");
				int subCategory1Id = rs.getInt("sub_category1_id");
				int mainCategory2Id = rs.getInt("main_category2_id");
				int subCategory2Id = rs.getInt("sub_category2_id");
				String companyName = rs.getString("company_name");

				BoardDTO board = new BoardDTO(
						employmentBoardId, title, jobType, career, hiringProcess,
						qualifications, preferred, mainCategory1Id, mainCategory2Id,
						subCategory1Id, subCategory2Id, companyName
				);
				EmploymentBoardList.add(board);
			}
			response = new Response<>(true, "성공적으로 가져왔습니다.", EmploymentBoardList);

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

	// select * from employment_board where main_category1_id=1 and sub_category1_id=?";
	// -> 상세 지역별 조회
	public Response<List<BoardDTO>> readByRegionDetail(int regionCode) {

		Response<List<BoardDTO>> response = null;
		List<BoardDTO> EmploymentBoardList = new ArrayList<>();

		String sql = "select * from employment_board where main_category1_id=1 and sub_category1_id=? order by employment_board_id desc";

		try {

			conn = db.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, regionCode);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// System.out.println("성공적으로 가져왔습니다.");
				int employmentBoardId = rs.getInt("employment_board_id");
				String title = rs.getString("title");
				String jobType = rs.getString("job_type");
				String career = rs.getString("career");
				String hiringProcess = rs.getString("hiring_process");
				String qualifications = rs.getString("qualifications");
				String preferred = rs.getString("preferred");
				int mainCategory1Id = rs.getInt("main_category1_id");
				int subCategory1Id = rs.getInt("sub_category1_id");
				int mainCategory2Id = rs.getInt("main_category2_id");
				int subCategory2Id = rs.getInt("sub_category2_id");
				String companyName = rs.getString("company_name");

				BoardDTO board = new BoardDTO(
						employmentBoardId, title, jobType, career, hiringProcess,
						qualifications, preferred, mainCategory1Id, mainCategory2Id,
						subCategory1Id, subCategory2Id, companyName
				);
				EmploymentBoardList.add(board);
			}
			response = new Response<>(true, "성공적으로 가져왔습니다.", EmploymentBoardList);

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

	// select * from employment_board where sub_category1_id=? and sub_category2_id=?";
	// -> 특정 지역 및 특정 직무 조회
	public Response<List<BoardDTO>> readByRegionDetailAndJobDetail(int regionCode, int jobCode) {

		Response<List<BoardDTO>> response = null;
		List<BoardDTO> EmploymentBoardList = new ArrayList<>();

		String sql = "select * from employment_board where sub_category1_id=? and sub_category2_id=? order by employment_board_id desc";

		try {

			conn = db.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, regionCode);
			pstmt.setInt(2, jobCode);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// System.out.println("성공적으로 가져왔습니다.");
				int employmentBoardId = rs.getInt("employment_board_id");
				String title = rs.getString("title");
				String jobType = rs.getString("job_type");
				String career = rs.getString("career");
				String hiringProcess = rs.getString("hiring_process");
				String qualifications = rs.getString("qualifications");
				String preferred = rs.getString("preferred");
				int mainCategory1Id = rs.getInt("main_category1_id");
				int subCategory1Id = rs.getInt("sub_category1_id");
				int mainCategory2Id = rs.getInt("main_category2_id");
				int subCategory2Id = rs.getInt("sub_category2_id");
				String companyName = rs.getString("company_name");

				BoardDTO board = new BoardDTO(
						employmentBoardId, title, jobType, career, hiringProcess,
						qualifications, preferred, mainCategory1Id, mainCategory2Id,
						subCategory1Id, subCategory2Id, companyName
				);
				EmploymentBoardList.add(board);
			}
			response = new Response<>(true, "성공적으로 가져왔습니다.", EmploymentBoardList);

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

	// select * from employment_board where main_category2_id=2 and sub_category2_id=?";
	// -> 상세 직무별 조회
	public Response<List<BoardDTO>> readByJobDetail(int jobCode) {

		Response<List<BoardDTO>> response = null;
		List<BoardDTO> EmploymentBoardList = new ArrayList<>();

		String sql = "select * from employment_board where main_category2_id=2 and sub_category2_id=? order by employment_board_id desc";

		try {

			conn = db.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, jobCode);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// System.out.println("성공적으로 가져왔습니다.");
				int employmentBoardId = rs.getInt("employment_board_id");
				String title = rs.getString("title");
				String jobType = rs.getString("job_type");
				String career = rs.getString("career");
				String hiringProcess = rs.getString("hiring_process");
				String qualifications = rs.getString("qualifications");
				String preferred = rs.getString("preferred");
				int mainCategory1Id = rs.getInt("main_category1_id");
				int subCategory1Id = rs.getInt("sub_category1_id");
				int mainCategory2Id = rs.getInt("main_category2_id");
				int subCategory2Id = rs.getInt("sub_category2_id");
				String companyName = rs.getString("company_name");

				BoardDTO board = new BoardDTO(
						employmentBoardId, title, jobType, career, hiringProcess,
						qualifications, preferred, mainCategory1Id, mainCategory2Id,
						subCategory1Id, subCategory2Id, companyName
				);
				EmploymentBoardList.add(board);
			}
			response = new Response<>(true, "성공적으로 가져왔습니다.", EmploymentBoardList);

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
package src.employment.recordDAO.employmentBoardCategory.update;


import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import java.util.ArrayList;
import java.util.List;

import src.database.Database;

import src.employment.board.BoardCategory;


public class DAO {
	
	private Database db = new Database();
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
}

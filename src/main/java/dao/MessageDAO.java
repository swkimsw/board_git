package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.tomcat.jdbc.pool.DataSource;

import dto.MessageDTO;

public class MessageDAO {
	private static MessageDAO instance = null;

	private MessageDAO() {

	}

	public synchronized static MessageDAO getInstacne() {
		if (instance == null) {
			instance = new MessageDAO();
		}
		return instance;
	}

	private Connection getConnection() throws Exception {
		Context iCtx = new InitialContext();
		String path = "java:/comp/env/jdbc/";
		String item = "ora";
		DataSource ds = (DataSource) iCtx.lookup(path + item);

		return ds.getConnection();
	}

	public List<MessageDTO> selectAll() throws Exception {
		List<MessageDTO> result = new ArrayList<MessageDTO>();

		try (Connection conn = this.getConnection()) {
			String sql = "SELECT * FROM MESSAGE ORDER BY ID";
			try (PreparedStatement pstat = conn.prepareStatement(sql)) {
				try (ResultSet rs = pstat.executeQuery()) {
					while (rs.next()) {
						MessageDTO dto = new MessageDTO();
						dto.setId(rs.getInt("ID"));
						dto.setWriter(rs.getString("WRITER"));
						dto.setMessage(rs.getString("MESSAGES"));

						result.add(dto);
					}
				}
			}
		}

		return result;
	}

	public int insert(MessageDTO dto) throws Exception {
		int result = 0;

		try (Connection conn = this.getConnection()) {
			String sql = "INSERT INTO MESSAGE VALUES(ID_SEQ.NEXTVAL, ?, ?)";
			try (PreparedStatement pstat = conn.prepareStatement(sql)) {
				pstat.setString(1, dto.getWriter());
				pstat.setString(2, dto.getMessage());

				result = pstat.executeUpdate();
				conn.commit();
			}
		}

		return result;
	}
	
	public int update(MessageDTO dto) throws Exception {
		int result = 0;

		try (Connection conn = this.getConnection()) {
			String sql = "UPDATE MESSAGE SET WRITER = ?, MESSAGES = ? WHERE ID =?";
			try (PreparedStatement pstat = conn.prepareStatement(sql)) {
				pstat.setString(1, dto.getWriter());
				pstat.setString(2, dto.getMessage());
				pstat.setInt(3, dto.getId());

				result = pstat.executeUpdate();
				conn.commit();
			}
		}

		return result;
	}
	
	public int delete(MessageDTO dto) throws Exception {
		int result = 0;

		try (Connection conn = this.getConnection()) {
			String sql = "DELETE FROM MESSAGE WHERE ID = ?";
			try (PreparedStatement pstat = conn.prepareStatement(sql)) {
				pstat.setInt(1, dto.getId());

				result = pstat.executeUpdate();
				conn.commit();
			}
		}

		return result;
	}

}

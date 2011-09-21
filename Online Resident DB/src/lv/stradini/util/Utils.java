package lv.stradini.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class Utils {
	private static Logger logger = Logger.getLogger(Utils.class);
	
	public static void setAutoCommit(Connection conn, boolean value) {
		try {
			conn.setAutoCommit(value);
		} catch (SQLException e) {
			logger.error("Exception while setting auto commit value", e);
		}
	}

	public static void rollback(Connection conn) {
		try {
			conn.rollback();
		} catch (SQLException e) {
			logger.error("Exception while rollbacking the connection", e);
		}
	}
}

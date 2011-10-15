package lv.stradini.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;
import java.util.TreeSet;

import lv.stradini.comparator.StudijuGadsResidentComparator;
import lv.stradini.domain.Resident;

import org.apache.log4j.Logger;

public class Utils {
	private static Logger logger = Logger.getLogger(LoggerUtils.getClassName(Utils.class));
	
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
	
	public static void sortResidentsByStudijuGads(Set<Resident> residentList) {
		Set<Resident> residentSet = new TreeSet<Resident>(new StudijuGadsResidentComparator());
		residentSet.addAll(residentList);
		residentList = residentSet;
	}

//	public static void sortResidentsBySpecialitate(List<Resident> residentList) {
//		List<Resident> residentSet = new LinkedList<Resident>(new SpecialitateResidentComparator());
//		residentSet.addAll(residentList);
//		residentList = residentSet;
//	}
}

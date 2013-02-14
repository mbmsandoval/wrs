package data.utils;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;

public class QueryUtil {

	private static Logger logger = Logger.getLogger(QueryUtil.class);

	public static List<Object[]> getQueryResultsForVariousFields(Query query) {
		List<Object[]> result = null;
		try {
			result = query.getResultList();
		} catch (Exception e) {
			logger.debug("ERROR: " + e);
		} finally {

		}
		return result;
	}

}

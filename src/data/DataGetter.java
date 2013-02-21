package data;

import java.util.List;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.Constants;

import data.utils.QueryUtil;

public class DataGetter {

	private EntityManager em = null;

	public DataGetter(EntityManager em) {
		this.setEntityManager(em);
	}

	public List<Object[]> getCountryListMinimalInfo() {
		Query query = em
				.createNamedQuery(Constants.queryNameMinimalInfoFromFindAllCountries);
		return QueryUtil.getQueryResultsForVariousFields(query);
	}

	public List<Object[]> getContinentAndGroupListMinimalInfo() {
		Query query = em
				.createNamedQuery(Constants.queryNameMinimalInfoFromFindAllContinentsAndGroups);
		return QueryUtil.getQueryResultsForVariousFields(query);
	}
	
	public List<Object[]> getSubnationalListMinimalInfo() {
		Query query = em
				.createNamedQuery(Constants.queryNameMinimalInfoFromFindAllSubnationalCountries);
		return QueryUtil.getQueryResultsForVariousFields(query);
	}

	public List<Object[]> getVariableListMinimalInfo() {
		Query query = em
				.createNamedQuery(Constants.queryNameMinimalInfoFromFindAllVariables);
		return QueryUtil.getQueryResultsForVariousFields(query);
	}

	public List<Object[]> getSourceListMinimalInfo() {
		Query query = em
				.createNamedQuery(Constants.queryNameMinimalInfoFromFindAllSources);
		return QueryUtil.getQueryResultsForVariousFields(query);
	}

	// public SourceIdForVariableId getSourceListForVariables() {
	// SourceIdForVariableId sourceListForVariables = new
	// SourceIdForVariableId();
	// Query query = em
	// .createNamedQuery(Constants.queryNameFindAllPairsOfVariableIdsAndSourceIds);
	//
	// List<Object[]> list = QueryUtil.getQueryResultsForVariousFields(query);
	//
	// for (Object[] listElement : list) {
	// Integer variableId = new Integer(-1);
	// try {
	// variableId = (Integer) listElement[0];
	// } catch (Exception e) {
	// }
	//
	// Integer sourceId = new Integer(-1);
	// try {
	// sourceId = (Integer) listElement[1];
	// } catch (Exception e) {
	// }
	//
	// if ((0 < variableId) && (0 < sourceId)) {
	// Vector<Integer> sourceIdList = sourceListForVariables
	// .get(variableId);
	// if (null == sourceIdList) {
	// sourceIdList = new Vector<Integer>();
	// }
	// sourceIdList.addElement(sourceId);
	//
	// sourceListForVariables.put(variableId, sourceIdList);
	// }
	// }
	//
	// return sourceListForVariables;
	// }

	public SourceIdForVariableId getSourceListForVariables() {
		SourceIdForVariableId sourceListForVariables = new SourceIdForVariableId();
		Query query = em
				.createNamedQuery(Constants.queryNameFindAllPairsOfVariablesAndSources);

		List<Object[]> list = QueryUtil.getQueryResultsForVariousFields(query);

		for (Object[] listElement : list) {
			String varAbbr = null;
			try {
				varAbbr = (String) listElement[0];
			} catch (Exception e) {
			}

			String codeOfSource = null;
			try {
				codeOfSource = (String) listElement[1];
			} catch (Exception e) {
			}

			if ((null != varAbbr) && (null != codeOfSource)) {
				Vector<String> sourceIdList = sourceListForVariables
						.get(varAbbr);
				if (null == sourceIdList) {
					sourceIdList = new Vector<String>();
				}
				sourceIdList.addElement(codeOfSource);

				sourceListForVariables.put(varAbbr, sourceIdList);
			}
		}

		return sourceListForVariables;
	}

	// public Integer[] getListOfSourcesForVariable(int variableId) {
	// Integer[] listOfSourcesForVariable = null;
	//
	// // NOTE: Get sourceListForVariables from session
	// SourceIdForVariableId sourceListForVariables = new
	// SourceIdForVariableId();
	//
	// try {
	//
	// if (null != sourceListForVariables) {
	// Vector<Integer> listOfSources = sourceListForVariables
	// .get(new Integer(variableId));
	// if (null != listOfSources) {
	// listOfSourcesForVariable = (Integer[]) listOfSources
	// .toArray();
	// }
	// }
	//
	// } catch (Exception e) {
	// }
	//
	// return listOfSourcesForVariable;
	// }

	public String[] getListOfSourcesForVariable(String variableId) {
		String[] listOfSourcesForVariable = null;

		// NOTE: Get sourceListForVariables from session
		SourceIdForVariableId sourceListForVariables = new SourceIdForVariableId();

		try {

			if (null != sourceListForVariables) {
				Vector<String> listOfSources = sourceListForVariables
						.get(variableId);
				if (null != listOfSources) {
					listOfSourcesForVariable = (String[]) listOfSources
							.toArray();
				}
			}

		} catch (Exception e) {
		}

		return listOfSourcesForVariable;
	}

	// NOTE: the return value of this function needs to be replaced by the
	// minimum year from the data_reg table or whichever data is appropriate
	public int getMinYearForContinentalData() {
		return 1980;
	}

	// NOTE: the return value of this function needs to be replaced by the
	// maximum year from the data_reg table or whichever data is appropriate
	public int getMaxYearForContinentalData() {
		return 2012;
	}

	// NOTE: the return value of this function needs to be replaced by the
	// minimum year from the data_front table or whichever data is appropriate
	public int getMinYearForNationalData() {
		return 1970;
	}

	// NOTE: the return value of this function needs to be replaced by the
	// maximum year from the data_front table or whichever data is appropriate
	public int getMaxYearForNationalData() {
		return 2002;
	}

	// NOTE: the return value of this function needs to be replaced by the
	// minimum year from the pay table or whichever data is appropriate
	public int getMinYearForSubnationalData() {
		return 1960;
	}

	// NOTE: the return value of this function needs to be replaced by the
	// maximum year from the pay table or whichever data is appropriate
	public int getMaxYearForSubnationalData() {
		return 1992;
	}

	public EntityManager getEntityManager() {
		return em;
	}

	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

}

package temp;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.persistence.Persistence;

import com.Constants;

import data.DataGetter;
import data.SourceIdForVariableId;

public class Main {

	public static void main(String args[]) {

		DataGetter dataGetter = new DataGetter(Persistence
				.createEntityManagerFactory(Constants.persistenceUnitName)
				.createEntityManager());

		List<Object[]> countries = dataGetter.getCountryListMinimalInfo();
		if ((null == countries) || (0 >= countries.size())) {
			System.out
					.println("No record of countries was found in the database.");
		} else {
			System.out
					.println("Records of countries are found in the database. Total number of countries: "
							+ countries.size());
			displayListOfCountriesOrContinents(countries);
		}

		System.out.println("---");

		List<Object[]> continentsAndGroups = dataGetter
				.getContinentAndGroupListMinimalInfo();

		if ((null == continentsAndGroups) || (0 >= continentsAndGroups.size())) {
			System.out
					.println("No record of continent/group was found in the database.");
		} else {
			System.out
					.println("Records of continent/groups are found in the database. Total number of continents and groups: "
							+ continentsAndGroups.size());
			displayListOfCountriesOrContinents(continentsAndGroups);
		}

		System.out.println("---");

		List<Object[]> variables = dataGetter.getVariableListMinimalInfo();

		if ((null == variables) || (0 >= variables.size())) {
			System.out
					.println("No record of variables was found in the database.");
		} else {
			System.out
					.println("Records of variables are found in the database. Total number of variables: "
							+ variables.size());
			displayListOfVariables(variables);
		}

		System.out.println("---");

		List<Object[]> sources = dataGetter.getSourceListMinimalInfo();

		if ((null == sources) || (0 >= sources.size())) {
			System.out
					.println("No record of sources was found in the database.");
		} else {
			System.out
					.println("Records of sources are found in the database. Total number of sources: "
							+ sources.size());
			displayListOfSources(sources);
		}

		System.out.println("---");

		SourceIdForVariableId sourceListForVariables = dataGetter
				.getSourceListForVariables();

		if ((null == sourceListForVariables)
				|| (0 >= sourceListForVariables.size())) {
			System.out
					.println("No pair of variableIds and sourceIds are found in the database.");
		} else {
			displaySourceListForVariables(sourceListForVariables);
		}

		System.out.println("---");
	}

	public static void displayListOfCountriesOrContinents(List<Object[]> list) {

		for (Object[] listElement : list) {
			Integer countryId = new Integer(-1);
			try {
				countryId = (Integer) listElement[0];
			} catch (Exception e) {
			}
			String isoAbbr = (String) listElement[1];
			String isoFull = (String) listElement[2];
			System.out.println("\t" + (0 > countryId ? "-" : countryId) + ": "
					+ isoAbbr + " - " + isoFull);
		}

	}

	public static void displayListOfVariables(List<Object[]> list) {

		for (Object[] listElement : list) {
			Integer variableId = new Integer(-1);
			try {
				variableId = (Integer) listElement[0];
			} catch (Exception e) {
			}
			String varAbbr = (String) listElement[1];
			String varFull = (String) listElement[2];
			System.out.println("\t" + (0 > variableId ? "-" : variableId)
					+ ": " + varAbbr + " - " + varFull);
		}

	}

	public static void displayListOfSources(List<Object[]> list) {

		for (Object[] listElement : list) {
			Integer sourceId = new Integer(-1);
			try {
				sourceId = (Integer) listElement[0];
			} catch (Exception e) {
			}
			String srcAbbr = (String) listElement[1];
			String srcFull = (String) listElement[2];
			System.out.println("\t" + (0 > sourceId ? "-" : sourceId) + ": "
					+ srcAbbr + " - " + srcFull);
		}

	}

	// public static void displaySourceListForVariables(
	// SourceIdForVariableId sourceListForVariables) {
	// Set<Integer> variableIds = sourceListForVariables.keySet();
	// System.out
	// .println("SourceId lists are found in the database for variableIds: "
	// + variableIds.size());
	//
	// Iterator<Integer> keysIterator = variableIds.iterator();
	//
	// while (keysIterator.hasNext()) {
	// Integer variableId = (Integer) keysIterator.next();
	// System.out.println("VariableId: " + variableId);
	// Vector<Integer> sourceIds = sourceListForVariables.get(variableId);
	// System.out.println("\tSourceIds: ");
	// for (int i = 0; i < sourceIds.size(); i++) {
	// System.out.println("\t\t" + sourceIds.elementAt(i));
	// }
	// }
	// }

	public static void displaySourceListForVariables(
			SourceIdForVariableId sourceListForVariables) {
		Set<String> variableIds = sourceListForVariables.keySet();
		System.out
				.println("SourceId lists are found in the database for variableIds: "
						+ variableIds.size());

		Iterator<String> keysIterator = variableIds.iterator();

		while (keysIterator.hasNext()) {
			String variableId = (String) keysIterator.next();
			System.out.println("VariableId: " + variableId);
			Vector<String> sourceIds = sourceListForVariables.get(variableId);
			System.out.println("\tSourceIds: ");
			for (int i = 0; i < sourceIds.size(); i++) {
				System.out.println("\t\t" + sourceIds.elementAt(i));
			}
		}
	}

}

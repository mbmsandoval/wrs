package ui.web.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import org.apache.click.control.Option;
import org.apache.click.control.Select;
import org.apache.click.dataprovider.DataProvider;

import ui.web.util.element.TitledOption;
import ui.web.util.element.TitledOptionsCheckList;

import com.Constants;

import data.DataGetter;

public class EntrypointOptionsProvider {

	private static final long serialVersionUID = 1L;

	private static final String COUNTRY = Constants.locationTypeCodeCountry;
	private static final String CONTINENT_or_REGION = Constants.locationTypeCodeContinentOrRegion;
	private static final String SUBNATIONAL = Constants.locationTypeCodeSubnational;

	public static void populateLocationTypeOptions(final DataGetter dataGetter,
			Select locationType) {
		locationType.setDataProvider(new DataProvider() {

			public List getData() {
				List<Option> optionList = new ArrayList<Option>();
				optionList.add(new Option(COUNTRY, "Country"));
				optionList.add(new Option(CONTINENT_or_REGION,
						"Continent/Region"));
				optionList.add(new Option(SUBNATIONAL, "Subnational"));
				return optionList;
			}
		});
		locationType.setValue(Constants.locationTypeCodeCountry);
	}

	public static void populateCountryOrContinentOptions(
			TitledOptionsCheckList countryOrContinentCheckList,
			final String locationTypeCode,
			final Hashtable<String, List<TitledOption>> locationOptions) {
		try {
			countryOrContinentCheckList.setOptionList(locationOptions
					.get(locationTypeCode));
		} catch (Exception e) {
		}
	}

	public static void populateYearOptions(
			TitledOptionsCheckList yearCheckList,
			final String locationTypeCode,
			final Hashtable<String, List<TitledOption>> yearOptions) {
		try {
			yearCheckList.setOptionList(yearOptions.get(locationTypeCode));
		} catch (Exception e) {
		}
	}

	public static void populateVariableOptions(
			TitledOptionsCheckList variableCheckList,
			final List<TitledOption> variableOptions) {
		try {
			variableCheckList.setOptionList(variableOptions);
		} catch (Exception e) {
		}
	}

	public static void populateSourceOptions(
			TitledOptionsCheckList sourceCheckList,
			final List<TitledOption> sourceOptions) {
		try {
			sourceCheckList.setOptionList(sourceOptions);
		} catch (Exception e) {
		}
	}

	public static Hashtable<String, List<TitledOption>> getLocationOptions(
			final DataGetter dataGetter) {
		Hashtable<String, List<TitledOption>> locations = new Hashtable<String, List<TitledOption>>();

		List<TitledOption> countryOptions = null;
		List<TitledOption> continentOrRegionOptions = null;
		List<TitledOption> subnationalOptions = null;

		try {
			countryOptions = Collections
					.unmodifiableList(prepareLocationOptionList(dataGetter
							.getCountryListMinimalInfo()));
			continentOrRegionOptions = Collections
					.unmodifiableList(prepareLocationOptionList(dataGetter
							.getContinentAndGroupListMinimalInfo()));
			subnationalOptions = Collections
					.unmodifiableList(prepareLocationOptionList(dataGetter
							.getSubnationalListMinimalInfo()));
		} catch (Exception e) {
			System.out.println("ERROR on getLocationOptions:");
			e.printStackTrace();
		}
		locations.put(Constants.locationTypeCodeCountry, countryOptions);
		locations.put(Constants.locationTypeCodeContinentOrRegion,
				continentOrRegionOptions);
		locations
				.put(Constants.locationTypeCodeSubnational, subnationalOptions);

		return locations;

	}

	public static List<TitledOption> prepareLocationOptionList(
			List<Object[]> locationData) {
		List<TitledOption> list = new ArrayList<TitledOption>();
		if (null != locationData) {
			for (Object[] listElement : locationData) {
				Integer countryId = new Integer(-1);
				try {
					countryId = (Integer) listElement[0];
				} catch (Exception e) {
				} finally {
					String isoAbbr = (String) listElement[1];
					String isoFull = (String) listElement[2];
					if (0 < countryId) {
						if (!isoAbbr.equals("-")) {
							// list.add(new TitledOption(
							// "" + countryId.intValue(), isoAbbr, isoFull));
							list.add(new TitledOption(
									"" + countryId.intValue(),
									((isoFull != null) && (isoFull.length() > 1)) ? isoFull
											: isoAbbr, isoFull));
						}
					}

				}
			}
		}
		return list;
	}

	public static Hashtable<String, List<TitledOption>> getYearOptions(
			final DataGetter dataGetter) {
		Hashtable<String, List<TitledOption>> years = new Hashtable<String, List<TitledOption>>();
		List<TitledOption> countryDataYearOptions = null;
		List<TitledOption> continentOrRegionDataYearOptions = null;
		List<TitledOption> subnationalDataYearOptions = null;

		try {
			countryDataYearOptions = Collections
					.unmodifiableList(prepareYearOptionList(
							dataGetter.getMinYearForNationalData(),
							dataGetter.getMaxYearForNationalData()));

			continentOrRegionDataYearOptions = Collections
					.unmodifiableList(prepareYearOptionList(
							dataGetter.getMinYearForContinentalData(),
							dataGetter.getMaxYearForContinentalData()));

			subnationalDataYearOptions = Collections
					.unmodifiableList(prepareYearOptionList(
							dataGetter.getMinYearForSubnationalData(),
							dataGetter.getMaxYearForSubnationalData()));
		} catch (Exception e) {
		}
		years.put(Constants.locationTypeCodeCountry, countryDataYearOptions);
		years.put(Constants.locationTypeCodeContinentOrRegion,
				continentOrRegionDataYearOptions);
		years.put(Constants.locationTypeCodeSubnational,
				subnationalDataYearOptions);

		return years;
	}

	public static List<TitledOption> prepareYearOptionList(int minYear,
			int maxYear) {
		List<TitledOption> list = new ArrayList<TitledOption>();
		if ((0 < minYear) && (0 < maxYear) && (minYear <= maxYear)) {
			for (int i = minYear; i <= maxYear; i++) {
				list.add(new TitledOption("" + i));
			}
		}
		return list;
	}

	public static List<TitledOption> getVariableOptions(
			final DataGetter dataGetter) {

		List<TitledOption> variableOptions = null;

		try {
			variableOptions = Collections
					.unmodifiableList(prepareVariableOptionList(dataGetter
							.getVariableListMinimalInfo()));
		} catch (Exception e) {
		}

		return variableOptions;

	}

	public static List<TitledOption> prepareVariableOptionList(
			List<Object[]> variablesData) {
		List<TitledOption> list = new ArrayList<TitledOption>();
		if (null != variablesData) {
			for (Object[] listElement : variablesData) {
				Integer varId = new Integer(-1);
				try {
					varId = (Integer) listElement[0];
				} catch (Exception e) {
				} finally {
					String varName = (String) listElement[1];
					String varDesc = (String) listElement[2];
					if (0 < varId) {
						// list.add(new TitledOption("" + varId.intValue(),
						// varName, varDesc));
						// list.add(new TitledOption(varName, varName,
						// varDesc));
						list.add(new TitledOption(varName, varDesc, varDesc));
					}

				}
			}
		}
		return list;
	}

	public static List<TitledOption> getSourceOptions(
			final DataGetter dataGetter) {

		List<TitledOption> sourceOptions = null;

		try {
			sourceOptions = Collections
					.unmodifiableList(prepareSourceOptionList(dataGetter
							.getSourceListMinimalInfo()));
		} catch (Exception e) {
		}

		return sourceOptions;

	}

	public static List<TitledOption> prepareSourceOptionList(
			List<Object[]> sourcesData) {
		List<TitledOption> list = new ArrayList<TitledOption>();
		if (null != sourcesData) {
			for (Object[] listElement : sourcesData) {
				Integer srcId = new Integer(-1);
				try {
					srcId = (Integer) listElement[0];
				} catch (Exception e) {
				} finally {
					String srcName = (String) listElement[1];
					String srcDesc = (String) listElement[2];
					if (0 < srcId) {
						// list.add(new TitledOption("" + srcId.intValue(),
						// srcName, srcDesc));
						list.add(new TitledOption(srcName, srcName, srcDesc));
					}

				}
			}
		}
		return list;
	}

}

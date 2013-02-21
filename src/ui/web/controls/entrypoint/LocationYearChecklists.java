package ui.web.controls.entrypoint;

import java.util.Hashtable;
import java.util.List;

import org.apache.click.control.Button;
import org.apache.click.control.Checkbox;
import org.apache.click.control.FieldSet;
import org.apache.click.control.Form;
import org.apache.click.control.Label;
import org.apache.click.control.Select;
import org.apache.click.control.Submit;
import org.apache.click.control.TextField;
import org.apache.click.util.ClickUtils;
import org.apache.commons.lang.StringUtils;

import ui.web.util.EntrypointOptionsProvider;
import ui.web.util.element.ImageButton;
import ui.web.util.element.SimpleFieldSet;
import ui.web.util.element.TitledOption;
import ui.web.util.element.TitledOptionsCheckList;
import data.DataGetter;

public class LocationYearChecklists extends SimpleFieldSet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DataGetter dataGetter = null;
	private Select locationType = new Select("locationType");
	private TitledOptionsCheckList countryOrContinent = new TitledOptionsCheckList(
			"countryOrContinent");
	private TitledOptionsCheckList year = new TitledOptionsCheckList("year",
			false);
	private TextField locationSearchField = new TextField("Search");
	private Checkbox locationSearchStringStart = new Checkbox(
			"Value(s) starting with");
	private TextField yearSearchField = new TextField("Search");
	private Checkbox yearSearchStringStart = new Checkbox(
			"Value(s) starting with");

	private Hashtable<String, List<TitledOption>> LOCATION_OPTIONS = new Hashtable<String, List<TitledOption>>();
	private Hashtable<String, List<TitledOption>> YEAR_OPTIONS = new Hashtable<String, List<TitledOption>>();

	public LocationYearChecklists(String name, DataGetter dataGetter) {
		super(name);
		this.name = name;
		this.dataGetter = dataGetter;

		locationType.setAttribute("onchange",
				"handleChange(['countryOrContinent', 'year'], form);");

		locationType.setLabel("");
		countryOrContinent.setLabel("");
		year.setLabel("");

		locationType.setWidth("300px");
		countryOrContinent.setWidth("300px");
		year.setWidth("300px");

		countryOrContinent.setHeight("15em");
		year.setHeight("15em");

		setColumns(2);

		FieldSet locationFieldSet = new FieldSet("Location");
		locationFieldSet.setColumns(2);
		locationFieldSet.setLabel("");
		locationFieldSet.setLegend("");
		locationFieldSet.setShowBorder(false);

		SimpleFieldSet locationListHeader = new SimpleFieldSet(
				"Location List Header");
		locationListHeader.setShowBorder(false);
		locationListHeader.setLegend("");
		locationListHeader.setLabel("");
		locationListHeader.setColumns(2);

		Checkbox locationListSelectDeselectAll = new Checkbox(
				"locationListSelectAll");
		locationListSelectDeselectAll.setLabel("");
		locationListSelectDeselectAll.setAttribute("onchange",
				"selectDeselectAll('countryOrContinent', this.checked);");

		
		locationListHeader.add(new Label("label", "Select/Deselect All"));
		locationListHeader.add(locationListSelectDeselectAll);

		locationSearchField.setAttribute("id", "countryOrContinent_search");
		ImageButton locationSearchButton = new ImageButton("Search Location",
				"images/icon/search.png",
				"searchOnCheckList('countryOrContinent');");

		locationSearchStringStart.setAttribute("id",
				"countryOrContinent_searchtext_begin");

		locationSearchField.setValue("");
		locationSearchStringStart.setValue(null);

		SimpleFieldSet locationSearchFieldSet = new SimpleFieldSet(
				"Location Search Field Set");
		locationSearchFieldSet.setColumns(2);
		locationSearchFieldSet.setShowBorder(false);
		locationSearchFieldSet.setLegend("");
		locationSearchFieldSet.setLabel("");
		locationSearchFieldSet.add(locationSearchField);
		locationSearchFieldSet.add(locationSearchButton);
		locationSearchFieldSet.add(locationSearchStringStart, 2);

		locationFieldSet.add(locationType, 2);
		locationFieldSet.add(locationListHeader,2);
		locationFieldSet.add(countryOrContinent, 2);
		locationFieldSet.add(locationSearchFieldSet);

		FieldSet yearFieldSet = new FieldSet("Year");
		yearFieldSet.setColumns(2);
		yearFieldSet.setLabel("");
		yearFieldSet.setLegend("");
		yearFieldSet.setShowBorder(false);

		yearSearchField.setAttribute("id", "year_search");
		ImageButton yearSearchButton = new ImageButton("Search Year",
				"images/icon/search.png", "searchOnCheckList('year');");
		yearSearchStringStart.setAttribute("id", "year_searchtext_begin");

		yearSearchField.setValue("");
		yearSearchStringStart.setValue(null);

		SimpleFieldSet yearSearchFieldSet = new SimpleFieldSet(
				"year Search Field Set");
		yearSearchFieldSet.setColumns(2);
		yearSearchFieldSet.setShowBorder(false);
		yearSearchFieldSet.setLegend("");
		yearSearchFieldSet.setLabel("");
		yearSearchFieldSet.add(yearSearchField);
		yearSearchFieldSet.add(yearSearchButton);
		yearSearchFieldSet.add(yearSearchStringStart, 2);

		SimpleFieldSet yearListHeader = new SimpleFieldSet("Year List Header");
		yearListHeader.setColumns(3);
		yearListHeader.setShowBorder(false);
		yearListHeader.setLegend("");
		yearListHeader.setLabel("");

		Checkbox yearListSelectDeselectAll = new Checkbox("yearListSelectAll");
		yearListSelectDeselectAll.setLabel("");
		yearListSelectDeselectAll.setAttribute("onchange",
				"selectDeselectAll('year', this.checked);");

		
		yearListHeader.add(new Label("label",
				"Select/Deselect All"));
		yearListHeader.add(yearListSelectDeselectAll);

		Label yearLabel = new Label("label", "&nbsp;Year");
		yearLabel.setTextAlign("center");
		yearFieldSet.add(yearLabel, 2);
		yearFieldSet.add(yearListHeader, 2);
		yearFieldSet.add(year, 2);
		yearFieldSet.add(yearSearchFieldSet);

		this.add(locationFieldSet);
		this.add(yearFieldSet);

		this.LOCATION_OPTIONS = EntrypointOptionsProvider
				.getLocationOptions(this.dataGetter);
		this.YEAR_OPTIONS = EntrypointOptionsProvider
				.getYearOptions(this.dataGetter);

	}

	public Select getLocationTypeSelect() {
		return this.locationType;
	}

	public TitledOptionsCheckList getCountryOrContinentChecklist() {
		return this.countryOrContinent;
	}

	public TitledOptionsCheckList getYearChecklist() {
		return this.year;
	}

	public void buildSelects(Form form, Submit save) {

		// Populate the locationType before binding requests
		EntrypointOptionsProvider.populateLocationTypeOptions(dataGetter,
				locationType);

		locationSearchField.setValue("");
		locationSearchStringStart.setValue(null);
		yearSearchField.setValue("");
		yearSearchStringStart.setValue(null);

		// Bind the form field request values
		ClickUtils.bind(form);

		if (StringUtils.isEmpty(locationType.getValue())) {
			// No locationType selected, exit early
			return;
		}

		// If locationType is selected, proceed to populate countryOrContinent
		// and year
		String locationTypeValue = locationType.getValue();
		EntrypointOptionsProvider.populateCountryOrContinentOptions(
				countryOrContinent, locationTypeValue, LOCATION_OPTIONS);

		EntrypointOptionsProvider.populateYearOptions(year, locationTypeValue,
				YEAR_OPTIONS);

		// If save was not clicked, don't validate
		if (form.isFormSubmission() && !save.isClicked()) {
			form.setValidate(false);
		}
	}

}

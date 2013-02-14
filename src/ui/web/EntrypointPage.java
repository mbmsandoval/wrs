package ui.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Persistence;

import org.apache.click.control.FieldSet;
import org.apache.click.control.Form;
import org.apache.click.control.Submit;
import org.apache.click.element.Element;
import org.apache.click.element.JsScript;

import ui.web.controls.entrypoint.LocationYearChecklists;
import ui.web.controls.entrypoint.VariableSourceChecklists;

import com.Constants;

import data.DataGetter;

public class EntrypointPage extends BorderPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String title = "WRS";
	private Date time = new Date();

	private Form form = new Form("form");
	private LocationYearChecklists locationYearChecklistsFieldset = null;
	private VariableSourceChecklists variableSourceChecklistsFieldset = null;
	private Submit go = new Submit("GO");

	private DataGetter dataGetter = null;

	public EntrypointPage() {
		addModel("title", title);
		addModel("time", time);

		// NOTE: Get this from session
		dataGetter = new DataGetter(Persistence.createEntityManagerFactory(
				Constants.persistenceUnitName).createEntityManager());

		locationYearChecklistsFieldset = new LocationYearChecklists(
				"Location and Year", dataGetter);
		locationYearChecklistsFieldset.setLegend("");

		variableSourceChecklistsFieldset = new VariableSourceChecklists(
				"Variable and Source", dataGetter);
		variableSourceChecklistsFieldset.setLegend("");

		form.setLabelsPosition("left");
		addControl(form);

		form.add(locationYearChecklistsFieldset);
		form.add(variableSourceChecklistsFieldset);
		form.add(go);

		// build the Selects in the onInit phase
		locationYearChecklistsFieldset.buildSelects(form, go);
		variableSourceChecklistsFieldset.buildSelects(form, go);
	}

	@Override
	public List<Element> getHeadElements() {
		if (headElements == null) {
			headElements = super.getHeadElements();

			Map<String, Object> templateModel = new HashMap<String, Object>();
			templateModel.put("locationTypeId", locationYearChecklistsFieldset
					.getLocationTypeSelect().getId());
			templateModel.put("countryOrContinentId",
					locationYearChecklistsFieldset
							.getCountryOrContinentChecklist().getId());
			templateModel.put("yearId", locationYearChecklistsFieldset
					.getYearChecklist().getId());
			templateModel.put("variableId", variableSourceChecklistsFieldset
					.getVariableChecklist().getId());
			templateModel.put("sourceId", variableSourceChecklistsFieldset
					.getSourceChecklist().getId());

			// populate-on-select.js is a Velocity template which is rendered
			// directly
			// from this Page
			JsScript script = new JsScript("/form/entrypoint.js", templateModel);
			headElements.add(script);

		}
		return headElements;
	}

}
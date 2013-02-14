package ui.web.controls.entrypoint;

import java.util.ArrayList;
import java.util.List;

import org.apache.click.control.Button;
import org.apache.click.control.Checkbox;
import org.apache.click.control.FieldSet;
import org.apache.click.control.Form;
import org.apache.click.control.Label;
import org.apache.click.control.Submit;
import org.apache.click.control.TextField;
import org.apache.click.util.ClickUtils;

import ui.web.util.EntrypointOptionsProvider;
import ui.web.util.element.ImageButton;
import ui.web.util.element.SimpleFieldSet;
import ui.web.util.element.TitledOption;
import ui.web.util.element.TitledOptionsCheckList;
import data.DataGetter;

public class VariableSourceChecklists extends SimpleFieldSet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DataGetter dataGetter = null;

	private TitledOptionsCheckList variable = new TitledOptionsCheckList(
			"variable");
	private TitledOptionsCheckList source = new TitledOptionsCheckList(
			"source", false);
	private TextField variableSearchField = new TextField("Search");
	private Checkbox variableSearchStringStart = new Checkbox(
			"Value(s) starting with");
	private TextField sourceSearchField = new TextField("Search");
	private Checkbox sourceSearchStringStart = new Checkbox(
			"Value(s) starting with");

	private List<TitledOption> VARIABLE_OPTIONS = new ArrayList<TitledOption>();
	private List<TitledOption> SOURCE_OPTIONS = new ArrayList<TitledOption>();

	public VariableSourceChecklists(String name, DataGetter dataGetter) {
		super(name);
		this.name = name;
		this.dataGetter = dataGetter;

		variable.setLabel("");
		source.setLabel("");

		variable.setWidth("300px");
		source.setWidth("300px");

		variable.setHeight("15em");
		source.setHeight("15em");

		setColumns(2);

		FieldSet variableFieldSet = new FieldSet("Variable");
		variableFieldSet.setColumns(2);
		variableFieldSet.setLabel("");
		variableFieldSet.setLegend("");
		variableFieldSet.setShowBorder(false);

		variableSearchField.setAttribute("id", "variable_search");

		ImageButton variableSearchButton = new ImageButton("Search Variable",
				"images/icon/search.png", "searchOnCheckList('variable');");
		variableSearchStringStart.setAttribute("id",
				"variable_searchtext_begin");

		variableSearchField.setValue("");
		variableSearchStringStart.setValue(null);

		SimpleFieldSet variableSearchFieldSet = new SimpleFieldSet(
				"Variable Search Field Set");
		variableSearchFieldSet.setColumns(2);
		variableSearchFieldSet.setShowBorder(false);
		variableSearchFieldSet.setLegend("");
		variableSearchFieldSet.setLabel("");
		variableSearchFieldSet.add(variableSearchField);
		variableSearchFieldSet.add(variableSearchButton);
		variableSearchFieldSet.add(variableSearchStringStart, 2);

		String labelTextVariable = "&nbsp;Variable";
		variableFieldSet.add(new Label("label", labelTextVariable), 2);
		variableFieldSet.add(variable, 2);
		variableFieldSet.add(variableSearchFieldSet);

		FieldSet sourceFieldSet = new FieldSet("Source");
		sourceFieldSet.setColumns(2);
		sourceFieldSet.setLabel("");
		sourceFieldSet.setLegend("");
		sourceFieldSet.setShowBorder(false);

		sourceSearchField.setAttribute("id", "source_search");
		ImageButton sourceSearchButton = new ImageButton("Search Source",
				"images/icon/search.png", "searchOnCheckList('source');");
		sourceSearchStringStart.setAttribute("id", "source_searchtext_begin");

		sourceSearchField.setValue("");
		sourceSearchStringStart.setValue(null);

		SimpleFieldSet sourceSearchFieldSet = new SimpleFieldSet(
				"source Search Field Set");
		sourceSearchFieldSet.setColumns(2);
		sourceSearchFieldSet.setShowBorder(false);
		sourceSearchFieldSet.setLegend("");
		sourceSearchFieldSet.setLabel("");
		sourceSearchFieldSet.add(sourceSearchField);
		sourceSearchFieldSet.add(sourceSearchButton);
		sourceSearchFieldSet.add(sourceSearchStringStart, 2);

		String labelTextSource = "&nbsp;Source";
		sourceFieldSet.add(new Label("label", labelTextSource), 2);
		sourceFieldSet.add(source, 2);
		sourceFieldSet.add(sourceSearchFieldSet);

		this.add(variableFieldSet);
		this.add(sourceFieldSet);

		this.VARIABLE_OPTIONS = EntrypointOptionsProvider
				.getVariableOptions(this.dataGetter);
		this.SOURCE_OPTIONS = EntrypointOptionsProvider
				.getSourceOptions(this.dataGetter);

	}

	public TitledOptionsCheckList getVariableChecklist() {
		return this.variable;
	}

	public TitledOptionsCheckList getSourceChecklist() {
		return this.source;
	}

	public void buildSelects(Form form, Submit save) {

		variableSearchField.setValue("");
		variableSearchStringStart.setValue(null);
		sourceSearchField.setValue("");
		sourceSearchStringStart.setValue(null);

		// Bind the form field request values
		ClickUtils.bind(form);

		EntrypointOptionsProvider.populateVariableOptions(variable,
				VARIABLE_OPTIONS);

		EntrypointOptionsProvider.populateSourceOptions(source, SOURCE_OPTIONS);

		// If save was not clicked, don't validate
		if (form.isFormSubmission() && !save.isClicked()) {
			form.setValidate(false);
		}
	}

}

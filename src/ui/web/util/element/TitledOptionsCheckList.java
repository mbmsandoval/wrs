package ui.web.util.element;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.click.Context;
import org.apache.click.control.Field;
import org.apache.click.dataprovider.DataProvider;
import org.apache.click.element.CssImport;
import org.apache.click.element.Element;
import org.apache.click.element.JsImport;
import org.apache.click.element.JsScript;
import org.apache.click.util.ClickUtils;
import org.apache.click.util.HtmlStringBuffer;
import org.apache.click.util.PropertyUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

public class TitledOptionsCheckList extends Field {

	private static final long serialVersionUID = 1L;
	protected static final String STYLE_CLASS = "checkList";

	protected final static String VALIDATE_CHECKLIST_FUNCTION = "function validate_{0}() '{'\n"
			+ "   var msg = validateCheckList(''{1}'', ''{2}'', {3}, [''{4}'']);\n"
			+ "   if (msg) '{'\n"
			+ "      return msg + ''|{0}'';\n"
			+ "   '}' else '{'\n"
			+ "      return null;\n"
			+ "   '}'\n"
			+ "'}'\n";

	protected DataProvider<TitledOption> dataProvider;

	protected String height;

	protected List<TitledOption> optionList;

	protected boolean sortable;

	protected List<String> sortorder;

	protected List<String> selectedValues;

	public TitledOptionsCheckList(String name) {
		super(name);
	}

	public TitledOptionsCheckList(String name, String label) {
		super(name, label);
	}

	public TitledOptionsCheckList(String name, boolean required) {
		super(name);
		setRequired(required);

	}

	public TitledOptionsCheckList(String name, String label, boolean required) {
		super(name, label);
		setRequired(required);
	}

	public TitledOptionsCheckList() {
	}

	@Override
	public String getTag() {
		return "div";
	}

	public void add(TitledOption option) {
		if (option == null) {
			String msg = "option parameter cannot be null";
			throw new IllegalArgumentException(msg);
		}
		getOptionList().add(option);
	}

	public void add(String value) {
		if (value == null) {
			String msg = "value parameter cannot be null";
			throw new IllegalArgumentException(msg);
		}
		getOptionList().add(new TitledOption(value));
	}

	public void add(Object option) {
		if (option instanceof TitledOption) {
			getOptionList().add((TitledOption) option);

		} else if (option instanceof String) {
			getOptionList().add(new TitledOption(option.toString()));

		} else if (option instanceof Number) {
			getOptionList().add(new TitledOption(option.toString()));

		} else if (option instanceof Boolean) {
			getOptionList().add(new TitledOption(option.toString()));

		} else {
			String message = "Unsupported options class "
					+ option.getClass().getName()
					+ ". Please use method "
					+ "TitledOptionsCheckList.addAll(Collection, String, String) instead.";
			throw new IllegalArgumentException(message);
		}
	}

	public void addAll(Collection<?> options) {
		if (options == null) {
			String msg = "options parameter cannot be null";
			throw new IllegalArgumentException(msg);
		}

		if (!options.isEmpty()) {
			for (Object option : options) {
				add(option);
			}
		}
	}

	public void addAll(Map<?, ?> options) {
		if (options == null) {
			String msg = "options parameter cannot be null";
			throw new IllegalArgumentException(msg);
		}
		for (Map.Entry<?, ?> entry : options.entrySet()) {
			TitledOption option = new TitledOption(entry.getKey().toString(),
					entry.getValue().toString());
			getOptionList().add(option);
		}
	}

	public void addAll(String[] options) {
		if (options == null) {
			String msg = "options parameter cannot be null";
			throw new IllegalArgumentException(msg);
		}
		for (String option : options) {
			getOptionList().add(new TitledOption(option, option));
		}
	}

	public void addAll(Collection<?> objects, String optionValueProperty,
			String optionLabelProperty) {
		if (objects == null) {
			String msg = "objects parameter cannot be null";
			throw new IllegalArgumentException(msg);
		}
		if (optionValueProperty == null) {
			String msg = "optionValueProperty parameter cannot be null";
			throw new IllegalArgumentException(msg);
		}

		if (objects.isEmpty()) {
			return;
		}

		Map<?, ?> methodCache = new HashMap<Object, Object>();

		for (Object object : objects) {
			try {
				Object valueResult = PropertyUtils.getValue(object,
						optionValueProperty, methodCache);

				Object labelResult = valueResult;

				if (optionLabelProperty != null) {
					labelResult = PropertyUtils.getValue(object,
							optionLabelProperty, methodCache);
				}

				TitledOption option = null;

				if (labelResult != null) {
					option = new TitledOption(valueResult,
							labelResult.toString());
				} else {
					option = new TitledOption(valueResult.toString());
				}

				getOptionList().add(option);

			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	public DataProvider<TitledOption> getDataProvider() {
		return dataProvider;
	}

	public void setDataProvider(DataProvider<TitledOption> dataProvider) {
		this.dataProvider = dataProvider;
		if (dataProvider != null) {
			if (optionList != null) {
				ClickUtils.getLogService().warn(
						"please note that setting a"
								+ " dataProvider nullifies the optionList");
			}
			setOptionList(null);
		}
	}

	public void addStyle(String style) {
		if (StringUtils.isBlank(style)) {
			throw new IllegalArgumentException("The style is empty");
		}
		style = style.trim();

		if (style.charAt(style.length() - 1) == ';') {
			style = style + ";";
		}

		String old = getAttribute("style");
		if (old == null || (old = old.trim()).length() == 0) {
			setAttribute("style", style);
		} else {
			if (old.charAt(old.length() - 1) != ';') {
				old = old + ';';
			}
			old = old + style;
			setAttribute("style", old);
		}
	}

	@Override
	public String getFocusJavaScript() {
		HtmlStringBuffer buffer = new HtmlStringBuffer();

		buffer.append("setFocus('");
		buffer.append(getName() + "_0");
		buffer.append("');");

		return buffer.toString();
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getHeight() {
		return height;
	}

	public void setHtmlClass(String clazz) {
		addStyleClass(clazz);
	}

	public String getHtmlClass() {
		return getAttribute("class");
	}

	@Override
	public List<Element> getHeadElements() {
		if (headElements == null) {
			Context context = getContext();
			String versionIndicator = ClickUtils
					.getResourceVersionIndicator(context);

			headElements = super.getHeadElements();

			headElements.add(new CssImport("/click/checklist/checklist.css",
					versionIndicator));
			headElements.add(new JsImport("/click/checklist/checklist.js",
					versionIndicator));

			if (isSortable()) {
				headElements.add(new JsImport("/click/prototype/prototype.js",
						versionIndicator));
				headElements.add(new JsImport("/click/prototype/builder.js",
						versionIndicator));
				headElements.add(new JsImport("/click/prototype/effects.js",
						versionIndicator));
				headElements.add(new JsImport("/click/prototype/dragdrop.js",
						versionIndicator));
				headElements.add(new JsImport("/click/prototype/controls.js",
						versionIndicator));
				headElements.add(new JsImport("/click/prototype/slider.js",
						versionIndicator));
			}
		}

		String checkListId = getId();
		JsScript script = new JsScript();
		script.setId(checkListId + "-js-setup");

		if (!headElements.contains(script)) {
			script.setExecuteOnDomReady(true);

			HtmlStringBuffer buffer = new HtmlStringBuffer(50);

			if (isSortable()) {
				if (getHeight() != null) {
					buffer.append("Position.includeScrollOffset = true;\n");
				}

				buffer.append("Sortable.create('");
				buffer.append(StringEscapeUtils.escapeJavaScript(checkListId));
				buffer.append("-ul'");

				if (getHeight() != null) {
					buffer.append(", { scroll : '");
					buffer.append(StringEscapeUtils
							.escapeJavaScript(checkListId));
					buffer.append("'}");
				}
				buffer.append(");");

			} else {
				buffer.append("initChecklist('");
				buffer.append(StringEscapeUtils.escapeJavaScript(checkListId));
				buffer.append("-ul');\n");
			}
			script.setContent(buffer.toString());
			headElements.add(script);
		}

		return headElements;
	}

	public List<TitledOption> getOptionList() {
		if (optionList == null) {

			DataProvider<TitledOption> dp = getDataProvider();

			if (dp != null) {
				Iterable<TitledOption> iterableData = dp.getData();

				if (iterableData instanceof List<?>) {
					setOptionList((List<TitledOption>) iterableData);

				} else {
					optionList = new ArrayList<TitledOption>();

					if (iterableData != null) {
						for (Object option : iterableData) {
							add(option);
						}
					}
				}
			} else {
				optionList = new ArrayList<TitledOption>();
			}
		}
		return optionList;
	}

	public void setOptionList(List<TitledOption> options) {
		optionList = options;
	}

	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}

	public boolean isSortable() {
		return sortable;
	}

	public List<String> getSortorder() {
		return sortorder;
	}

	public List<String> getValues() {
		return getSelectedValues();
	}

	public List<String> getSelectedValues() {
		if (selectedValues != null) {
			return selectedValues;

		} else {
			return Collections.emptyList();
		}
	}

	public void setValues(List<String> values) {
		this.selectedValues = values;
	}

	public void setSelectedValues(List<String> selectedValues) {
		this.selectedValues = selectedValues;
	}

	@Override
	public Object getValueObject() {
		return getSelectedValues();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setValueObject(Object object) {
		if (object instanceof List<?>) {
			setSelectedValues((List<String>) object);
		}
	}

	@Override
	public String getValidationJavaScript() {
		Object[] args = new Object[5];
		args[0] = getId();
		args[1] = getName();
		args[2] = getForm().getId();
		args[3] = String.valueOf(isRequired());
		args[4] = getMessage("field-required-error", getErrorLabel());

		return MessageFormat.format(VALIDATE_CHECKLIST_FUNCTION, args);
	}

	@Override
	public void bindRequestValue() {

		Context context = getContext();

		if (getOptionList().isEmpty()) {
			return;
		}

		List<String> localSelectedValues = new ArrayList<String>();

		String[] parameterValues = context.getRequestParameterValues(getName());

		if (parameterValues != null) {
			for (String parameterValue : parameterValues) {
				localSelectedValues.add(parameterValue);
			}
		}

		if (isSortable()) {
			String[] orderParameterValues = context.getRequest()
					.getParameterValues(getName() + "_order");
			if (orderParameterValues != null) {
				this.sortorder = new ArrayList<String>(
						orderParameterValues.length);
				for (String orderParameterValue : orderParameterValues) {
					if (orderParameterValue != null) {
						sortorder.add(orderParameterValue);
					}
				}
				sortOptions(orderParameterValues);
			}
		}
		setSelectedValues(localSelectedValues);
	}

	@Override
	public boolean onProcess() {
		if (isDisabled()) {
			Context context = getContext();

			if (context.hasRequestParameter(getName())) {
				setDisabled(false);
			} else {
				return true;
			}
		}

		bindRequestValue();

		if (getValidate()) {
			validate();
		}

		dispatchActionEvent();

		return true;
	}

	protected void sortOptions(String[] order) {
		final List<TitledOption> options = getOptionList();
		final List<TitledOption> orderList = new ArrayList<TitledOption>(
				options.size());

		for (int i = 0, size = order.length; i < size; i++) {
			String orderValue = order[i];
			if (orderValue != null) {
				int oI = -1;
				for (int j = 0, jSize = options.size(); j < jSize; j++) {
					TitledOption optT = options.get(j);
					if (orderValue.equals(optT.getValue())) {
						oI = j;
					}
				}
				if (oI != -1) {
					orderList.add(options.remove(oI));
				}
			}
		}
		options.addAll(0, orderList);
	}

	@Override
	public int getControlSizeEst() {
		int bufferSize = 50;
		if (!getOptionList().isEmpty()) {
			bufferSize = bufferSize + (optionList.size() * 48);
		}
		return bufferSize;
	}

	@Override
	public void render(HtmlStringBuffer buffer) {
		final boolean sortable = isSortable();

		buffer.elementStart(getTag());

		buffer.appendAttribute("id", getId());

		addStyleClass(STYLE_CLASS);

		if (isValid()) {
			removeStyleClass("error");
		} else {
			addStyleClass("error");
		}

		setStyle("height", getHeight());

		if (sortable && getHeight() == null) {
			setStyle("overflow", "hidden");
		} else {
			setStyle("overflow", "auto");
		}

		setStyle("border-style", "solid");
		setStyle("border-width", "1px");
		setStyle("border-color", "gray");

		appendAttributes(buffer);

		buffer.closeTag();

		// buffer.elementStart("ul");
		// buffer.append(" id=\"").append(getId()).append("-ul\"");
		// buffer.closeTag();

		List<TitledOption> optionsList = getOptionList();
		if (!optionsList.isEmpty()) {
			int i = -1;
			for (TitledOption option : optionsList) {
				i++;

				// buffer.append("<li>");
				if (sortable) {
					buffer.elementStart("div");
					buffer.appendAttribute("style", "cursor:move;");
				} else {
					buffer.elementStart("label");
					buffer.append(" for=\"").append(getName()).append('_')
							.append(i).append("\"");

				}
				// ADDED by MMMBM
				buffer.append(" id=\"label_on_").append(getName()).append('_')
						.append(i).append("\"");

				buffer.appendAttribute("class", "checkListLabel");

				// ADDED by MMMBM
				if (!StringUtils.isEmpty(option.getTitle())) {
					buffer.appendAttribute("title", option.getTitle());
				}

				buffer.closeTag();

				buffer.append("<input type=\"checkbox\" ");
				buffer.appendAttributeEscaped("value", option.getValue());
				buffer.append(" id=\"").append(getName()).append('_').append(i)
						.append("\"");
				buffer.appendAttribute("name", getName());

				if (sortable) {
					buffer.appendAttribute("style", "cursor:default;");
				}

				boolean checked = false;
				List<String> values = getSelectedValues();
				for (int k = 0; k < values.size(); k++) {
					if (String.valueOf(values.get(k)).equals(option.getValue())) {
						checked = true;
					}
				}

				if (checked) {
					buffer.appendAttribute("checked", "checked");
				}
				if (isReadonly() || isDisabled()) {
					buffer.appendAttributeDisabled();
				}
				buffer.elementEnd();
				buffer.appendEscaped(option.getLabel());

				if (sortable) {
					buffer.append("</div>");
				} else {
					buffer.append("</label>");
				}

				if (checked && (isReadonly() || isDisabled())) {
					buffer.elementStart("input");
					buffer.appendAttribute("type", "hidden");
					buffer.appendAttribute("name", getName());
					buffer.appendAttributeEscaped("value", option.getValue());
					buffer.elementEnd();
				}

				if (sortable) {
					buffer.append("<input type=\"hidden\"");
					buffer.appendAttribute("name", getName() + "_order");
					buffer.appendAttributeEscaped("value", option.getValue());
					buffer.elementEnd();
				}

				buffer.append("<br>");
				// buffer.append("</li>");
			}
			buffer.append("<input type='hidden' id='" + getName()
					+ "_size' value='" + (i + 1) + "' />");
		}
		// buffer.append("</ul>");
		buffer.elementEnd(getTag());
	}

	@Override
	public void validate() {
		if (isRequired()) {
			if (getSelectedValues().isEmpty()) {
				setErrorMessage("select-error");
			}
		}
	}

}

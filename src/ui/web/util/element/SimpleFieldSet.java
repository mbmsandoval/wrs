package ui.web.util.element;

import org.apache.click.util.HtmlStringBuffer;

public class SimpleFieldSet extends org.apache.click.control.FieldSet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SimpleFieldSet(String name) {
		super(name);
	}

	@Override
	public void render(HtmlStringBuffer buffer) {

		if (getShowBorder()) {
			buffer.elementStart(getTag());

			buffer.appendAttribute("id", getId());

			appendAttributes(buffer);

			if (isDisabled()) {
				buffer.appendAttributeDisabled();
			}

			buffer.closeTag();
			buffer.append("\n");

			String legend = getLegend();
			if (legend != null && legend.length() > 0) {
				buffer.elementStart("legend");
				if (hasLegendAttributes()) {
					Object legendId = getLegendAttributes().get("id");
					if (legendId != null) {
						buffer.appendAttribute("id", legendId);
					} else {
						buffer.appendAttribute("id", getId() + "-legend");
					}
					buffer.appendAttributes(getLegendAttributes());
				} else {
					buffer.appendAttribute("id", getId() + "-legend");
				}
				buffer.closeTag();
				buffer.append(getLegend());
				buffer.elementEnd("legend");
				buffer.append("\n");
			}
		}

		// Render Controls
		renderFields(buffer);

		if (getShowBorder()) {
			buffer.elementEnd(getTag());
			buffer.append("\n");
		}
	}

}

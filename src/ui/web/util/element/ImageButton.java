package ui.web.util.element;

public class ImageButton extends ui.web.util.element.ext.Image {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ImageButton(String name, String src, String onClickActionJS) {
		super(name, src);
		this.setAttribute("onclick", onClickActionJS);
		this.setStyle("width", "25px");
		this.setStyle("height", "25px");
	}
}

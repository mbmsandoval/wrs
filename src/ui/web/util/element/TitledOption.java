package ui.web.util.element;

public class TitledOption extends org.apache.click.control.Option {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String title = "";

	public TitledOption(Object value) {
		super(value);

	}

	public TitledOption(Object value, Object label) {
		super(value, label);

		this.title = label.toString();
	}

	public TitledOption(Object value, Object label, String title) {
		super(value, label);

		this.title = title;

	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

}

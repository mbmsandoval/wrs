package ui.web.util.element.ext;

/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.click.Context;
import org.apache.click.control.AbstractControl;
import org.apache.click.util.HtmlStringBuffer;
import org.apache.commons.lang.StringUtils;

/**
 * Provides an Image control: &nbsp; &lt;img src='edit.gif'&gt;.
 * <p/>
 * The Image control is useful for displaying static images. Below is an
 * example.
 * <p/>
 * 
 * <tt>Image.java</tt> page class:
 * 
 * <pre class="prettyprint">
 * public ImagePage() {
 * 
 * 	addControl(new Image(&quot;image&quot;, &quot;/assets/images/penguin.png&quot;));
 * 
 * }
 * </pre>
 * 
 * Below is the <tt>image.htm</tt> template:
 * 
 * <pre class="prettyprint">
 * $image
 * </pre>
 * 
 * See also W3C HTML reference <a class="external" target="_blank"
 * title="W3C HTML 4.01 Specification"
 * href="http://www.w3.org/TR/html401/struct/objects.html#edef-IMG">IMG</a>
 * 
 * @see DynamicImage
 */
public class Image extends AbstractControl {

	private static final long serialVersionUID = 1L;

	// -------------------------------------------------------------- Variables

	/** The Image src attribute. */
	protected String src;

	// ----------------------------------------------------------- Constructors

	/**
	 * Create an Image.
	 */
	public Image() {
	}

	/**
	 * Create an Image with the given name.
	 * 
	 * @param name
	 *            the name of the control
	 */
	public Image(String name) {
		if (name != null) {
			setName(name);
		}
	}

	/**
	 * Create an Image with the given name and src attribute.
	 * <p/>
	 * If the src value begins with a <tt class="wr">"/"</tt> character the src
	 * attribute will be prefixed with the web applications
	 * <tt>context path</tt>. Note if the given src value is already prefixed
	 * with the <tt>context path</tt>, Click won't add it a second time.
	 * 
	 * @param name
	 *            the name of the image
	 * @param src
	 *            the src attribute of the image
	 */
	public Image(String name, String src) {
		if (name != null) {
			setName(name);
		}
		if (src != null) {
			setSrc(src);
		}
	}

	/**
	 * Create an Image with the given name, src and ID attributes.
	 * 
	 * @param name
	 *            the name of the image
	 * @param src
	 *            the src attribute of the image
	 * @param id
	 *            the id attribute of the image
	 */
	public Image(String name, String src, String id) {
		this(name, src);
		if (id != null) {
			setId(id);
		}
	}

	// ------------------------------------------------------ Public Properties

	/**
	 * Return the image html tag: <tt>img</tt>.
	 * 
	 * @see org.apache.click.control.AbstractControl#getTag()
	 * 
	 * @return this controls html tag
	 */
	public String getTag() {
		return "img";
	}

	/**
	 * Return the src attribute of the image.
	 * 
	 * @return the src attribute
	 */
	public String getSrc() {
		return src;
	}

	/**
	 * Set the src attribute of the image.
	 * <p/>
	 * If the src value begins with a <tt class="wr">"/"</tt> character the src
	 * attribute will be prefixed with the web applications
	 * <tt>context path</tt>. Note if the given src value is already prefixed
	 * with the <tt>context path</tt>, Click won't add it a second time.
	 * 
	 * @param src
	 *            the src attribute of the image
	 */
	public void setSrc(String src) {
		Context context = getContext();
		if (StringUtils.isNotBlank(src)) {
			if (src.charAt(0) == '/') {
				String contextPath = context.getRequest().getContextPath();

				// Guard against adding duplicate context path
				if (!src.startsWith(contextPath + '/')) {
					src = contextPath + src;
				}
			}
		}
		this.src = src;
	}

	// --------------------------------------------------------- Public Methods

	/**
	 * Render the HTML representation of the Image.
	 * 
	 * @see #toString()
	 * 
	 * @param buffer
	 *            the specified buffer to render the control's output to
	 */
	public void render(HtmlStringBuffer buffer) {
		buffer.elementStart(getTag());
		buffer.appendAttribute("name", getName());
		buffer.appendAttribute("id", getId());
		buffer.appendAttribute("src", getSrc());
		appendAttributes(buffer);
		buffer.closeTag();
		buffer.elementEnd(getTag());
	}
}
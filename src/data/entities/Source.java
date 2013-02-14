package data.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the source database table.
 * 
 */
@Entity
@Table(name="source")
public class Source implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int sourceid;

	private String srcabbr;

	private String srcdescription;

	private String srcdetails;

	private String srcfull;

	private String srcremarks;

	private String srctype;

	private String srcurl;

	public Source() {
	}

	public int getSourceid() {
		return this.sourceid;
	}

	public void setSourceid(int sourceid) {
		this.sourceid = sourceid;
	}

	public String getSrcabbr() {
		return this.srcabbr;
	}

	public void setSrcabbr(String srcabbr) {
		this.srcabbr = srcabbr;
	}

	public String getSrcdescription() {
		return this.srcdescription;
	}

	public void setSrcdescription(String srcdescription) {
		this.srcdescription = srcdescription;
	}

	public String getSrcdetails() {
		return this.srcdetails;
	}

	public void setSrcdetails(String srcdetails) {
		this.srcdetails = srcdetails;
	}

	public String getSrcfull() {
		return this.srcfull;
	}

	public void setSrcfull(String srcfull) {
		this.srcfull = srcfull;
	}

	public String getSrcremarks() {
		return this.srcremarks;
	}

	public void setSrcremarks(String srcremarks) {
		this.srcremarks = srcremarks;
	}

	public String getSrctype() {
		return this.srctype;
	}

	public void setSrctype(String srctype) {
		this.srctype = srctype;
	}

	public String getSrcurl() {
		return this.srcurl;
	}

	public void setSrcurl(String srcurl) {
		this.srcurl = srcurl;
	}

}
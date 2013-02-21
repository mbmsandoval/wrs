package data.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the location database table.
 * 
 */
@Entity
@Table(name="location")
public class Location implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int locationid;

	@Column(precision=10, scale=3)
	private BigDecimal localtitute;

	@Column(length=45)
	private String loccode;

	private int loccountryid;

	@Column(length=45)
	private String lochasc;

	@Column(precision=10, scale=3)
	private BigDecimal loclat;

	@Column(precision=10, scale=3)
	private BigDecimal loclon;

	@Column(length=45)
	private String locname;

	@Column(length=45)
	private String locnameother;

	@Column(length=45)
	private String locsub1;

	@Column(length=45)
	private String locsub2;

	public Location() {
	}

	public int getLocationid() {
		return this.locationid;
	}

	public void setLocationid(int locationid) {
		this.locationid = locationid;
	}

	public BigDecimal getLocaltitute() {
		return this.localtitute;
	}

	public void setLocaltitute(BigDecimal localtitute) {
		this.localtitute = localtitute;
	}

	public String getLoccode() {
		return this.loccode;
	}

	public void setLoccode(String loccode) {
		this.loccode = loccode;
	}

	public int getLoccountryid() {
		return this.loccountryid;
	}

	public void setLoccountryid(int loccountryid) {
		this.loccountryid = loccountryid;
	}

	public String getLochasc() {
		return this.lochasc;
	}

	public void setLochasc(String lochasc) {
		this.lochasc = lochasc;
	}

	public BigDecimal getLoclat() {
		return this.loclat;
	}

	public void setLoclat(BigDecimal loclat) {
		this.loclat = loclat;
	}

	public BigDecimal getLoclon() {
		return this.loclon;
	}

	public void setLoclon(BigDecimal loclon) {
		this.loclon = loclon;
	}

	public String getLocname() {
		return this.locname;
	}

	public void setLocname(String locname) {
		this.locname = locname;
	}

	public String getLocnameother() {
		return this.locnameother;
	}

	public void setLocnameother(String locnameother) {
		this.locnameother = locnameother;
	}

	public String getLocsub1() {
		return this.locsub1;
	}

	public void setLocsub1(String locsub1) {
		this.locsub1 = locsub1;
	}

	public String getLocsub2() {
		return this.locsub2;
	}

	public void setLocsub2(String locsub2) {
		this.locsub2 = locsub2;
	}

}
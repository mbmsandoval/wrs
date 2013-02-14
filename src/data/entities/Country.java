package data.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the country database table.
 * 
 */
@Entity
@Table(name = "country")
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int cntryid;

	private String continent;

	private String continent1;

	private String continent2;

	private Boolean continental;

	private String currency;

	private String faothree;

	private String fips;

	private String isoabbr;

	private String isofull;

	private int isonum;

	private String isothree;

	private String isotwo;

	private int oldId;

	private String wb;

	public Country() {
	}

	public int getCntryid() {
		return this.cntryid;
	}

	public void setCntryid(int cntryid) {
		this.cntryid = cntryid;
	}

	public String getContinent() {
		return this.continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public String getContinent1() {
		return this.continent1;
	}

	public void setContinent1(String continent1) {
		this.continent1 = continent1;
	}

	public String getContinent2() {
		return this.continent2;
	}

	public void setContinent2(String continent2) {
		this.continent2 = continent2;
	}

	public Boolean getContinental() {
		return this.continental;
	}

	public void setContinental(Boolean continental) {
		this.continental = continental;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getFaothree() {
		return this.faothree;
	}

	public void setFaothree(String faothree) {
		this.faothree = faothree;
	}

	public String getFips() {
		return this.fips;
	}

	public void setFips(String fips) {
		this.fips = fips;
	}

	public String getIsoabbr() {
		return this.isoabbr;
	}

	public void setIsoabbr(String isoabbr) {
		this.isoabbr = isoabbr;
	}

	public String getIsofull() {
		return this.isofull;
	}

	public void setIsofull(String isofull) {
		this.isofull = isofull;
	}

	public int getIsonum() {
		return this.isonum;
	}

	public void setIsonum(int isonum) {
		this.isonum = isonum;
	}

	public String getIsothree() {
		return this.isothree;
	}

	public void setIsothree(String isothree) {
		this.isothree = isothree;
	}

	public String getIsotwo() {
		return this.isotwo;
	}

	public void setIsotwo(String isotwo) {
		this.isotwo = isotwo;
	}

	public int getOldId() {
		return this.oldId;
	}

	public void setOldId(int oldId) {
		this.oldId = oldId;
	}

	public String getWb() {
		return this.wb;
	}

	public void setWb(String wb) {
		this.wb = wb;
	}

}
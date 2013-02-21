package data.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the region database table.
 * 
 */
@Entity
@Table(name="region")
public class Region implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int regionid;

	@Column(length=10)
	private String continent;

	@Column(length=45)
	private String continent1;

	@Column(length=45)
	private String continent2;

	@Column(length=10)
	private String currency;

	@Column(length=50)
	private String faothree;

	@Column(length=6)
	private String fips;

	@Column(nullable=false, length=25)
	private String isoabbr;

	@Column(length=50)
	private String isofull;

	private double isonum;

	@Column(nullable=false, length=6)
	private String isothree;

	@Column(length=2)
	private String isotwo;

	private int oldId;

	@Column(length=255)
	private String wb;

	public Region() {
	}

	public int getRegionid() {
		return this.regionid;
	}

	public void setRegionid(int regionid) {
		this.regionid = regionid;
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

	public double getIsonum() {
		return this.isonum;
	}

	public void setIsonum(double isonum) {
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
package data.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the pay database table.
 * 
 */
@Entity
@Table(name="pay")
public class Pay implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int payid;

	private float areaharvested;

	private float areaplanted;

	private int ecosystemid;

	private int locationid;

	private float production;

	private int seasonid;

	private int varietyid;

	@Column(length=45)
	private String year;

	private float yield;

	public Pay() {
	}

	public int getPayid() {
		return this.payid;
	}

	public void setPayid(int payid) {
		this.payid = payid;
	}

	public float getAreaharvested() {
		return this.areaharvested;
	}

	public void setAreaharvested(float areaharvested) {
		this.areaharvested = areaharvested;
	}

	public float getAreaplanted() {
		return this.areaplanted;
	}

	public void setAreaplanted(float areaplanted) {
		this.areaplanted = areaplanted;
	}

	public int getEcosystemid() {
		return this.ecosystemid;
	}

	public void setEcosystemid(int ecosystemid) {
		this.ecosystemid = ecosystemid;
	}

	public int getLocationid() {
		return this.locationid;
	}

	public void setLocationid(int locationid) {
		this.locationid = locationid;
	}

	public float getProduction() {
		return this.production;
	}

	public void setProduction(float production) {
		this.production = production;
	}

	public int getSeasonid() {
		return this.seasonid;
	}

	public void setSeasonid(int seasonid) {
		this.seasonid = seasonid;
	}

	public int getVarietyid() {
		return this.varietyid;
	}

	public void setVarietyid(int varietyid) {
		this.varietyid = varietyid;
	}

	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public float getYield() {
		return this.yield;
	}

	public void setYield(float yield) {
		this.yield = yield;
	}

}
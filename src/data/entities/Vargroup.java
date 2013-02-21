package data.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the vargroup database table.
 * 
 */
@Entity
@Table(name="vargroup")
public class Vargroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int vargroupid;

	@Column(length=45)
	private String vgroupabbr;

	@Column(length=45)
	private String vgroupdescription;

	@Column(length=45)
	private String vgroupname;

	public Vargroup() {
	}

	public int getVargroupid() {
		return this.vargroupid;
	}

	public void setVargroupid(int vargroupid) {
		this.vargroupid = vargroupid;
	}

	public String getVgroupabbr() {
		return this.vgroupabbr;
	}

	public void setVgroupabbr(String vgroupabbr) {
		this.vgroupabbr = vgroupabbr;
	}

	public String getVgroupdescription() {
		return this.vgroupdescription;
	}

	public void setVgroupdescription(String vgroupdescription) {
		this.vgroupdescription = vgroupdescription;
	}

	public String getVgroupname() {
		return this.vgroupname;
	}

	public void setVgroupname(String vgroupname) {
		this.vgroupname = vgroupname;
	}

}
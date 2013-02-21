package data.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the variables database table.
 * 
 */
@Entity
@Table(name="variables")
public class Variable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int variableid;

	@Column(length=45)
	private String varabbr;

	@Column(length=75)
	private String vardescription;

	private int vargroupid;

	private int varhierarchy;

	@Column(length=45)
	private String varname;

	@Column(length=45)
	private String varremarks;

	@Column(length=45)
	private String varunit;

	@Column(length=45)
	private String varvalue;

	public Variable() {
	}

	public int getVariableid() {
		return this.variableid;
	}

	public void setVariableid(int variableid) {
		this.variableid = variableid;
	}

	public String getVarabbr() {
		return this.varabbr;
	}

	public void setVarabbr(String varabbr) {
		this.varabbr = varabbr;
	}

	public String getVardescription() {
		return this.vardescription;
	}

	public void setVardescription(String vardescription) {
		this.vardescription = vardescription;
	}

	public int getVargroupid() {
		return this.vargroupid;
	}

	public void setVargroupid(int vargroupid) {
		this.vargroupid = vargroupid;
	}

	public int getVarhierarchy() {
		return this.varhierarchy;
	}

	public void setVarhierarchy(int varhierarchy) {
		this.varhierarchy = varhierarchy;
	}

	public String getVarname() {
		return this.varname;
	}

	public void setVarname(String varname) {
		this.varname = varname;
	}

	public String getVarremarks() {
		return this.varremarks;
	}

	public void setVarremarks(String varremarks) {
		this.varremarks = varremarks;
	}

	public String getVarunit() {
		return this.varunit;
	}

	public void setVarunit(String varunit) {
		this.varunit = varunit;
	}

	public String getVarvalue() {
		return this.varvalue;
	}

	public void setVarvalue(String varvalue) {
		this.varvalue = varvalue;
	}

}
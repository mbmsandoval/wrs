package data.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the varvalues database table.
 * 
 */
@Entity
@Table(name="varvalues")
public class Varvalue implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int varvaluesid;

	@Column(length=45)
	private String abbr;

	@Column(length=45)
	private String codeC;

	private int codeN;

	@Column(length=100)
	private String description;

	@Column(length=45)
	private String name;

	private int varid;

	public Varvalue() {
	}

	public int getVarvaluesid() {
		return this.varvaluesid;
	}

	public void setVarvaluesid(int varvaluesid) {
		this.varvaluesid = varvaluesid;
	}

	public String getAbbr() {
		return this.abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	public String getCodeC() {
		return this.codeC;
	}

	public void setCodeC(String codeC) {
		this.codeC = codeC;
	}

	public int getCodeN() {
		return this.codeN;
	}

	public void setCodeN(int codeN) {
		this.codeN = codeN;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getVarid() {
		return this.varid;
	}

	public void setVarid(int varid) {
		this.varid = varid;
	}

}
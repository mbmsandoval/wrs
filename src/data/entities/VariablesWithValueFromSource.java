package data.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the variables_with_value_from_source database table.
 * 
 */
@Entity
@Table(name="variables_with_value_from_source")
public class VariablesWithValueFromSource implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int id;

	private int sourceid;

	@Column(length=10)
	private String srccode;

	@Column(length=45)
	private String unit;

	@Column(length=45)
	private String varabbr;

	private int variableid;

	public VariablesWithValueFromSource() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSourceid() {
		return this.sourceid;
	}

	public void setSourceid(int sourceid) {
		this.sourceid = sourceid;
	}

	public String getSrccode() {
		return this.srccode;
	}

	public void setSrccode(String srccode) {
		this.srccode = srccode;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getVarabbr() {
		return this.varabbr;
	}

	public void setVarabbr(String varabbr) {
		this.varabbr = varabbr;
	}

	public int getVariableid() {
		return this.variableid;
	}

	public void setVariableid(int variableid) {
		this.variableid = variableid;
	}

}
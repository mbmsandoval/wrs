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
	private int id;

	private int sourceid;

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

	public int getVariableid() {
		return this.variableid;
	}

	public void setVariableid(int variableid) {
		this.variableid = variableid;
	}

}
package data.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the data_reg database table.
 * 
 */
@Entity
@Table(name="data_reg")
public class DataReg implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int dataid;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdon;

	@Column(length=45)
	private String group;

	private int regionid;

	private int source;

	@Column(length=10)
	private String srccode;

	@Column(precision=10, scale=2)
	private BigDecimal value;

	@Column(length=45)
	private String varabbr;

	private int vargroupid;

	private int variableid;

	private int year;

	public DataReg() {
	}

	public int getDataid() {
		return this.dataid;
	}

	public void setDataid(int dataid) {
		this.dataid = dataid;
	}

	public Date getCreatedon() {
		return this.createdon;
	}

	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}

	public String getGroup() {
		return this.group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public int getRegionid() {
		return this.regionid;
	}

	public void setRegionid(int regionid) {
		this.regionid = regionid;
	}

	public int getSource() {
		return this.source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public String getSrccode() {
		return this.srccode;
	}

	public void setSrccode(String srccode) {
		this.srccode = srccode;
	}

	public BigDecimal getValue() {
		return this.value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public String getVarabbr() {
		return this.varabbr;
	}

	public void setVarabbr(String varabbr) {
		this.varabbr = varabbr;
	}

	public int getVargroupid() {
		return this.vargroupid;
	}

	public void setVargroupid(int vargroupid) {
		this.vargroupid = vargroupid;
	}

	public int getVariableid() {
		return this.variableid;
	}

	public void setVariableid(int variableid) {
		this.variableid = variableid;
	}

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
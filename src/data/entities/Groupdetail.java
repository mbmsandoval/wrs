package data.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the groupdetails database table.
 * 
 */
@Entity
@Table(name="groupdetails")
public class Groupdetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int groupid;

	private int memberid;

	public Groupdetail() {
	}

	public int getGroupid() {
		return this.groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	public int getMemberid() {
		return this.memberid;
	}

	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}

}
package data.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the group database table.
 * 
 */
@Entity
@Table(name="group")
public class Group implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int groupid;

	@Column(length=45)
	private String groupdescription;

	private int grouphierarchy;

	@Column(length=45)
	private String groupname;

	public Group() {
	}

	public int getGroupid() {
		return this.groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	public String getGroupdescription() {
		return this.groupdescription;
	}

	public void setGroupdescription(String groupdescription) {
		this.groupdescription = groupdescription;
	}

	public int getGrouphierarchy() {
		return this.grouphierarchy;
	}

	public void setGrouphierarchy(int grouphierarchy) {
		this.grouphierarchy = grouphierarchy;
	}

	public String getGroupname() {
		return this.groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

}
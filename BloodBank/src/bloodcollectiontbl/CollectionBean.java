package bloodcollectiontbl;

public class CollectionBean {
	public String mob;
	public String bgroup;
	public String dod;
	public String getMob() {
		return mob;
	}
	public void setMob(String mob) {
		this.mob = mob;
	}
	public String getBgroup() {
		return bgroup;
	}
	public void setBgroup(String bgroup) {
		this.bgroup = bgroup;
	}
	public String getDod() {
		return dod;
	}
	public void setDod(String dod) {
		this.dod = dod;
	}
	public CollectionBean() {}
	public CollectionBean(String mob, String bgroup, String dod) {
		super();
		this.mob = mob;
		this.bgroup = bgroup;
		this.dod = dod;
	}
}

package bloodissuedtbl;

public class IssuedBean {
	public String name;
	public String mob;
	public String hospital;
	public String bgroup;
	public int units;
	public String reason;
	public String date;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMob() {
		return mob;
	}
	public void setMob(String mob) {
		this.mob = mob;
	}
	public String getHospital() {
		return hospital;
	}
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	public String getBgroup() {
		return bgroup;
	}
	public void setBgroup(String bgroup) {
		this.bgroup = bgroup;
	}
	public int getUnits() {
		return units;
	}
	public void setUnits(int units) {
		this.units = units;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public IssuedBean() {}
	public IssuedBean(String name, String mob, String hospital, String bgroup, int units, String reason, String date) {
		super();
		this.name = name;
		this.mob = mob;
		this.hospital = hospital;
		this.bgroup = bgroup;
		this.units = units;
		this.reason = reason;
		this.date = date;
	}
}

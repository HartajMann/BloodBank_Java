package infotable;

public class DonorBean {
	public String mob;
	public String picpath;
	public String name;
	public String gender;
	public String address;
	public String city;
	public String bgroup;
	public int age;
	public String disease;
	public String date;
	
	public String getMob() {
		return mob;
	}
	public void setMob(String mob) {
		this.mob = mob;
	}
	public String getPicpath() {
		return picpath;
	}
	public void setPicpath(String picpath) {
		this.picpath = picpath;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getBgroup() {
		return bgroup;
	}
	public void setBgroup(String bgroup) {
		this.bgroup = bgroup;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getDisease() {
		return disease;
	}
	public void setDisease(String disease) {
		this.disease = disease;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public DonorBean() {}
	public DonorBean(String mob, String picpath, String name, String gender, String address, String city, String bgroup,
			int age, String disease, String date) {
		super();
		this.mob = mob;
		this.picpath = picpath;
		this.name = name;
		this.gender = gender;
		this.address = address;
		this.city = city;
		this.bgroup = bgroup;
		this.age = age;
		this.disease = disease;
		this.date = date;
	}	
}

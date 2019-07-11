package libraryApp;

public class Member {
	
	//Data Members
	private String name;
	private String fatherName;
	private int cms;
	private String gender;
	private int semster;
	
	public Member(String name, String fatherName, String gender, int cms, int semster) {
		this.name = name;
		this.fatherName = fatherName;
		this.gender = gender;
		this.cms = cms;
		this.semster = semster;
	}
	
	//getter and setter Methods
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	
	public void setFatherName(String name) {
		this.fatherName = name;
	}
	public String getFatherName() {
		return this.fatherName;
	}
	
	public void setCMS(int cms) {
		this.cms = cms;
	}
	
	public int getCMS() {
		return this.cms;
	}
	public void setSemster(int num) {
		this.semster = num;
	}
	
	public int getSemster() {
		return this.semster;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getGender() {
		return this.gender;
	}
}

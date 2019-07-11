package libraryApp;

import java.util.HashMap;

public class Books {

	private String code;
	private String title;
	private String author;
	private int qty;
	private HashMap<Member,int[]> studentsEnrolled = new HashMap<>();
	
	public Books(String code, String title, String author, int qty){
		this.code = code;
		this.title = title;
		this.author = author;
		this.qty = qty;
	}
	
	public void enrollStudent(Member student, int[] marks) {
		studentsEnrolled.put(student, marks);
	}
	
	public void unEnrollStudent(Member student) {
		studentsEnrolled.remove(student);
	}
	public Member[] getEnrolledStudents() {
		
		Member[] arr = new Member[studentsEnrolled.size()];
		
		int i = 0;
		for(Member student : studentsEnrolled.keySet()) {
			arr[i] = student;
			i++;
		}
		return arr;
	}
	public HashMap<Member,int[]> getEnrolledStudentsAsMap(){
		return studentsEnrolled;
	}
	public int[] getStudentMarks(Member student) {
		return studentsEnrolled.get(student);
	}
	
	
	public void setCode(String code) {
		this.code = code;
	}
	public String getCode() {
		return code;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	
	public void setAuthor(String a) {
		this.author = a;
	}
	public String getauthor() {
		return author;
	}
	
	public void setQuantity(int num) {
		this.qty = num;
	}
	public int getQuantity() {
		return qty;
	}
}

package server.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StudDto {

	// 학생 정보를 포함하고 있는 class이다
	int id ,grade;
	String nnn ,tel , email;
	Date bbb ,regdate;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	
	
	
	public String getBbbStr() {
		return sdf.format(bbb);
	}



	public void setBbbStr(String bbbStr) {
		try {
			bbb = sdf.parse(bbbStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getGrade() {
		return grade;
	}



	public void setGrade(int grade) {
		this.grade = grade;
	}



	public String getNnn() {
		return nnn;
	}



	public void setNnn(String nnn) {
		this.nnn = nnn;
	}



	public String getTel() {
		return tel;
	}



	public void setTel(String tel) {
		this.tel = tel;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public Date getBbb() {
		return bbb;
	}



	public void setBbb(Date bbb) {
		this.bbb = bbb;
	}



	public Date getRegdate() {
		return regdate;
	}



	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}



	@Override
	public String toString() {
		return "StudDto [id=" + id + ", grade=" + grade + ", nnn=" + nnn + ", tel=" + tel + ", email=" + email
				+ ", bbb=" + bbb + ", regdate=" + regdate + "]";
	} 
	
	
}

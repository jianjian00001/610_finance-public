package cn.zhku.jsj144.zk.financialManage.pojo;

//import java.io.Serializable;

public class User{//  implements Serializableû�����л��ӿڣ�����tomcat�ᵼ��session��ʧ
	private int uid;//�û����
	private String username;//�û�����
	private String password;//����
	private String sex;//�Ա�
	private String email;//����
	private String phone;//�ֻ�
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}

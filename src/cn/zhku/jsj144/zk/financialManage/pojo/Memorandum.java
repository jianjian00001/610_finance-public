package cn.zhku.jsj144.zk.financialManage.pojo;

public class Memorandum {//����¼
	private int mid;//id
	private String recordTime;//��¼ʱ��
	private String thingPath;//�ļ�·��
	private String topFont;//�ļ�����ǰ83����
	private int user_id;//�����û�
	
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}
	public String getThingPath() {
		return thingPath;
	}
	public void setThingPath(String thingPath) {
		this.thingPath = thingPath;
	}
	public String getTopFont() {
		return topFont;
	}
	public void setTopFont(String topFont) {
		this.topFont = topFont;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
}

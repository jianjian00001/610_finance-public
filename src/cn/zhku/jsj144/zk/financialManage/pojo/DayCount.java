package cn.zhku.jsj144.zk.financialManage.pojo;


/*
 *��;���ڻ���ͼ��ʱ��Ҫ����1��ÿ������룬֧�����
 *				       ��2��һ����[28����29����30����31��]�ĸ������͵����룬֧���� 
 */
public class DayCount {
	private int dayName;//��
	private int moneyName;//���
	private String categoryName;//������
	public int getDayName() {
		return dayName;
	}
	public void setDayName(int dayName) {
		this.dayName = dayName;
	}
	public int getMoneyName() {
		return moneyName;
	}
	public void setMoneyName(int moneyName) {
		this.moneyName = moneyName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
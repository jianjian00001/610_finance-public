package cn.zhku.jsj144.zk.financialManage.pojo;

/*
 *��;���ڻ���ͼ��ʱ��Ҫ����1��ÿ���µ����룬֧����2��һ��[12����]�ĸ������͵����룬֧���� 
 */
public class MonthCount {
	private int monthName;//�·�
	private int moneyName;//���
	
	//���Ĵ��⣬mysql��ѯ֮���Ͳ�ƥ��
	//Invalid value for getInt()��һ��java.sql.SQLException�쳣
	//��ѯSQL�ῴ����ѯ���ֶ���vachar��  ,3��������ʹ��mybatisʱ��д�ķ���ֵ������int�ͣ�����ת��������
	//private int categoryName;//������  --����
	
	private String categoryName;//������
	public int getMonthName() {
		return monthName;
	}
	public void setMonthName(int monthName) {
		this.monthName = monthName;
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

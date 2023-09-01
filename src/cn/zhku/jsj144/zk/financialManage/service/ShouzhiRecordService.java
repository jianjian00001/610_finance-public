package cn.zhku.jsj144.zk.financialManage.service;

import java.util.List;
import java.util.Map;

import cn.zhku.jsj144.zk.financialManage.pojo.DayCount;
import cn.zhku.jsj144.zk.financialManage.pojo.MonthCount;
import cn.zhku.jsj144.zk.financialManage.pojo.PageBean;
import cn.zhku.jsj144.zk.financialManage.pojo.ShouzhiRecord;
import cn.zhku.jsj144.zk.financialManage.pojo.User;

public interface ShouzhiRecordService {

	//��ѯ�˵���ϸ
	PageBean<ShouzhiRecord> findShouzhiRecord(int currentPage, User user, ShouzhiRecord shouzhiRecord);

	//����id��ѯ��֧��¼��Ϣ
	ShouzhiRecord findShouzhiRecordById(Map<String, Integer> map);

	//�޸��û���֧��Ϣ
	void editShouzhiRecord(ShouzhiRecord shouzhiRecord);

	//ɾ���û���֧��Ϣ��һ����
	void deleteOneShouzhiRecord(int id);

	//����ɾ���û���֧��Ϣ��������������
	void deleteBatchShouzhiRecord(String id);

	//�����֧��¼��Ϣ������ ����  ֧����  
	void addShouzhiRecord(ShouzhiRecord shouzhiRecord);
	/*------------------------���ͳ��---------------------------------------------*/
	//ÿ���µ����룬֧��ͳ��      ÿ���µ����� -->�� �����
	List<MonthCount> findYearInMonthCountIncome(Map<String, String> paramMap);
	//ÿ���µ����룬֧��ͳ��     ÿ���µ�֧�� -->�� �����
	List<MonthCount> findYearInMonthCountSpend(Map<String, String> paramMap);

	//һ��ģ������������ͳ��     
	List<MonthCount> findYearInCategoryCountIncome(Map<String, String> paramMap);

	//һ��ģ�����֧�����ͳ��     
	List<MonthCount> findYearInCategoryCountSpend(Map<String, String> paramMap);
	
	/*------------------------�·�ͳ��---------------------------------------------*/
	//ÿ�������ͳ��       ->�죺���
	List<DayCount> findMonthInDayCountIncome(Map<String, String> paramMap);

	//ÿ���֧��ͳ��    -->�죺���
	List<DayCount> findMonthInDayCountSpend(Map<String, String> paramMap);

	//һ���µģ������������ͳ��    
	List<DayCount> findMonthInCategoryCountIncome(Map<String, String> paramMap);

	//һ���£�����֧�����ͳ��
	List<DayCount> findMonthInCategoryCountSpend(Map<String, String> paramMap);

	/*------------------------ʱ��ͳ��---------------------------------------------*/
	//ĳ��ʱ��εĲ�ͬ���͵�����ͳ��
	List<DayCount> findDayInTimeCountIncomes(Map<String, String> paramMap);
	//ĳ��ʱ��εĲ�ͬ���͵�֧��ͳ��
	List<DayCount> findDayInTimeCountSpends(Map<String, String> paramMap);

	//ͨ����֧���id ��ѯ������ ���� ֧��
	String findParentCategoryById(int szcid);

}

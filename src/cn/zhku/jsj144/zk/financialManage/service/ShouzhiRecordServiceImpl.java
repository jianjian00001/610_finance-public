package cn.zhku.jsj144.zk.financialManage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.zhku.jsj144.zk.financialManage.mapper.ShouzhiRecordMapper;
import cn.zhku.jsj144.zk.financialManage.pojo.DayCount;
import cn.zhku.jsj144.zk.financialManage.pojo.MonthCount;
import cn.zhku.jsj144.zk.financialManage.pojo.PageBean;
import cn.zhku.jsj144.zk.financialManage.pojo.ShouzhiRecord;
import cn.zhku.jsj144.zk.financialManage.pojo.ShouzhiRecordQueryVo;
import cn.zhku.jsj144.zk.financialManage.pojo.User;

@Transactional
@Service
public class ShouzhiRecordServiceImpl implements ShouzhiRecordService {

	@Autowired
	private ShouzhiRecordMapper shouzhiRecordMapper;
	
	// ��ѯ�˵���ϸ
	@Override
	public PageBean<ShouzhiRecord> findShouzhiRecord(int currentPage, User user, ShouzhiRecord shouzhiRecord) {
		
		// ��ʼλ��
		int pageRecord = 8;// ÿҳ��¼��
//		int pageRecord = 6;// ÿҳ��¼��
		int startPosition=0;
		//ע�⣺��Ϊ��ǰҳ   currentPage  ��ʵ�ʵĵ�ǰҳ  ��1
		//���磺currentPage=0ʱ����ǰҳ�ǵ�1ҳ     ��    currentPage=1ʱ����ǰҳ�ǵ�2ҳ  
		if(currentPage!=0){
			//ʧ��
			//startPosition = (currentPage - 1) * pageRecord;// ��ʼλ��
			startPosition = currentPage * pageRecord;// ��ʼλ��
		}
		// �ܼ�¼��
		int allRecord = 0;
		//ͨ����ѯ������ܼ�¼����1����ѯ
		Map<String,Object> map=new HashMap<String,Object>();
		
		map.put("user", user);
//		System.out.println("map1:"+user.getUid());
		if(shouzhiRecord!=null){
			if(shouzhiRecord.getSzr_date()!=null&&shouzhiRecord.getSzr_date()!=""){
				map.put("szr_date", shouzhiRecord.getSzr_date());
//				System.out.println("map2:"+map.get("szr_date"));
			}
			if(shouzhiRecord.getSzr_comment()!=null&&shouzhiRecord.getSzr_comment()!=""){
				map.put("szr_comment", shouzhiRecord.getSzr_comment());
//				System.out.println("map3:"+map.get("szr_comment"));
			}
		}
//		System.out.println("map:----"+map.size());
		allRecord=shouzhiRecordMapper.findShouzhiRecordCount(map);
//		System.out.println("�ܼ�¼����------"+allRecord);
		// ��ҳ��
		int allPage = allRecord / pageRecord;
		if (allRecord % pageRecord != 0)
			allPage = allPage + 1;
		
		//��ǰҳ��¼��     ��2����ѯ
		
		//mysql�ķ�ҳ��ѯ   SELECT * FROM table LIMIT 5,10;  ������¼�� 6-15 
		
	/*	Map<String,Object> map=new HashMap<String,Object>();
		map.put("uid", user.getUid()+"");//uid
		map.put("startPosition", startPosition+"");//startPosition
		map.put("pageRecord", pageRecord+"");//pageRecord
		//�����ѯ����
		if(shouzhiRecord!=null){
			if(shouzhiRecord.getSzr_date()!=null){
				map.put("date_condition", shouzhiRecord.getSzr_date());
			}
			if(shouzhiRecord.getSzr_comment()!=null){
				map.put("comment_condition", shouzhiRecord.getSzr_comment());
			}
		}*/
		
		ShouzhiRecordQueryVo queryVo=new ShouzhiRecordQueryVo();
		queryVo.setUid(user.getUid());
		queryVo.setStartPosition(startPosition);
		queryVo.setPageRecord(pageRecord);
		//�����ѯ����
		if(shouzhiRecord!=null){
			if(shouzhiRecord.getSzr_date()!=null&&shouzhiRecord.getSzr_date()!=""){
				queryVo.setSzr_date(shouzhiRecord.getSzr_date());
			}
			if(shouzhiRecord.getSzr_comment()!=null&&shouzhiRecord.getSzr_comment()!=""){
				queryVo.setSzr_comment(shouzhiRecord.getSzr_comment());
			}
		}
		
		//���ԣ���������
		/*System.out.println("uid:--------"+user.getUid());
		System.out.println("startPosition:--------"+startPosition);
		System.out.println("pageRecord:--------"+pageRecord);*/
		
		//��֧��ϸ
		//List<ShouzhiRecord> pageList=shouzhiRecordMapper.findCurrenPageRecordList(map);
		List<ShouzhiRecord> pageList=shouzhiRecordMapper.findCurrenPageRecordList(queryVo);
		/*����
	 	if(pageList!=null){
			//������
			for (ShouzhiRecord shouzhiRecord : pageList) {
				System.out.println("��¼id  id:"+shouzhiRecord.getShouzhiCategory().getSzcid());
			}
		}*/
		
		//��ҳ��ѯ���
		PageBean<ShouzhiRecord> pageBean = new PageBean<ShouzhiRecord>();
		pageBean.setAllPage(allPage);
		pageBean.setAllRecord(allRecord);
		pageBean.setCurrentPage(currentPage);
		pageBean.setPageList(pageList);
		pageBean.setPageRecord(pageRecord);
		pageBean.setStartPosition(startPosition);
		
	/*	System.out.println("��ҳ�������ݣ�allPage--"+allPage);
		System.out.println("��ҳ�������ݣ�allRecord--"+allRecord);
		System.out.println("��ҳ�������ݣ�currentPage--"+currentPage);
		System.out.println("��ҳ�������ݣ�pageRecord--"+pageRecord);
		System.out.println("��ҳ�������ݣ�startPosition--"+startPosition);*/
		return pageBean;
	}

	//����id��ѯ��֧��¼��Ϣ
	@Override
	public ShouzhiRecord findShouzhiRecordById(Map<String, Integer> map) {
		ShouzhiRecord shouzhiRecord=shouzhiRecordMapper.findShouzhiRecordById(map);
		return shouzhiRecord;
	}

	//�޸��û���֧��Ϣ
	@Override
	public void editShouzhiRecord(ShouzhiRecord shouzhiRecord) {
		shouzhiRecordMapper.editShouzhiRecord(shouzhiRecord);
	}

	//ɾ���û���֧��Ϣ��һ����
	@Override
	public void deleteOneShouzhiRecord(int id) {
		shouzhiRecordMapper.deleteOneShouzhiRecord(id);		
	}

	//����ɾ���û���֧��Ϣ��������������
	@Override
	public void deleteBatchShouzhiRecord(String id) {
		String[] strArray = id.split(",");
		//System.out.println("���ԣ�"+strArray[0]+":"+strArray[1]);
		//����ɾ����¼
		for (String s : strArray) {
			shouzhiRecordMapper.deleteOneShouzhiRecord(Integer.parseInt(s));
		}
	}

	//�����֧��¼��Ϣ������ ����  ֧����  
	@Override
	public void addShouzhiRecord(ShouzhiRecord shouzhiRecord) {
		shouzhiRecordMapper.addShouzhiRecord(shouzhiRecord);
	}

	//ÿ���µ����룬֧��ͳ��      ÿ���µ����� -->�� �����
	@Override
	public List<MonthCount> findYearInMonthCountIncome(Map<String, String> paramMap) {
		return shouzhiRecordMapper.findYearInMonthCountIncome(paramMap);
	}

	//ÿ���µ����룬֧��ͳ��     ÿ���µ�֧�� -->�� �����
	@Override
	public List<MonthCount> findYearInMonthCountSpend(Map<String, String> paramMap) {
		return shouzhiRecordMapper.findYearInMonthCountSpend(paramMap);
	}

	//һ��ģ������������ͳ��     
	@Override
	public List<MonthCount> findYearInCategoryCountIncome(
			Map<String, String> paramMap) {
		return shouzhiRecordMapper.findYearInCategoryCountIncome(paramMap);
	}

	//һ��ģ�����֧�����ͳ��     
	@Override
	public List<MonthCount> findYearInCategoryCountSpend(
			Map<String, String> paramMap) {
		return shouzhiRecordMapper.findYearInCategoryCountSpend(paramMap);
	}

	//ÿ�������ͳ��       ->�죺���
	@Override
	public List<DayCount> findMonthInDayCountIncome(
			Map<String, String> paramMap) {
		return shouzhiRecordMapper.findMonthInDayCountIncome(paramMap);
	}

	//ÿ���֧��ͳ��    -->�죺���
	@Override
	public List<DayCount> findMonthInDayCountSpend(
			Map<String, String> paramMap) {
		return shouzhiRecordMapper.findMonthInDayCountSpend(paramMap);
	}

	//һ���µģ������������ͳ��    
	@Override
	public List<DayCount> findMonthInCategoryCountIncome(
			Map<String, String> paramMap) {
		return shouzhiRecordMapper.findMonthInCategoryCountIncome(paramMap);
	}

	//һ���£�����֧�����ͳ��
	@Override
	public List<DayCount> findMonthInCategoryCountSpend(
			Map<String, String> paramMap) {
		return shouzhiRecordMapper.findMonthInCategoryCountSpend(paramMap);
	}

	//ĳ��ʱ��εĲ�ͬ���͵�����ͳ��
	@Override
	public List<DayCount> findDayInTimeCountIncomes(Map<String, String> paramMap) {
		return shouzhiRecordMapper.findDayInTimeCountIncomes(paramMap);
	}

	//ĳ��ʱ��εĲ�ͬ���͵�֧��ͳ��
	@Override
	public List<DayCount> findDayInTimeCountSpends(
			Map<String, String> paramMap) {
		return shouzhiRecordMapper.findDayInTimeCountSpends(paramMap);
	}

	//ͨ����֧���id ��ѯ������ ���� ֧��
	@Override
	public String findParentCategoryById(int szcid) {
		// TODO Auto-generated method stub
		return shouzhiRecordMapper.findParentCategoryById(szcid);
	}
}

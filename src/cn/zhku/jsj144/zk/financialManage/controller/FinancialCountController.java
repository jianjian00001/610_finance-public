package cn.zhku.jsj144.zk.financialManage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.zhku.jsj144.zk.financialManage.pojo.DayCount;
import cn.zhku.jsj144.zk.financialManage.pojo.MonthCount;
import cn.zhku.jsj144.zk.financialManage.pojo.User;
import cn.zhku.jsj144.zk.financialManage.service.ShouzhiRecordService;

@Controller
public class FinancialCountController {//��֧ͳ��
	
	@Autowired
	private ShouzhiRecordService shouzhiRecordService;
	
	@RequestMapping("/toFinancialCount.action")
	public String toFinancialCount(){//ȥ��֧ͳ��ҳ��
		return "/jsp/financialCount.jsp";
	}
	
	//ÿ���µ����룬֧��ͳ��
	@RequestMapping("/shouzhiRecord/yearInMonthCount.action")
	@ResponseBody    //json��ʽ����
	public String yearInMonthCount(String year,String uid){
		System.out.println("------------ÿ���µ����룬֧��ͳ��------------");
		//ͨ��year���û�id���в�ѯ�����û�����һ�꣬ÿ���µ���֧���
		Map<String,String> paramMap=new HashMap<String,String>();
		paramMap.put("year", year);
//		User user = (User) request.getSession().getAttribute("user");
//		paramMap.put("user_id",user.getUid()+"");
		paramMap.put("user_id",uid);
		
		//ÿ���µ����룬֧��ͳ��      ÿ���µ����� -->�� �����
		List<MonthCount> incomes=shouzhiRecordService.findYearInMonthCountIncome(paramMap);
		//ÿ���µ����룬֧��ͳ��     ÿ���µ�֧�� -->�� �����
		List<MonthCount> spends=shouzhiRecordService.findYearInMonthCountSpend(paramMap);
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("incomes", incomes);
		map.put("spends", spends);
		//תΪjson����
		String jsonString = JSON.toJSONString(map);
		System.out.println("ÿ���µ���֧ͳ�ƽ����jsonString:\n"+jsonString);
		
		return jsonString;//������ҳ
	}

	//һ�꣨12���£��Ĳ�ͬ���͵�����ͳ��
	@RequestMapping("/shouzhiRecord/yearInCategoryCount.action")
	@ResponseBody    //json��ʽ����
	public String yearInCategoryCount(String year,String uid){
		//ͨ��year���û�id���в�ѯ�����û�����һ�꣬ÿ���µ���֧���
		Map<String,String> paramMap=new HashMap<String,String>();
		paramMap.put("year", year);
//		User user = (User) request.getSession().getAttribute("user");
//		paramMap.put("user_id",user.getUid()+"");
		paramMap.put("user_id",uid);
		
		//һ��ģ������������ͳ��     
		List<MonthCount> incomes=shouzhiRecordService.findYearInCategoryCountIncome(paramMap);
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("incomes", incomes);
		//תΪjson����
		String jsonString = JSON.toJSONString(map);
		System.out.println("�������������jsonString:\n"+jsonString);
		
		return jsonString;//������ҳ
	}
	
	
	//һ�꣨12���£��Ĳ�ͬ���͵�֧��ͳ��
	@RequestMapping("/shouzhiRecord/yearInCategoryCountSpends.action")
	@ResponseBody    //json��ʽ����
	public String yearInCategoryCountSpends(String year,String uid){
		//ͨ��year���û�id���в�ѯ�����û�����һ�꣬ÿ���µ���֧���
		Map<String,String> paramMap=new HashMap<String,String>();
		paramMap.put("year", year);
		paramMap.put("user_id",uid);
		
		//һ��ģ�����֧�����ͳ��     
		List<MonthCount> spends=shouzhiRecordService.findYearInCategoryCountSpend(paramMap);
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("spends", spends);
		//תΪjson����
		String jsonString = JSON.toJSONString(map);
		System.out.println("�������������jsonString:\n"+jsonString);
		return jsonString;//������ҳ
	}
	
	/*----------------------------------------------��             ͳ               ��----------------------------------------------------*/
	//ÿ������룬֧��ͳ�ơ���ͳ�ơ�
	@RequestMapping("/shouzhiRecord/MonthInDayCount.action")
	@ResponseBody    //json��ʽ����
	public String MonthInDayCount(String currentTime,String uid){

		//����currentTime
		String[] arr = currentTime.split("-");
		String year=arr[0];
		String month=arr[1];
		//ͨ��year���û�id���в�ѯ�����û�����һ�꣬ÿ���µ���֧���
		Map<String,String> paramMap=new HashMap<String,String>();
		paramMap.put("year", year);
		paramMap.put("month", month);
		paramMap.put("user_id",uid);
		//ÿ�������ͳ��       ->�죺���
		List<DayCount> incomes=shouzhiRecordService.findMonthInDayCountIncome(paramMap);
		//ÿ���֧��ͳ��    -->�죺���
		List<DayCount> spends=shouzhiRecordService.findMonthInDayCountSpend(paramMap);
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("incomes", incomes);
		map.put("spends", spends);
		//תΪjson����
		String jsonString = JSON.toJSONString(map);
		System.out.println("����ÿ�����֧ͳ�ƽ����jsonString:\n"+jsonString);
		
		return jsonString;//������ҳ
	}
	
		//һ���£�n�죩�Ĳ�ͬ���͵�����ͳ��
		@RequestMapping("/shouzhiRecord/monthInCategoryCountIncome.action")
		@ResponseBody    //json��ʽ����
		public String monthInCategoryCountIncome(String currentTime,String uid){
			//����currentTime
			String[] arr = currentTime.split("-");
			String year=arr[0];
			String month=arr[1];
			Map<String,String> paramMap=new HashMap<String,String>();
			paramMap.put("year", year);
			paramMap.put("month", month);
			paramMap.put("user_id",uid);
			//һ���µģ������������ͳ��     
			List<DayCount> incomes=shouzhiRecordService.findMonthInCategoryCountIncome(paramMap);
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("incomes", incomes);
			//תΪjson����
			String jsonString = JSON.toJSONString(map);
			System.out.println("ÿ������������������jsonString:\n"+jsonString);
			return jsonString;//������ҳ
		}	
		
		/*  ����Ԥ��ģ����Ҫʹ��*/
		//һ���£�n�죩�Ĳ�ͬ���͵����룬�Լ�֧��ͳ��
		@RequestMapping("/shouzhiRecord/monthInCategoryCountSpend.action")
		@ResponseBody    //json��ʽ����
		public String monthInCategoryCountSpend(String currentTime,String uid){
			//����currentTime
			String[] arr = currentTime.split("-");
			String year=arr[0];
			String month=arr[1];
			Map<String,String> paramMap=new HashMap<String,String>();
			paramMap.put("year", year);
			paramMap.put("month", month);
			paramMap.put("user_id",uid);
			//һ���£�����֧�����ͳ��     
			List<DayCount> spends=shouzhiRecordService.findMonthInCategoryCountSpend(paramMap);
			//һ���µģ������������ͳ��     
			List<DayCount> incomes=shouzhiRecordService.findMonthInCategoryCountIncome(paramMap);
			
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("incomes", incomes);
			map.put("spends", spends);
			//תΪjson����
			String jsonString = JSON.toJSONString(map);
			System.out.println("ÿ��������룬�Լ�֧�����������jsonString:\n"+jsonString);
			return jsonString;//������ҳ
		}	
		
		//һ���£�n�죩�Ĳ�ͬ���͵����룬֧��ͳ��
		@RequestMapping("/shouzhiRecord/monthInCategoryCountAll.action")
		@ResponseBody    //json��ʽ����
		public String monthInCategoryCountAll(String currentTime,String uid){
			//����currentTime
			String[] arr = currentTime.split("-");
			String year=arr[0];
			String month=arr[1];
			Map<String,String> paramMap=new HashMap<String,String>();
			paramMap.put("year", year);
			paramMap.put("month", month);
			paramMap.put("user_id",uid);
			//һ���£�����֧�����ͳ��     
			List<DayCount> spends=shouzhiRecordService.findMonthInCategoryCountSpend(paramMap);
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("spends", spends);
			//תΪjson����
			String jsonString = JSON.toJSONString(map);
			System.out.println("ÿ�����֧�����������jsonString:\n"+jsonString);
			return jsonString;//������ҳ
		}	
		
		
		/*-------------------------ʱ        ��            ͳ         ��-------------------------------------*/
		//ĳ��ʱ��εĲ�ͬ���͵����룬֧��ͳ��
		@RequestMapping("/shouzhiRecord/dayInTimeCount.action")
		@ResponseBody    //json��ʽ����
		public String dayInTimeCount(String start,String end,String uid){
			Map<String,String> paramMap=new HashMap<String,String>();
			paramMap.put("start", start);
			paramMap.put("end", end);
			paramMap.put("user_id",uid);
			List<DayCount> incomes=shouzhiRecordService.findDayInTimeCountIncomes(paramMap);//����
			List<DayCount> spends=shouzhiRecordService.findDayInTimeCountSpends(paramMap);//֧��
			
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("incomes", incomes);
			map.put("spends", spends);
			
			//תΪjson����
			String jsonString = JSON.toJSONString(map);
			System.out.println("��ͬʱ��εģ�������֧���������jsonString:\n"+jsonString);
			return jsonString;//������ҳ
		}	
}

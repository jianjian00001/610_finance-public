package cn.zhku.jsj144.zk.financialManage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.zhku.jsj144.zk.financialManage.pojo.DayCount;
import cn.zhku.jsj144.zk.financialManage.pojo.MonthAnalysis;
import cn.zhku.jsj144.zk.financialManage.service.FinancialAnalysisService;
import cn.zhku.jsj144.zk.financialManage.service.ShouzhiRecordService;

@Controller
@RequestMapping("/financialAnalysis")
public class FinancialAnalysisController {

	@Autowired
	private FinancialAnalysisService financialAnalysisService;//ͳ�ƣ�������ǰ�µ���֧���
	
	@Autowired
	private ShouzhiRecordService shouzhiRecordService;//ͳ�ƣ�����  ��ǰ�£���ǰʱ���ڵĸ�������������ԭ�д��롿
	
	@RequestMapping("/toFinancialAnalysis.action")
	public String toFinancialAnalysis(){//ȥ��֧����ҳ��
		return "/jsp/financialAnalysis.jsp";
	}
	
	@RequestMapping("/monthAnalysis.action")
	@ResponseBody
	public String monthAnalysis(String currentTime,String lastTime,int uid){//�·���
		
		//��������
		Map<String,String> paramMap=new HashMap<String,String>();//�������
		paramMap.put("currentTime", currentTime);
		paramMap.put("uid", uid+"");
		int incomeRecordCount=financialAnalysisService.findMonthincomeRecordCount(paramMap);//�����¼
		int spendsRecordCount=financialAnalysisService.findMonthspendsRecordCount(paramMap);//֧����¼
		int incomeMoney=financialAnalysisService.findMonthincomeMoney(paramMap);//������
		int spendsMoney=financialAnalysisService.findMonthspendsMoney(paramMap);//֧�����
		
		
//		System.out.println("incomeRecordCount:"+incomeRecordCount);
//		System.out.println("spendsRecordCount:"+spendsRecordCount);
//		System.out.println("incomeMoney:"+incomeMoney);
//		System.out.println("spendsMoney:"+spendsMoney);
		
		int allMoney=incomeMoney+spendsMoney;//�ܽ��
//		System.out.println("allMoney:"+allMoney);
		
		
		MonthAnalysis ms=new MonthAnalysis();//��װ���·ݵ�����
		ms.setIncomeMoney(incomeMoney);
		ms.setIncomeRecordCount(incomeRecordCount);
		ms.setSpendsMoney(spendsMoney);
		ms.setSpendsRecordCount(spendsRecordCount);
		ms.setAllMoney(allMoney);
		
		//��������
		Map<String,String> paramMap2=new HashMap<String,String>();//�������
		paramMap2.put("currentTime", lastTime);
		paramMap2.put("uid", uid+"");
		int incomeRecordCount2=financialAnalysisService.findMonthincomeRecordCount(paramMap2);//�����¼
		int spendsRecordCount2=financialAnalysisService.findMonthspendsRecordCount(paramMap2);//֧����¼
		int incomeMoney2=financialAnalysisService.findMonthincomeMoney(paramMap2);//������
		int spendsMoney2=financialAnalysisService.findMonthspendsMoney(paramMap2);//֧�����
		
//		System.out.println("incomeRecordCount2:"+incomeRecordCount2);
//		System.out.println("spendsRecordCount2:"+spendsRecordCount2);
//		System.out.println("incomeMoney2:"+incomeMoney2);
//		System.out.println("spendsMoney2:"+spendsMoney2);
		
		int allMoney2=incomeMoney2+spendsMoney2;//�ܽ��
//		System.out.println("allMoney2:"+allMoney2);
		
		MonthAnalysis msLast=new MonthAnalysis();//��װ���·ݵ�����
		msLast.setIncomeMoney(incomeMoney2);
		msLast.setIncomeRecordCount(incomeRecordCount2);
		msLast.setSpendsMoney(spendsMoney2);
		msLast.setSpendsRecordCount(spendsRecordCount2);
		msLast.setAllMoney(allMoney2);
		
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("current", ms);//��������
		param.put("last", msLast);//��������
		
		String jsonString=JSON.toJSONString(param);
		System.out.println("json���ݣ�"+jsonString);
		return jsonString;
	}
	
	@RequestMapping("/monthCurrentDayAnalysis.action")
	@ResponseBody
	public String monthCurrentDayAnalysis(String currentTimeDay,String lastTimeDay,String uid){//�µ�ǰʱ���ڵķ���
		//�������Լ� ���
//		System.out.println("ִ�д��롣����������������������������������������");
		//��������
		int last = currentTimeDay.lastIndexOf("-");//xxxx-xx-xx ��-��-��
		String currentStart=currentTimeDay.substring(0, last)+"-01";//xxxx-xx-01
//		System.out.println("currentStart:-------------------"+currentStart);
		//��ǰ�µ���ʼʱ��
		Map<String,String> currentParamMap=new HashMap<String,String>();
		currentParamMap.put("start", currentStart);
		currentParamMap.put("end", currentTimeDay);
		currentParamMap.put("user_id",uid);
		List<DayCount> incomes=shouzhiRecordService.findDayInTimeCountIncomes(currentParamMap);//��������  ���� ���
		List<DayCount> spends=shouzhiRecordService.findDayInTimeCountSpends(currentParamMap);//֧������     ����  ���
		Map<String,Object> currentMap=new HashMap<String,Object>();
		currentMap.put("incomes", incomes);
		currentMap.put("spends", spends);
		System.out.println("incomes:"+incomes);
		System.out.println("spends:"+spends);
		//��������
		int last2 = lastTimeDay.lastIndexOf("-");//xxxx-xx-xx ��-��-��
		String currentStart2=lastTimeDay.substring(0, last2)+"-01";//xxxx-xx-01
//		System.out.println("currentEnd2:-------------------"+currentStart2);
		//��ǰ�µ���ʼʱ��
		Map<String,String> currentParamMap2=new HashMap<String,String>();
		currentParamMap2.put("start", currentStart2);
		currentParamMap2.put("end", lastTimeDay);
		currentParamMap2.put("user_id",uid);
		List<DayCount> incomes2=shouzhiRecordService.findDayInTimeCountIncomes(currentParamMap2);//��������  ���� ���
		List<DayCount> spends2=shouzhiRecordService.findDayInTimeCountSpends(currentParamMap2);//֧������     ����  ���
		Map<String,Object> lastMap=new HashMap<String,Object>();
		lastMap.put("incomes", incomes2);
		lastMap.put("spends", spends2);
		System.out.println("incomes2:"+incomes2);
		System.out.println("spends2:"+spends2);
		//���汾�����ݣ��Լ���������
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("current", currentMap);
		map.put("last", lastMap);
		
		//תΪjson����
		String jsonString = JSON.toJSONString(map);
		System.out.println("��ǰ�£��������ݣ�jsonString:\n"+jsonString);
		return jsonString;
	}
}

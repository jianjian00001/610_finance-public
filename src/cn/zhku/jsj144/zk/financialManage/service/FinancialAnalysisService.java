package cn.zhku.jsj144.zk.financialManage.service;

import java.util.Map;

public interface FinancialAnalysisService {

	int findMonthincomeRecordCount(Map<String, String> paramMap);//�����¼

	int findMonthspendsRecordCount(Map<String, String> paramMap);//֧����¼

	int findMonthincomeMoney(Map<String, String> paramMap);//������

	int findMonthspendsMoney(Map<String, String> paramMap);//֧�����


}

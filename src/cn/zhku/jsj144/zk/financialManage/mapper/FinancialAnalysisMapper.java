package cn.zhku.jsj144.zk.financialManage.mapper;

import java.util.Map;

public interface FinancialAnalysisMapper {

	int findMonthincomeRecordCount(Map<String, String> paramMap);//�����¼

	int findMonthspendsRecordCount(Map<String, String> paramMap);//֧����¼

	Integer findMonthincomeMoney(Map<String, String> paramMap);//������

	Integer findMonthspendsMoney(Map<String, String> paramMap);//֧�����

}

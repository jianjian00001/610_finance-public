package cn.zhku.jsj144.zk.financialManage.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.zhku.jsj144.zk.financialManage.mapper.FinancialAnalysisMapper;

@Transactional
@Service
public class FinancialAnalysisServiceImpl implements FinancialAnalysisService {

	@Autowired
	private FinancialAnalysisMapper financialAnalysisMapper;
	
	
	@Override
	public int findMonthincomeRecordCount(Map<String, String> paramMap) {
		int count=financialAnalysisMapper.findMonthincomeRecordCount(paramMap);
		return count;//�����¼
	}

	@Override
	public int findMonthspendsRecordCount(Map<String, String> paramMap) {
		int count=financialAnalysisMapper.findMonthspendsRecordCount(paramMap);
		return count;//֧����¼
	}

	@Override
	public int findMonthincomeMoney(Map<String, String> paramMap) {
		int num = 0;
		Integer count=financialAnalysisMapper.findMonthincomeMoney(paramMap);//�˴���ѯ����֮��Ľ����null�����Խ�int��Ϊinteger��
		if(count==null){
			num=0;
		}
		else{
			num=count;
		}
		return num;//������
	}

	@Override
	public int findMonthspendsMoney(Map<String, String> paramMap) {
		int num = 0;
		Integer count=financialAnalysisMapper.findMonthspendsMoney(paramMap);
		if(count==null){
			num=0;
		}
		else{
			num=count;
		}
		return num;//֧�����
	}

}

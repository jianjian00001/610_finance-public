package cn.zhku.jsj144.zk.financialManage.mapper;

import cn.zhku.jsj144.zk.financialManage.pojo.Budget;

public interface BudgetMapper {

	//���Ԥ��
	void addBudget(Budget budget);

	//���ҵ�ǰ�·��Ƿ����Ԥ��
	Budget findBudget(Budget budget);

	//�༭Ԥ��
	void editBudget(Budget budget);

	//ɾ��Ԥ��
	void deleteBudget(int wid);

}

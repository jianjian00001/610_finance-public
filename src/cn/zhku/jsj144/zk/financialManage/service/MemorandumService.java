package cn.zhku.jsj144.zk.financialManage.service;

import java.util.List;

import cn.zhku.jsj144.zk.financialManage.pojo.Memorandum;
import cn.zhku.jsj144.zk.financialManage.pojo.PageBean;

public interface MemorandumService {

	void addMemorandum(Memorandum me);//��ӱ���

	PageBean<Memorandum> listMemorandum(int uid, Integer currentPage);// ��ʾ���б���¼[��ҳ��ѯ]

	Memorandum oneMemorandum(Memorandum me);// ��ǰ����¼

	void editMemorandum(Memorandum memorandum);// �༭����¼

	void deleteMemorandum(int mid);// ɾ������¼

	int findMemorandumCount(int uid);// ��ѯ����¼�ܼ�¼��

}

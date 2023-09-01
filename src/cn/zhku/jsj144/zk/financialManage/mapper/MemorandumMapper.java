package cn.zhku.jsj144.zk.financialManage.mapper;

import java.util.List;
import java.util.Map;

import cn.zhku.jsj144.zk.financialManage.pojo.Memorandum;

public interface MemorandumMapper {

	//��ӱ���
	void addMemorandum(Memorandum me);

	//��ʾ���б���¼
	List<Memorandum> listMemorandum(int uid);

	// ��ǰ����¼
	Memorandum oneMemorandum(Memorandum me);

	// �༭����¼
	void editMemorandum(Memorandum memorandum);

	// ɾ������¼
	void deleteMemorandum(int mid);

	
	// ��ѯ����¼�ܼ�¼��
	int findMemorandumCount(int uid);

	// ��ҳ��ѯ,����¼ �ĵ�ǰҳ��¼�б�
	List<Memorandum> findMemorandumList(Map<String, Object> map);

}

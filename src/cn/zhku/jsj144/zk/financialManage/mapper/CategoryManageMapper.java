package cn.zhku.jsj144.zk.financialManage.mapper;

import java.util.List;
import java.util.Map;

import cn.zhku.jsj144.zk.financialManage.pojo.ShouzhiCategory;

public interface CategoryManageMapper {

	// ��ѯ��֧����ܼ�¼��
	int findCategorysCount(ShouzhiCategory shouzhiCategory);

	// ��ҳ��ѯ��֧���ǰҳ��¼�б�
	List<ShouzhiCategory> findCategorysCurrentPageList(Map<String, Object> map);

	//�жϵ�ǰ�������Ƿ����
	ShouzhiCategory findCategory(ShouzhiCategory shouzhiCategory);

	//�����֧����
	void insertCategory(ShouzhiCategory shouzhiCategory);

	//ͨ��id��ѯ��ǰ��֧������Ϣ
	ShouzhiCategory queryShouzhiCategoryById(Integer szcid);
	
	//�༭��֧������Ϣ
	void editShouzhiCategory(ShouzhiCategory shouzhiCategory);

	//���ҵ�ǰ��֧�������µ���֧��¼
	int countShouzhiRecord(int szcid);

	//ɾ����֧������
	void deleteShouzhiCategory(int szcid);

	//��ǰ������֧�����ͼ�¼��
	int countShouzhiCategorys();

}

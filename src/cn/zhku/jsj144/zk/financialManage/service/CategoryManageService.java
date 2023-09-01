package cn.zhku.jsj144.zk.financialManage.service;

import cn.zhku.jsj144.zk.financialManage.pojo.PageBean;
import cn.zhku.jsj144.zk.financialManage.pojo.ShouzhiCategory;

public interface CategoryManageService {

	///��ҳ��ѯ��֧���
	PageBean<ShouzhiCategory> findCategorys(ShouzhiCategory shouzhiCategory,
			Integer currentPage);

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

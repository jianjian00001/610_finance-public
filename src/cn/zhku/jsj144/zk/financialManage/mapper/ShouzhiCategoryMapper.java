package cn.zhku.jsj144.zk.financialManage.mapper;

import java.util.List;

import cn.zhku.jsj144.zk.financialManage.pojo.ShouzhiCategory;

public interface ShouzhiCategoryMapper {

	//ͨ�������ͣ��Ӷ���ѯ���ø����͵�����������
	List<String> findSonCategoryByParent(String parent_category);

	//������֧�����ͣ������֧����id  ����
	ShouzhiCategory findCategoryBySonCategory(String son_category);

	//��ѯ�������֧��������   --ͨ�������࣬�Ӷ���ѯ���µ������ӷ���
	List<ShouzhiCategory> findShouzhiCategoryByParent(String parent_category);

	//�����֧����
	void addShouzhiCategory(ShouzhiCategory shouzhiCategory);

}

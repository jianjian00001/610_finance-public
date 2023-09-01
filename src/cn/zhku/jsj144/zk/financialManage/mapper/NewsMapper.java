package cn.zhku.jsj144.zk.financialManage.mapper;

import java.util.List;
import java.util.Map;

import cn.zhku.jsj144.zk.financialManage.pojo.News;

public interface NewsMapper {

	//�������
	void addNews(News news);

	// ��ѯ�����ܼ�¼��
	int findNewsCount(News news);

	// ��ҳ��ѯ���ŵ�ǰҳ��¼�б�
	List<News> findNewsCurrentPageList(Map<String, Object> map);

	//ͨ��id��ѯ��ǰ������Ϣ
	News queryNewsById(Integer nid);

	//�༭������Ϣ
	void editNews(News news);

	//ɾ��������Ϣ
	void deleteNews(int nid);

	//��ѯ��ǰ�������ż�¼��
	int countNews();

	//���8���������ţ�ͨ��¼��ʱ�� ��  �������Ķ�����������ʾ
	List<News> findNewsEightList();

}

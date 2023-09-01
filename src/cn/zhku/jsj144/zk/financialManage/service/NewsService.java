package cn.zhku.jsj144.zk.financialManage.service;

import java.util.List;

import cn.zhku.jsj144.zk.financialManage.pojo.News;
import cn.zhku.jsj144.zk.financialManage.pojo.PageBean;

public interface NewsService {

	//�������
	void addNews(News news);

	//��ҳ��ѯ�����б�
	PageBean<News> findNewsList(News news, Integer currentPage);//10����¼

	PageBean<News> findNewsList2(News news, Integer currentPage);//12����¼
	//ͨ��id��ѯ��ǰ������Ϣ
	News queryNewsById(Integer nid);

	//�༭������Ϣ
	void editNews(News news);

	//ɾ��������Ϣ
	void deleteNews(int nid);

	//��ѯ��ǰ�������ż�¼��
	int countNews();

	//ǰ̨--------------------------------------------------------------
	
	//���8���������ţ�ͨ��¼��ʱ�� ��  �������Ķ�����������ʾ
	List<News> findNewsEightList();
	
	//��ѯ��ʾ��ĳ������������Ϣ
	//��Ϊ����̨���Ѿ����ڸô��룬���Դ˴�����д
	//News queryNewsById(Integer nid);
}

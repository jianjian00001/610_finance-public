package cn.zhku.jsj144.zk.financialManage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.zhku.jsj144.zk.financialManage.mapper.NewsMapper;
import cn.zhku.jsj144.zk.financialManage.pojo.News;
import cn.zhku.jsj144.zk.financialManage.pojo.PageBean;
import cn.zhku.jsj144.zk.financialManage.pojo.ShouzhiCategory;

@Transactional
@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsMapper newsMapper;

	//�������
	@Override
	public void addNews(News news) {
		newsMapper.addNews(news);
	}

	//��ҳ��ѯ�����б�[10����¼]
	@Override
	public PageBean<News> findNewsList(News news, Integer currentPage) {//��װ��ҳBean
		if (currentPage == null) {
			currentPage = 0;
		}

		int pageRecord = 10;// ÿҳ��¼��
		int allRecord = newsMapper.findNewsCount(news);// ��ѯ�����ܼ�¼��
		
		int allPage = 0;// ��ҳ��
		if (allRecord % pageRecord == 0) {
			allPage = allRecord / pageRecord;
		} else {
			allPage = allRecord / pageRecord + 1;
		}
		int startPosition = currentPage * pageRecord;// ��ʼλ��

		Map<String, Object> map = new HashMap<String, Object>();// ��ҳ��ѯ����
		map.put("news", news);
		map.put("startPosition", startPosition);
		map.put("pageRecord", pageRecord);
		List<News> pageList = newsMapper.findNewsCurrentPageList(map);// ��ҳ��ѯ���ŵ�ǰҳ��¼�б�

		PageBean<News> pageBean = new PageBean<News>();
		pageBean.setAllPage(allPage);
		pageBean.setAllRecord(allRecord);
		pageBean.setCurrentPage(currentPage);
		pageBean.setPageRecord(pageRecord);
		pageBean.setStartPosition(startPosition);
		pageBean.setPageList(pageList);

//		//����
		System.out.println("allRecord��" + allRecord);
		System.out.println("currentPage:"+currentPage);
		System.out.println("pageList.size()"+pageList.size());
		return pageBean;
	}
	
	//��ҳ��ѯ�����б� [12����¼]
	@Override
	public PageBean<News> findNewsList2(News news, Integer currentPage) {//��װ��ҳBean
		if (currentPage == null) {
			currentPage = 0;
		}

		int pageRecord = 12;// ÿҳ��¼��
		int allRecord = newsMapper.findNewsCount(news);// ��ѯ�����ܼ�¼��
		
		int allPage = 0;// ��ҳ��
		if (allRecord % pageRecord == 0) {
			allPage = allRecord / pageRecord;
		} else {
			allPage = allRecord / pageRecord + 1;
		}
		int startPosition = currentPage * pageRecord;// ��ʼλ��

		Map<String, Object> map = new HashMap<String, Object>();// ��ҳ��ѯ����
		map.put("news", news);
		map.put("startPosition", startPosition);
		map.put("pageRecord", pageRecord);
		List<News> pageList = newsMapper.findNewsCurrentPageList(map);// ��ҳ��ѯ���ŵ�ǰҳ��¼�б�

		PageBean<News> pageBean = new PageBean<News>();
		pageBean.setAllPage(allPage);
		pageBean.setAllRecord(allRecord);
		pageBean.setCurrentPage(currentPage);
		pageBean.setPageRecord(pageRecord);
		pageBean.setStartPosition(startPosition);
		pageBean.setPageList(pageList);

//			//����
		System.out.println("allRecord��" + allRecord);
		System.out.println("currentPage:"+currentPage);
		System.out.println("pageList.size()"+pageList.size());
		return pageBean;
	}
	

	//ͨ��id��ѯ��ǰ������Ϣ
	@Override
	public News queryNewsById(Integer nid) {
		return newsMapper.queryNewsById(nid);
	}

	//�༭������Ϣ
	@Override
	public void editNews(News news) {
		newsMapper.editNews(news);
	}

	//ɾ��������Ϣ
	@Override
	public void deleteNews(int nid) {
		newsMapper.deleteNews(nid);
	}

	//��ѯ��ǰ�������ż�¼��
	@Override
	public int countNews() {
		return newsMapper.countNews();
	}

	//���8���������ţ�ͨ��¼��ʱ�� ��  �������Ķ�����������ʾ
	@Override
	public List<News> findNewsEightList() {
		return newsMapper.findNewsEightList();
	}
}

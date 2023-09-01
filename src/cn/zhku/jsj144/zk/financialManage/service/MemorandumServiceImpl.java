package cn.zhku.jsj144.zk.financialManage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.zhku.jsj144.zk.financialManage.mapper.MemorandumMapper;
import cn.zhku.jsj144.zk.financialManage.pojo.Memorandum;
import cn.zhku.jsj144.zk.financialManage.pojo.PageBean;
import cn.zhku.jsj144.zk.financialManage.pojo.WishList;

@Transactional
@Service
public class MemorandumServiceImpl implements MemorandumService {

	@Autowired
	private MemorandumMapper memorandumMapper;
	
	@Override
	public void addMemorandum(Memorandum me) {//��ӱ���
		memorandumMapper.addMemorandum(me);
	}

//	@Override
//	public List<Memorandum> listMemorandum(int uid) {//��ʾ���б���¼
//		return memorandumMapper.listMemorandum(uid);
//	}

	// ��ʾ���б���¼[��ҳ��ѯ]
	@Override
	public PageBean<Memorandum> listMemorandum(int uid, Integer currentPage) {
		if (currentPage == null) {
			currentPage = 0;
		}

		int pageRecord = 6;// ÿҳ��¼��
		int allRecord = memorandumMapper.findMemorandumCount(uid);// ��ѯ����¼�ܼ�¼��
		
		int allPage = 0;// ��ҳ��
		if (allRecord % pageRecord == 0) {
			allPage = allRecord / pageRecord;
		} else {
			allPage = allRecord / pageRecord + 1;
		}
		int startPosition = currentPage * pageRecord;// ��ʼλ��

		Map<String, Object> map = new HashMap<String, Object>();// ��ҳ��ѯ����
		map.put("uid", uid);
		map.put("startPosition", startPosition);
		map.put("pageRecord", pageRecord);
		List<Memorandum> pageList = memorandumMapper.findMemorandumList(map);// ��ҳ��ѯ,����¼ �ĵ�ǰҳ��¼�б�

		PageBean<Memorandum> pageBean = new PageBean<Memorandum>();
		pageBean.setAllPage(allPage);
		pageBean.setAllRecord(allRecord);
		pageBean.setCurrentPage(currentPage);
		pageBean.setPageRecord(pageRecord);
		pageBean.setStartPosition(startPosition);
		pageBean.setPageList(pageList);

		//����
//		System.out.println("allRecord��" + allRecord);
//		System.out.println("currentPage:"+currentPage);
//		System.out.println("pageList.size()"+pageList.size());
		return pageBean;
//		return memorandumMapper.listMemorandum(uid);
//		return null;
	}
	
	
	@Override
	public Memorandum oneMemorandum(Memorandum me) {// ��ǰ����¼
		return memorandumMapper.oneMemorandum(me);
	}

	@Override
	public void editMemorandum(Memorandum memorandum) {// �༭����¼
		memorandumMapper.editMemorandum(memorandum);
	}

	@Override
	public void deleteMemorandum(int mid) {// ɾ������¼
		memorandumMapper.deleteMemorandum(mid);
	}

	@Override
	public int findMemorandumCount(int uid) {// ��ѯ����¼�ܼ�¼��
		return memorandumMapper.findMemorandumCount(uid);// ��ѯ����¼�ܼ�¼��
	}



}

package cn.zhku.jsj144.zk.financialManage.pojo;

import java.util.List;
//��ҳʵ����
/*
 	mysql�ķ�ҳ��ѯ
 	SELECT * FROM table LIMIT 5,10; // ������¼�� 6-15 
           ��һ������5����ʼλ��
           �ڶ�������10��ÿҳ��¼��
 ������ϵ
 ��1����ʼλ��
 	startPosition=(currentPage-1)*pageRecord
 ��2����ҳ��
  allRecord/pageRecord=allPage;
  if( allRecord%pageRecord!=0) 
    allPage=allPage+1;  ��ҳ�� allPage
  ��3��ÿҳ��¼��
    ����Ϊ8���̶���
    
 ��4����ǰҳ�ļ�¼����mysql����ɣ����ԡ�
  ��1�����һҳ��¼��=allRecord-(currentPage-1)*pageRecord
  ��2������ҳ��¼��=pageRecord
 */
public class PageBean<T> {

	private int startPosition;//��ʼλ��
	private int currentPage;//��ǰҳ
	private int allPage;//��ҳ��
	private int pageRecord;//ÿҳ��¼��   
	private int allRecord;//�ܼ�¼��
	
	private List<T> pageList;//��ǰҳ��¼��ʵ�����ݣ�
	
	public int getStartPosition() {
		return startPosition;
	}
	public void setStartPosition(int startPosition) {
		this.startPosition = startPosition;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getAllPage() {
		return allPage;
	}
	public void setAllPage(int allPage) {
		this.allPage = allPage;
	}
	public int getPageRecord() {
		return pageRecord;
	}
	public void setPageRecord(int pageRecord) {
		this.pageRecord = pageRecord;
	}
	public int getAllRecord() {
		return allRecord;
	}
	public void setAllRecord(int allRecord) {
		this.allRecord = allRecord;
	}
	public List<T> getPageList() {
		return pageList;
	}
	public void setPageList(List<T> pageList) {
		this.pageList = pageList;
	}
}

package cn.zhku.jsj144.zk.financialManage.pojo;

/*
map.put("uid", user.getUid()+"");//uid
map.put("startPosition", startPosition+"");//startPosition
map.put("pageRecord", pageRecord+"");//pageRecord
//�����ѯ����
map.put("date_condition", shouzhiRecord.getSzr_date());
map.put("comment_condition", shouzhiRecord.getSzr_comment());

}*/
//�����ѯʱ���һЩ����
public class ShouzhiRecordQueryVo {
	
	private int uid;//�û�id
	private int startPosition;//��ʼλ��
	private int pageRecord;//ÿҳ��¼��
	private String szr_date;//��ѯ--��֧��������
	private String szr_comment;//��ѯ--��֧��ע����
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getStartPosition() {
		return startPosition;
	}
	public void setStartPosition(int startPosition) {
		this.startPosition = startPosition;
	}
	public int getPageRecord() {
		return pageRecord;
	}
	public void setPageRecord(int pageRecord) {
		this.pageRecord = pageRecord;
	}
	public String getSzr_date() {
		return szr_date;
	}
	public void setSzr_date(String szr_date) {
		this.szr_date = szr_date;
	}
	public String getSzr_comment() {
		return szr_comment;
	}
	public void setSzr_comment(String szr_comment) {
		this.szr_comment = szr_comment;
	}
	
}

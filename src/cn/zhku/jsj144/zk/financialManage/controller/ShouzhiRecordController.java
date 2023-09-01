package cn.zhku.jsj144.zk.financialManage.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.zhku.jsj144.zk.financialManage.pojo.News;
import cn.zhku.jsj144.zk.financialManage.pojo.PageBean;
import cn.zhku.jsj144.zk.financialManage.pojo.ShouzhiCategory;
import cn.zhku.jsj144.zk.financialManage.pojo.ShouzhiRecord;
import cn.zhku.jsj144.zk.financialManage.pojo.User;
import cn.zhku.jsj144.zk.financialManage.service.NewsService;
import cn.zhku.jsj144.zk.financialManage.service.ShouzhiCategoryService;
import cn.zhku.jsj144.zk.financialManage.service.ShouzhiRecordService;

@Controller
@RequestMapping("/shouzhiRecord")   //����·����  /shouzhiRecord/xxx
public class ShouzhiRecordController {

	//ע��shouzhiRecordService
	@Autowired
	private ShouzhiRecordService shouzhiRecordService;
	
	@Autowired
	private ShouzhiCategoryService shouzhiCategoryService;//ע��shouzhiCategoryService
	
	@Autowired
	private NewsService newsService;
	//��¼֮����ת����֧��ϸ
	//�˵���ϸ      +��ҳ��ѯ  +��������ѯ
	@RequestMapping(value="findShouzhiRecord.action")
	public String findShouzhiRecord(ShouzhiRecord shouzhiRecord,HttpServletRequest request) throws UnsupportedEncodingException{
		
		//��ȡ��ǰҳ  ��   �û���
		int currentPage=0;
		//ʹ�ô���   request.getAttribute("currentPage")!=null
		if(request.getParameter("currentPage")!=null){
			currentPage=Integer.parseInt((String) request.getParameter("currentPage"));
		}
		//���� User user=(User) request.getAttribute("user");
		User user=(User) request.getSession().getAttribute("user");
		if(user==null){
			return "/index.jsp";//��¼ҳ��
		}
		//System.out.println("��ǰ�û�δ����---"+user.getUid());
		
		//�����ѯ����
		if(shouzhiRecord!=null){
			if(shouzhiRecord.getSzr_date()!=null){
//				System.out.println("����-----��"+shouzhiRecord.getSzr_date());
//				request.getSession().setAttribute("date_condition", shouzhiRecord.getSzr_date());
				request.setAttribute("date_condition", shouzhiRecord.getSzr_date());
			}
			if(shouzhiRecord.getSzr_comment()!=null){
//			if(szr_comment!=null){
//				System.out.println("��ע-----��"+shouzhiRecord.getSzr_comment());
				String com=new String((shouzhiRecord.getSzr_comment()).getBytes("ISO-8859-1"),"utf-8");
//				System.out.println("��ע222-----��"+com);
//				request.getSession().setAttribute("comment_condition", com);
				request.setAttribute("comment_condition", com);
				shouzhiRecord.setSzr_comment(com);//���¸�ֵ
			}
		}
		
		//��ѯ�˵���ϸ
		PageBean<ShouzhiRecord> pageBean= shouzhiRecordService.findShouzhiRecord(currentPage,user,shouzhiRecord);
//		System.out.println("pageBean.getPageList().size():"+pageBean.getPageList().size());
		
//		String  szr_comment=null;
//		if(request.getParameter("szr_comment")!=null){
//			szr_comment=new String(request.getParameter("szr_comment").getBytes("ISO-8859-1"),"utf-8");//�Դ���ת�����룬�ͻ�����������⡣
//		}
		

		
		//��ѯ����������   --ͨ�������࣬�Ӷ���ѯ���µ������ӷ���
		List<ShouzhiCategory> incomes=shouzhiCategoryService.findShouzhiCategoryByParent("����");
		request.setAttribute("incomes", incomes);
		//��ѯ֧��������
		List<ShouzhiCategory> spends=shouzhiCategoryService.findShouzhiCategoryByParent("֧��");
		request.setAttribute("spends", spends);
		
		//���8���������ţ�ͨ��¼��ʱ�� ��  �������Ķ�����������ʾ
		List<News> newsList=newsService.findNewsEightList();
		request.setAttribute("newsList", newsList);//��������
		
		
		if(pageBean.getPageList().size()==0){//��ѯ���Ϊnullʱ��ȷ������Ϊ��
			pageBean.setPageList(null);
		}
		request.setAttribute("pageBean", pageBean);//��ҳ��¼
		return "/jsp/main.jsp";//��ת����ҳ
	}
	
	//ajax�첽����ȥ�޸���Ϣҳ�棬����id��ȡ����Ҫ�Ĳ������Ӷ����л���
	@RequestMapping("toEdit.action")
	@ResponseBody
	public String toEdit(Long id,HttpServletRequest request){
//		System.out.println("�����޸���Ϣ��ҳ��........................................");
		//�˴���ע��id�����ͣ�������������������
		User user = (User) request.getSession().getAttribute("user");
		if(user==null){
			return "/index.jsp";//��¼ҳ��
		}
		Map<String,Integer> map=new HashMap<String,Integer>();
		map.put("uid", user.getUid());//�����û�
		map.put("szrid", id.intValue());//������֧��¼
		//����id��ѯ��֧��¼��Ϣ
		ShouzhiRecord shouzhiRecord=shouzhiRecordService.findShouzhiRecordById(map);
		//���ݿ���ʱ�䣺ΪString����֮��ʡȥ�˶���ʱ��Ĺ���
		
		//Date���ͱ�Ϊ  String��������
	/*	Date szr_date = shouzhiRecord.getSzr_date();
		System.out.println("ʱ�䣺-------"+szr_date);
		String formatDate=null;
	    DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd"); //HH��ʾ24Сʱ�ƣ�  
	    formatDate = dFormat.format(szr_date);  
	    System.out.println("��ʽ��֮���ʱ�䣺------------"+formatDate);  */
	    
	    
		//��ø����͵ĸ�����
		String parent_category = shouzhiRecord.getShouzhiCategory().getParent_category();
//		System.out.println("�������ǣ�---------------"+parent_category);
		//ͨ�������ͣ��Ӷ���ѯ���ø����͵����������ͣ��Ӷ�������ʾ
		List<String> son=shouzhiCategoryService.findSonCategoryByParent(parent_category);
		
		//��α����������ͣ��Ӷ���ʾ��ҳ����
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("shouzhiRecord", shouzhiRecord);//һ��shouzhiRecord����
		jsonMap.put("son", son);//һ��list����
		//jsonMap.put("formatDate",formatDate);//�����ʽ��֮���ʱ�����
		
		//fastjson��map����תΪjson��ʽ�ַ���
		String jsonString = JSON.toJSONString(jsonMap);
		
		//���Խ��
		System.out.println("jsonString:\n"+jsonString);
		
		//��ǰ���  --��Ч
		//1���첽��������request��Ч!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//2����������session    �����ӳ�ЧӦ���Ծ�ʧ��
		//3����ajax���data��ͨ�� js��ӱ�ǩ��ɣ�
		
		//request.setAttribute("currentCategory", shouzhiRecord.getShouzhiCategory().getSon_category());
/*		request.getSession().setAttribute("currentCategory", shouzhiRecord.getShouzhiCategory().getSon_category());
		List<ShouzhiCategory> list=new ArrayList<ShouzhiCategory>();
		for (String one : son) {
			ShouzhiCategory shouzhiCategory=new ShouzhiCategory();
			shouzhiCategory.setSon_category(one);
			list.add(shouzhiCategory);
		}
		//request.setAttribute("sonCategory", list);//�������   ������request��
		request.getSession().setAttribute("sonCategory", list);*/
		
		//{"shouzhiRecord":
		//{"shouzhiCategory":{"parent_category":"֧��","son_category":"��ʳ��","szcid":2},
		//"szr_comment":"���","szr_date":1519920000000,"szr_num":-10,"szrid":2,"user_id":1},
		//"son":["��ʳ��","ס�޷�","��ͨ��","ͨѶ��"]}
		return jsonString;
	}
	
	//�޸��û���֧��Ϣ
	@RequestMapping("edit.action")
	@ResponseBody
	public String editShouzhiRecord(ShouzhiRecord shouzhiRecord,HttpServletRequest request){
		//System.out.println("-----�޸��û���Ϣ�ɹ�-----");
		User user=(User)request.getSession().getAttribute("user");
		if(user==null){
			return "/index.jsp";//��¼ҳ��
		}
		shouzhiRecord.setUser_id(user.getUid());
		//������֧�����ͣ������֧����id  ����
		ShouzhiCategory shouzhiCategory=shouzhiCategoryService.findCategoryBySonCategory(shouzhiRecord.getShouzhiCategory().getSon_category());
		shouzhiRecord.setShouzhiCategory(shouzhiCategory);
		
		//��һ���жϻ���
		//Ϊ����ʱ��ȷ�����ΪΪ����
		if("����"==shouzhiCategory.getParent_category()){
			int num=shouzhiRecord.getSzr_num();
			if(num<0){//�����������Ա�Ϊ����
				shouzhiRecord.setSzr_num(-num);
			}
		}
		//Ϊ֧��ʱ��ȷ�����Ϊ����
		else{
			int num=shouzhiRecord.getSzr_num();
			if(num>0){//�����������Ա�Ϊ����
				shouzhiRecord.setSzr_num(-num);
			}
		}
		
		//�޸��û���֧��Ϣ
		shouzhiRecordService.editShouzhiRecord(shouzhiRecord);
		//return "ok";
		return "OK";
	}
	
	//ɾ���û���֧��Ϣ��һ����
	@RequestMapping("deleteOne.action")
	@ResponseBody
	public String deleteOneShouzhiRecord(int id){
		//����id��������Ҫ���н�һ����˼��
		shouzhiRecordService.deleteOneShouzhiRecord(id);
		return "OK";
	}
	
	//����ɾ���û���֧��Ϣ��������������
	@RequestMapping("deleteBatch.action")
	@ResponseBody
	public String deleteBatchShouzhiRecord(String id){//id���ַ�������
		shouzhiRecordService.deleteBatchShouzhiRecord(id);
		return "OK";
	}
	
	//�����֧��¼��Ϣ������ ����  ֧����   -->ͨ��jquery��ʵ��    --ʧ��
	////�����֧��¼��Ϣ������ ����  ֧����   -->ͨ��form��ʵ��
	//addShouzhiRecord.action
	@RequestMapping("addShouzhiRecord.action")
//	@ResponseBody
	public String addShouzhiRecord(ShouzhiRecord shouzhiRecord,HttpServletRequest request) throws IOException{
		
		//�ж������뻹�ǣ�֧��
		//�����֧������֤Ϊ����
		int szcid = shouzhiRecord.getShouzhiCategory().getSzcid();//ͨ��id����ѯ�����룬����֧��
		String cat=shouzhiRecordService.findParentCategoryById(szcid);//ͨ����֧���id ��ѯ������ ���� ֧��
//		System.out.println("cat:----"+cat);
		if("֧��".equals(cat)){//��֤Ϊ����
			int num=shouzhiRecord.getSzr_num();
			if(num>=0){
				shouzhiRecord.setSzr_num(-num);//��֤Ϊ����
			}
//			System.out.println("֧��:----");
		}
		else{//��֤Ϊ����
			int num=shouzhiRecord.getSzr_num();
			if(num<=0){
				shouzhiRecord.setSzr_num(-num);//��֤Ϊ����
			}
//			System.out.println("����:----");
		}
		shouzhiRecordService.addShouzhiRecord(shouzhiRecord);
		
		/*response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter();
		out.print("<script language=\"javascript\">alert('�����֧��Ϣ�ɹ�');window.location.href='/financialManage/shouzhiRecord/findShouzhiRecord.action'</script>");*/

		
		//request.getSession().setAttribute("add_income_category", "add_income_category");
		
		return "redirect:/shouzhiRecord/findShouzhiRecord.action";
		
		//return "OK";    //jqueryʵ��ʧ��
		//return "redirect:/shouzhiRecord/findShouzhiRecord.action";//���²�ѯ
	}
}


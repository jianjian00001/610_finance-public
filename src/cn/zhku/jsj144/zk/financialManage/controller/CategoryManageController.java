package cn.zhku.jsj144.zk.financialManage.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.zhku.jsj144.zk.financialManage.pojo.PageBean;
import cn.zhku.jsj144.zk.financialManage.pojo.ShouzhiCategory;
import cn.zhku.jsj144.zk.financialManage.pojo.User;
import cn.zhku.jsj144.zk.financialManage.service.CategoryManageService;

@Controller
@RequestMapping("/categoryManage")
public class CategoryManageController {

	@Autowired
	private CategoryManageService categoryManageService;
	
	//��ҳ��ѯ��֧���
	@RequestMapping("/findCategorys.action")
	public String findCategorys(ShouzhiCategory shouzhiCategory,Integer currentPage,Model model){//��������ҳ��ѯ+��������ҳ��ѯ
		//��ѯ��������֧����  + ��֧������      +  ��ǰҳ 
		//��ǰҳ��Ĭ������£�currentPage=0   �ύ�����ǵ�1ҳ  
		
		model.addAttribute("findShouzhiCategory", shouzhiCategory);//���ز�ѯ����
		
		PageBean<ShouzhiCategory> pageBean=categoryManageService.findCategorys(shouzhiCategory,currentPage);//��ҳ��ѯ��֧���
		
		if(pageBean.getPageList().size()==0){//ȷ��Ϊ��
			pageBean.setPageList(null);
		}
		model.addAttribute("pageBean", pageBean);//��ҳ��ѯ���
		
		return "/admin/categoryManage.jsp";//��֧������
	}
	
	//ajax�жϵ�ǰ�������Ƿ����
	@RequestMapping("/ajaxFindCategory.action")
	@ResponseBody
	public String ajaxFindCategory(ShouzhiCategory shouzhiCategory){//@RequestBody 
		ShouzhiCategory findCategory=categoryManageService.findCategory(shouzhiCategory);//�жϵ�ǰ�������Ƿ����
		if(findCategory!=null){//�Ѿ�����
			return "{\"name\":\"yes\"}";//json��ʽ		
		}
		return "{\"name\":\"no\"}";//json��ʽ	
	}
	
	//�����֧����
	@RequestMapping("/addCategory.action")
	public String addCategory(ShouzhiCategory shouzhiCategory,Integer currentPage){//��Ӻ��Ծɷ��ص�ǰҳ
		categoryManageService.insertCategory(shouzhiCategory);
		return "redirect:/categoryManage/findCategorys.action?currentPage="+currentPage;//�ض���
	}
	
	//�༭��֧����ҳ��
	@RequestMapping("/toEditPage.action")
	@ResponseBody
	public String toEditPage(Integer szcid){
		ShouzhiCategory shouzhiCategory=categoryManageService.queryShouzhiCategoryById(szcid);//ͨ��id��ѯ��ǰ��֧������Ϣ
		Map<String,Object> map=new HashMap<String,Object>();//���ݷŵ�map��
		map.put("shouzhiCategory", shouzhiCategory);
		map.put("old_son_category", shouzhiCategory.getSon_category());//����ԭ������֧��������
		String js = JSON.toJSONString(map);//����json��
		System.out.println("js:------"+js);
		return js;
	}
	
	//�༭��֧������Ϣ
	@RequestMapping("/editShouzhiCategory.action")
	public String editShouzhiCategory(ShouzhiCategory shouzhiCategory,Integer currentPage2){
		categoryManageService.editShouzhiCategory(shouzhiCategory);
		return "redirect:/categoryManage/findCategorys.action?currentPage="+currentPage2;//��ǰҳ���ڷ�ҳ��ʱ������˱���
	}
	
	//ajax�ж��Ƿ����ɾ����ǰ��֧������
	@RequestMapping("/ajaxConfirmDeleteShouzhiCategory.action")
	@ResponseBody
	public String ajaxConfirmDeleteShouzhiCategory(int szcid){  //ע�⣬��ǰ��֧�����ʹ������ʱ�����ܽ���ɾ��
		//��֧��¼�� �������ݣ������ܽ���ɾ��
		int count=categoryManageService.countShouzhiRecord(szcid);//��֧��¼
		if(count==0){
			//�������˵������ɾ������
			return "{\"name\":\"yes\"}";//json��ʽ
		}
		//��������˵��������ɾ������
		return "{\"name\":\"no\"}";//json��ʽ
	}
	
	//ɾ����֧������Ϣ
	@RequestMapping("/deleteShouzhiCategory.action")
	public String deleteShouzhiCategory(int szcid,Integer currentPage2){
		categoryManageService.deleteShouzhiCategory(szcid);//ɾ����֧������
		
		//�����ǰҳ��ֻ��һ����¼��ɾ����Ӧ�÷�����һҳ
		//ÿҳ��¼����10������ѯ�û����ܼ�¼��
		int pageRecord=10;
		int count=categoryManageService.countShouzhiCategorys();//��ǰ������֧�����ͼ�¼��
		int allPage=0;//��ǰ��ҳ��
		if(count%pageRecord==0){
			allPage=count/pageRecord;
		}
		else{
			allPage=count/pageRecord+1;
		}
		allPage=allPage-1;
		
		if(currentPage2>allPage){
			currentPage2=currentPage2-1;//��һҳ
		}
		
		return "redirect:/categoryManage/findCategorys.action?currentPage="+currentPage2;//��ǰҳ���ڷ�ҳ��ʱ������˱���
	}
	
	
}

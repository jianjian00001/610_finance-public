package cn.zhku.jsj144.zk.financialManage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zhku.jsj144.zk.financialManage.pojo.ShouzhiCategory;
import cn.zhku.jsj144.zk.financialManage.service.ShouzhiCategoryService;

@Controller
@RequestMapping("/shouzhiCategory")   //����·����  /shouzhiCategory/xxx
public class ShouzhiCategoryController {

	@Autowired
	private ShouzhiCategoryService shouzhiCategoryService;
	
	//�����֧����
	@RequestMapping("addShouzhiCategory.action")
	@ResponseBody
	public String addShouzhiCategory(ShouzhiCategory shouzhiCategory){
		shouzhiCategoryService.addShouzhiCategory(shouzhiCategory);
		return "OK";
	}
	
	//ajax����    ��֧�������Ƿ����
	@RequestMapping("findsonCategoryByNameAndAjax.action")
	@ResponseBody
	public String findsonCategoryByNameAndAjax(String son_category){
		ShouzhiCategory result = shouzhiCategoryService.findCategoryBySonCategory(son_category);
		if(result!=null){
			return "{\"name\":\"exit\"}";//json��ʽ
		}
		else{
			return "{\"name\":\"notexit\"}";//������
		}
	}
}

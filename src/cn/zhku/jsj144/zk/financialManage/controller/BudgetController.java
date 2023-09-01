package cn.zhku.jsj144.zk.financialManage.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zhku.jsj144.zk.financialManage.pojo.Budget;
import cn.zhku.jsj144.zk.financialManage.pojo.User;
import cn.zhku.jsj144.zk.financialManage.service.BudgetService;

@Controller

@RequestMapping("/budget")//����·����/budget/xxx
public class BudgetController {
	
	@Autowired
	private BudgetService budgetService;
	
	//���ݵ�ǰʱ�䣬�Լ���ǰ�û�id����ѯԤ�㣬������ʾԤ����ص�����
	@RequestMapping("/findBudget.action")
	public String findBudget(HttpServletRequest request,Model model){
		String current=getCurrentTime();//��ǰ�·�
		User user=(User) request.getSession().getAttribute("user");
		int uid=user.getUid();//�û�id
		Budget budget=new Budget();
		budget.setUser_id(uid);
		budget.setWtime(current);
		
		//���ҵ�ǰ�·��Ƿ����Ԥ��
		Budget findBudget=budgetService.findBudget(budget);
		model.addAttribute("budget", findBudget);
		return "/jsp/financialBudget.jsp";
	}
	
	
	//���Ԥ��
	@RequestMapping("/addBudget.action")
	@ResponseBody
	public String addBudget(Budget budget){
		//��ǰ�·�
		budget.setWtime(getCurrentTime());//��ʽ��ʱ��  ��ǰ�·�
		budgetService.addBudget(budget);//���Ԥ��
		return "OK";
	}
	
	//�༭Ԥ��
	@RequestMapping("/editBudget.action")
	@ResponseBody
	public String editBudget(Budget budget){
		budgetService.editBudget(budget);//�༭Ԥ��
		return "OK";
	}
	
	//ɾ��Ԥ��
	@RequestMapping("/deleteBudget.action")
	@ResponseBody
	public String deleteBudget(int wid){
		budgetService.deleteBudget(wid);
		return "OK";
	}
	//��ȡ��ǰʱ��
	public String getCurrentTime(){
		Date time=new Date();
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM"); 
		String current = dFormat.format(time);
		return current;
		
	}
	
}

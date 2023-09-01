package cn.zhku.jsj144.zk.financialManage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;

import cn.zhku.jsj144.zk.financialManage.pojo.Admin;
import cn.zhku.jsj144.zk.financialManage.pojo.PageBean;
import cn.zhku.jsj144.zk.financialManage.pojo.ShouzhiRecord;
import cn.zhku.jsj144.zk.financialManage.pojo.User;
import cn.zhku.jsj144.zk.financialManage.service.AdminService;
import cn.zhku.jsj144.zk.financialManage.service.UserService;

@Controller
@RequestMapping("/userManage")
public class UserManageController {//�û�����

	@Autowired
	private UserService userService;//��ͨ�û�
	@Autowired
	private AdminService adminService;//��̨����Ա
	
	@RequestMapping("/login.action")
	public String login(Admin admin,HttpServletRequest request){//��¼
		Admin findAdmin=adminService.findAdmin(admin);//��̨��¼
		if(findAdmin==null){
			request.setAttribute("msg", "��¼ʧ��,�˺Ż��������");
			return "/admin/index.jsp";
		}
		request.getSession().setAttribute("admin", findAdmin);//���浽��session�������
		
		return "/userManage/findUsers.action";//��ҳ��ѯ��ʾ�û��б�
	}
	
	@RequestMapping("/logout.action")
	public String logout(HttpSession session){//�˳���¼
		session.removeAttribute("admin");//ɾ��
		return "/admin/index.jsp";
	}
	
	
	
	@RequestMapping("/findUsers.action")
	public String findUsers(User user,Integer currentPage,Model model){//��������ҳ��ѯ+��������ҳ��ѯ
		//��ѯ�������û���+����+�ֻ���   +  ��ǰҳ 
		//��ǰҳ��Ĭ������£�currentPage=0  �ύ�����ǵ�1ҳ  

		//�����ѯ����
		/*
		if(user!=null){
			if(user.getUsername()!=""){
				model.addAttribute("username_condition", user.getUsername());
			}
			if(user.getEmail()!=""){
				model.addAttribute("email_condition", user.getEmail());
			}
			if(user.getPhone()!=""){
				model.addAttribute("phone_condition", user.getPhone());
			}
		}
		*/
		model.addAttribute("findUser", user);
		
		PageBean<User> pageBean=userService.findUsers(user,currentPage);//��ҳ��ѯ�û��б�
		if(pageBean.getPageList().size()==0){//ȷ��Ϊ��
			pageBean.setPageList(null);
		}
		model.addAttribute("pageBean", pageBean);//��ҳ��ѯ���
		
		return "/admin/main.jsp";//��ҳ���
	}
	
	
	
	//����û�
	@RequestMapping("/addUser.action")
	public String addUser(User user,HttpServletRequest request){  //��ǰ�û���������ʱ���ſ��Խ����û������[ǰ�˽���У��]
		
		//����ʱ������������������Դ�ʱ��User����������id�ģ�������������user�����Ϣ
		userService.insertUser(user);
		return "redirect:/userManage/findUsers.action";//�ض���
	}
	
	//�༭�û���Ϣҳ��
	@RequestMapping("/toEditPage.action")
	@ResponseBody
	public String toEditPage(Integer uid){//Integer uid   String uid
//		Long id=Long.parseLong(uid);//����
		User user=userService.queryUserById(uid);//id.intValue()
		
//		model.addAttribute("user", user);
		
		Map<String,Object> map=new HashMap<String,Object>();//���ݷŵ�map��
		map.put("user", user);
		map.put("old_username", user.getUsername());//����ԭ�����û���
		
		String js = JSON.toJSONString(map);//����json��
		System.out.println("js:------"+js);
		
		
		return js;//������������д��
		//return "js";д��
	}
	
	
	//�༭�û���Ϣ
	@RequestMapping("/editUser.action")
//	@ResponseBody
	public String editUser(User user,Integer currentPage2){
//		System.out.println("��ǰҳ��"+currentPage2);
		userService.editUserAll(user);//�༭ȫ���û���Ϣ
//		return "ok";
//		return "redirect:/userManage/findUsers.action";
		return "redirect:/userManage/findUsers.action?currentPage="+currentPage2;//��ǰҳ���ڷ�ҳ��ʱ������˱���
	}
	
	
	//ajax�ж��Ƿ����ɾ����ǰ�û�
	@RequestMapping("/ajaxConfirmDeleteUser.action")
	@ResponseBody
	public String ajaxConfirmDeleteUser(int uid){  //ע�⣬��ǰ�û��������ʱ�����ܽ���ɾ��
		//Ԥ���  ��Ը����   ����¼��   ��֧��¼�� 
		//�κ�һ�����У��������ݣ������ܽ���ɾ��
		int count1=adminService.countShouzhiRecord(uid);//��֧��¼
		int count2=0;
		int count3=0;
		int count4=0;
		
		if(count1==0){
			count2=adminService.countBudget(uid);//Ԥ���¼
			if(count2==0){
				count3=adminService.countWishList(uid);//��Ը����¼
				if(count3==0){
					count4=adminService.countMemorandum(uid);//����¼��¼
					if(count4==0){
						//�������˵������ɾ������
						return "{\"name\":\"yes\"}";//json��ʽ		
					}
				}
			}
		}
		//��������˵��������ɾ������
		return "{\"name\":\"no\"}";//json��ʽ
	}
	
	//ɾ���û���Ϣ
	@RequestMapping("/deleteUser.action")
	public String deleteUser(int uid,Integer currentPage2){
		userService.deleteUser(uid);//ɾ���û�
		
		//�����ǰҳ��ֻ��һ����¼��ɾ����Ӧ�÷�����һҳ
		//ÿҳ��¼����10������ѯ�û����ܼ�¼��
		int pageRecord=10;
		int count=userService.countUser();//��ǰ�����û��ܼ�¼��
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
		
		return "redirect:/userManage/findUsers.action?currentPage="+currentPage2;//��ǰҳ���ڷ�ҳ��ʱ������˱���
	}
	
}

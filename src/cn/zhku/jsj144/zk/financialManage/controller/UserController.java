package cn.zhku.jsj144.zk.financialManage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zhku.jsj144.zk.financialManage.pojo.User;
import cn.zhku.jsj144.zk.financialManage.service.ShouzhiCategoryService;
import cn.zhku.jsj144.zk.financialManage.service.ShouzhiRecordService;
import cn.zhku.jsj144.zk.financialManage.service.UserService;

@Controller
@RequestMapping("/user")   //����·����  /user/xxx
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private ShouzhiCategoryService shouzhiCategoryService;
	
	//�û���¼
	@RequestMapping("login.action")
	public String login(User user,HttpServletRequest request){
		System.out.println("�û��������룺"+user.getUsername()+"::"+user.getPassword());
		//����û��������룬�ж��Ƿ����
		User findUser=userService.queryUserByUser(user);
		
		if(findUser!=null){
			System.out.println("���ҵ��û��������룺"+findUser.getUsername()+"::"+findUser.getPassword());
		}
		System.out.println("");
		//1.���ڣ����浽session��  �� Ȼ��   ��ת����ҳ��
		if(findUser!=null){
			HttpSession session = request.getSession();
			session.setAttribute("user", findUser);
//			System.out.println("id----------------------"+findUser.getUid());
			
			//ͨ�������ͣ��Ӷ���ѯ���ø����͵����������ͣ��Ӷ�������ʾ
			//List<String> son=shouzhiCategoryService.findSonCategoryByParent(parent_category);
			
			//��ѯ�˵���ϸ
			return "redirect:/shouzhiRecord/findShouzhiRecord.action";
			
			//return "/jsp/main.jsp";//��ת����ҳ
		}
		
		//2.�����ڣ����ص�¼ʧ����Ϣ
		String msg="�û����������������������������";
		request.setAttribute("msg", msg);
		return "/index.jsp";//��ת����¼ҳ��
	}
	
	//ͨ���û���   �ж��û��Ƿ����
	@RequestMapping(value="findUserByNameAndAjax.action",method=RequestMethod.POST)
	public @ResponseBody String findUserByNameAndAjax(@RequestBody User user){
		//@ResponseBody ���ؽ�java����תΪjson��ʽ������
		//@RequestBody ���json��ʽ������תΪjava����
		
		//ͨ���û�����ѯ�û��Ƿ����
		User findUser=userService.queryUserByUsername(user.getUsername());
		if(findUser!=null){
			System.out.println("{\"name\":\"exit\"}");
			//����
			return "{\"name\":\"exit\"}";//json��ʽ
		}
		else{
			System.out.println("{\"name\":\"notexit\"}");
			return "{\"name\":\"notexit\"}";//������
		}
	}
	
	//�޸����루�һ����룩
	@RequestMapping("updatePasswordByUsername.action")
	public  String updatePasswordByUsername(User user,HttpServletRequest request){
		userService.updatePasswordByUsername(user);
		request.setAttribute("msg", "�����޸ĳɹ������¼");
		return "/index.jsp";
	}
	
	//�û�ע�ᣨ����û���
	@RequestMapping("regist.action")
	public String regist(User user,String repassword,HttpServletRequest request){
		//�ж��Ƿ����
		//ͨ���û�����ѯ�û��Ƿ����
		User findUser=userService.queryUserByUsername(user.getUsername());
		if(findUser!=null){
			//����
			request.setAttribute("msg", "��ǰ�û��Ѿ����ڣ������������û���");
			request.setAttribute("user", user);//����ԭ������������
			request.setAttribute("repassword", repassword);
			return"/regist.jsp";//json��ʽ
		}
		
		//����ʱ������������������Դ�ʱ��User����������id�ģ�������������user�����Ϣ
		userService.insertUser(user);
		//ֱ����ת����ҳ�桾�Զ���¼��
		//ע���֮꣬�󱣴��¼��Ϣ
		HttpSession session = request.getSession();
		session.setAttribute("user", user);//�����¼��Ϣ
		
		//return "/jsp/main.jsp";//�û���ҳ
		
		//��ת��   ȥ��ѯ    ��ѯ�˵���ϸ
		return "redirect:/shouzhiRecord/findShouzhiRecord.action";
	}

	
	//���û���Ϣ����ҳ��
	@RequestMapping("/toUserSetting.action")
	public String toUserSetting(HttpServletRequest request){
		User user=(User) request.getSession().getAttribute("user");
		request.setAttribute("user", user);
		return "/jsp/userSetting.jsp";
	}
		
		
		
	//�û���Ϣ����
	@RequestMapping("/editUser.action")
//	@ResponseBody
	public String editUser(HttpServletRequest request,User user,String oldusername){
		
		//�ж��û����Ƿ��޸ĺ���Ѿ�����
		String username = user.getUsername();//�޸ĺ������
		System.out.println("��ǰ�û�����"+username);
		System.out.println("֮ǰ���û�����"+oldusername);
		if(username!=""&&username!=null){
			System.out.println("�û�����Ϊ�ա���������������������������");
			
//			if(username!=oldusername){//�Ƚϵ��ǵ�ַ��������
			if(!username.equals(oldusername)){
				//�ж��Ƿ��Ѿ����������ݿ���
				User findUser = userService.queryUserByUsername(username);
				if(findUser==null){
					System.out.println("�û��������ڣ����µ��û�����������������������������");
					//��ע����û��������޸�
					userService.editUser(user);//�û���Ϣ����
					request.getSession().setAttribute("user", user);//����ԭ����user
					return  "redirect:/shouzhiRecord/findShouzhiRecord.action";
				}
				else{
					System.out.println("���û��Ѵ��ڣ����޸ĵ�ǰ�û���");
					//�����û����������µ��û���
					user.setUsername(findUser.getUsername());
					request.setAttribute("user", user);
					//��Ϊsession��ԭ�򣬵�����Ϣһ�»���ʧ��
//					request.getSession().setAttribute("user", user);//������Ϣ
					request.setAttribute("msg", "���û��Ѵ��ڣ����޸ĵ�ǰ�û���");
					return  "/jsp/userSetting.jsp";
				}
			}
			else{
				System.out.println("�ǵ�ǰ�û���ֻ���޸���һЩ�û�����Ϣ");
				//ֱ���޸ģ����û���֮�����Ϣ
				userService.editUser(user);//�û���Ϣ����
				request.getSession().setAttribute("user", user);//����ԭ����user
				return  "redirect:/shouzhiRecord/findShouzhiRecord.action";
			}
		}
		else{
			System.out.println("�û���Ϊ�գ��뱣֤�û�������Ϊ��");
//			user.setUsername(oldusername);
			request.setAttribute("user", user);//������Ϣ
//			request.getSession().setAttribute("user", user);//������Ϣ
			request.setAttribute("msg", "�û�������Ϊ��");
			return  "/jsp/userSetting.jsp";
		}
		
		
		
	}
	
	//�û��˳���¼
	@RequestMapping("/logout.action")
	public String logout(HttpServletRequest request){
		request.getSession().removeAttribute("user");//ɾ��session
		return "/index.jsp";
	}
	
}

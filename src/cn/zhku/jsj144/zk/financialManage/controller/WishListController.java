package cn.zhku.jsj144.zk.financialManage.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.zhku.jsj144.zk.financialManage.pojo.PageBean;
import cn.zhku.jsj144.zk.financialManage.pojo.User;
import cn.zhku.jsj144.zk.financialManage.pojo.WishList;
import cn.zhku.jsj144.zk.financialManage.service.WishListService;

@Controller
@RequestMapping("/wishlist")   //����·����  /wishlist/xxx
public class WishListController {

	//ע��WishListService
	@Autowired
	private WishListService wishListService;
	
	
	//��ѯ������Ը��
	@RequestMapping("/findAllWishList.action")
	public String findAllWishList(HttpServletRequest request,Integer currentPage,Model model){//��ҳ��ѯcurrentPage
		User user=(User) request.getSession().getAttribute("user");
		
		PageBean<WishList> pageBean=wishListService.findAllWishList(user.getUid(),currentPage);
		
		if(pageBean.getPageList().size()==0){//ȷ��Ϊ��
			pageBean.setPageList(null);
		}
		model.addAttribute("pageBean", pageBean);//��ҳ��ѯ���
//		List<WishList> wishs=wishListService.findAllWishList(user.getUid(),currentPage);
//		model.addAttribute("wishs",wishs);
		
		return "/jsp/wishlist.jsp";
	}
	
	//�����Ը��
	@RequestMapping("/addWish.action")
	@ResponseBody
	public String addWish(WishList wishList){
		//���yyyy-MM-dd��ʽ��ʱ��
//		Date dt = new Date();
//		String currentTime = null;
//		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd"); 
//		currentTime = dFormat.format(dt);
//		System.out.println(currentTime);
		
		String currentTime = wishList.getWdate();
		String wid=null;//��Ը��id
		//��ȡĳ�ˣ�ĳ�����Ը������
		int count=wishListService.CountWishByTimeAndId(wishList);
		count=count+1;
		if(count+1<10){
			wid="��Ը��"+currentTime+"-0"+count;
		}
		else{
			wid="��Ը��"+currentTime+"-"+count;
		}
		String state="δ���";
		wishList.setWid(wid);
		wishList.setState(state);
		wishList.setWdate(currentTime);
		
		wishListService.addWish(wishList);//�����Ը��
		return "OK";
//		return "/wishlist/findAllWishList.action";//���²�ѯ
	}
	
	//ȥ��Ը��ҳ�棬����id��ѯ��Ը����Ϣ
	@RequestMapping("/toEdit.action")
	@ResponseBody
	public String toEdit(Long id){
		//ʧ��ԭ��mybatis��û��д����ֵ
		WishList findWishlist=wishListService.findWishById(id.intValue());//idΪint����
		String jsonString=JSON.toJSONString(findWishlist);//����תΪjson
		System.out.println("jsonString:"+jsonString);
		return jsonString;
	}
	
	//�༭��Ը��
	@RequestMapping("/editWish.action")
	@ResponseBody
	public void editWish(WishList wishList){
		String wid=null;//��Ը��id
		String currentTime=wishList.getWdate();
		//��ȡĳ�ˣ�ĳ�����Ը������
		int count=wishListService.CountWishByTimeAndId(wishList);
		count=count+1;
		if(count+1<10){
			wid="��Ը��"+currentTime+"-0"+count;
		}
		else{
			wid="��Ը��"+currentTime+"-"+count;
		}
		wishList.setWid(wid);
		wishListService.editWish(wishList);
		return;
	}
	
	//ɾ����Ը��
	@RequestMapping("/deleteWish.action")
	@ResponseBody
	public void deleteWish(int id){
		wishListService.deleteWish(id);
		return;
	}
	
	
}

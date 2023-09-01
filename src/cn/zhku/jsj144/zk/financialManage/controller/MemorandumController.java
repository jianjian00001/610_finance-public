package cn.zhku.jsj144.zk.financialManage.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zhku.jsj144.zk.financialManage.pojo.Memorandum;
import cn.zhku.jsj144.zk.financialManage.pojo.PageBean;
import cn.zhku.jsj144.zk.financialManage.pojo.User;
import cn.zhku.jsj144.zk.financialManage.pojo.WishList;
import cn.zhku.jsj144.zk.financialManage.service.MemorandumService;

@Controller
@RequestMapping("/memorandum")
public class MemorandumController {

	@Autowired
	private MemorandumService memorandumService;

	@RequestMapping("/addMemorandum.action")
	// @ResponseBody
	public String addMemorandum(HttpServletRequest request, String editvalue)
			throws IOException {//��ӱ���

		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			return "/index.jsp";// ���µ�¼
		}
		int uid = user.getUid();
		// �༭����Ϊ��ʱ���ſ����ϴ�
		// System.out.println("editvalue:"+editvalue);//���ı�����
		String topFont = null;
		if (editvalue.length() > 80) {
			topFont = editvalue.substring(0, 80);// ǰ83���ַ�������
													// --��ǰ82���ַ�������--��ǰ80���ַ�������
		} else {
			topFont = editvalue;
		}
		// System.out.println("topFont:"+topFont);
		// �õ��༭��������
		String content = request.getParameter("editorValue");// ���и�ʽ������
		// ��ǰʱ�� formatDate
		Date dt = new Date();
		String recordTime = null;
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd"); // HH��ʾ24Сʱ�ƣ�
		recordTime = dFormat.format(dt);

		// ���༭��������д���ļ����� �洢·��Ϊ��
		String realPath = "d:/upload"; // d�̵�upload��
		// ����Ψһ���ļ���
		String filename = generateUUIDName();
		// ��������ļ���
		String savePath = generateSavePath(realPath, filename);

		// ���յ��ļ�·���� d:/upload/ 1/2 / xxx.txt
		String thingPath = savePath + filename + ".txt";

		// �����ļ�����
		File fileText = new File(thingPath);// �����ļ�
		FileWriter fileWriter = new FileWriter(fileText);// ���ļ�д�����д����Ϣ
		fileWriter.write(content);// ���ļ���д��String�ַ���������
		fileWriter.close();// �ر�

		// ����
		// System.out.println("content:"+content);//���и�ʽ������
		// System.out.println("topFont:"+topFont);//���µ�ͷ80���ַ�
		// System.out.println("recordTime:"+recordTime);
		// System.out.println("thingPath:"+thingPath);

		Memorandum me = new Memorandum();// ����
		me.setRecordTime(recordTime);
		me.setThingPath(thingPath);
		me.setTopFont(topFont);
		me.setUser_id(uid);

		memorandumService.addMemorandum(me);// ��ӱ���

		// request.setAttribute("content", content);//��ʾ����
		// request.setAttribute("editvalue", editvalue);

		// return "ok";
		return "/memorandum/listMemorandum.action";// ��ѯҳ��
	}

	@RequestMapping("/listMemorandum.action")
	public String listMemorandum(HttpServletRequest request,Integer currentPage, Model model) {// ��ʾ���б���¼[��ҳ��ѯ]

		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			return "/index.jsp";// ���µ�¼
		}
		int uid = user.getUid();
		
		PageBean<Memorandum> pageBean = memorandumService.listMemorandum(uid,currentPage);// ��ʾ���б���¼[��ҳ��ѯ]
		if(pageBean.getPageList().size()==0){//ȷ��Ϊ��
			pageBean.setPageList(null);
		}
		model.addAttribute("pageBean", pageBean);//��ҳ��ѯ���
		
//		model.addAttribute("memorandumlist", memorandumlist);

		return "/jsp/memorandum/memorandum.jsp";
	}

	// ȥ�༭ҳ��
	@RequestMapping("/oneMemorandum.action")
	public String oneMemorandum(HttpServletRequest request,int mid, Model model,Integer currentPage) {// ��ǰ����¼
																			// -->ȥ�༭ҳ��

		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			return "/index.jsp";// ���µ�¼
		}
		int uid = user.getUid();
		
		Memorandum me=new Memorandum();//��ѯ����
		me.setUser_id(uid);
		me.setMid(mid);
		
		// ����¼��Ϣ
		Memorandum memorandum = memorandumService.oneMemorandum(me);// ��ǰ����¼

		// ���ݱ���¼·������ȡ����¼�ļ����ݣ���ʾ��ҳ����
		String thingPath = memorandum.getThingPath();// ����¼·��
		// ��ȡ�ļ����ݣ�д��String��
		int len = 0;
		StringBuffer str = new StringBuffer("");
		File file = new File(thingPath);
		try {
			FileInputStream is = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader in = new BufferedReader(isr);
			String line = null;
			while ((line = in.readLine()) != null)
			{
				if (len != 0) // �����з�������
				{
//					str.append("\r\n" + line);
					str.append(line);
				}
				else
				{
					str.append(line);
				}
				len++;
			}
			in.close();
			is.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		String content=str.toString();//����
//		System.out.println("content:"+content);
		
		model.addAttribute("content",content);
		model.addAttribute("memorandum",memorandum);//��Ϣ
		model.addAttribute("currentPage",currentPage);//��ס��ǰҳ
		
		return "/jsp/memorandum/editmemorandum.jsp";// ���༭ҳ��
	}

	// �༭����¼
	@RequestMapping("/editMemorandum.action")
	public String editMemorandum(HttpServletRequest request,Memorandum memorandum,String editvalue, Model model,Integer currentPage) throws IOException {
		// ��Ҫ�޸�    ��1���ļ������ݣ�����ԭ�����ļ�     ��2���ļ���ͷ80���ַ�
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			return "/index.jsp";// ���µ�¼
		}
		int uid = user.getUid();
		
		// �༭����Ϊ��ʱ���ſ����ϴ�
		String topFont = null;
		if (editvalue.length() > 80) {
			topFont = editvalue.substring(0, 80);// ǰ83���ַ�������    // --��ǰ82���ַ�������--��ǰ80���ַ�������
		} else {
			topFont = editvalue;
		}
		
		// �õ��༭��������
		String content = request.getParameter("editorValue");// ���и�ʽ������
		//д�ļ������Ǹ�·��
		String thingPath = memorandum.getThingPath();//���༭��������д��ԭ���ļ��У�����ԭ�����ļ�
		//��д�ļ�------------------------------------------------------------------------
		// �����ļ�����
		File fileText = new File(thingPath);// �����ļ�
		FileWriter fileWriter = new FileWriter(fileText);// ���ļ�д�����д����Ϣ
		fileWriter.write(content);// ���ļ���д��String�ַ���������
		fileWriter.close();// �ر�
		//�޸�-----------------------------------------------------------------------------
		memorandum.setTopFont(topFont);//�޸ĵ�����  �ַ�
		memorandum.setUser_id(uid);
		//��������
		memorandumService.editMemorandum(memorandum);// �༭����¼
		
		return "/memorandum/listMemorandum.action?currentPage="+currentPage;// ��ѯҳ��
	}

	// ɾ������¼
	@RequestMapping("/deleteMemorandum.action")
//	@ResponseBody
	public String deleteMemorandum(HttpServletRequest request,int mid,Integer currentPage) {
		memorandumService.deleteMemorandum(mid);// ɾ������¼
		
		
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			return "/index.jsp";// ���µ�¼
		}
		int uid = user.getUid();
		
		//��ѯ���ݿ⣬�����ֶ���Ҳ���ݣ��������һҳ������ʾ��һҳ������
		int pageRecord = 6;// ÿҳ��¼��
		
		int allPage = 0;// ��ҳ��
		int allRecord = memorandumService.findMemorandumCount(uid);// ��ѯ����¼�ܼ�¼��
		if (allRecord % pageRecord == 0) {
			allPage = allRecord / pageRecord;
		} else {
			allPage = allRecord / pageRecord + 1;
		}
		
		//��ǰҳ  vs  ��ҳ��
		if(currentPage>allPage-1){
			currentPage=currentPage-1;
		}
//		return "ok";
		return "/memorandum/listMemorandum.action?currentPage="+currentPage;// ��ѯҳ��
	}

	// ����Ψһ���ļ���
	private static String generateUUIDName() {
		return UUID.randomUUID().toString();
	}

	// ��������ļ���
	private static String generateSavePath(String realPath, String filename) {

		int hashCode = filename.hashCode();
		// ͨ��λ���㣬�����һ���Ͷ���Ŀ¼������
		int first = hashCode & (0xf);// �Լ�Ŀ¼
		int second = (hashCode >> 4) & (0xf);// ����Ŀ¼
		String savePath = realPath + "/" + first + "/" + second + "/";
		File f = new File(savePath);
		if (!f.exists()) {
			f.mkdirs();// �����༶Ŀ¼
		}
		return savePath;// ����·��
	}

}

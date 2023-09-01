package cn.zhku.jsj144.zk.financialManage.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

import cn.zhku.jsj144.zk.financialManage.pojo.News;
import cn.zhku.jsj144.zk.financialManage.pojo.PageBean;
import cn.zhku.jsj144.zk.financialManage.pojo.News;
import cn.zhku.jsj144.zk.financialManage.pojo.User;
import cn.zhku.jsj144.zk.financialManage.service.NewsService;
@Controller
@RequestMapping("/newsManage")
public class NewsManageController { 

	@Autowired
	private NewsService newsService;
	
	//�����б�
	@RequestMapping("/findNewsList.action")
	public String findNewsList(News news,Integer currentPage,Model model){//��������ҳ��ѯ+��������ҳ��ѯ
		//��ѯ���������±���+��������+���¹ؼ���    +  ��ǰҳ 
		//��ǰҳ��Ĭ������£�currentPage=0   �ύ�����ǵ�1ҳ  
		
		model.addAttribute("findNews", news);
		
		PageBean<News> pageBean=newsService.findNewsList(news,currentPage);//��ҳ��ѯ�����б�
		if(pageBean.getPageList().size()==0){//ȷ��Ϊ��
			pageBean.setPageList(null);
		}
		model.addAttribute("pageBean", pageBean);//��ҳ��ѯ���
		
		return "/admin/newsManage.jsp";//��֧������
	}
	
	//�������
	@RequestMapping("/addNews.action")
	public String addNews(News news, MultipartFile file,String editvalue,HttpServletRequest request) throws IllegalStateException, IOException{//�ϴ��ļ�
		
		//�ж��߼�����ʽ�����ļ�����Ϊ�գ����Է�ʽ����ʽ�����ϴ��ļ��������Է�ʽһ����ʽ�����ϴ��ļ�
		
		String nContent=null;//�ϴ����ļ�·��
		
		String realPath = "d:/upload/news"; // d�̵�upload�µ�news��
		String uuidName = generateUUIDName();// ����Ψһ���ļ���
		String savePath = generateSavePath(realPath, uuidName);// ��������ļ���  --d:/upload/news/1/2/
//		System.out.println(file==null);//false
		//��ʽ����
		if(file.getOriginalFilename()!=""&&file.getOriginalFilename()!=null){//�ϴ��ļ�����Ϊ��
			System.out.println("��ʽ�����ϴ��ļ�");
			String oriName = file.getOriginalFilename();// ��ȡ�ļ���(xxx.xxx)
			String extName = oriName.substring(oriName.lastIndexOf("."));// ��ȡ�ļ���׺
			nContent=savePath + uuidName + extName;//�ϴ����ļ���
			file.transferTo(new File(nContent));//springmvc�ϴ��ļ�
		}
		else{
			System.out.println("��ʽһ���ϴ��ļ�");
			// �õ��༭��������
			//��ʽһ��
			String content = request.getParameter("editorValue");// �������༭������ݡ����и�ʽ�����ݡ�    
			// ���յ��ļ�·���� d:/upload/news/1/2/ xxx.txt
			nContent = savePath + uuidName + ".txt";
			// �����ļ�����
			File fileText = new File(nContent);// �����ļ�
			FileWriter fileWriter = new FileWriter(fileText);// ���ļ�д�����д����Ϣ
			fileWriter.write(content);// ���ļ���д��String�ַ���������
			fileWriter.close();// �ر�
		}
	
		//�������
		news.setnContent(nContent);//�����ļ�·��
		news.setVisitCount(0);//���÷��ʴ���
		newsService.addNews(news);//�������
		
		return "/newsManage/findNewsList.action";//�ϴ��ɹ�   �б�ҳ��
	}
	
	
	//�༭����ҳ��
	@RequestMapping("/toEditPage.action")
	public String toEditPage(Integer nid,Model model,Integer currentPage){
		News news=newsService.queryNewsById(nid);//ͨ��id��ѯ��ǰ������Ϣ
		
		// ��������·������ȡ�����ļ����ݣ���ʾ��ҳ����
		String thingPath = news.getnContent();//��ȡ����·��
		
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
		System.out.println("content:"+content);
		
		model.addAttribute("content",content);
		model.addAttribute("news", news);//��Ϣ
		model.addAttribute("currentPage", currentPage);//���浱ǰҳ��
		return "/admin/news/editnews.jsp";// ���༭ҳ��
	}
	
	//�༭������Ϣ
	@RequestMapping("/editNews.action")      //  �޸��ļ����ݣ�����������������
	public String editNews(News news,String editvalue,HttpServletRequest request,Integer currentPage2) throws IOException{
		
		System.out.println("�޸�-------------------------");
		System.out.println(news.getAuthor()+"::"+news.getKeyword()+":::"+news.getnTitle()+":::"+news.getRecordTime());
		System.out.println("·��:"+news.getnContent());
		
		// �õ��༭��������
		String content = request.getParameter("editorValue");// ���и�ʽ������
		System.out.println("�༭�����ݣ�--"+editvalue);
		//д�ļ������Ǹ�·��
		String thingPath =news.getnContent();//���༭��������д��ԭ���ļ��У�����ԭ�����ļ�
		//��д�ļ�
		File fileText = new File(thingPath);// �����ļ�
		FileWriter fileWriter = new FileWriter(fileText);// ���ļ�д�����д����Ϣ
		fileWriter.write(content);// ���ļ���д��String�ַ���������
		fileWriter.close();// �ر�
		
		//�༭������Ϣ
		newsService.editNews(news); 
		return "redirect:/newsManage/findNewsList.action?currentPage="+currentPage2;//��ǰҳ���ڷ�ҳ��ʱ������˱���
	}	
	
	//ɾ��������Ϣ
	@RequestMapping("/deleteNews.action")
	public String deleteNews(int  nid,Integer currentPage2){
		newsService.deleteNews(nid);//ɾ��������Ϣ
		
		//�����ǰҳ��ֻ��һ����¼��ɾ����Ӧ�÷�����һҳ
		//ÿҳ��¼����10������ѯ���ŵ��ܼ�¼��
		int pageRecord=10;
		int count=newsService.countNews();//��ѯ��ǰ�������ż�¼��
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
//		"/newsManage/findNewsList.action
		return "redirect:/newsManage/findNewsList.action?currentPage="+currentPage2;//��ǰҳ���ڷ�ҳ��ʱ������˱���
	}
	
	
	//��������ҳ
	
	
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

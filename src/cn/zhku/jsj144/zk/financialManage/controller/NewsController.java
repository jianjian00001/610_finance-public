package cn.zhku.jsj144.zk.financialManage.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.zhku.jsj144.zk.financialManage.pojo.News;
import cn.zhku.jsj144.zk.financialManage.pojo.PageBean;
import cn.zhku.jsj144.zk.financialManage.service.NewsService;

@Controller
@RequestMapping("/news")
public class NewsController {

	@Autowired
	private NewsService newsService;
	
	//ͨ��id����ѯ����������Ϣ
	@RequestMapping("/news.action")
	public String toEditPage(Integer nid,Model model){
		News news=newsService.queryNewsById(nid);//ͨ��id��ѯ��ǰ������Ϣ
		
		// ��������·������ȡ�����ļ����ݣ���ʾ��ҳ����
		String thingPath = news.getnContent();//��ȡ����·��
		
		// ��ȡ�ļ����ݣ�д��String��
		int len = 0;
		StringBuffer str = new StringBuffer("");
		File file = new File(thingPath);
		try {
			FileInputStream is = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(is,"GBK");
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
		System.out.println("content:"+content);//��������
		
		model.addAttribute("content",content);
		model.addAttribute("news", news);//��Ϣ
		return "/jsp/news.jsp";// ���༭ҳ��
	}
	
	

	//�����б�
	@RequestMapping("/findNewsList.action")
	public String findNewsList(News news,Integer currentPage,Model model){//��������ҳ��ѯ+��������ҳ��ѯ
		//��ѯ���������±���+��������+���¹ؼ���     +  ��ǰҳ 
		//��ǰҳ��Ĭ������£�currentPage=0   �ύ�����ǵ�1ҳ  
		
		model.addAttribute("findNews", news);
		
		PageBean<News> pageBean=newsService.findNewsList2(news,currentPage);//��ҳ��ѯ�����б�
		if(pageBean.getPageList().size()==0){//ȷ��Ϊ��
			pageBean.setPageList(null);
		}
		model.addAttribute("pageBean", pageBean);//��ҳ��ѯ���
		
		return "/jsp/newsList.jsp";//��֧������
	}
}

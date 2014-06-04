package com.nian.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import cn.rainier.nian.utils.PageRainier;
import com.brightengold.util.Tools;
import com.nian.ftl.FreemarkerUtil;
import com.nian.model.News;
import com.nian.service.NewsService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Component
@Scope("prototype")
public class NewsAction extends ActionSupport implements ModelDriven<News>{
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1601202245876329643L;
	private News model = new News();
	private PageRainier<News> page;
	private Integer pageSize = 10;
	private Integer pageNo = 1;
	@Autowired
	private NewsService newsService;
	
	public String list(){
		page = newsService.list(pageNo, pageSize);
		return "list";
	}
	
	public String listSpeci(){
		page = newsService.listSpeci(pageNo,pageSize);
		return "listSpeci";
	}
	
	public String detail(){
		if(model.getId()!=null){
			model = newsService.getNews(model.getId());
		}
		return "detail";
	}
	
	public void publish(){	//发布切记标志下news已发布！
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = null;
		try{
		if(model.getId()!=null){
			Map<String,Object> root = new HashMap<String,Object>();
			model = newsService.getNews(model.getId());
			root.put("ctx", request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath());
			long count = newsService.countSpici();	//已发布的总数量
			int totalPageNum = Math.max(1, (int) Math.ceil(1.0 * count/pageSize));	//已发布的列表总页数
			String path = request.getSession().getServletContext().getRealPath("/");
			String parentPath = path + File.separator+"html"+File.separator+"news"+File.separator;
			String pPath = parentPath;
			long si = count-(totalPageNum-1)*pageSize;	//判断这一页是否结束
			int total = totalPageNum;	//默认在本页
			if(count>0){	//已经有发布的记录
				if(model.getPublishDate()==null){	//此新闻没有发布
					if(si>=pageSize){	//下一页
						total = totalPageNum + 1;
					}
					//parentPath = parentPath+total+File.separator;	//生成页码的页面
				}else{	//对于已发布的新闻，找到在第几页
					total = model.getPageNum();
				}
			}else{	//没有发布的记录
				total = 1;
			}
			model.setPublishDate(new Date());
			model.setHasPublish(true);
			root.put("model", model);
			model.setPageNum(total);
			parentPath = parentPath+total+File.separator;	//找到第几页
			FreemarkerUtil.fprint("detail.ftl", root, parentPath,model.getUrl());
			page = newsService.listSpeci(1, pageSize);	//已发布的新闻
			root.put("newsPage", page);
			FreemarkerUtil.fprint("list.ftl", root, pPath,1+".html");
			root.clear();
			newsService.save(model);
		}
		writer = response.getWriter();
		writer.write(1+"");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(writer!=null){
				writer.close();
			}
		}
	}
	
	public void batchPublish(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = null;
		String ids = request.getParameter("ids");
		String[] idArr = null;
		if(ids.length()>0){
			idArr = ids.split(",");
		}
		try{
		if(idArr.length>0){
			long count = newsService.countSpici();	//已发布的总数量
			int totalPageNum = Math.max(1, (int) Math.ceil(1.0 * count/pageSize));	//已发布的列表总页数
			Map<String,Object> root = new HashMap<String,Object>();
			String path = request.getSession().getServletContext().getRealPath("/");
			String parentPath = path + File.separator+"html"+File.separator+"news"+File.separator;
			String pPath = parentPath;
			if(count>0){
				long si = count-(totalPageNum-1)*pageSize;	//判断这一页是否结束
				int total = totalPageNum;	//默认在本页
				long condition = 0;
				for(int i=0;i<idArr.length;i++){
					root.put("ctx", request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath());
					model = newsService.getNews(Integer.parseInt(idArr[i]));
					if(model.getPublishDate()==null){	//对于未发布的新闻
						if(si<pageSize){	//＜pageSize则在totalPageNum页
							if(i<(pageSize-si)){	//在本页
								condition = pageSize-si-1;
							}else{	//在下一页  //生成页码的页面
								total = totalPageNum+1;
								condition = idArr.length-1;
							}
						}else{	//在下一页开始 
							condition = idArr.length-1;
							total = totalPageNum+1;
						}
					}else{	//对于已发布的新闻，需要找到发布到的页码，然后更新
						total = model.getPageNum();
						condition = 0;
					}
					model.setPublishDate(new Date());
					model.setHasPublish(true);
					root.put("model", model);
					model.setPageNum(total);
					parentPath = parentPath+total+File.separator;
					FreemarkerUtil.fprint("detail.ftl", root, parentPath,model.getUrl());
					parentPath = path + File.separator+"html"+File.separator+"news"+File.separator;
					if(i>=condition){
						page = newsService.listSpeci(total,pageSize);	//得到发布的新闻
						root.put("newsPage", page);
						FreemarkerUtil.fprint("list.ftl", root, pPath,total+".html");
						root.clear();
					}
					newsService.save(model);
				}
			}else{	//没有发布的记录（首次发布）
				for(int i=0;i<idArr.length;i++){
					model = newsService.getNews(Integer.parseInt(idArr[i]));
					model.setPublishDate(new Date());
					model.setHasPublish(true);
					root.put("model", model);
					parentPath = parentPath+1+File.separator;
					FreemarkerUtil.fprint("detail.ftl", root, parentPath,model.getUrl());
					parentPath = path + File.separator+"html"+File.separator+"news"+File.separator;
					if(i>=idArr.length-1){
						page = newsService.listSpeci(1, pageSize);	//得到发布的新闻
						root.put("newsPage", page);
						FreemarkerUtil.fprint("list.ftl", root, pPath,1+".html");
						root.clear();
					}
					model.setPageNum(1);
					newsService.save(model);
				}
			}
		}
			writer = response.getWriter();
			writer.write(1+"");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(writer!=null){
				writer.close();
			}
		}
		
	}
	
	public void publishAll(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = null;
		int retuenFlag = -1;
		int totalPageNum = Math.max(1, (int) Math.ceil(1.0 * newsService.count()/this.pageSize));
		try{
			writer = response.getWriter();
			String path = request.getSession().getServletContext().getRealPath("/");
			String parentPath = "";
			Map<String,Object> root = new HashMap<String,Object>();
			List<News> newsList = null;
			List<News> tempNewsList = new ArrayList<News>();
			String fPath = null;
			for(int i=0;i<totalPageNum;i++){
				page = newsService.list((i+1), pageSize);	//得到所有的新闻
				root.put("newsPage", page);
				parentPath = path + File.separator+"html"+File.separator+"news"+File.separator;
				//FreemarkerUtil.print("list.ftl", root);
				FreemarkerUtil.fprint("list.ftl", root, parentPath,(i+1)+".html");
				root.clear();
				newsList = page.getResult();
				for(News news:newsList){
					root.put("ctx", request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath());
					if(news.getPublishDate()!=null){
						fPath = path +File.separator+"html"+File.separator+"news"+
								File.separator+news.getPageNum()+File.separator+news.getUrl();
						Tools.delFile(fPath);
					}
					news.setPublishDate(new Date());
					news.setHasPublish(true);
					root.put("model", news);
					parentPath = parentPath+(i+1)+File.separator;
					news.setPageNum(i+1);
					FreemarkerUtil.fprint("detail.ftl", root, parentPath,news.getUrl());
					parentPath = path + File.separator+"html"+File.separator+"news"+File.separator;
					tempNewsList.add(news);
				}
			}
			newsService.batchInsert(tempNewsList);
			retuenFlag=1;
			writer.write(retuenFlag+"");
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(writer!=null){
				writer.close();
			}
		}
	}
	
	public News getModel() {
		return model;
	}
	public PageRainier<News> getPage() {
		return page;
	}
	public void setPage(PageRainier<News> page) {
		this.page = page;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
}

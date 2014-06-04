package com.nian.service;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import cn.rainier.nian.utils.PageRainier;

import com.nian.dao.NewsDao;
import com.nian.model.News;

@Component
public class NewsService {
	@Autowired
	private NewsDao newsDao;
	
	public PageRainier<News> list(Integer pageNo,Integer pageSize){
		Page<News> tempPage = newsDao.findAll(new PageRequest(pageNo-1, pageSize, new Sort(Direction.DESC,"id")));
		PageRainier<News> newsPage = new PageRainier<News>(tempPage.getTotalElements(),pageNo,pageSize);
		newsPage.setResult(tempPage.getContent());
		return newsPage;
	}

	public News getNews(Integer id) {
		return newsDao.findOne(id);
	}
	
	/**
	 * 发布的数量
	 * @return
	 */
	public long countSpici() {
		return newsDao.count(getPublishSpeci());
	}
	
	private Specification<News> getPublishSpeci(){
		return new Specification<News>() {
			public Predicate toPredicate(Root<News> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.isNotNull(root.<Date>get("publishDate"));
			}
		};
	}
	
	private Specification<News> getPublishByHasSpeci(){
		return new Specification<News>() {
			public Predicate toPredicate(Root<News> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.isTrue(root.<Boolean>get("hasPublish"));
			}
		};
	}
	
	/**
	 * 批量插入或者更新
	 */
	public void batchInsert(Iterable<News> news){
		newsDao.save(news);
	}
	
	/**
	 * 批量删除
	 * @param news
	 */
	public void batchDel(Iterable<News> news){
		newsDao.deleteInBatch(news);
	}

	public News save(News model) {
		return newsDao.save(model);
	}

	public PageRainier<News> listSpeci(Integer pageNo, Integer pageSize) {
		Page<News> tempPage = newsDao.findAll(getPublishSpeci(),new PageRequest(pageNo-1, pageSize, new Sort(Direction.DESC,"id")));
		PageRainier<News> newsPage = new PageRainier<News>(tempPage.getTotalElements(),pageNo,pageSize);
		newsPage.setResult(tempPage.getContent());
		return newsPage;
	}

	public long count() {
		return newsDao.count();
	}

}

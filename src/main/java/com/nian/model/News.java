package com.nian.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
public class News implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	/**
	 * 新闻标题
	 */
	private String title;
	/**
	 * 新闻摘要
	 */
	private String introduce;
	/**
	 * 新闻内容
	 */
	private String content;
	/**
	 * 新闻优先值
	 */
	private Integer priority;
	/**
	 * 发布后的页码
	 */
	private Integer pageNum;
	/**
	 * 新闻url,静态页面发布以后才有
	 */
	private String url;
	/**
	 * 新闻创建日期
	 */
	private Date createDate;
	/**
	 * 新闻发布日期
	 */
	private Date publishDate;
	/**
	 * 是否发布
	 */
	private boolean hasPublish;
	/**
	 * 点击率
	 */
	private Integer clicks;
	/**
	 * 关键字
	 */
	private String keyWords;
	/**
	 * 备用字段
	 */
	private String temp1;
	private String temp2;
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(length=100)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	@Column(length=100)
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTemp1() {
		return temp1;
	}
	public void setTemp1(String temp1) {
		this.temp1 = temp1;
	}
	public String getTemp2() {
		return temp2;
	}
	public void setTemp2(String temp2) {
		this.temp2 = temp2;
	}
	@Temporal(TemporalType.DATE)
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Temporal(TemporalType.DATE)
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public Integer getClicks() {
		return clicks;
	}
	public void setClicks(Integer clicks) {
		this.clicks = clicks;
	}
	public String getKeyWords() {
		return keyWords;
	}
	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public boolean isHasPublish() {
		return hasPublish;
	}
	public void setHasPublish(boolean hasPublish) {
		this.hasPublish = hasPublish;
	}
	
}

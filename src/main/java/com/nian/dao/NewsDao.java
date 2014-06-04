package com.nian.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.nian.model.News;

public interface NewsDao extends JpaRepository <News, Integer>,JpaSpecificationExecutor<News>{
}

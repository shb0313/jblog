package com.douzone.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BlogVo;

@Repository
public class BlogRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public void createBlog(String id) {
		
		sqlSession.insert("blog.createBlog", id);
	}

	public BlogVo findBlogById(String id) {
		
		return sqlSession.selectOne("blog.findBlogById", id);
		
	}

	public void update(BlogVo vo) {
		
		sqlSession.update("blog.update", vo);
		
	}

}

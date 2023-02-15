package com.douzone.jblog.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.PostVo;

@Repository
public class PostRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public void insert(PostVo vo) {
		
		sqlSession.insert("post.insert", vo);
	}

	public List<PostVo> findPostByCatNo(Long categoryNo) {
		
		return sqlSession.selectList("post.findPostByCatNo", categoryNo);
	}

	public PostVo findPostByNo(Long postNo) {
		
		return sqlSession.selectOne("post.findPostById", postNo);
	}

	public PostVo findPostByCatNoAndPostNo(Map<String, Object> map) {
		
		return sqlSession.selectOne("post.findPostByCatNoAndPostNo", map);
	}

	

}

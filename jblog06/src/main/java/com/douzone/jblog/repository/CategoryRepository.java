package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {

	@Autowired
	private SqlSession sqlSession;

	public void createCategory(String id) {

		sqlSession.insert("category.createCategory", id);
	}

	public List<CategoryVo> findCategoryById(String id) {

		return sqlSession.selectList("category.findCategoryById", id);
	}

	public void insert(CategoryVo vo) {
		
		sqlSession.insert("category.insert", vo);		
	}

	public void delete(CategoryVo vo) {
		
		sqlSession.delete("category.delete", vo);
	}

}

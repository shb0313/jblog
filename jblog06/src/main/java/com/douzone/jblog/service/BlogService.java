package com.douzone.jblog.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.repository.PostRepository;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Service
public class BlogService {

	@Autowired
	private BlogRepository blogRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	public void defaultBlogAndCategory(String id) {
		
		blogRepository.createBlog(id);
		categoryRepository.createCategory(id);
	}

	public BlogVo getBlog(String id) {

		return blogRepository.findBlogById(id);
		
	}

	public void updateBlog(BlogVo vo) {
		
		blogRepository.update(vo);
	}

	public List<CategoryVo> getCategoryList(String id) {
				
		return categoryRepository.findCategoryById(id);
	}

	public void addCategory(CategoryVo vo) {
		
		categoryRepository.insert(vo);	
	}

	public void deleteCategory(String id, Long no) {
		
		CategoryVo vo = new CategoryVo();
		vo.setId(id);
		vo.setNo(no);
		
		categoryRepository.delete(vo);	
	}

	public void addPost(PostVo vo) {
		
		postRepository.insert(vo);		
	}

	public List<PostVo> getPostList(Long categoryNo) {
		
		return postRepository.findPostByCatNo(categoryNo);
	}

	public PostVo getPost(String id, Long categoryNo, Long postNo) {
		
		Map<String, Object> map = Map.of("id", id, "categoryNo", categoryNo, "no", postNo);
		return postRepository.findPostByCatNoAndPostNo(map);
	}



}

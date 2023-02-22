package com.douzone.jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.security.Auth;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.FileuploadService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {

	@Autowired
	private BlogService blogService;

	@Autowired
	private FileuploadService fileuploadService;

	@RequestMapping("/create")
	public String joinCreate(@PathVariable String id) {

		blogService.defaultBlogAndCategory(id);

		return "redirect:/user/joinsuccess";
	}

	@RequestMapping({ "", "/{cNo}", "/{cNo}/{pNo}" })
	public String index(@PathVariable("id") String id, @PathVariable("cNo") Optional<Long> catNo,
			@PathVariable("pNo") Optional<Long> posNo, Model model) {

		Long categoryNo = 0L;
		Long postNo = 0L;

		BlogVo blogVo = blogService.getBlog(id);
		model.addAttribute("blogVo", blogVo);

		List<CategoryVo> listCategory = blogService.getCategoryList(id);
		model.addAttribute("listCategory", listCategory);

		List<PostVo> listPost = null;
		PostVo postVo = null;

		if (posNo.isPresent()) {

			categoryNo = catNo.get();
			postNo = posNo.get();
			postVo = blogService.getPost(id, categoryNo, postNo);

			listPost = blogService.getPostList(categoryNo);

		} else if (catNo.isPresent()) {

			categoryNo = catNo.get();
			postVo = blogService.getPost(id, categoryNo, postNo);
			listPost = blogService.getPostList(categoryNo);

		} else {

			postVo = blogService.getPost(id, categoryNo, postNo);
			listPost = blogService.getPostList(listCategory.get(0).getNo());
		}

		model.addAttribute("postVo", postVo);
		model.addAttribute("listPost", listPost);
		return "/blog/main";
	}

	@Auth(role="ADMIN")
	@RequestMapping("/admin/basic")
	public String adminblog(@PathVariable String id, Model model) {

		BlogVo vo = blogService.getBlog(id);
		model.addAttribute(vo);
		model.addAttribute("menu", "admin");

		return "/blog/admin-basic";
	}

	@Auth(role="ADMIN")
	@RequestMapping("blog/update")
	public String blogUpdate(@PathVariable String id, MultipartFile file, BlogVo vo) {
		
		String profile = fileuploadService.restore(file);

		if (profile != null) {
			vo.setProfile(profile);
		}

		blogService.updateBlog(vo);

		return "redirect:/" + id + "/admin/basic";
	}

	@Auth(role="ADMIN")
	@RequestMapping("/admin/category")
	public String adminCategory(@PathVariable String id, Model model) {

		BlogVo blogVo = blogService.getBlog(id);
		model.addAttribute("blogVo", blogVo);
		
		List<CategoryVo> list = blogService.getCategoryList(id);
		model.addAttribute("list", list);
		model.addAttribute("menu", "category");

		return "/blog/admin-category";
	}

	@Auth(role="ADMIN")
	@RequestMapping("/category/delete/{no}")
	public String deleteCategory(@PathVariable String id, @PathVariable Long no) {

		blogService.deleteCategory(id, no);

		return "redirect:/{id}/admin/category";
	}

	@Auth(role="ADMIN")
	@RequestMapping("/category/add")
	public String addCategory(@PathVariable String id, CategoryVo vo) {

		blogService.addCategory(vo);

		return "redirect:/{id}/admin/category";
	}

	@Auth(role="ADMIN")
	@RequestMapping("/admin/write")
	public String adminPost(@PathVariable String id, Model model) {

		BlogVo blogVo = blogService.getBlog(id);
		model.addAttribute("blogVo", blogVo);
				
		List<CategoryVo> list = blogService.getCategoryList(id);
		model.addAttribute("list", list);
		model.addAttribute("menu", "post");

		return "/blog/admin-write";
	}

	@Auth(role="ADMIN")
	@RequestMapping("/post/add")
	public String addPost(@PathVariable String id, PostVo vo) {

		System.out.println("Con addPost vo : " + vo);

		blogService.addPost(vo);

		return "redirect:/{id}/admin/write";
	}
}

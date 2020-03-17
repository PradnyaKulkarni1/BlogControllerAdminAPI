package com.blog.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.model.UserPostsData;
import com.blog.serviceimpl.BlogServiceImpl;

/**
 * Controller class to route the requests to appropriate service class/method
 *
 */
@RestController
public class BlogController {

	@Autowired
	private BlogServiceImpl service;

	@GetMapping(path = "/allusersandblogs")
	public List<UserPostsData> getAllUsersAndBlogs() {
		// Retrieve all users and their posts
		return service.getAllUsersNPosts();
	}

	@GetMapping(path = "/blogsforuser")
	public UserPostsData getUserBlogs(@RequestParam Long userId) {
		// REtrieve all posts of given user

		return service.getPostsForUser(userId);
	}

}

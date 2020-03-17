package com.blog.service;

import java.util.List;

import com.blog.model.Post;
import com.blog.model.User;
import com.blog.model.UserPostsData;

public interface BlogService {

	public List<UserPostsData> getAllUsersNPosts();

	public UserPostsData getPostsForUser(Long userId);

	public List<User> getAllUsers();

	public List<Post> getAllPosts();

	public User getUserDetails(Long userId);
}

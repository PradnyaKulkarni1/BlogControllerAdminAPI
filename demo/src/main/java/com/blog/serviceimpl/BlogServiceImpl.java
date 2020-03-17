package com.blog.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.blog.model.Post;
import com.blog.model.User;
import com.blog.model.UserPostsData;
import com.blog.service.BlogService;

/**
 * ServiceImpl class to get the data from REST APIs and provide data to
 * controller
 *
 */

@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${user.rest.api.path}")
	private String usersApiPath;

	@Value("${post.rest.api.path}")
	private String postsApiPath;

	/*
	 * Get all posts for all users
	 */
	public List<UserPostsData> getAllUsersNPosts() {
		List<UserPostsData> userPostsData = new ArrayList<UserPostsData>();
		List<User> users = getAllUsers();
		List<Post> posts = getAllPosts();

		users.forEach(user -> userPostsData.add(new UserPostsData(user,
				posts.stream().filter(post -> post.getUserId().equals(user.getId())).collect(Collectors.toList()))));

		return userPostsData;
	}

	/*
	 * Get all posts for given user
	 */
	public UserPostsData getPostsForUser(Long userId) {
		UserPostsData userPostsData = new UserPostsData();
		List<Post> posts = getAllPosts();

		User blogUser = getUserDetails(userId);
		userPostsData.setUserDetails(blogUser);
		userPostsData.setPostList(
				posts.stream().filter(post -> post.getUserId().equals(userId)).collect(Collectors.toList()));

		return userPostsData;
	}

	/*
	 * Get list of all users from Users API
	 */
	public List<User> getAllUsers() {
		ResponseEntity<List<User>> userResponse = restTemplate.exchange(usersApiPath, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<User>>() {
				});
		List<User> usersList = userResponse.getBody();
		return usersList;
	}

	/*
	 * Get list of all posts from Posts API
	 */
	public List<Post> getAllPosts() {
		ResponseEntity<List<Post>> postResponse = restTemplate.exchange(postsApiPath, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Post>>() {
				});
		List<Post> postList = postResponse.getBody();
		return postList;
	}

	/*
	 * Get user details based on UserId
	 */
	public User getUserDetails(Long userId) {
		User blogUser = new User();
		ResponseEntity<List<User>> userResponse = restTemplate.exchange(usersApiPath, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<User>>() {
				});
		List<User> userList = userResponse.getBody();
		userList.stream().filter(user -> (user.getId().equals(userId)));

		blogUser.setId(userId);
		blogUser.setEmail(userList.get(0).getEmail());
		blogUser.setName(userList.get(0).getName());
		blogUser.setPhone(userList.get(0).getPhone());
		blogUser.setUserName(userList.get(0).getUserName());
		blogUser.setWebsite(userList.get(0).getWebsite());
		blogUser.setAddress(userList.get(0).getAddress());
		blogUser.setCompany(userList.get(0).getCompany());

		return blogUser;
	}
}

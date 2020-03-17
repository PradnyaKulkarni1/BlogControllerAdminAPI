package com.blog.model;

import java.util.List;

public class UserPostsData {

	private User userDetails;
	private List<Post> postList;

	public UserPostsData() {

	}

	public UserPostsData(User userDetails, List<Post> postList) {
		this.userDetails = userDetails;
		this.postList = postList;
	}

	public User getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(User userDetails) {
		this.userDetails = userDetails;
	}

	public List<Post> getPostList() {
		return postList;
	}

	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}

	@Override
	public String toString() {
		return "User Posts Data == User Details - " + userDetails + ", Posts = {" + postList.toString() + "}";
	}

}

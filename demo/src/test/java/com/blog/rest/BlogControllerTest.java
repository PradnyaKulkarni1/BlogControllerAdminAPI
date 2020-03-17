package com.blog.rest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.blog.model.Address;
import com.blog.model.Company;
import com.blog.model.Post;
import com.blog.model.User;
import com.blog.model.UserPostsData;
import com.blog.rest.BlogController;
import com.blog.serviceimpl.BlogServiceImpl;

import org.junit.Assert;

@RunWith(MockitoJUnitRunner.class)
public class BlogControllerTest {

	@Mock
	private BlogServiceImpl service;

	@InjectMocks
	private BlogController controller;

	@Test
	public void test_getAllUsersAndBlogs_Success() {
		List<UserPostsData> userPostsDataActual = new ArrayList<UserPostsData>();
		User user1 = new User(new Long(1), "TestName1", "TestUserName1", "TestEmail1", new Address(), "TestPhone1",
				"TestWebsite1", new Company());
		User user2 = new User(new Long(2), "TestName2", "TestUserName2", "TestEmail2", new Address(), "TestPhone2",
				"TestWebsite2", new Company());
		User user3 = new User(new Long(3), "TestName3", "TestUserName3", "TestEmail3", new Address(), "TestPhone3",
				"TestWebsite3", new Company());

		Post post1 = new Post(new Long(1), new Long(1), "TestTitle1", "TestBody1");
		Post post2 = new Post(new Long(2), new Long(1), "TestTitle2", "TestBody2");
		Post post3 = new Post(new Long(3), new Long(2), "TestTitle3", "TestBody3");
		Post post4 = new Post(new Long(4), new Long(3), "TestTitle4", "TestBody4");

		List<Post> pstsList1 = new ArrayList<Post>();
		pstsList1.add(post1);
		pstsList1.add(post2);

		List<Post> pstsList2 = new ArrayList<Post>();
		pstsList2.add(post3);

		List<Post> pstsList3 = new ArrayList<Post>();
		pstsList3.add(post4);

		userPostsDataActual.add(new UserPostsData(user1, pstsList1));
		userPostsDataActual.add(new UserPostsData(user2, pstsList2));
		userPostsDataActual.add(new UserPostsData(user3, pstsList3));

		Mockito.when(service.getAllUsersNPosts()).thenReturn(userPostsDataActual);

		List<UserPostsData> userPostsDataExpected = controller.getAllUsersAndBlogs();

		Assert.assertEquals(userPostsDataExpected, userPostsDataActual);
	}

	@Test
	public void test_getUserBlogs() {
		Long userId = new Long(1);
		UserPostsData userPostsDataActual = new UserPostsData();
		User user1 = new User(new Long(1), "TestName1", "TestUserName1", "TestEmail1", new Address(), "TestPhone1",
				"TestWebsite1", new Company());

		Post post1 = new Post(new Long(1), new Long(1), "TestTitle1", "TestBody1");
		Post post2 = new Post(new Long(2), new Long(1), "TestTitle2", "TestBody2");
		List<Post> pstsList1 = new ArrayList<Post>();
		pstsList1.add(post1);
		pstsList1.add(post2);

		userPostsDataActual.setUserDetails(user1);
		userPostsDataActual.setPostList(pstsList1);

		Mockito.when(service.getPostsForUser(userId)).thenReturn(userPostsDataActual);

		UserPostsData userPostsDataExpected = controller.getUserBlogs(userId);
		Assert.assertEquals(userPostsDataExpected, userPostsDataActual);
	}
}

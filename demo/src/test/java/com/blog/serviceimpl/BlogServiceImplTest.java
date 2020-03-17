package com.blog.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.blog.model.Address;
import com.blog.model.Company;
import com.blog.model.Post;
import com.blog.model.User;

@RunWith(MockitoJUnitRunner.class)
public class BlogServiceImplTest {

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private BlogServiceImpl testService;

	@Value("${user.rest.api.path}")
	private String usersApiPath;

	@Value("${post.rest.api.path}")
	private String postsApiPath;

	@Test
	public void test_getAllUsers_Success() {

		List<User> usersActual = new ArrayList<User>();
		usersActual.add(new User(new Long(1), "TestName1", "TestUserName1", "TestEmail1", new Address(), "TestPhone1",
				"TestWebsite1", new Company()));
		usersActual.add(new User(new Long(2), "TestName2", "TestUserName2", "TestEmail2", new Address(), "TestPhone2",
				"TestWebsite2", new Company()));
		usersActual.add(new User(new Long(3), "TestName3", "TestUserName3", "TestEmail3", new Address(), "TestPhone3",
				"TestWebsite3", new Company()));

		ResponseEntity<List<User>> myEntity = new ResponseEntity<List<User>>(usersActual, HttpStatus.ACCEPTED);

		Mockito.when(restTemplate.exchange(ArgumentMatchers.eq(usersApiPath), ArgumentMatchers.eq(HttpMethod.GET),
				ArgumentMatchers.<HttpEntity<List<User>>>any(),
				ArgumentMatchers.<ParameterizedTypeReference<List<User>>>any())).thenReturn(myEntity);

		List<User> usersExpected = testService.getAllUsers();
		Assert.assertEquals(usersExpected, usersActual);
	}

	@Test
	public void test_getAllUsers_Failure() {

		List<User> usersActual = null;

		ResponseEntity<List<User>> myEntity = new ResponseEntity<List<User>>(usersActual, HttpStatus.NOT_FOUND);

		Mockito.when(restTemplate.exchange(ArgumentMatchers.eq(usersApiPath), ArgumentMatchers.eq(HttpMethod.GET),
				ArgumentMatchers.<HttpEntity<List<User>>>any(),
				ArgumentMatchers.<ParameterizedTypeReference<List<User>>>any())).thenReturn(myEntity);

		List<User> usersExpected = testService.getAllUsers();
		Assert.assertEquals(usersExpected, usersActual);
	}

	@Test
	public void test_getAllPosts_Success() {

		List<Post> postsActual = new ArrayList<Post>();
		postsActual.add(new Post(new Long(1), new Long(1), "TestTitle1", "TestBody1"));
		postsActual.add(new Post(new Long(2), new Long(1), "TestTitle2", "TestBody2"));
		postsActual.add(new Post(new Long(3), new Long(2), "TestTitle3", "TestBody3"));
		postsActual.add(new Post(new Long(4), new Long(3), "TestTitle4", "TestBody4"));

		ResponseEntity<List<Post>> myEntity = new ResponseEntity<List<Post>>(postsActual, HttpStatus.OK);

		Mockito.when(restTemplate.exchange(ArgumentMatchers.eq(postsApiPath), ArgumentMatchers.eq(HttpMethod.GET),
				ArgumentMatchers.<HttpEntity<List<Post>>>any(),
				ArgumentMatchers.<ParameterizedTypeReference<List<Post>>>any())).thenReturn(myEntity);

		List<Post> postsExpected = testService.getAllPosts();
		Assert.assertEquals(postsExpected, postsActual);
	}

	@Test
	public void test_getAllPosts_Failure() {

		List<Post> postsActual = null;

		ResponseEntity<List<Post>> myEntity = new ResponseEntity<List<Post>>(postsActual, HttpStatus.NOT_FOUND);

		Mockito.when(restTemplate.exchange(ArgumentMatchers.eq(postsApiPath), ArgumentMatchers.eq(HttpMethod.GET),
				ArgumentMatchers.<HttpEntity<List<Post>>>any(),
				ArgumentMatchers.<ParameterizedTypeReference<List<Post>>>any())).thenReturn(myEntity);

		List<Post> postsExpected = testService.getAllPosts();
		Assert.assertEquals(postsExpected, postsActual);
	}
}

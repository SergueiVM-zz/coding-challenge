package com.example.demo.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserStoryRepository;
import com.example.demo.exception.ItemNotFoundException;
import com.example.demo.model.UserStory;

@Service
public class UserStoryService {

	@Autowired
	UserStoryRepository userStoryRepository;

	public List<UserStory> fetchUserStories() {
		// TODO Auto-generated method stub
		return userStoryRepository.findAll();
	}

	public UserStory createUserStory(UserStory userStory) {
		return userStoryRepository.save(userStory);
	}

	public void deleteUserStory(int userStoryId) {
		Optional<UserStory> story = userStoryRepository.findById(userStoryId);
		try {
			if(story.get()!=null) {
				userStoryRepository.deleteById(userStoryId);
			}
		} catch (NoSuchElementException e) {
			throw new ItemNotFoundException("UserStory doesn't exist");
		}

	}

}

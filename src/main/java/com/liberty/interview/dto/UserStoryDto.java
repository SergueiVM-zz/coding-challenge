package com.liberty.interview.dto;

import javax.validation.constraints.NotEmpty;

import com.liberty.interview.util.Status;

import lombok.Data;

@Data
public class UserStoryDto implements Dto {

	@NotEmpty
	private String idUserStory;

	@NotEmpty
	private String description;

	private Status status;

}

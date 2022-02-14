package com.liberty.interview.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class SessionDto implements Dto {

	@NotEmpty
	private String idSession;

	private String title;

}

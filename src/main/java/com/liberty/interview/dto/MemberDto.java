package com.liberty.interview.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class MemberDto implements Dto {

	@NotEmpty
	private String idMember;

	@NotEmpty
	private String name;

}

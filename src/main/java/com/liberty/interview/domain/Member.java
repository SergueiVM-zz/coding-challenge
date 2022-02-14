package com.liberty.interview.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
public class Member {

	@Id
	private String id;

	@NotEmpty
	private String name;

	@ManyToOne
	@JoinColumn(name = "session_id", nullable = false, updatable = false)
	private Session session;

	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Vote> votes = new ArrayList<>();

}

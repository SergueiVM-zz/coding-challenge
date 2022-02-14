package com.liberty.interview.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liberty.interview.dto.MemberDto;
import com.liberty.interview.service.MemberService;

@RestController
@RequestMapping("/sessions")
public class MemberController {

	@Autowired
	private MemberService memberService;

	@GetMapping("/{idSession}/members")
	public ResponseEntity<List<MemberDto>> findMembersFromSession(@PathVariable("idSession") String idSession) {
		List<MemberDto> members = memberService.findMembersFromSession(idSession);
		return ResponseEntity.status(members.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK).body(members);
	}

	@PostMapping("/{idSession}/members")
	public ResponseEntity<MemberDto> joinSession(@PathVariable("idSession") String idSession,
			@RequestBody MemberDto memberDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(memberService.joinSession(idSession, memberDto));
	}

	@GetMapping("/{idSession}/members/{idMember}")
	public ResponseEntity<MemberDto> logout(@PathVariable("idSession") String idSession,
			@PathVariable("idMember") String idMember) {
		Optional<MemberDto> memberDto = memberService.logout(idSession, idMember);
		return memberDto.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(memberDto.get());
	}

}

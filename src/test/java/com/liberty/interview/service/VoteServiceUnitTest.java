package com.liberty.interview.service;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.Random;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.liberty.interview.domain.Member;
import com.liberty.interview.domain.Session;
import com.liberty.interview.domain.UserStory;
import com.liberty.interview.domain.Vote;
import com.liberty.interview.dto.VoteDto;
import com.liberty.interview.mapper.VoteMapper;
import com.liberty.interview.repository.VoteRepository;

@ExtendWith(MockitoExtension.class)
class VoteServiceUnitTest {

	@Mock
	private SessionService sessionService;

	@Mock
	private MemberService memberService;

	@Mock
	private UserStoryService userStoryService;

	@Mock
	private VoteRepository voteRepository;

	@Mock
	private VoteMapper voteMapper;

	@InjectMocks
	private VoteServiceImpl voteService;

	@Test
	@DisplayName("Register vote")
	void registerVoteTest() {

		Session validSession = SessionMother.validSession();
		Member validMember = MemberMother.validMember();
		UserStory validUserStory = UserStoryMother.validUserStory(validSession);
		Vote validVote = VoteMother.validVote(validMember, validUserStory);

		VoteDto voteDto = new VoteDto();
		voteDto.setIdMember(validVote.getMember().getId());
		voteDto.setIdUserStory(validVote.getUserStory().getId());
		voteDto.setValue("nice user story");

		given(voteMapper.dtoToModel(voteDto)).willReturn(validVote);

		given(memberService.findMemberAsEntity(validSession.getId(), voteDto.getIdMember())).willReturn(validMember);

		given(userStoryService.findUserStoryAsEntity(validSession.getId(), voteDto.getIdUserStory()))
				.willReturn(validUserStory);

		given(voteRepository.save(validVote)).willReturn(validVote);

		given(voteMapper.modelToDto(validVote)).willReturn(voteDto);

		voteService.registerVote(validSession.getId(), voteDto);

		verify(voteMapper).dtoToModel(voteDto);

		verify(memberService).findMemberAsEntity(validSession.getId(), voteDto.getIdMember());

		verify(userStoryService).findUserStoryAsEntity(validSession.getId(), voteDto.getIdUserStory());

		verify(voteRepository).save(validVote);

		verify(voteMapper).modelToDto(validVote);

	}

	static class SessionMother {
		static Session validSession() {
			Session session = new Session();
			session.setId(UUID.randomUUID().toString());
			session.setTitle("title_" + new Random().nextInt());
			return session;
		}
	}

	static class MemberMother {
		static Member validMember() {
			Member member = new Member();
			member.setId(UUID.randomUUID().toString());
			member.setName("name_" + new Random().nextInt());
			return member;
		}
	}

	static class UserStoryMother {
		static UserStory validUserStory(Session session) {
			UserStory userStory = new UserStory();
			userStory.setSession(session);
			userStory.setId(UUID.randomUUID().toString());
			userStory.setDescription("description_" + new Random().nextInt());
			return userStory;
		}
	}

	static class VoteMother {
		static Vote validVote(Member member, UserStory userStory) {
			Vote vote = new Vote();
			vote.setId(new Random().nextInt());
			vote.setMember(member);
			vote.setUserStory(userStory);
			return vote;

		}
	}

}

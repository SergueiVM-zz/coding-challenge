package com.example.demo.service;

import com.example.demo.dto.MemberDto;
import com.example.demo.dto.UserStoryStatus;
import com.example.demo.dto.MemberDtoRequest;
import com.example.demo.dto.UserStoryDtoRequest;
import com.example.demo.dto.UserStoryDto;
import com.example.demo.dto.PokerPlanningSessionDto;
import com.example.demo.dto.PokerSessionRequestDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.exceptions.PlanningPokerException;
import com.example.demo.model.Memebers;
import com.example.demo.model.PokerBoard;
import com.example.demo.model.UserStory;
import com.example.demo.repository.MembersRepository;
import com.example.demo.repository.PokerBoardRepository;
import com.example.demo.repository.UserStoryRepository;
import com.example.demo.repository.VotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PokerBoardServiceImpl implements PokerBoardService {

	@Autowired
	PokerBoardRepository pokerBoardRepository;
	@Autowired
	MembersRepository membersRepository;
	@Autowired
	UserStoryRepository userStoryRepository;
	@Autowired
	VotesRepository votesRepository;

	@Override
	public PokerPlanningSessionDto createPokerSession(PokerSessionRequestDto pokerSessionRequestDto) {

		Optional<String> titleOptional = Optional.of(pokerSessionRequestDto.getTitle());

		if (!titleOptional.isPresent()) {

			throw new PlanningPokerException("title should mandetory");

		} else {
			String uuid = UUID.randomUUID().toString();

			PokerBoard pokerBoard = new PokerBoard();
			pokerBoard.setIdSession(uuid);
			pokerBoard.setTitle(titleOptional.get());
			pokerBoardRepository.save(pokerBoard);

			PokerPlanningSessionDto pokerPlanningSessionDto = new PokerPlanningSessionDto();
			pokerPlanningSessionDto.setIdSession(pokerBoard.getIdSession());
			pokerPlanningSessionDto.setTitle(pokerBoard.getTitle());
			return pokerPlanningSessionDto;
		}

	}

	@Override
	public PokerPlanningSessionDto getPokerSession(String idSession) {

		Optional<PokerBoard> pokerBoard = pokerBoardRepository.findById(idSession);
		if (!pokerBoard.isPresent()) {
			throw new PlanningPokerException("invalid session");
		} else {

			PokerPlanningSessionDto pokerPlanningSessionDto = new PokerPlanningSessionDto();
			pokerPlanningSessionDto.setIdSession(pokerBoard.get().getIdSession());
			pokerPlanningSessionDto.setTitle(pokerBoard.get().getTitle());

			return pokerPlanningSessionDto;
		}
	}

	@Override
	public MemberDto addMember(MemberDtoRequest memberDtoRequest) {
		Memebers memebers = new Memebers();
		memebers.setIdSession(memberDtoRequest.getIdSession());
		memebers.setName(memberDtoRequest.getName());

		membersRepository.save(memebers);

		MemberDto memberDto = new MemberDto();
		memberDto.setName(memebers.getName());
		memberDto.setIdMemeber(memebers.getIdMemeber());

		return memberDto;
	}

	@Override
	public List<MemberDto> getSessionMembers(String idSession) {

		Optional<List<Memebers>> memebersOptional = membersRepository.findByIdSession(idSession);

		List<MemberDto> memberDtoList = new ArrayList<>();
		if (memebersOptional.isPresent()) {
			memebersOptional.get().stream().forEach(memebers -> {
				MemberDto memberDto = new MemberDto();
				memberDto.setName(memebers.getName());
				memberDto.setIdMemeber(memebers.getIdMemeber());
				memberDtoList.add(memberDto);

			});
		}

		return memberDtoList;
	}

	@Override
	public UserStoryDto addUserStory(UserStoryDtoRequest userStoryDtoRequest) {

		UserStory userStory = new UserStory();
		userStory.setDescriptions(userStoryDtoRequest.getDescriptions());
		userStory.setIdSession(userStoryDtoRequest.getIdSession());
		userStory.setStatus(UserStoryStatus.PENDING.name());

		userStoryRepository.save(userStory);

		UserStoryDto userStoryDto = new UserStoryDto();
		userStoryDto.setIdUserStory(userStory.getIdUserStory());
		userStoryDto.setStatus(userStory.getStatus());
		userStoryDto.setDescriptions(userStory.getDescriptions());
		return userStoryDto;
	}

	@Override
	public List<UserStoryDto> getSessionUserStories(String idSession) {

		Optional<List<UserStory>> userStoriesOptional = userStoryRepository.findByIdSession(idSession);

		List<UserStoryDto> userStoryDtoList = new ArrayList<>();
		if (userStoriesOptional.isPresent()) {
			userStoriesOptional.get().stream().forEach(userStory -> {
				UserStoryDto userStoryDto = new UserStoryDto();
				userStoryDto.setIdUserStory(userStory.getIdUserStory());
				userStoryDto.setStatus(userStory.getStatus());
				userStoryDto.setDescriptions(userStory.getDescriptions());

				userStoryDtoList.add(userStoryDto);
			});
		}

		return userStoryDtoList;
	}

	@Override
	public List<PokerPlanningSessionDto> getAllPokerSession() {
		List<PokerBoard> pokerBoardList = pokerBoardRepository.findAll();

		List<PokerPlanningSessionDto> pokerPlanningSessionDtos = new ArrayList<>();
		pokerBoardList.stream().forEach(pokerBoard -> {

			PokerPlanningSessionDto pokerPlanningSessionDto = new PokerPlanningSessionDto();
			pokerPlanningSessionDto.setIdSession(pokerBoard.getIdSession());
			pokerPlanningSessionDto.setTitle(pokerBoard.getTitle());

			pokerPlanningSessionDtos.add(pokerPlanningSessionDto);

		});

		return pokerPlanningSessionDtos;
	}

	@Transactional
	@Override
	public ResponseDto deleteSession(String idSession) {

		membersRepository.deleteByIdSession(idSession);
		pokerBoardRepository.deleteById(idSession);

		ResponseDto response = new ResponseDto();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("session destroyed successfully");
		return response;
	}

	@Override
	public ResponseDto startOrStopVotesOfUserStory(String idSession, Integer userStory, String status) {

		Optional<UserStory> userStoryOptional = userStoryRepository.findById(userStory);

		ResponseDto responseDto = new ResponseDto();
		if (userStoryOptional.isPresent()) {
			try {
				userStoryOptional.get().setStatus(UserStoryStatus.valueOf(status.toUpperCase()).name());
				userStoryRepository.save(userStoryOptional.get());
			} catch (Exception e) {
				responseDto.setStatusCode(HttpStatus.NO_CONTENT.value());
				responseDto.setMessage("invalid status");
			}

			responseDto.setStatusCode(HttpStatus.OK.value());
			responseDto.setMessage("status updated sucessfully");

		} else {

			responseDto.setStatusCode(HttpStatus.OK.value());
			responseDto.setMessage("no user story present");
		}

		return responseDto;
	}

	@Transactional
	@Override
	public ResponseDto deleteUserStory(Integer userStory) {

		userStoryRepository.deleteById(userStory);

		votesRepository.deleteByStoryId(userStory);
		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatusCode(HttpStatus.NO_CONTENT.value());
		responseDto.setMessage("userStory deleted successfully");
		return responseDto;
	}
}

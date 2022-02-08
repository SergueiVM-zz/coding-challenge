package com.example.demo.service;

import com.example.demo.dto.AddVoteDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.UserStoryStatus;
import com.example.demo.dto.VotesGetByStory;
import com.example.demo.model.Memebers;
import com.example.demo.model.PokerBoard;
import com.example.demo.model.UserStory;
import com.example.demo.model.Votes;
import com.example.demo.repository.MembersRepository;
import com.example.demo.repository.PokerBoardRepository;
import com.example.demo.repository.UserStoryRepository;
import com.example.demo.repository.VotesRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VotingManagementImpl implements VotingManagement {
	@Autowired
	VotesRepository votesRepository;
	@Autowired
	UserStoryRepository userStoryRepository;

	@Autowired
	PokerBoardRepository pokerBoardRepository;
	@Autowired
	MembersRepository membersRepository;

	@Transactional
	@Override
	public ResponseDto addVoteToUserStory(String idSession, AddVoteDto addVoteDto) {
		/// {idSession}/votes:
		Optional<UserStory> userStory = userStoryRepository.findById(addVoteDto.getStoryId());

		Optional<PokerBoard> pokerBoard = pokerBoardRepository.findById(idSession);
		Optional<List<Memebers>> members = membersRepository.findByIdSession(idSession);

		Optional<Votes> votesforCheck = votesRepository.findByStoryIdAndMemberId(addVoteDto.getStoryId(),
				addVoteDto.getMemberId());

		boolean membertatus = false;
		if (members.isPresent()) {
			List<Memebers> memberVote = members.get().stream()
					.filter(member -> member.getIdMemeber() == addVoteDto.getMemberId()).collect(Collectors.toList());
			if (memberVote.size() > 0) {
				membertatus = true;
			}
		}

		if (!(userStory.isPresent() && pokerBoard.isPresent() && membertatus)) {
			ResponseDto responseDto = new ResponseDto();

			responseDto.setMessage("invalid data");
			responseDto.setStatusCode(HttpStatus.OK.value());
			return responseDto;
		} else if (!userStory.get().getStatus().equalsIgnoreCase(UserStoryStatus.VOTING.name())) {
			ResponseDto responseDto = new ResponseDto();

			responseDto.setMessage("story is not in voting stage");
			responseDto.setStatusCode(HttpStatus.OK.value());
			return responseDto;
		} else if (votesforCheck.isPresent()) {
			ResponseDto responseDto = new ResponseDto();

			responseDto.setMessage("story is already voted");
			responseDto.setStatusCode(HttpStatus.OK.value());
			return responseDto;

		} else {
			Votes votes = new Votes();
			votes.setStoryId(addVoteDto.getStoryId());
			votes.setMemberId(addVoteDto.getMemberId());
			votes.setValue(addVoteDto.getValue());

			votesRepository.save(votes);

			ResponseDto responseDto = new ResponseDto();

			responseDto.setMessage("vote added successfully");
			responseDto.setStatusCode(HttpStatus.OK.value());
			return responseDto;
		}

	}

	@Override
	public List<VotesGetByStory> getVotesOfUserStory(String idSession, Integer storyId) {

		Optional<PokerBoard> pokerBoard = pokerBoardRepository.findById(idSession);
		Optional<UserStory> userStory = userStoryRepository.findById(storyId);

		List<VotesGetByStory> votesGetByStoryList = new ArrayList<>();

		if (userStory.isPresent() && pokerBoard.isPresent()) {

			Optional<List<Votes>> votesListOptional = votesRepository.findByStoryId(storyId);

			if (votesListOptional.isPresent()) {

				List<Votes> votesList = votesListOptional.get();
				votesList.forEach(votes -> {
					VotesGetByStory votesGetByStory = new VotesGetByStory();
					BeanUtils.copyProperties(votes, votesGetByStory);
					votesGetByStoryList.add(votesGetByStory);
				});
			}

		}
		return votesGetByStoryList;
	}

}

package com.cdac.osvs.service.imple;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.osvs.dto.Election;
import com.cdac.osvs.repo.ElectionRepo;
import com.cdac.osvs.service.ElectionService;



@Service
public class ElectionServiceImple  implements ElectionService{

	@Autowired
	private ElectionRepo electionRepo;
	
	@Override
	public List<Election> selectAllElection() {
		List<Election> list= electionRepo.findAll();
		return list;
	}

	@Override
	public Election selectById(int id) {
		Optional<Election> opt= electionRepo.findById(id);
		
		return opt.get();
	}

	@Override
	public void deleteById(int id) {
		electionRepo.deleteById(id);
		
		
	}

	@Override
	public void insertElection(Election election) {
		electionRepo.save(election);
		
	}

	
	@Override
	public String update(Election election) {
		
	Optional<Election> pt=	electionRepo.findById(election.getElectionId());
	
	if(pt.isPresent()) {
		electionRepo.save(election);
	    return "Election is updated";
	}else {
		 return "Election is not found";
	}
		
	}

	@Override
	public String addingVoterToElection(int voterId, int electionId) {
		return null;
	}

	@Override
	public String addingCandidateToElection(int candidateId, int electionId) {
		return null;
	}


}

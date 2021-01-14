package com.cdac.osvs.service.imple;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.osvs.dto.Voter;
import com.cdac.osvs.repo.VoterRepo;
import com.cdac.osvs.service.VoterService;



@Service
public class VoterServiceImple  implements VoterService{
    
	@Autowired
	private VoterRepo VoterRepo;
	
	@Override
	public void insertVoter(Voter voter) {
		VoterRepo.save(voter);
	}

	@Override
	public List<Voter> selectAllVoter() {
		List<Voter> list=VoterRepo.findAll();
		return list;
	}

	@Override
	public Voter selectById(int id) {
		Optional<Voter> opt=VoterRepo.findById(id);
		
		return opt.get();
	}

	@Override
	public void deleteById(int id) {
		VoterRepo.deleteById(id);
		
	}

 
	@Override
	public String update(Voter voter) {
		
	Optional<Voter> pt=	VoterRepo.findById(voter.getVoterId());
	
	if(pt.isPresent()) {
		VoterRepo.save(voter);
	    return "Voter is updated";
	}else {
		 return "Voter is not found";
	}
		
	}

	
}

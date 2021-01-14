package com.cdac.osvs.service.imple;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.osvs.dto.Admin;
import com.cdac.osvs.dto.Candidate;
import com.cdac.osvs.repo.CandidateRepo;
import com.cdac.osvs.service.CandidateService;



@Service
public class CandidateServiceImple implements CandidateService {

	@Autowired
	private CandidateRepo CandidateRepo;
	
	@Override
	public List<Candidate> selectAllCandidate() {
		List<Candidate> list=CandidateRepo.findAll();
		return list;
	}

	@Override
	public Candidate selectById(int id) {
		Optional<Candidate> opt=CandidateRepo.findById(id);
		
		return opt.get();
	}

	@Override
	public void deleteById(int id) {
		CandidateRepo.deleteById(id);
		
	}

	@Override
	public void insertCandidate(Candidate candidate) {
		CandidateRepo.save(candidate);
		
	}

	
	@Override
	public String update(Candidate candidate)  {
		
	Optional<Candidate> pt=	CandidateRepo.findById(candidate.getCandidateId());
	
	if(pt.isPresent()) {
		CandidateRepo.save(candidate);
	    return "Candidate is updated";
	}else {
		 return "Candidate is not found";
	}
		
	}

}

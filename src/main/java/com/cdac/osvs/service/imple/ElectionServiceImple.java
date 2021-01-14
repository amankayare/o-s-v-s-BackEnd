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
	private ElectionRepo EletionRepo;
	
	@Override
	public List<Election> selectAllElection() {
		List<Election> list=EletionRepo.findAll();
		return list;
	}

	@Override
	public Election selectById(int id) {
		Optional<Election> opt=EletionRepo.findById(id);
		
		return opt.get();
	}

	@Override
	public void deleteById(int id) {
		EletionRepo.deleteById(id);
		
		
	}

	@Override
	public void insertElection(Election election) {
		EletionRepo.save(election);
		
	}

	
	@Override
	public String update(Election election) {
		
	Optional<Election> pt=	EletionRepo.findById(election.getElectionId());
	
	if(pt.isPresent()) {
		EletionRepo.save(election);
	    return "Election is updated";
	}else {
		 return "Election is not found";
	}
		
	}

	
}

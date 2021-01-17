package com.cdac.osvs.service.imple;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.cdac.osvs.dto.Security;
import com.cdac.osvs.dto.Voter;
import com.cdac.osvs.service.SecurityService;
import com.cdac.osvs.util.RandomUtil;
import com.cdac.osvs.util.images.SplitImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.osvs.dto.Election;
import com.cdac.osvs.repo.ElectionRepo;
import com.cdac.osvs.service.ElectionService;



@Service
public class ElectionServiceImple  implements ElectionService{

	@Autowired
	private ElectionRepo electionRepo;

	@Autowired
	private SecurityService securityService;


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


		Set<Voter> voterList = election.getVoterList();

		for (Voter voter :voterList) {
			int voterId = voter.getVoterId();
			String randomKey = RandomUtil.generatingRandomAlphanumericFileName();

			String randomImageName = RandomUtil.generatingRandomAlphanumericFileName();
			File  file= RandomUtil.generateRamdomImage(randomImageName);
			byte[] originalfile = new byte[(int) file.length()];


			ArrayList<File> splitedFiles = SplitImage.breakImage(file);
			byte[] databaseShare = new byte[(int) splitedFiles.get(0).length()];

			Security security = new Security();
			security.setOrignalImg(originalfile);
			security.setShareoneImg(databaseShare);
			security.setKeyValue(randomKey);
			security.setVoterId(voterId);

			File emailShare = splitedFiles.get(1);


			securityService.insertSecurity(security);





		}

		
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




}

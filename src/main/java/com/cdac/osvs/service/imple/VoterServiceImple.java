package com.cdac.osvs.service.imple;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

import com.cdac.osvs.util.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.osvs.dto.Voter;
import com.cdac.osvs.repo.VoterRepo;
import com.cdac.osvs.service.VoterService;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;


@Service
public class VoterServiceImple  implements VoterService{
    
	@Autowired
	private VoterRepo VoterRepo;
	@Autowired
	private  EmailService emailService;
	
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

	@Override
	public void readFileAndSendEmail(File excelFile) {

		try {
			// Creating a Workbook from an Excel file (.xls or .xlsx)
			Workbook workbook = WorkbookFactory.create(excelFile);

			// Getting the Sheet at index zero
			Sheet sheet = workbook.getSheetAt(0);

			// Create a DataFormatter to format and get each cell's value as String
			DataFormatter dataFormatter = new DataFormatter();

			Iterator<Row> rowIterator = sheet.rowIterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				// Now let's iterate over the columns of the current row
				Iterator<Cell> cellIterator = row.cellIterator();

				String name = "";
				int count = 0;
				while (cellIterator.hasNext()) {
					count++;
					Cell cell = cellIterator.next();

					if(count == 2)
						name = dataFormatter.formatCellValue(cell);


					if(count == 3) {
						String email = dataFormatter.formatCellValue(cell);

						try {
							emailService.sendMessageForVoterRegister(email,name);
						} catch (Exception e) {
							e.printStackTrace();
						}
						// Send mail for voter registration

					}
				}
				System.out.println();
			}
		} catch (Exception exception){
			exception.printStackTrace();
		}


		}

		//Iterating over Rows and Columns using for-each loop



	}

/*
* EXCEL Sheet format template
*
* 1st column-> EmployeeId
* 2nd Column-> Name
* 3rd Column-> email
*
*
*
* */


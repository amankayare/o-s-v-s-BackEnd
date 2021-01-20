package com.cdac.osvs.service.imple;

import java.io.File;
import java.util.List;
import java.util.Optional;

import com.cdac.osvs.util.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.osvs.dto.Voter;
import com.cdac.osvs.repo.VoterRepo;
import com.cdac.osvs.service.VoterService;


import org.apache.poi.ss.usermodel.*;

import java.util.Iterator;


@Service
public class VoterServiceImple implements VoterService {

    @Autowired
    private VoterRepo voterRepo;
    @Autowired
    private EmailService emailService;

    @Override
    public Boolean insertVoter(Voter voter) {

        Voter voterIsalreadyPresent = voterRepo.voterIsAlreadyRegistered(voter.getAdharNo(), voter.getEmail());

        if (voterIsalreadyPresent == null) {
            voterRepo.save(voter);
            return true;
        }
        return false;

    }

    @Override
    public List<Voter> selectAllVoter() {
        List<Voter> list = voterRepo.findAll();
        return list;
    }

    @Override
    public Voter selectById(int id) {
    	
        return voterRepo.getOne(id);
    }

    @Override
    public void deleteById(int id) {
        voterRepo.deleteById(id);

    }


    @Override
    public String update(Voter voter) {

        Optional<Voter> pt = voterRepo.findById(voter.getVoterId());

        if (pt.isPresent()) {
            voterRepo.save(voter);
            return "Voter is updated";
        } else {
            return "Voter is not found";
        }

    }

    @Override
    public void readFileAndSendEmail(File excelFile) {

        try {
            // Creating a Workbook from an Excel file (.xls or .xlsx)
            Workbook workbook = WorkbookFactory.create(excelFile);

            // Getting the Sheet at index zero
            Sheet sheet1 = workbook.getSheetAt(0);//voter

            Sheet sheet2 = workbook.getSheetAt(1);//candidate


            ///voter file reading

            // Create a DataFormatter to format and get each cell's value as String
            DataFormatter dataFormatter = new DataFormatter();

            Iterator<Row> rowIterator1 = sheet1.rowIterator();
            while (rowIterator1.hasNext()) {
                Row row = rowIterator1.next();

                // Now let's iterate over the columns of the current row
                Iterator<Cell> cellIterator = row.cellIterator();

                String name = "";
                int count = 0;
                while (cellIterator.hasNext()) {
                    count++;
                    Cell cell = cellIterator.next();

                    if (count == 2)
                        name = dataFormatter.formatCellValue(cell);


                    if (count == 3) {
                        String email = dataFormatter.formatCellValue(cell);

                        try {
                            emailService.sendMessageForVoterRegister(email, name);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        // Send mail for voter registration

                    }
                }
                System.out.println();
            }

            ///Candidate file reading


            Iterator<Row> rowIterator2 = sheet2.rowIterator();
            while (rowIterator2.hasNext()) {
                Row row = rowIterator2.next();

                // Now let's iterate over the columns of the current row
                Iterator<Cell> cellIterator = row.cellIterator();

                String name = "";
                int count = 0;
                while (cellIterator.hasNext()) {
                    count++;
                    Cell cell = cellIterator.next();

                    if (count == 2)
                        name = dataFormatter.formatCellValue(cell);


                    if (count == 3) {
                        String email = dataFormatter.formatCellValue(cell);

                        try {
                            emailService.sendMessageForCandidateRegister(email, name);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        // Send mail for voter registration

                    }
                }
                System.out.println();
            }


        } catch (Exception exception) {
            exception.printStackTrace();
        }


    }

    @Override
    public Voter checkLoginStatus(long adharNo, String password) {
        return voterRepo.voterIsPresent(adharNo, password);
    }


    //Iterating over Rows and Columns using for-each loop
}

/*
 * EXCEL Sheet format template
 *
 * 1st column-> EmployeeId
 * 2nd Column-> Name
 * 3rd Column-> email
 * 4th Column -> AdharCard
 *
 * */


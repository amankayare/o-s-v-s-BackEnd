package com.cdac.osvs.service.imple;

import com.cdac.osvs.Exception.OrganizationException;
import com.cdac.osvs.dto.Organization;
import com.cdac.osvs.repo.OrganizationRepo;
import com.cdac.osvs.repo.VoterRepo;
import com.cdac.osvs.service.OrganizationService;
import com.cdac.osvs.util.email.EmailService;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class OrganizationServiceImple implements OrganizationService {

    @Autowired
    OrganizationRepo organizationRepo;

    private EmailService emailService;
    @Override
    public Boolean insertOrganization(Organization org) {
        Organization organization = null;
        organization = organizationRepo.getAlreadyExistOrganization(org.getCin());

        if (organization == null) {
            organizationRepo.save(org);
            return true;
        } else {
            System.out.println("Else .." + organization);
            org.setId(organization.getId());
            updateOrganization(org);
            return false;
        }
        //  organizationRepo.save(org);

    }

    @Override
    public Organization updateOrganization(Organization org) {
        Optional<Organization> opt = organizationRepo.findById(org.getId());
        if (opt.isPresent()) {
            organizationRepo.save(org);
            return opt.get();

        }else{
            return null;
        }

    }

    @Override
    public Boolean deleteOrganizationByCinNo(String cinNo) {

        try {
            organizationRepo.deleteExistOrganization(cinNo);
            return true;

        }catch (Exception exception){
            return false;
        }
    }

    @Override
    public Organization getOrganizationById(int id) {
        return organizationRepo.getOne(id);
    }

    @Override
    public Organization getOrganizationByCinNo(String cinNo) {
        return organizationRepo.getAlreadyExistOrganization(cinNo);
    }

    @Override
    public List<Organization> getAllOrganization() {
        return organizationRepo.findAll();
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
                            throw new OrganizationException("Invalid Excel Data or file Formate ");
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
}

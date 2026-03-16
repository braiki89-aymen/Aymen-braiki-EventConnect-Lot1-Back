package tn.esprit.tic.se.spr01.UserManagement.Services;

import tn.esprit.tic.se.spr01.UserManagement.DTO.MedicalStaffDto;
import tn.esprit.tic.se.spr01.UserManagement.Entities.MedicalStaff;

public interface iMedicalStaffService {


    MedicalStaff addMedicalStaff(MedicalStaff medicalStaff);
    MedicalStaff getMedicalStaffById(Long id);
    MedicalStaff updateMedicalStaff(Long id, MedicalStaff medicalStaff);
    void deleteMedicalStaff(Long id);
}

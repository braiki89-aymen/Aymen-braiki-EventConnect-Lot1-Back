package tn.esprit.tic.se.spr01.UserManagement.Services;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tic.se.spr01.UserManagement.DTO.MedicalStaffDto;
import tn.esprit.tic.se.spr01.UserManagement.DTO.UserDto;
import tn.esprit.tic.se.spr01.UserManagement.Entities.MedicalStaff;
import tn.esprit.tic.se.spr01.UserManagement.Entities.User;
import tn.esprit.tic.se.spr01.UserManagement.Repositories.MedicalStaffRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class MedicalStaffService implements iMedicalStaffService{

    @Autowired
    MedicalStaffRepository medicalStaffRepository;

    // Create
    @Override
    public MedicalStaff addMedicalStaff(MedicalStaff medicalStaff) {
        return medicalStaffRepository.save(medicalStaff);
    }

    // Read
    @Override
    public MedicalStaff getMedicalStaffById(Long id) {
        return medicalStaffRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("MedicalStaff not found"));
    }

    // Update
    @Override
    public MedicalStaff updateMedicalStaff(Long id, MedicalStaff medicalStaff) {
        MedicalStaff existingMedicalStaff = medicalStaffRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("MedicalStaff not found"));
        existingMedicalStaff.setFirstname(medicalStaff.getFirstname());
        existingMedicalStaff.setLastname(medicalStaff.getLastname());
        existingMedicalStaff.setEmail(medicalStaff.getEmail());
        existingMedicalStaff.setPassword(medicalStaff.getPassword());
        existingMedicalStaff.setDateOfBirth(medicalStaff.getDateOfBirth());
        existingMedicalStaff.setEnabled(medicalStaff.isEnabled());
        existingMedicalStaff.setAccountLocked(medicalStaff.isAccountLocked());
        existingMedicalStaff.setSpecializationMed(medicalStaff.getSpecializationMed());
        existingMedicalStaff.setLicenseNumber(medicalStaff.getLicenseNumber());
        return medicalStaffRepository.save(existingMedicalStaff);
    }

    // Delete
    @Override
    public void deleteMedicalStaff(Long id) {
        if (!medicalStaffRepository.existsById(id)) {
            throw new EntityNotFoundException("MedicalStaff not found");
        }
        medicalStaffRepository.deleteById(id);
    }
}

package tn.esprit.tic.se.spr01.UserManagement.Controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tic.se.spr01.UserManagement.DTO.MedicalStaffDto;
import tn.esprit.tic.se.spr01.UserManagement.Entities.MedicalStaff;
import tn.esprit.tic.se.spr01.UserManagement.Services.iMedicalStaffService;

@RestController

@RequestMapping("/medicalStaff")
public class MedicalStaffController {

    final
    iMedicalStaffService medicalStaffService;

    public MedicalStaffController(iMedicalStaffService medicalStaffService) {
        this.medicalStaffService = medicalStaffService;
    }


    @PostMapping("/addMedicalStaff")
public ResponseEntity<MedicalStaff> addMedicalStaff(@RequestBody MedicalStaff medicalStaff) {
    return ResponseEntity.ok(medicalStaffService.addMedicalStaff(medicalStaff));
}

@GetMapping("/getMedicalStaff/{id}")
public ResponseEntity<MedicalStaff> getMedicalStaffById(@PathVariable Long id) {
    return ResponseEntity.ok(medicalStaffService.getMedicalStaffById(id));
}

@PutMapping("/updateMedicalStaff//{id}")
public ResponseEntity<MedicalStaff> updateMedicalStaff(@PathVariable Long id, @RequestBody MedicalStaff medicalStaff) {
    return ResponseEntity.ok(medicalStaffService.updateMedicalStaff(id, medicalStaff));
}

@DeleteMapping("/deleteMedicalStaff//{id}")
public ResponseEntity<Void> deleteMedicalStaff(@PathVariable Long id) {
    medicalStaffService.deleteMedicalStaff(id);
    return ResponseEntity.noContent().build();
}
}
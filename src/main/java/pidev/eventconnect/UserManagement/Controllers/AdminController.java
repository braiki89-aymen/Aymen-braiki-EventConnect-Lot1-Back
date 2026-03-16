package tn.esprit.tic.se.spr01.UserManagement.Controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tic.se.spr01.UserManagement.DTO.AdminDto;
import tn.esprit.tic.se.spr01.UserManagement.Entities.Admin;
import tn.esprit.tic.se.spr01.UserManagement.Services.iAdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {


    final
    iAdminService adminService;

    public AdminController(iAdminService adminService) {
        this.adminService = adminService;
    }


    @PostMapping("/addAdmin")
    public ResponseEntity<Admin> addAdmin(@RequestBody Admin admin) {
        return ResponseEntity.ok(adminService.addAdmin(admin));
    }

    @GetMapping("/getAdmin/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.getAdminById(id));
    }

    @PutMapping("/updateAdmin/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable Long id, @RequestBody Admin admin) {
        return ResponseEntity.ok(adminService.updateAdmin(id, admin));
    }

    @DeleteMapping("/deleteAdmin/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.noContent().build();
    }
}

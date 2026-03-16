package tn.esprit.tic.se.spr01.UserManagement.Services;

import tn.esprit.tic.se.spr01.UserManagement.DTO.AdminDto;
import tn.esprit.tic.se.spr01.UserManagement.Entities.Admin;

public interface iAdminService {


    Admin addAdmin(Admin admin);
    Admin getAdminById(Long id);
    Admin updateAdmin(Long id, Admin admin);
    void deleteAdmin(Long id);
}

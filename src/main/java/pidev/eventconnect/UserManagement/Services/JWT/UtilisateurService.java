package tn.esprit.tic.se.spr01.UserManagement.Services.JWT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tn.esprit.tic.se.spr01.UserManagement.Repositories.UserRepository;

@Service
public class UtilisateurService implements UserDetailsService {
    @Autowired
    UserRepository utilisateurRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return utilisateurRepository.findByEmail(email).orElseThrow(()->
                new UsernameNotFoundException("user not found with email :"+ email));
       /* return new User(
                utilisateur.getEmail(),
                utilisateur.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + utilisateur.getRole().name())) // 🔥 Correction ici !
        );*/

    }
}

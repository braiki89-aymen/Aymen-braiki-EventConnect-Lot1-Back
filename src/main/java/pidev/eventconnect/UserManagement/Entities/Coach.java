package tn.esprit.tic.se.spr01.UserManagement.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import tn.esprit.tic.se.spr01.Exercises.Entities.Exercise;
import tn.esprit.tic.se.spr01.TacticalBoard.Entites.TacticsBoard;
import tn.esprit.tic.se.spr01.TeamandPlayer.entities.Club;
import tn.esprit.tic.se.spr01.TeamandPlayer.entities.Team;
import tn.esprit.tic.se.spr01.UserManagement.Entities.User;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("COACH")
public class Coach extends User implements Serializable {
    private String specializationCoach;
    private String diplome;
    @OneToMany(mappedBy = "coach")
    private Set<TacticsBoard> tacticsBoards;

    @OneToMany(mappedBy = "coach", cascade = CascadeType.ALL)
    private Set<Exercise> exercises ;

    @ManyToOne(fetch = FetchType.LAZY)

    Club club;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)  // This will apply to updates (not save)
    @JoinColumn(name = "team_id", referencedColumnName = "teamId", nullable = true)
    @JsonIgnore
    private Team team;

    public String getSpecializationCoach() {
        return specializationCoach;
    }

    public String getDiplome() {
        return diplome;
    }

    public void setspecializationCoach(String specializationCoach) {
        this.specializationCoach = specializationCoach;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

}
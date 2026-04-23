package tn.esprit.tic.se.spr01.UserManagement.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import tn.esprit.tic.se.spr01.TacticalBoard.Entites.Position;
import tn.esprit.tic.se.spr01.TacticalBoard.Entites.TacticsBoard;
import tn.esprit.tic.se.spr01.TeamandPlayer.entities.Club;
import tn.esprit.tic.se.spr01.TeamandPlayer.entities.Team;
import tn.esprit.tic.se.spr01.UserManagement.Entities.User;

import tn.esprit.tic.se.spr01.health.entities.HealthReport;
import tn.esprit.tic.se.spr01.health.entities.PerformanceTracking;
import tn.esprit.tic.se.spr01.health.entities.TypeStatusHealth;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("PLAYER")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Player  extends User implements Serializable {
    private String role;// Position du joueur (ex: "Attaquant", "Défenseur")
    private Integer jerseyNumber;
    @Enumerated(EnumType.STRING)
    private TypeStatusHealth healthStatus;
    @OneToMany(mappedBy = "player",fetch = FetchType.EAGER)
    List<HealthReport> healthReports = new ArrayList<>();
    @OneToMany(mappedBy = "player", fetch = FetchType.LAZY)
    List<PerformanceTracking> performances = new ArrayList<>();


    @ManyToOne
    @JsonIgnore

    Club club;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", referencedColumnName = "teamId", nullable = true)
    @JsonIgnore
    private Team team;


    public String getRole() {
        return role;
    }




    public void setRole(String role) {
        this.role = role;
    }




}

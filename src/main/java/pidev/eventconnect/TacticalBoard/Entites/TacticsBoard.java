package tn.esprit.tic.se.spr01.TacticalBoard.Entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.tic.se.spr01.Exercises.Entities.Exercise;
import tn.esprit.tic.se.spr01.TeamandPlayer.entities.Team;
import tn.esprit.tic.se.spr01.UserManagement.Entities.Coach;
import tn.esprit.tic.se.spr01.UserManagement.Entities.Player;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class TacticsBoard implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idTactic;
    String name;
//    @ElementCollection
//    @CollectionTable(name = "formation", joinColumns = @JoinColumn(name = "tactics_board_id"))
//    @MapKeyColumn(name = "jersey_number")
//    private Map<String, Position> formation;
    LocalDate createdAt;
    @ManyToMany
    @JsonIgnore
    Set<TacticsStats> tacticsStats;
    @OneToMany(mappedBy = "tacticsBoard")
    @JsonIgnore
    Set<TacticsAnimation> tacticsAnimations;
    @ManyToOne(fetch =FetchType.LAZY)
    @JsonIgnore
    Coach coach;
    @OneToMany(mappedBy = "tacticsBoard", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private Set<PlayerIcon> players;
    @ManyToOne
    @JoinColumn(name = "home_team_id")
    @JsonIgnore
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team_id")
    @JsonIgnore
    private Team awayTeam;

    @Version
    private Long version = 0L;


    @ManyToMany
    @JoinTable(
            name = "tactics_board_exercises",  // Nom de la table de jointure
            joinColumns = @JoinColumn(name = "tactics_board_id"),  // Clé étrangère pour TacticsBoard
            inverseJoinColumns = @JoinColumn(name = "exercise_id")  // Clé étrangère pour Exercise
    )
    private Set<Exercise> exercises;

    public void setName(String name) {
        this.name = name;
    }

//    public void setFormation(Map<String, Position> formation) {
//        this.formation = formation;
//    }


    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public void addPlayer(PlayerIcon player) {
        this.players.add(player);
        player.setTacticsBoard(this);
    }

    public void setTacticsStats(Set<TacticsStats> tacticsStats) {
        this.tacticsStats = tacticsStats;
    }

    public void setTacticsAnimations(Set<TacticsAnimation> tacticsAnimations) {
        this.tacticsAnimations = tacticsAnimations;
    }

    public void setPlayersIcon(Set<PlayerIcon> players) {
        this.players = players;
    }
}

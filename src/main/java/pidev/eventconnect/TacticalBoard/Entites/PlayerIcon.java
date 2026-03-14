package tn.esprit.tic.se.spr01.TacticalBoard.Entites;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

@Entity
public class PlayerIcon implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIcon;
    @NotNull
    private String role;
    @NotNull// Position du joueur (ex: "Attaquant", "Défenseur")
    private Integer jerseyNumber; // Numéro de maillot du joueur
    @NotNull
    private String jerseyColor;  // Add jerseyColor field


    @Embedded
    private Position position;

    @ManyToOne
    @JoinColumn(name = "tactics_board_id")
    @JsonBackReference
    private TacticsBoard tacticsBoard;

    public Long getIdIcon() {
        return idIcon;
    }

    public void setIdIcon(Long idIcon) {
        this.idIcon = idIcon;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getJerseyNumber() {
        return jerseyNumber;
    }

    public void setJerseyNumber(Integer jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    public String getJerseyColor() {
        return jerseyColor;
    }

    public void setJerseyColor(String jerseyColor) {
        this.jerseyColor = jerseyColor;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public TacticsBoard getTacticsBoard() {
        return tacticsBoard;
    }

    public void setTacticsBoard(TacticsBoard tacticsBoard) {
        this.tacticsBoard = tacticsBoard;
    }


}

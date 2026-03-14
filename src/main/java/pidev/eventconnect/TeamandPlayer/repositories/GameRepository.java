package tn.esprit.tic.se.spr01.TeamandPlayer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.tic.se.spr01.TeamandPlayer.entities.Game;

public interface GameRepository extends JpaRepository<Game, Integer> {
}

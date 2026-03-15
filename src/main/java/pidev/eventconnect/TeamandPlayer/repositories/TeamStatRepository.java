package tn.esprit.tic.se.spr01.TeamandPlayer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.tic.se.spr01.TeamandPlayer.entities.TeamStat;

public interface TeamStatRepository extends JpaRepository<TeamStat, Integer> {
}

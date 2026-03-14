package tn.esprit.tic.se.spr01.TeamandPlayer.Services;


import tn.esprit.tic.se.spr01.TeamandPlayer.entities.Club;

import java.util.List;

public interface IClubService {

    public Club addClub(Club club);

    public void assignPlayerToClub(Integer clubId, Long playerId);
    public void assignCoachToClub(Integer clubId, Long coachId);
    public void removePlayerFromClub(Integer clubId, Long playerId);
    public void removeCoachFromClub(Integer clubId, Long coachId);
    public void updatePlayerClub(Long playerId, Integer newClubId);
    public void updateCoachClub(Long coachId, Integer newClubId);
    public void deleteClub(Integer clubId);
    public List<Club> getClubsByName(String name);
}

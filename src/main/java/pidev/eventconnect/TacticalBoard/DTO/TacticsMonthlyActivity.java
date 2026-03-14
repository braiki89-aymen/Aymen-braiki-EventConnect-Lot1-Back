package tn.esprit.tic.se.spr01.TacticalBoard.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TacticsMonthlyActivity {
    private String month;
    private Long count;
}
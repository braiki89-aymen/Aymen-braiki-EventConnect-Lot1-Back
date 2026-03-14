package tn.esprit.tic.se.spr01.TacticalBoard.Entites;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class TacticsVideo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;
    private String filePath;
    private LocalDateTime uploadDate;

    // Store the JSON string of player information
    @Column(columnDefinition = "TEXT")
    private String playerData;

    @ManyToOne
    @JsonBackReference
    private TacticsBoard tacticsBoard;

    // Constructors
    public TacticsVideo() {
    }

    public TacticsVideo(String filename, String filePath, TacticsBoard tacticsBoard, String playerData) {
        this.filename = filename;
        this.filePath = filePath;
        this.tacticsBoard = tacticsBoard;
        this.playerData = playerData;
        this.uploadDate = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }

    public TacticsBoard getTacticsBoard() {
        return tacticsBoard;
    }

    public void setTacticsBoard(TacticsBoard tacticsBoard) {
        this.tacticsBoard = tacticsBoard;
    }

    public String getPlayerData() {
        return playerData;
    }

    public void setPlayerData(String playerData) {
        this.playerData = playerData;
    }

    public String getUrl() {
        return this.filePath;
    }
}
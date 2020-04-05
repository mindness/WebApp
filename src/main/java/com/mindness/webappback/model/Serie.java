package com.mindness.webappback.model;

import javax.persistence.*;

@Entity
@Table(name = "series")
public class Serie {

    private long id;
    private String name;
    private String affiche;
    private float note;
    private String synopsis;
    private int episode;
    private int saison;
    private String status;
    private int duree;


    public Serie() {

    }

    public Serie(long id, String name, String affiche, float note, String synopsis, int episode, int saison, String status, int duree) {
        this.id = id;
        this.name = name;
        this.affiche = affiche;
        this.note = note;
        this.synopsis = synopsis;
        this.episode = episode;
        this.saison = saison;
        this.status = status;
        this.duree = duree;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "Nom", nullable = false)
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    @Column(name = "Affiche", nullable = false)
    public String getAffiche() { return affiche;}
    public void setAffiche(String affiche) {this.affiche = affiche;}

    @Column(name = "Note", nullable = false)
    public float getNote() {return note;}
    public void setNote(float note) {this.note = note;}

    @Column(name = "Synopsis", nullable = false)
    public String getSynopsis() {return synopsis;}
    public void setSynopsis(String synopsis) {this.synopsis = synopsis;}

    @Column(name = "nbEpisode", nullable = false)
    public int getEpisode() {return episode;}
    public void setEpisode(int episode) {this.episode = episode;}

    @Column(name = "Saison", nullable = false)
    public int getSaison() {return saison;}
    public void setSaison(int saison) {this.saison = saison;}

    @Column(name = "Status", nullable = false)
    public String getStatus() {return status; }
    public void setStatus(String status) {this.status = status;}

    @Column(name = "Duree", nullable = false)
    public int getDuree() { return duree;}
    public void setDuree(int duree) {this.duree = duree;}
}

package com.mindness.webappback.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "films")
public class Film {

    private long id;
    private String name;
    private String affiche;
    private String dateSortie;
    private float note;
    private String synopsis;

    public Film() {

    }

    public Film(long id, String name, String affiche, String dateSortie, float note, String synopsis) {
        this.id = id;
        this.name = name;
        this.affiche = affiche;
        this.dateSortie = dateSortie;
        this.note = note;
        this.synopsis = synopsis;
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
    public String getAffiche() {return affiche;}
    public void setAffiche(String affiche) {this.affiche = affiche;}

    @Column(name = "Datedesortie", nullable = false)
    public String getDateSortie() {return dateSortie;    }
    public void setDateSortie(String dateSortie) {this.dateSortie = dateSortie;    }

    @Column(name = "Note", nullable = false)
    public float getNote() {return note;}
    public void setNote(float note) {this.note = note;}

    @Column(name = "Synopsis", nullable = false)
    public String getSynopsis() {return synopsis;}
    public void setSynopsis(String synopsis) {this.synopsis = synopsis;}

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", affiche='" + affiche + '\'' +
                ", dateSortie='" + dateSortie + '\'' +
                ", note=" + note +
                ", synopsis='" + synopsis + '\'' +
                '}';
    }
}

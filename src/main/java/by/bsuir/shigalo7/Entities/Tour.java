package by.bsuir.shigalo7.Entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Blob;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Data
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String target;
    private String name;
    private Integer places;
    private Boolean post;
    private LocalDateTime date;
    private Blob picture;
    private String about;
    private Integer length;
    private String type;
    private Double cost;

    public Tour(String target, String name, Integer places, Boolean post, Integer length, String type, Double cost, Timestamp date, Blob picture, String about) {
        this.target = target;
        this.name = name;
        this.places = places;
        this.post = post;
        this.date = date.toLocalDateTime();
        this.picture = picture;
        this.about = about;
        this.type = type;
        this.length = length;
        this.cost = cost;
    }

    public Tour(String target, String name, Integer places, Boolean post, Integer length, String type, Double cost, Timestamp date) {
        this.target = target;
        this.name = name;
        this.places = places;
        this.post = post;
        this.date = date.toLocalDateTime();
        this.picture = null;
        this.about = null;
        this.type = type;
        this.length = length;
        this.cost = cost;
    }

    public Tour() {
    }
}

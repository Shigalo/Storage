package by.bsuir.shigalo7.Entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Data
public class Info {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tour_id")
    private Tour tour;

    Integer sequence;
    String header;
    String about;
    Blob picture;

    public Info(Tour tour, Integer sequence, String header, String about, Blob picture) {
        this.tour = tour;
        this.sequence = sequence;
        this.header = header;
        this.about = about;
        this.picture = picture;
    }

    public Info() {
    }
}

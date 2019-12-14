package by.bsuir.shigalo7.Entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Flight {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "outp")
    String out;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tour_id")
    private Tour tour;

    LocalDateTime arrival;
    Double cost;

    public Flight(String out, Double cost, LocalDateTime arrival) {
        this.out = out;
        this.arrival = arrival;
        this.cost = cost;
    }

    public Flight() {
    }
}

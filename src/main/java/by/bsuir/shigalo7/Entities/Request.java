package by.bsuir.shigalo7.Entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Request {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tour_id")
    private Tour tour;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "flight_id")
    private Flight flight;

    private String questions;
    private Double cost;
    private Integer places;
    private Boolean confirm;

    public Request(Tour tour, User user, Double cost, Flight flight, String questions, Integer places, Boolean confirm) {
        this.tour = tour;
        this.user = user;
        this.cost = cost;
        this.flight = flight;
        this.questions = questions;
        this.places = places;
        this.confirm = confirm;
    }

    public Request() {

    }
}

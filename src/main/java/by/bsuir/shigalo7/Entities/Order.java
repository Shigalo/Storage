package by.bsuir.shigalo7.Entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private Integer reorder_level;
    private Integer quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "stock_id")
    private Stock stock;

    public Order(Integer reorder_level, Integer quantity, Stock stock) {
        this.reorder_level = reorder_level;
        this.quantity = quantity;
        this.stock = stock;
    }

    public Order() {
    }
}

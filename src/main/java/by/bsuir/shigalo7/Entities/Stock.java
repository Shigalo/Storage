package by.bsuir.shigalo7.Entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Stock {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private Integer reorder_level;
    private Integer quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    public Stock(Integer reorder_level, Integer quantity, Product product, Warehouse warehouse) {
        this.reorder_level = reorder_level;
        this.quantity = quantity;
        this.product = product;
        this.warehouse = warehouse;
    }

    public Stock() {
    }

    public void replenishment(int quantity) {
        this.quantity += quantity;
    }

    public void decrease(int quantity) {
        this.quantity -= quantity;
    }
}

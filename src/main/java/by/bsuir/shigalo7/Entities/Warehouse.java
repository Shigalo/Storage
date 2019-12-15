package by.bsuir.shigalo7.Entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Warehouse {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String address;


    public Warehouse(String address) {
        this.address = address;
    }

    public Warehouse() {
    }
}

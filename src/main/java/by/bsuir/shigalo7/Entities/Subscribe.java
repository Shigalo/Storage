package by.bsuir.shigalo7.Entities;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
public class Subscribe {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    public Subscribe() { }

    public Subscribe(User user, Warehouse warehouse) {
        this.user = user;
        this.warehouse = warehouse;
    }
}

package diplomna.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "orders")
public class Orders extends BaseEntity {

    private LocalDateTime orderDate;
    private Integer orderNum;
    private Double amount;
    private String username;
    private String userAddress;
    private String userPhone;


}

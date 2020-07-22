package diplomna.model;

import diplomna.model.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "purchases")
public class Purchase extends BaseEntity {

    private LocalDateTime date;
    private BigDecimal priceInUserCurrency;

    public Purchase() {
    }

    @Column(name = "date",nullable = false)
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Column(name = "price_in_user_currency", nullable = false)
    public BigDecimal getPriceInUserCurrency() {
        return priceInUserCurrency;
    }

    public void setPriceInUserCurrency(BigDecimal priceInUserCurrency) {
        this.priceInUserCurrency = priceInUserCurrency;
    }
}

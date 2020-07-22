package diplomna.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "payments")
public class Payment extends BaseEntity{

    private Category categoryName;

    public Payment() {
    }

    @ManyToOne
    public Category getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(Category categoryName) {
        this.categoryName = categoryName;
    }
}

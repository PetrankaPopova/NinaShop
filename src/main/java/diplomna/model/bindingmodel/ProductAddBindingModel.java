package diplomna.model.bindingmodel;

import diplomna.model.entity.CategoryName;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.DecimalMin;


public class ProductAddBindingModel {
    private String name;
    private String description;
    private double price;
    private CategoryName category;
    private MultipartFile[] photo;

    public ProductAddBindingModel() {
    }

    @Length(min = 3, max = 20, message = "Name length must be between 3 and 20 characters (inclusive 3 and 20).")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 5, message = "Description min length must be minimum 5(inclusive) characters")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @DecimalMin(value = "0", message = "Price must be a positive number")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    //@NotNull(message = "Category cannot be null")
    @Enumerated(EnumType.STRING)
    public CategoryName getCategory() {
        return category;
    }

    public void setCategory(CategoryName category) {
        this.category = category;
    }

    public MultipartFile[] getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartFile[] photo) {
        this.photo = photo;
    }
}

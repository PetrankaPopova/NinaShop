package diplomna.model.service;

import diplomna.model.entity.Category;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductServiceModel extends BaseEntityService {
    private String name;
    private String description;
    private BigDecimal price;
    private Category category;
    private String url;
    private MultipartFile[] photo;

    public ProductServiceModel() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public MultipartFile[] getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartFile[] photo) {
        this.photo = photo;
    }
}

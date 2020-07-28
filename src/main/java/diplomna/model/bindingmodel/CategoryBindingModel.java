package diplomna.model.bindingmodel;

import diplomna.model.entity.CategoryName;

import javax.persistence.Column;

public class CategoryBindingModel {

    private CategoryName categoryName;
    private String description;

    public CategoryBindingModel() {
    }

    @Column(name = "category_name", nullable = false,unique = true)
    public CategoryName getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(CategoryName categoryName) {
        this.categoryName = categoryName;
    }

    @Column(name = "description",nullable = false,columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

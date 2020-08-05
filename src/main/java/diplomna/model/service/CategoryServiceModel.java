package diplomna.model.service;

import diplomna.model.entity.Category;
import diplomna.model.entity.CategoryName;

public class CategoryServiceModel {
    public Category getGetName() {
        return getName;
    }

    public void setGetName(Category getName) {
        this.getName = getName;
    }

    public Category getName;
    private CategoryName categoryName;
    private String description;

    public CategoryServiceModel() {
    }

    public CategoryName getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(CategoryName categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

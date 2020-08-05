package diplomna.model.bindingmodel;

import diplomna.model.entity.CategoryName;
import org.hibernate.validator.constraints.Length;

public class CategoryEditBindingModel {
    private CategoryName categoryName;
    private String description;

    private boolean isPresent() {
        return true;
    }


    public CategoryEditBindingModel() {
    }

    @Length(min = 3, max = 20, message = "CategoryName length must be between 3 and 20 characters (inclusive 3 and 20).")
    public CategoryName getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(CategoryName categoryName) {
        this.categoryName = categoryName;
    }

    @Length(min = 5, max = 250,message = "Description lenght must be 5 and 250 characters!")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

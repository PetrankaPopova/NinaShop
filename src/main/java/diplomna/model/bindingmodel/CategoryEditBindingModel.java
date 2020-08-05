package diplomna.model.bindingmodel;

import diplomna.model.entity.CategoryName;

public class CategoryEditBindingModel {
    private CategoryName categoryName;

    private boolean isPresent() {
        return true;
    }


    public CategoryEditBindingModel() {
    }

    public CategoryName getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(CategoryName categoryName) {
        this.categoryName = categoryName;
    }
}

package diplomna.service;

import diplomna.model.entity.Category;
import diplomna.model.entity.CategoryName;

public interface CategoryService {

    Category find(CategoryName categoryName);

    Category getByName(CategoryName categoryName);

    void initCategories();
}

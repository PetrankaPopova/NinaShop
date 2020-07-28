package diplomna.service;

import diplomna.model.entity.Category;
import diplomna.model.entity.CategoryName;
import diplomna.model.service.CategoryServiceModel;

import java.util.List;

public interface CategoryService {

    Category find(CategoryName categoryName);

    Category getByName(CategoryName categoryName);

    void initCategories();


    List<CategoryServiceModel> findAllCategories();
}

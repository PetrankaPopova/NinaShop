package diplomna.service.serviceImpl;

import diplomna.model.entity.Category;
import diplomna.model.entity.CategoryName;
import diplomna.repository.CategoryRepository;
import diplomna.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Category find(CategoryName categoryName) {
        return this.categoryRepository.findByCategoryName(categoryName)
                .orElse(null);
    }


    @Override
    public Category getByName(CategoryName categoryName) {
        return this.categoryRepository.findByCategoryName(categoryName)
                .orElse(null);
    }

    @PostConstruct
    public void initCategories() {
        if (this.categoryRepository.count() == 0) {
            Arrays.stream(CategoryName.values())
                    .forEach(categoryName ->
                            this.categoryRepository.save(new Category(categoryName,
                                    String.format("Description for %s", categoryName.name()))));

        }
    }
}

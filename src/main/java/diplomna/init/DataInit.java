package diplomna.init;

import diplomna.model.entity.Category;
import diplomna.model.entity.DataForImport;
import diplomna.model.entity.Product;
import diplomna.repository.CategoryRepository;
import diplomna.repository.ProductRepository;
import diplomna.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInit implements CommandLineRunner {
    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public DataInit(CategoryService categoryService, CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        this.categoryService.initCategories();
        if (false) {
            for (DataForImport oneProduct : DataForImport.values()) {
                Category fCategory = this.categoryRepository
                        .findByCategoryName(oneProduct.getCategoryName()).orElse(null);
                this.productRepository.saveAndFlush(
                        new Product(
                                oneProduct.getName(),
                                oneProduct.getDescription(),
                                oneProduct.getPrice(),
                                fCategory,
                                oneProduct.getUrl()
                        )
                );
            }
        }
    }

}
package diplomna.validation.category;

import diplomna.model.bindingmodel.CategoryBindingModel;
import diplomna.repository.CategoryRepository;
import diplomna.validation.ValidationConstants;
import diplomna.validation.annotation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

@Validator
public class CategoryAddValidator implements org.springframework.validation.Validator {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryAddValidator(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CategoryBindingModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CategoryBindingModel categoryBindingModel = (CategoryBindingModel) o;

        if (categoryBindingModel.getCategoryName().length() < 3) {
            errors.rejectValue(
                    "name",
                    ValidationConstants.NAME_LENGTH,
                    ValidationConstants.NAME_LENGTH
            );
        }

        if (this.categoryRepository.findByCategoryName(categoryBindingModel.getCategoryName()).isPresent()) {
            errors.rejectValue(
                    "name",
                    String.format(ValidationConstants.NAME_ALREADY_EXISTS, "Category", categoryBindingModel.getCategoryName()),
                    String.format(ValidationConstants.NAME_ALREADY_EXISTS, "Category", categoryBindingModel.getCategoryName())
            );
        }
    }
}

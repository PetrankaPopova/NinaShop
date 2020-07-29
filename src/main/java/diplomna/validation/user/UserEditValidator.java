package diplomna.validation.user;

import diplomna.model.entity.User;
import diplomna.repository.UserRepository;
import diplomna.validation.ValidationConstants;
import diplomna.validation.annotation.Validator;
import diplomna.view.UserEditViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;

@Validator
public class UserEditValidator implements org.springframework.validation.Validator {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserEditViewModel userEditViewModel;

    @Autowired
    public UserEditValidator(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, UserEditViewModel userEditViewModel) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userEditViewModel = userEditViewModel;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserEditViewModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserEditViewModel editViewModel = (UserEditViewModel) o;

        User user = this.userRepository.findByUsername(editViewModel.getUsername()).orElse(null);

        if (!this.bCryptPasswordEncoder.matches(userEditViewModel.getOldPassword(), user.getPassword())) {
            errors.rejectValue(
                    "oldPassword",
                    ValidationConstants.WRONG_PASSWORD,
                    ValidationConstants.WRONG_PASSWORD
            );
        }

        if (userEditViewModel.getPassword() != null && !editViewModel.getPassword().equals(userEditViewModel.getConfirmPassword())) {
            errors.rejectValue(
                    "password",
                    ValidationConstants.PASSWORDS_DO_NOT_MATCH,
                    ValidationConstants.PASSWORDS_DO_NOT_MATCH
            );
        }

        if (!user.getEmail().equals(editViewModel.getEmail()) && this.userRepository.findByEmail(editViewModel.getEmail()).isPresent()) {
            errors.rejectValue(
                    "email",
                    String.format(ValidationConstants.EMAIL_ALREADY_EXISTS, editViewModel.getEmail()),
                    String.format(ValidationConstants.EMAIL_ALREADY_EXISTS, editViewModel.getEmail())
            );
        }
    }
}

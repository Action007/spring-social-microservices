package com.social.microservices.iam_service.security.validation;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import com.social.microservices.iam_service.model.constants.ApiErrorMessage;
import com.social.microservices.iam_service.model.entity.User;
import com.social.microservices.iam_service.model.exception.DataExistException;
import com.social.microservices.iam_service.model.exception.InvalidPasswordException;
import com.social.microservices.iam_service.model.exception.NotFoundException;
import com.social.microservices.iam_service.repository.UserRepository;
import com.social.microservices.iam_service.service.model.IamServiceUserRole;
import com.social.microservices.iam_service.utils.ApiUtils;
import com.social.microservices.iam_service.utils.PasswordUtils;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AccessValidator {
    private final UserRepository userRepository;
    private final ApiUtils apiUtils;

    public void validateNewUser(String username, String email, String password, String confirmPassword) {
        userRepository.findByUsername(username)
                .ifPresent((existingUser) -> {
                    throw new DataExistException(
                            ApiErrorMessage.USERNAME_ALREADY_EXISTS.getMessage(username));
                });

        userRepository.findByEmail(email)
                .ifPresent((existingUser) -> {
                    throw new DataExistException(
                            ApiErrorMessage.EMAIL_ALREADY_EXISTS.getMessage(email));
                });

        if (!password.equals(confirmPassword)) {
            throw new InvalidPasswordException(ApiErrorMessage.MISMATCH_PASSWORDS.getMessage());
        }

        if (PasswordUtils.isNotValidPassword(confirmPassword)) {
            throw new InvalidPasswordException(ApiErrorMessage.INVALID_PASSWORD.getMessage());
        }
    }

    public boolean isAdminOrSuperAdmin(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ApiErrorMessage.USER_NOT_FOUND_BY_ID.getMessage(userId)));

        return user.getRoles().stream()
                .map((role) -> IamServiceUserRole.fromName(role.getName()))
                .anyMatch((role) -> role == IamServiceUserRole.ADMIN || role == IamServiceUserRole.SUPER_ADMIN);
    }

    public void validateAdminOrOwnerAccess(Integer ownerId) {
        Integer currentId = apiUtils.getUserIdFromAuthentication();

        if (!currentId.equals(ownerId) && !isAdminOrSuperAdmin(currentId)) {
            throw new AccessDeniedException(
                    ApiErrorMessage.HAVE_NO_ACCESS.getMessage());
        }
    }
}

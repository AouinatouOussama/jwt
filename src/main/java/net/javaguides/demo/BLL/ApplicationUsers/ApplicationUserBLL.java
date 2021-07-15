package net.javaguides.demo.BLL.ApplicationUsers;

import net.javaguides.demo.ClientModels.ClientModelRequest.ChangePassword;
import net.javaguides.demo.ClientModels.ClientModelResponse.ApplicationUserClient;
import net.javaguides.demo.ClientModels.ClientModelRequest.RegisterForm;
import net.javaguides.demo.ClientModels.ClientModelRequest.UpdateApplicationUser;
import net.javaguides.demo.DAL.DataBaseEntities.ApplicationRole;
import net.javaguides.demo.DAL.DataBaseEntities.ApplicationUser;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

//All used Methods will be here
public interface ApplicationUserBLL {

    List<ApplicationUserClient> GetAllApplicationUser();
    ApplicationUserClient GetApplicationUserById(Long id);
    ApplicationUserClient   RegisterApplicationUser(RegisterForm registerForm);
    ApplicationUserClient UpdateApplicationUser(Long id, UpdateApplicationUser updateApplicationUser);
    ApplicationUserClient UpdateApplicationUserProfilePic(Long id, String imageURL);
    boolean DeleteApplicationUser(Long id);

    //this one is for Login
    Optional<ApplicationUser> loadUserByUsername(String emailOrUserName);

    //
    ApplicationUser AddRoleToUser(Long userId, Integer roleId);
    void AddRoleToUser(String userName, String roleName);

    ApplicationUserClient Validate(Long userId);

    List<ApplicationUserClient> GetAllApplicationUserNotValidated();

    ApplicationUserClient GetApplicationUserByUsername(String username);

    boolean RemoveRoleToUser(Long userId, Integer roleId);

    ApplicationUserClient ChangePasswordUser(String username, ChangePassword changePassword);
}

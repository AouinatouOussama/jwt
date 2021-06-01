package net.javaguides.demo.ClientModels.ClientModelRequest;


public class UpdateApplicationUser {
    private String email;
    private  String username;
    private  String fullName;
    private String password;
    private String profileImage;

    public UpdateApplicationUser(String email, String username,String fullName, String password, String image) {
        this.email = email;
        this.username = username;
        this.fullName =fullName;
        this.password = password;
        this.profileImage =image ;
    }

    public UpdateApplicationUser( String image) {
        this.profileImage =image ;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getImage() {
        return profileImage;
    }

    public void setImage(String image) {
        this.profileImage = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

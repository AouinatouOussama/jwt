package net.javaguides.demo.ClientModels.ClientModelRequest;

public class RegisterForm {

    private String email ;
    private String username ;
    private String fullName;
    private String password ;

    public RegisterForm( String email, String username, String fullName, String password) {
        this.password = password;
        this.email = email;
        this.username = username;
        this.fullName = fullName;
    }


    public RegisterForm() {
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

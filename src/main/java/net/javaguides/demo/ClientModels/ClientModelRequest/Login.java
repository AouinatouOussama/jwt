package net.javaguides.demo.ClientModels.ClientModelRequest;


public class Login {
    private String emailOrUserName;
    private String password;
    public  Login(){

    }
    public  Login(String emailOrUserName, String password){
            this.emailOrUserName=emailOrUserName;
            this.password= password;
    }

    @Override
    public String toString() {
        return "Login{" +
                "emailOrUserName='" + emailOrUserName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getEmailOrUserName() {
        return emailOrUserName;
    }

    public void setEmailOrUserName(String emailOrUserName) {
        this.emailOrUserName = emailOrUserName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

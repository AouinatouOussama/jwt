package net.javaguides.demo.ClientModels.ClientModelRequest;

public class ChangePassword {

    String lastPassword;
    String newPassword;

    public ChangePassword(String lastPassword, String newPassword) {
        this.lastPassword = lastPassword;
        this.newPassword = newPassword;
    }

    public String getLastPassword() {
        return lastPassword;
    }

    public void setLastPassword(String lastPassword) {
        this.lastPassword = lastPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}

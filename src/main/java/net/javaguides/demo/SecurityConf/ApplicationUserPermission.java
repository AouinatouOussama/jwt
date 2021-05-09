package net.javaguides.demo.SecurityConf;

public enum ApplicationUserPermission {

    //constants of the enum (we put inside enum something that will not change)
    //well explained above
    //https://www.youtube.com/watch?v=pbAkJiz-DNU&ab_channel=Graven-D%C3%A9veloppement

    STUDENT_READ("student:read"),
    STUDENT_WRITE("student:write"),
    COURSE_READ("course:read"),
    COURSE_WRITE("course:write");

    private String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}

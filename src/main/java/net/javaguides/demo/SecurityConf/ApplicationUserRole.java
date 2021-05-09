package net.javaguides.demo.SecurityConf;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;


public enum ApplicationUserRole {

    //Sets is coming from the Guava dependency
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(
            ApplicationUserPermission.COURSE_READ,
            ApplicationUserPermission.COURSE_WRITE,
            ApplicationUserPermission.STUDENT_READ,
            ApplicationUserPermission.STUDENT_WRITE)),
    ADMINTRAINEE(Sets.newHashSet(
            ApplicationUserPermission.COURSE_READ,
            ApplicationUserPermission.STUDENT_READ));

    // we use Set form the permissions to be unique
    private Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(
                        permission -> new SimpleGrantedAuthority(permission.getPermission())
                )
                .collect(
                        Collectors.toSet()
                );
        permissions.add(
                new SimpleGrantedAuthority("ROLE_" + this.name())
        );
        return permissions;
    }
}
package net.javaguides.demo.DAL.repository;

import net.javaguides.demo.DAL.DataBaseEntities.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationUserRepo extends JpaRepository<ApplicationUser,Long> {

    //so the user can authenticate either using his email or using his userName
    @Query(value ="SELECT s from ApplicationUser s where  s.email Like :emailOrUserName OR s.username Like:emailOrUserName")
    Optional<ApplicationUser> loadUserByUsername(@Param("emailOrUserName") String emailOrUserName);


}

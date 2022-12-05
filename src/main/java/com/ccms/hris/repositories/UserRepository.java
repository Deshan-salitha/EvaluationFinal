package com.ccms.hris.repositories;

import com.ccms.hris.models.entities.User;
import com.ccms.hris.enums.UserStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

    Page<User> findAll(Pageable pageable);
    List<User> findAll ();

//    Designation findById();

    Optional<User> findByEmail(String email);

    List<User> findByAddress_City(String city);
    Page<User> findByAddress_City(String city, Pageable pageable);

    List<User> findByUserStatus(UserStatus userstatus);
    Page<User> findByUserStatus(UserStatus userstatus, Pageable pageable);



    List<User> findAllByUserStatus(UserStatus userStatus);

    List<User> findAllByFirstNameContainingOrLastNameContainingOrEmailContaining(String q, String q1, String q2);

    List<User> findAllByJoinDateGreaterThanAndJoinDateLessThan(Date start, Date end);

    List<User> findAllByUserStatusAndFirstNameContainingOrUserStatusAndLastNameContainingOrUserStatusAndEmailContaining(UserStatus userStatus, String q, UserStatus userStatus1, String q1, UserStatus userStatus2, String q2);
    List<User> findAllByUserStatusOrFirstNameContainingOrUserStatusAndLastNameContainingOrUserStatusAndEmailContainingOrUserStatusAndDesignation_DesignationNameContains(UserStatus userStatus, String q, UserStatus userStatus1, String q1, UserStatus userStatus2, String q2, UserStatus userStatus3, String q3);
@Query(value = "select User from User where User.firstName LIKE %?1%")
    List<User> findAllByq(String q);
}

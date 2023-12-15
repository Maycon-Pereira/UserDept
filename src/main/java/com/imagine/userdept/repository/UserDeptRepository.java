package com.imagine.userdept.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imagine.userdept.entity.UserDeptEntity;

@Repository
public interface UserDeptRepository extends JpaRepository<UserDeptEntity, String>{

}

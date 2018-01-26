package com.torikraju.repository;

import com.torikraju.domain.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, String> {

    User findByUserName(String username);


}

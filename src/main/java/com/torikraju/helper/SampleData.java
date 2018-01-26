package com.torikraju.helper;


import com.torikraju.domain.User;
import com.torikraju.domain.Role;
import com.torikraju.repository.RoleRepository;
import com.torikraju.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;

@Component
public class SampleData implements ApplicationRunner {

    private UserRepository userRepository;
    private BCryptPasswordEncoder encoder;
    private Environment environment;
    private RoleRepository roleRepository;

    @Autowired
    public SampleData(UserRepository userRepository, BCryptPasswordEncoder encoder, Environment environment, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.environment = environment;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        initialiseData();
    }

    private void initialiseData() {
        if (userRepository.count() == 0) {
            setUpData();
        }
    }

    private void setUpData() {
        setUserRole();
        User user = setUser();
        user.setRoles(new HashSet<>((Collection<? extends Role>) roleRepository.findAll()));
        userRepository.save(user);
        System.out.println(user.toString());
    }

    private User setUser() {
        User user = new User();
        user.setEmail(environment.getProperty("Sample.User.Email"));
        user.setUserName(environment.getProperty("Sample.User.Username"));
        //user.setPassword(encoder.encode(environment.getProperty("spring.datasource.password")));
        user.setPassword((environment.getProperty("Sample.User.Passwrod")));
        user.setEnabled(true);
        return user;
    }

    private void setUserRole() {
        Role user = new Role();
        user.setName("ROLE_USER");
        roleRepository.save(user);


        Role admin = new Role();
        admin.setName("ROLE_ADMIN");
        roleRepository.save(admin);
    }


}

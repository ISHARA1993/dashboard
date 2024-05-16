package com.loan.generation.dashboard.security;

import com.loan.generation.dashboard.security.model.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class MyUserService implements UserDetailsService {

    @Autowired
    private MyUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> user= repository.findByUsername(username);
        if(user.isPresent()){
            var userObject=user.get();
            return User.builder()
                    .username(userObject.getUsername())
                    .password(userObject.getPassword())//9876
                    .roles(getRoles(userObject))
                    .build();
        }
        else{
            throw new UsernameNotFoundException(username);
        }

    }

    private String[] getRoles(MyUser user) {

        if(Objects.isNull(user.getRole())){
            return new String[] {"USER"};
        }
        return user.getRole().split(",");
    }
}

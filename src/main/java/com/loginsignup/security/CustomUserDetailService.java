package com.loginsignup.security;

import com.loginsignup.exceptions.CustomNotFoundException;
import com.loginsignup.models.Usermodel;
import com.loginsignup.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class customUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        Usermodel usermodel = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Username Not Found with given username: " + username));
        return CustomUserDetail.create(usermodel);
    }

    public UserDetails loadUserById(Long id) {
        Usermodel usermodel = userRepository.findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Usermodel", "id", id));

        return CustomUserDetail.create(usermodel);
    }

}
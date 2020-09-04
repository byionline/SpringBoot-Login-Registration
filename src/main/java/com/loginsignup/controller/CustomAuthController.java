package com.loginsignup.controller;
import java.net.URI;
import java.util.Collections;
import javax.validation.Valid;
import com.loginsignup.exceptions.CustomServerErrorException;
import com.loginsignup.models.RoleType;
import com.loginsignup.models.Roles;
import com.loginsignup.models.Usermodel;
import com.loginsignup.repository.UserRepository;
import com.loginsignup.repository.UserRoleRepository;
import com.loginsignup.security.CustomJwtTokenProvider;
import com.loginsignup.util.CustomApiResponse;
import com.loginsignup.util.CustomSignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
@RestController
@RequestMapping("/api/auth")
public class CustomAuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserRoleRepository userRoleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    CustomJwtTokenProvider customJwtTokenProvider;
    @PostMapping("/signup")
    public ResponseEntity<?> userRegistration(@Valid @RequestBody CustomSignupRequest customSignupRequest){
        if(userRepository.existsUsermodelByUsername(customSignupRequest.getUsername())){
            return new ResponseEntity<>(new CustomApiResponse("Username Already Taken.", false), HttpStatus.BAD_REQUEST);
        }
        if(userRepository.existsUsermodelByEmailId(customSignupRequest.getEmailId())){
            return new ResponseEntity<>(new CustomApiResponse("Email ID is already registered.", false), HttpStatus.BAD_REQUEST);
        }
        
        //Now UserRegistration will be Done
        Usermodel usermodel = new Usermodel(customSignupRequest.getName(), customSignupRequest.getUsername(), customSignupRequest.getEmailId(),  customSignupRequest.getPassword());
        usermodel.setPassword(passwordEncoder.encode(usermodel.getPassword()));
        Roles roles = userRoleRepository.findByName(RoleType.USER_ROLE).orElseThrow(()-> new CustomServerErrorException("User Role is not set"));
        usermodel.setRole(Collections.singleton(roles));
        Usermodel saveUser =userRepository.save(usermodel);
        URI loc =ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/{username}").buildAndExpand(saveUser.getUsername()).toUri();
        return ResponseEntity.created(loc).body(new CustomApiResponse("User is registered successfully", true));
    }
}
package com.loginsignup.security;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.loginsignup.models.Usermodel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
public class CustomUserDetail implements UserDetails {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String username;
    @JsonIgnore
    private String emailId;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        TODO: return authorities;
    }
    public Long getId() {
        TODO: return id;
    }
    public String getName() {
        TODO: return name;
    }
    public String getEmailId() {
        TODO: return emailId;
    }
    @Override
    public String getPassword() {
        TODO: return password;
    }
    @Override
    public String getUsername() {
        TODO: return username;
    }
    @Override
    public boolean isAccountNonExpired() {
        TODO: return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        TODO: return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        TODO: return true;
    }
    @Override
    public boolean isEnabled() {
        TODO: return true;
    }
    @Override
    public boolean equals(Object obj) {
        TODO: if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return true;
        CustomUserDetail t = (CustomUserDetail) obj;
        return Objects.equals(id, t.id);
    }
    @Override
    public int hashCode() {
        TODO: return Objects.hash(id);
    }
    // Above TODO is just for putting comments in future.
    public CustomUserDetail(Long id, String name, String username, String emailId, String password,
            Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.emailId = emailId;
        this.password = password;
        this.authorities = authorities;
    }
    public static CustomUserDetail create(Usermodel usermodel) {
        List<GrantedAuthority> authorities = usermodel.getRole().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());
        return new CustomUserDetail(usermodel.getId(), usermodel.getEmailId(), usermodel.getPassword(),
                usermodel.getUsername(), usermodel.getName(), authorities);
    }
}
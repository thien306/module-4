package com.codegym.service;

import com.codegym.model.AppUser;
import com.codegym.model.UserRole;
import com.codegym.repository.AppUserRepository;
import com.codegym.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User " + userName + " was not found in the database"));

        List<GrantedAuthority> authorities = getGrantedAuthorities(appUser);

        return new User(appUser.getUserName(), appUser.getPasswordIsEncrypted(), authorities);
    }

    private List<GrantedAuthority> getGrantedAuthorities(AppUser appUser) {
        List<UserRole> roles = userRoleRepository.findByAppUser(appUser);

        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getAppRole().getRoleName()))
                .collect(Collectors.toList());
    }

}

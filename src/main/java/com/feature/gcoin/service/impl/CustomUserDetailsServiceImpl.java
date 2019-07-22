package com.feature.gcoin.service.impl;

import com.feature.gcoin.security.TokenHelper;
import com.feature.gcoin.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.feature.gcoin.model.User;
import com.feature.gcoin.repository.UserRepository;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    protected final Log LOGGER = LogFactory.getLog(getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(userName);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", userName));
        } else {
            return user;
        }
    }

    public void changePassword(String oldPassword, String newPassword) {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();
        if (authenticationManager != null) {
            LOGGER.debug("Re-authenticating user '" + username + "' for password change request.");

//			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, oldPassword));
        } else {
            LOGGER.debug("No authentication manager set. can't change Password!");

            return;
        }

        LOGGER.debug("Changing password for user '" + username + "'");

        User user = (User) loadUserByUsername(username);

        if (!passwordEncoder.encode(oldPassword).equals(user.getPassword())) {
            throw new UsernameNotFoundException(String.format("Incorrect current password '%s'.", ""));
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

    }

    public void updateProfile(String name, String phone){
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();

        User user = userService.findByUsername(username);
        if(name != null && name.length()>0) {
            user.setName(name);
        }else {
            LOGGER.debug("name is not available!");
        }

        if(phone != null && (phone.length()>= 9 || phone.length()<=11)) {
            user.setPhone(phone);
        }else {
            LOGGER.debug("phone is not available!");

        }
        userRepository.save(user);
    }

    public void signUp(User user) {
        userRepository.save(user);
    }
}

package com.feature.gcoin.service.impl;

import java.math.BigInteger;
import java.util.List;

import com.feature.gcoin.common.util.ModelMapperUtil;
import com.feature.gcoin.dto.UserDTO;
import com.feature.gcoin.dto.request.LoginRequest;
import com.feature.gcoin.service.GcoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.feature.gcoin.model.User;
import com.feature.gcoin.repository.UserRepository;
import com.feature.gcoin.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GcoinService gcoinService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findById(Long id) throws AccessDeniedException {
        User u = userRepository.findOne(id);
        return u;
    }

    @Override
    public User findByUsername(String userName) throws UsernameNotFoundException {
        User u = userRepository.findByUsername(userName);
        return u;
    }

    public List<User> findAll() throws AccessDeniedException {
        List<User> result = userRepository.findAll();
        return result;
    }

    @Override
    public List<User> findByIsFeature()  {
        List<User> result = userRepository.findByIsFeature();
        return result;
    }

    @Override
    public boolean login(LoginRequest loginRequest) {
        List<User> lst = userRepository.login(loginRequest.getUsername(), loginRequest.getPassword());
//        List<User> lst = userRepository.login(loginRequest.getUsername(), passwordEncoder.encode(loginRequest.getPassword()));
        return lst != null & lst.size() > 0 ? true : false;
    }

    @Override
    public User findByAddress(String addressReceive) {
        List<User> lst = userRepository.findByAddress(addressReceive);
        return lst != null & lst.size() > 0 ? lst.get(0) : null;
    }

    @Override
    public UserDTO getCoins(User user) {
        UserDTO userDTO = ModelMapperUtil.map(user, UserDTO.class);
        try {
            userDTO.setNumberCoin(gcoinService.getCoin(userDTO.getAddress()));
            userDTO.setPriceCoin(BigInteger.valueOf(5));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userDTO;
    }

}

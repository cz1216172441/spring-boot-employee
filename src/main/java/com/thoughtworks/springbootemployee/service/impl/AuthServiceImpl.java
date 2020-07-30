package com.thoughtworks.springbootemployee.service.impl;

import com.thoughtworks.springbootemployee.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public boolean authValidate(String token) {
        return "TOKEN6LA3OSD1DAS3B34JA2SD".equals(token);
    }
}

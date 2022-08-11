package com.full.project.controllers;

import com.full.project.controllers.utils.JWTUtil;
import com.full.project.dao.UserDao;
import com.full.project.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody User user) {

        User userLoggedIn = userDao.getUserByCredentials(user);

        if (userLoggedIn != null) {
            String tokenJwt = jwtUtil.create(String.valueOf(userLoggedIn.getId()), userLoggedIn.getEmail());
            return tokenJwt;
        }
        return "FAIL";
        /*
        if (userDao.verifyCredentials(user)) {
            return "OK";
        }
        return "FAIL";
         */
    }
}

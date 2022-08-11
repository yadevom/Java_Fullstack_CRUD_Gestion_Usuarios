package com.full.project.controllers;

import com.full.project.controllers.utils.JWTUtil;
import com.full.project.dao.UserDao;
import com.full.project.models.User;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/users", method = RequestMethod.GET)
    public List<User> getUsers(@RequestHeader(value = "Authorization") String token) {
        if(validateToken(token)) {
            return null;
        }
        return userDao.getUsers();
    }

    private boolean validateToken(String token) {
        String userId = jwtUtil.getKey(token); // Tambien se podria validar que usuario este en BD
        return userId != null;
    }

    @RequestMapping(value = "api/users", method = RequestMethod.POST)
    public void registerUser(@RequestBody User user) {

        // Encriptar contraseña antes de enviar a BD con libreria Argon
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, user.getPassword());
        user.setPassword(hash);

        userDao.register(user);
    }

    // El eliminar tambien valida la sesion iniciada con el token
    @RequestMapping(value = "api/users/{id}", method = RequestMethod.DELETE)
    public void delete(@RequestHeader(value = "Authorization") String token, @PathVariable Long id) {

        if(!validateToken(token)) {
            return;
        }

        userDao.deleteUser(id);
    }



    @RequestMapping(value = "api/users/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        User user = new User();

        user.setId(id);
        user.setName("Rigo");
        user.setLastName("Parra");
        user.setEmail("ripa@gmail.com");
        user.setPhone("123321");

        return user;
    }







    /*
    // PRUEBA DATOS QUEMADOS
    @RequestMapping(value = "users")
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();

        User user = new User();
        user.setId(123L);
        user.setName("Rigo");
        user.setLastName("Parra");
        user.setEmail("ripa@gmail.com");
        user.setPhone("123321");

        User user2 = new User();
        user2.setId(234L);
        user2.setName("Rodri");
        user2.setLastName("Perez");
        user2.setEmail("fsa@gmail.com");
        user2.setPhone("124543321");

        User user3 = new User();
        user3.setId(567L);
        user3.setName("Raul");
        user3.setLastName("Perdomo");
        user3.setEmail("afas@gmail.com");
        user3.setPhone("12345321");

        users.add(user);
        users.add(user2);
        users.add(user3);

        return users;
    }
    */
}

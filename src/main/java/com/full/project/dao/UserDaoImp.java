package com.full.project.dao;

import com.full.project.models.User;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<User> getUsers() {
        String query = "FROM User";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void deleteUser(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public void register(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getUserByCredentials(User user) {
        String query = "FROM User WHERE email = :email";
        List<User> listUser = entityManager
                .createQuery(query)
                .setParameter("email", user.getEmail())
                .getResultList();

        // if (listUser.isEmpty()) { return false; } else { return true;}

        // validar lista esta vacia
        if (listUser.isEmpty()) {
            return null;
        }

        String passwordHashed = listUser.get(0).getPassword();

        // Validar clave con liberia Argon
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

        // Si credenciales son correctas devuelve un usario de lo contrario nada
        if (argon2.verify(passwordHashed, user.getPassword())) {
            return listUser.get(0);
        }
        return null;
    }

}

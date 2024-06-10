package task.springcourse.springboot.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import task.springcourse.springboot.model.User;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public void delete(long id) {
        entityManager.remove(getUserById(id));
    }

    @Override
    public void update(long id, User userOld) {
        User user = getUserById(id);
        user.setFirstName(userOld.getFirstName());
        user.setLastName(userOld.getLastName());
        user.setAge(userOld.getAge());
        entityManager.merge(user);
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User u order by u.id asc", User.class).getResultList();
    }

    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }
}

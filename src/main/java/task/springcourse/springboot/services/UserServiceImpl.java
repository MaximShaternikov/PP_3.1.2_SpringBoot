package task.springcourse.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import task.springcourse.springboot.models.User;
import task.springcourse.springboot.repositories.UsersRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UsersRepository usersRepository;

    @Autowired
    public UserServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Transactional
    public void save(User user) {
        usersRepository.save(user);
    }

    @Transactional
    public void delete(long id) {
        usersRepository.deleteById(id);
    }

    @Transactional
    public void update(long id, User user) {
        user.setId(id);
        usersRepository.save(user);
    }

    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    public User getUserById(long id) {
        return usersRepository.findById(id)
                .stream()
                .findFirst()
                .orElse(null);
    }
}
package task.springcourse.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import task.springcourse.springboot.model.User;
import task.springcourse.springboot.repositories.UsersRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UsersService {
    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Transactional
    public void save(User user) {
        usersRepository.save(user);
    }

    @Transactional
    public void delete(long id) {
        usersRepository.deleteById((int) id);
    }

    @Transactional
    public void update(long id, User user) {
        user.setId(id);
        usersRepository.save(user);
    }

    public List<User> findAll() {
        return usersRepository.findAll();
    }

    public User findOne(long id) {
        return usersRepository.findById((int) id)
                .stream()
                .findFirst()
                .orElse(null);
    }
}

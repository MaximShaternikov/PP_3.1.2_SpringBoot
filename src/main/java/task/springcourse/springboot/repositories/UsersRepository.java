package task.springcourse.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import task.springcourse.springboot.model.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {
}

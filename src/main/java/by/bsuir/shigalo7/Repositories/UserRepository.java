package by.bsuir.shigalo7.Repositories;

import by.bsuir.shigalo7.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByNameAndPassword(String name, String password);

    public User findByName(String username);
}

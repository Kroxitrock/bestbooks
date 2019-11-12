package bg.bestbooks.components.user.repository;

import bg.bestbooks.components.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Long> {

}

package bg.bestbooks.BestBooks.components.user.repository;

import bg.bestbooks.BestBooks.components.user.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<User, Long> {

}

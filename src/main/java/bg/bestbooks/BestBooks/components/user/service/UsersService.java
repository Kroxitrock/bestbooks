package bg.bestbooks.BestBooks.components.user.service;

import bg.bestbooks.BestBooks.components.user.model.User;
import bg.bestbooks.BestBooks.components.user.repository.UsersRepository;
import bg.bestbooks.BestBooks.components.user.service.dto.UserDto;
import java.util.List;
import java.util.NoSuchElementException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

  private final UsersRepository usersRepository;

  @Autowired
  public UsersService(UsersRepository usersRepository) {
    this.usersRepository = usersRepository;
  }

  public User findById(Long id) {
    return usersRepository.findById(id).orElse(null);
  }

  public List<User> findAll() {
    return usersRepository.findAll();
  }

  @Transactional
  public User createUser(User user) {
    return usersRepository.save(user);
  }

  @Transactional
  public User register(UserDto dto) {
    User user = new User(dto.getUsername(), dto.getPassword());
    return usersRepository.save(user);
  }

  @Transactional
  public User updateUser(Long id, User user) {
    if (usersRepository.existsById(id)) {
      user.setId(id);
      return usersRepository.save(user);
    }

    throw new NoSuchElementException();
  }
}

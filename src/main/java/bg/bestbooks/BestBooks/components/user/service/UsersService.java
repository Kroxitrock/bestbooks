package bg.bestbooks.BestBooks.components.user.service;

import bg.bestbooks.BestBooks.components.user.model.User;
import bg.bestbooks.BestBooks.components.user.repository.UsersRepository;
import bg.bestbooks.BestBooks.components.user.service.dto.InputUserDto;
import bg.bestbooks.BestBooks.components.user.service.dto.OutputUserDto;
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

  public OutputUserDto findById(Long id) {
    return new OutputUserDto(usersRepository.findById(id).orElse(null));
  }

  public List<OutputUserDto> findAll() {
    return OutputUserDto.transferToDtoList(usersRepository.findAll());
  }

  @Transactional
  public OutputUserDto createUser(InputUserDto inputUser) {
    User user = inputUser.transformToEntity();
    user = usersRepository.save(user);
    return new OutputUserDto(user);
  }

  @Transactional
  public OutputUserDto register(InputUserDto inputUser) {
    User user = inputUser.transformToEntity();
    user = usersRepository.save(user);
    user.setAuthorities(User.getUserAuthority());
    return new OutputUserDto(user);
  }

  @Transactional
  public OutputUserDto updateUser(Long id, InputUserDto inputUser) {
    if (usersRepository.existsById(id)) {
      User user = inputUser.transformToEntity();
      user.setId(id);
      user = usersRepository.save(user);
      return new OutputUserDto(user);
    }

    throw new NoSuchElementException();
  }
}

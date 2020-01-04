package bg.bestbooks.BestBooks.components.user.service;

import bg.bestbooks.BestBooks.components.user.model.Authority;
import bg.bestbooks.BestBooks.components.user.model.User;
import bg.bestbooks.BestBooks.components.user.repository.UsersRepository;
import bg.bestbooks.BestBooks.components.user.service.dto.UserDto;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

  private final UsersRepository usersRepository;

  @Autowired
  public CustomUserDetailsService(UsersRepository usersRepository) {
    this.usersRepository = usersRepository;
  }

  private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
    String[] userAuthorities = user.getAuthorities().stream().map(Authority::getName).toArray(String[]::new);
    return AuthorityUtils.createAuthorityList(userAuthorities);
  }

  @Override
  public UserDetails loadUserByUsername(String s) {
    User user = usersRepository.findByUsername(s)
        .orElseThrow(() -> new UsernameNotFoundException("username " + s + " not found"));
    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
        getAuthorities(user));
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

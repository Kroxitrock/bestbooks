package bg.bestbooks.BestBooks.components.user.service;

import bg.bestbooks.BestBooks.components.user.model.Authority;
import bg.bestbooks.BestBooks.components.user.model.User;
import bg.bestbooks.BestBooks.components.user.repository.UsersRepository;
import java.util.Collection;
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
}

package bg.bestbooks.BestBooks.components.user.controller;

import bg.bestbooks.BestBooks.components.user.model.User;
import bg.bestbooks.BestBooks.components.user.service.UsersService;
import bg.bestbooks.BestBooks.components.user.service.dto.UserDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {

  private final UsersService usersService;

  @Autowired
  public UsersController(UsersService usersService) {
    this.usersService = usersService;
  }

  @GetMapping
  public List<User> getUsers() {
    return usersService.findAll();
  }

  @GetMapping(path = "/{id}")
  public User getUser(@PathVariable("id") Long id) {
    return usersService.findById(id);
  }

  @PostMapping
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public User createUser(@RequestBody User user) {
    return usersService.createUser(user);
  }

  @PostMapping(path = "/register")
  public User register(@RequestBody UserDto user) {
    return usersService.register(user);
  }

  @PutMapping(path = "/{id}")
  @PreAuthorize("hasAuthority('ADMIN') or id = authentication.principal.id")
  public User updateUser(@PathVariable("id") Long id, @RequestBody User user) {
    return usersService.updateUser(id, user);
  }
}

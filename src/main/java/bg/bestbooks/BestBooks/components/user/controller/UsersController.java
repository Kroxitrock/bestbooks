package bg.bestbooks.BestBooks.components.user.controller;

import bg.bestbooks.BestBooks.components.user.model.User;
import bg.bestbooks.BestBooks.components.user.service.CustomUserDetailsService;
import bg.bestbooks.BestBooks.components.user.service.dto.UserDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("users")
public class UsersController {

  private final CustomUserDetailsService userService;

  @Autowired
  public UsersController(CustomUserDetailsService userService) {
    this.userService = userService;
  }

  @GetMapping
  public List<User> getUsers() {
    return userService.findAll();
  }

  @GetMapping(path = "/{id}")
  public User getUser(@PathVariable Long id) {
    return userService.findById(id);
  }

  @PostMapping
  public User createUser(@RequestBody User user) {
    return userService.createUser(user);
  }

  @PostMapping(path = "register")
  public User register(@RequestBody UserDto user) {
    return userService.register(user);
  }

  @PutMapping(path = "/{id}")
  public User updateUser(@PathVariable Long id, @RequestBody User user) {
    return userService.updateUser(id, user);
  }
}

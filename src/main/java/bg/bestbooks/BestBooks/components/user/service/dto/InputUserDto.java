package bg.bestbooks.BestBooks.components.user.service.dto;

import bg.bestbooks.BestBooks.components.user.model.User;

public class InputUserDto {

  private String username;
  private String password;

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public User transformToEntity() {
    User user = new User();
    user.setUsername(username);
    user.setPassword(password);
    return user;
  }
}

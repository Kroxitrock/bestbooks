package bg.bestbooks.BestBooks.components.user.service.dto;

import bg.bestbooks.BestBooks.components.user.model.User;
import java.util.ArrayList;
import java.util.List;

public class OutputUserDto {

  private String username;
  private String password;

  public OutputUserDto(User user) {
    if (user == null) {
      return;
    }
    username = user.getUsername();
    password = user.getPassword();
  }

  public static List<OutputUserDto> transferToDtoList(List<User> users) {
    List<OutputUserDto> outputUserDtos = new ArrayList<>();
    users.forEach((user)
        -> outputUserDtos.add(new OutputUserDto(user)));
    return outputUserDtos;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }
}

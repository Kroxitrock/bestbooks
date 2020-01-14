package bg.bestbooks.BestBooks.common;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurtyController {

  @GetMapping(path = "/csrf")
  private CsrfToken getCsrfToken(CsrfToken csrfToken) {
    return csrfToken;
  }
}

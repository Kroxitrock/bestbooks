package bg.bestbooks.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {
  @GetMapping
  public ResponseEntity<String> healthCheck() {
    return ResponseEntity.ok("System is up and running.");
  }
}

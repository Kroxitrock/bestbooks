package bg.bestbooks.BestBooks.common;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DefaultController {

  @GetMapping
  public String index() {
    return "index";
  }

  @GetMapping(path = "/books/{id}")
  public String getBooks(Model model, @PathVariable Integer id) {
    model.addAttribute("id", id);
    return "book";
  }
}

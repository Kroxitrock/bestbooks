package bg.bestbooks.BestBooks.components.book.repository;

import bg.bestbooks.BestBooks.components.book.model.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {

  List<Book> findByNameContaining(String name);
}

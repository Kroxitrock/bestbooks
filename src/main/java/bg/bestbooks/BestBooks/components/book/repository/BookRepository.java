package bg.bestbooks.BestBooks.components.book.repository;

import bg.bestbooks.BestBooks.components.book.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

}

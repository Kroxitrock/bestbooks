package bg.bestbooks.BestBooks.components.book.repository;

import bg.bestbooks.BestBooks.components.book.model.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Integer> {

}

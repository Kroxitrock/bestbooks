package bg.bestbooks.BestBooks.components.author.repository;

import bg.bestbooks.BestBooks.components.author.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorsRepository extends JpaRepository<Author, Integer> {

}

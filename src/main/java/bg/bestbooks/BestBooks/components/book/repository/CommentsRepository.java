package bg.bestbooks.BestBooks.components.book.repository;

import bg.bestbooks.BestBooks.components.book.model.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends JpaRepository<Comment, Long> {

  List<Comment> findAllByBook_Id(Integer id);

}

package bg.bestbooks.BestBooks.components.book.service;

import bg.bestbooks.BestBooks.components.book.model.Book;
import bg.bestbooks.BestBooks.components.book.model.Comment;
import bg.bestbooks.BestBooks.components.book.repository.BooksRepository;
import bg.bestbooks.BestBooks.components.book.repository.CommentsRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BooksService {

  private final BooksRepository booksRepository;

  private final CommentsRepository commentsRepository;

  @Autowired
  public BooksService(BooksRepository booksRepository, CommentsRepository commentsRepository) {
    this.booksRepository = booksRepository;
    this.commentsRepository = commentsRepository;
  }

  public List<Book> findBooks(String query) {
    return booksRepository.findByNameContaining(query);
  }

  public Optional<Book> findById(Integer id) {
    return booksRepository.findById(id);
  }

  @Transactional
  public Book create(Book book) {
    return booksRepository.save(book);
  }

  @Transactional
  public Book update(Integer id, Book book) {
    book.setId(id);
    return booksRepository.save(book);
  }

  @Transactional
  public void deleteById(Integer id) {
    booksRepository.deleteById(id);
  }

  public List<Comment> findBooks(Integer bookId) {
    return commentsRepository.findAllByBook_Id(bookId);
  }

  @Transactional
  public Comment createComment(Comment comment) {
    return commentsRepository.save(comment);
  }

  @Transactional
  public Comment updateComment(Long id, Comment comment) {
    comment.setId(id);
    return commentsRepository.save(comment);
  }

  @Transactional
  public void deleteComment(Long id) {
    commentsRepository.deleteById(id);
  }
}

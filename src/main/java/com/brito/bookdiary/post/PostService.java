package com.brito.bookdiary.post;

import com.brito.bookdiary.book.Book;
import com.brito.bookdiary.book.BookService;
import com.brito.bookdiary.exception.InvalidDataException;
import com.brito.bookdiary.post.dto.PostRequestDTO;
import com.brito.bookdiary.post.dto.PostRespondeDTO;
import com.brito.bookdiary.user.User;
import com.brito.bookdiary.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class PostService {

    private final UserService userService;
    private final BookService bookService;
    private final PostRepository postRepository;

    @Transactional
    public PostRespondeDTO createPost(PostRequestDTO dto){

        if (!isToThePageAfterFromPage(dto)){
            throw new InvalidDataException(String.format("The page final can't be grant than the inicial page."));
        }

        String emailAuthor = dto.userEmail();

        User userAuthor = userService.findOrThrow(emailAuthor);
        Book book = bookService.findOrThrow(dto.bookId());

        Post post = new Post();
        post.setUserAuthor(userAuthor);
        post.setBook(book);
        post.setComment(dto.comment());
        post.setTimestamp(LocalDateTime.now());
        post.setFromPage(dto.fromPage());
        post.setToThePage(dto.toThePage());

        post = postRepository.save(post);

        bookService.addPostToBook(post);

        return new PostRespondeDTO(post);
    }

    public List<PostRespondeDTO> getAllPostByUser(String userEmail){

        User user = userService.findOrThrow(userEmail);

        return postRepository.findAllByUserAuthorId(user.getId())
                .stream()
                .sorted(Comparator.comparing(Post::getTimestamp).reversed())
                .map(PostRespondeDTO::new)
                .toList();
    }

    public boolean isToThePageAfterFromPage(PostRequestDTO dto){
        return dto.toThePage() >= dto.fromPage();
    }
}

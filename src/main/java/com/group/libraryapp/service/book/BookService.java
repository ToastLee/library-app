package com.group.libraryapp.service.book;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.book.BookRepository;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository;
import com.group.libraryapp.dto.book.request.BookCreateRequest;
import com.group.libraryapp.dto.book.request.BookLoanRequest;
import com.group.libraryapp.dto.book.request.BookReturnRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final UserLoanHistoryRepository userLoanHistoryRepository;
    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository,
                       UserLoanHistoryRepository userLoanHistoryRepository,
                       UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userLoanHistoryRepository = userLoanHistoryRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveBook(BookCreateRequest request) {
        bookRepository.save(new Book(request.getName()));
    }

    @Transactional
    public void loanBook(BookLoanRequest request) {
        //1.책 정보를 가져온다
        Book book = bookRepository.findByName(request.getBookName())
                .orElseThrow(IllegalArgumentException::new);

        //2. 대출 기록 정보를 확인해서 대출 중인지 확인
        //3. 확인 했는데 대출 중이면 예외 발생.
        if (userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName(), false)) {
            throw new IllegalArgumentException("대출되어 있는 책입니다.");
        }

        // 4. 유저 정보를 가져옴
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);
        user.loanBook(book.getName());
    }


    @PutMapping("/book/return")
    public void returnBook(@RequestBody BookReturnRequest request) {
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);

        user.returnBook(request.getBookName());
    }

}

package com.example.javaorm.controller;

import com.example.javaorm.model.Author;
import com.example.javaorm.model.Book;
import com.example.javaorm.model.request.AuthorCreationRequest;
import com.example.javaorm.model.request.BookCreationRequest;
import com.example.javaorm.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import io.swagger.annotations.*;

@Api(tags = "forTest", description = "01")
@RestController
@RequestMapping(value = "/api/library")
@RequiredArgsConstructor
@CrossOrigin("*")
public class LibararyController {
    private final LibraryService libraryService;

    @PostMapping("/author")
    @ApiOperation(value = "작가 목록 추가", notes = "작가 목록 추가 기능을 테스트 합니다.")
    public ResponseEntity<Author> createAuthor(@RequestBody AuthorCreationRequest request) {
        return ResponseEntity.ok(libraryService.createAuthor(request));
    }

    @GetMapping("/author")
    @ApiOperation(value = "작가 목록 조회", notes = "작가 목록 조회 기능을 테스트 합니다.")
    public ResponseEntity<List<Author>> readAuthors() {
        return ResponseEntity.ok(libraryService.readAuthors());
    }

    @GetMapping("/book")
    @ApiOperation(value = "국제 표준 도서 번호로 도서 정보 조회", notes = "국제 표준 도서 번호로 도서 정보를 조회하는 기능을 테스트 합니다.")
    public ResponseEntity readBooks(@RequestParam(required = false) String isbn) {
        if (isbn == null) {
            return ResponseEntity.ok(libraryService.readBooks());
        }
        return ResponseEntity.ok(libraryService.readBook(isbn));
    }

    @GetMapping("/book/{bookId}")
    @ApiOperation(value = "도서 등록 번호로 도서 정보 조회", notes = "도서 등록 번호로 도서 정보를 조회하는 기능을 테스트 합니다.")
    public ResponseEntity<Book> readBook(@PathVariable Long bookId) {
        return ResponseEntity.ok(libraryService.readBook(bookId));
    }

    @PostMapping("/book")
    @ApiOperation(value = "도서 목록 추가", notes = "도서 목록 추가 기능을 테스트 합니다.")
    public ResponseEntity<Book> createBook(@RequestBody BookCreationRequest request) {
        return ResponseEntity.ok(libraryService.createBook(request));
    }

    @PatchMapping("/book/{bookId}")
    @ApiOperation(value = "특정 도서 정보 수정", notes = "특정 도서의 정보 기능을 테스트 합니다.")
    public ResponseEntity<Book> updateBook(@PathVariable("bookId") Long bookId,
            @RequestBody BookCreationRequest request) {
        return ResponseEntity.ok(libraryService.updateBook(bookId, request));
    }

    @DeleteMapping("/book/{bookId}")
    @ApiOperation(value = "도서 목록 삭제", notes = "도서 목록 삭제 기능을 테스트 합니다.")
    public ResponseEntity<Void> deleteBook(@PathVariable Long bookId) {
        libraryService.deleteBook(bookId);
        return ResponseEntity.ok().build();
    }
}

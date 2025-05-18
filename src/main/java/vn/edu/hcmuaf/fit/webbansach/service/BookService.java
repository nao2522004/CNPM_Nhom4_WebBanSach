package vn.edu.hcmuaf.fit.webbansach.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.hcmuaf.fit.webbansach.model.dto.BookDto;
import vn.edu.hcmuaf.fit.webbansach.model.entity.Books;
import vn.edu.hcmuaf.fit.webbansach.model.entity.Categories;
import vn.edu.hcmuaf.fit.webbansach.repository.BookRepository;
import vn.edu.hcmuaf.fit.webbansach.repository.CategoryRepository;

import java.util.List;

// BookService.java
@Service
public class BookService {
    @Autowired
    private BookRepository bookRepo;
    @Autowired private CategoryRepository categoryRepo;

    @Transactional
    public Books createBook(BookDto dto) {
        Books book = new Books();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setDescription(dto.getDescription());
        book.setPrice(dto.getPrice());
        book.setStockQty(dto.getStockQty());
        book.setPublishedDate(dto.getPublishedDate());
        book.setImageUrl(
                dto.getImageUrl() != null && !dto.getImageUrl().isBlank()
                        ? dto.getImageUrl()
                        : "/images/books/default.jpg"
        );

        // 1. Lưu sách trước để sinh id
        Books savedBook = bookRepo.save(book);

        // 2. Lấy category theo id
        List<Categories> cats = categoryRepo.findAllById(dto.getCategoryIds());

        // 3. Gán lại category
        savedBook.getCategories().addAll(cats);

        // 4. Lưu lại lần nữa để cập nhật mối quan hệ N-N
        return bookRepo.save(savedBook);
    }

}

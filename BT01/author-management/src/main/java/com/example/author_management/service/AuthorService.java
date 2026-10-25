package com.example.author_management.service;

import com.example.author_management.model.Author;
import com.example.author_management.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author createAuthor(Author author) {
        authorRepository.save(author);
        return author;
    }

    public Author getAuthorById(int id) {
        return authorRepository.findById(id);
    }

    public Author updateAuthor(int id, Author request) {
        Author existingAuthor = authorRepository.findById(id);
        if (existingAuthor != null) {
            existingAuthor.setName(request.getName());
            existingAuthor.setEmail(request.getEmail());
            return existingAuthor;
        }
        return null;
    }

    // Xóa + chặn tài khoản Admin
    public String deleteAuthor(int id) {
        Author author = authorRepository.findById(id);
        if (author == null) {
            return "NOT_FOUND";
        }
        // Kiểm tra nếu tên là "Admin" (không phân biệt hoa thường)
        if (author.getName().equalsIgnoreCase("Admin")) {
            return "ADMIN_RESTRICTED";
        }
        authorRepository.delete(id);
        return "SUCCESS";
    }

    // Tìm theo từ khóa (không phân biệt hoa thường, chứa kw)
    public List<Author> searchAuthors(String keyword) {
        List<Author> result = new ArrayList<>();
        List<Author> allAuthors = authorRepository.findAll();
        for (Author a : allAuthors) {
            if (keyword == null || a.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(a);
            }
        }
        return result;
    }
}

package com.example.author_management.repository;

import com.example.author_management.model.Author;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AuthorRepository {
    private List<Author> authorList = new ArrayList<>();

    public AuthorRepository() {
        // Khởi tạo sẵn dữ liệu mẫu (có tài khoản Admin để kiểm thử tính năng xóa)
        authorList.add(new Author(1, "Admin", "admin@library.com"));
        authorList.add(new Author(2, "Nguyễn Nhật Ánh", "anh.nguyen@library.com"));
        authorList.add(new Author(3, "Nam Cao", "namcao@email.com"));
    }

    public List<Author> findAll() {
        return authorList;
    }

    // Thêm
    public void save(Author author) {
        authorList.add(author);
    }

    // Tìm
    public Author findById(int id) {
        for (Author a : authorList) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }

    // Xóa
    public void delete(int id) {
        authorList.removeIf(a -> a.getId() == id);
    }
}

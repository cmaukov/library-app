package com.bmstechpro.repository;
/* library-app
 * @created 10/07/2022
 * @author Konstantin Staykov
 */

import com.bmstechpro.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class BookDao extends AbstractDao implements Dao<Book> {
    @Override
    public Optional<Book> findById(long id) {
        Optional<Book> book = Optional.empty();
        String sql = "SELECT ID, TITLE FROM BOOK WHERE ID = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rset = ps.executeQuery()) {
                Book resultBook = new Book();
                if (rset.next()) {
                    resultBook.setId(rset.getLong("id"));
                    resultBook.setTitle(rset.getString("title"));
                    book = Optional.of(resultBook);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return book;
    }

    @Override
    public List<Book> findAll() {

        List<Book> books = Collections.emptyList();

        String sql = "SELECT * FROM BOOK";
        try (Connection con = getConnection();
             Statement stmt = con.createStatement();
             ResultSet rset = stmt.executeQuery(sql)) {

            books = new ArrayList<>();

            while (rset.next()) {
                Book book = new Book();
                book.setId(rset.getLong("id"));
                book.setTitle(rset.getString("title"));
                books.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
}

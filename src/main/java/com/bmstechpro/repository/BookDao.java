package com.bmstechpro.repository;
/* library-app
 * @created 10/07/2022
 * @author Konstantin Staykov
 */

import com.bmstechpro.model.Book;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class BookDao extends JdbcQueryTemplate<Book> implements Dao<Book> {
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
        String sql = "SELECT * FROM BOOK";
        return queryForList(sql);
    }


//
//    @Override
//    public List<Book> findAll() {
//
//        List<Book> books = Collections.emptyList();
//
//        String sql = "SELECT * FROM BOOK";
//        try (Connection con = getConnection();
//             Statement stmt = con.createStatement();
//             ResultSet rset = stmt.executeQuery(sql)) {
//
//            books = new ArrayList<>();
//
//            while (rset.next()) {
//                Book book = new Book();
//                book.setId(rset.getLong("id"));
//                book.setTitle(rset.getString("title"));
//                books.add(book);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return books;
//    }

    @Override
    public Book create(Book book) {
        String sql = "INSERT INTO BOOK (TITLE) VALUES (?)";
        try (Connection conn = getConnection();
             // Here PreparedStatement will return the generated keys
             PreparedStatement prepStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            prepStmt.setString(1, book.getTitle());
            prepStmt.executeUpdate();
            // Here we are getting the generated key from the database!
            try (ResultSet genKeys = prepStmt.getGeneratedKeys()) {
                if (genKeys.next()) {
                    // Here we cannot use the column name and just have to use the first column
                    book.setId(genKeys.getLong(1));
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return book;
    }

    @Override
    public Book update(Book book) {
        String sql = "UPDATE BOOK SET TITLE = ? WHERE ID = ?";
        try (Connection conn = getConnection();
             // Here PreparedStatement will return the generated keys
             PreparedStatement prepStmt = conn.prepareStatement(sql)) {
            prepStmt.setString(1, book.getTitle());
            prepStmt.setLong(2, book.getId());
            prepStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return book;
    }

    @Override
    public int[] update(List<Book> books) {
        int[] records = {};
        String sql = "UPDATE BOOK SET TITLE = ?, RATING = ? WHERE ID = ? ";

        try (Connection conn = getConnection();
             PreparedStatement prepStmt = conn.prepareStatement(sql)) {
            for (Book book : books) {
                prepStmt.setString(1, book.getTitle());
                prepStmt.setInt(2, book.getRating());
                prepStmt.setLong(3, book.getId());
                prepStmt.addBatch();
            }
            records = prepStmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return records;
    }

    @Override
    public int delete(Book book) {
        String sql = "DELETE FROM BOOK WHERE ID = ?";
        int rowsAffected = 0;
        try (Connection conn = getConnection();
             PreparedStatement prepStmt = conn.prepareStatement(sql)) {
            prepStmt.setLong(1, book.getId());
            rowsAffected = prepStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }


    @Override
    public Book mapItem(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getLong("id"));
        book.setTitle(resultSet.getString("title"));
        book.setRating(resultSet.getInt("rating"));
        return book;
    }
}

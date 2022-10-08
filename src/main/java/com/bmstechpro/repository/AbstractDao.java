package com.bmstechpro.repository;
/* library-app
 * @created 10/07/2022
 * @author Konstantin Staykov
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AbstractDao {
    protected Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/library_db";
        String username = "dbuser";
        String password = "dbuser";

        return DriverManager.getConnection(url,username,password);
    }
}

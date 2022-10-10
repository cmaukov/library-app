package com.bmstechpro.repository;
/* library-app
 * @created 10/09/2022
 * @author Konstantin Staykov
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class JdbcQueryTemplate<T> extends AbstractDao {

    public JdbcQueryTemplate() {
    }

    public List<T> queryForList(String sql) {
        List<T> items = new ArrayList<>();
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {
            while (resultSet.next()) {
                items.add(mapItem(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    public abstract T mapItem(ResultSet resultSet) throws SQLException;
}

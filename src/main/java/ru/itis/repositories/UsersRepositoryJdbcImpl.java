package ru.itis.repositories;

import ru.itis.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    private Connection connection;

    public UsersRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }

      public void save(User entity) {
        try {
            PreparedStatement resultAuthorization = connection.prepareStatement("insert into Registration (firstName, lastName, email, username, password) values (?,?,?,?,?);");
            resultAuthorization.setString(1, entity.getFirstName());
            resultAuthorization.setString(2, entity.getLastName());
            resultAuthorization.setString(3, entity.getEmail());
            resultAuthorization.setString(4, entity.getUsername());
            resultAuthorization.setString(5, entity.getPassword());
            resultAuthorization.executeUpdate();
            resultAuthorization.close();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}


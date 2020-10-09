package ru.itis.repositories;

import ru.itis.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    private Connection connection;

    public UsersRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }

      public void save(User entity) {
        try {
            PreparedStatement resultAuthorization = connection.prepareStatement("insert into registration (firstName, lastName, email, username, password) values (?,?,?,?,?);");
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
    private  static final String SQL_SELECT_ALL_FROM_USERS = "select * from registration";
    @Override
    public List<User> findAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(SQL_SELECT_ALL_FROM_USERS);
            List<User> users = new ArrayList<>();
            while (result.next()) {
                users.add(User.builder()
                        .firstName(result.getString("firstName"))
                        .lastName(result.getString("lastName"))
                        .email(result.getString("email")).
                                username(result.getString("username"))
                        .password(result.getString("password"))
                        .build());
            }
            return users;
        } catch (SQLException exception) {
            throw new IllegalStateException(exception);
        }
    }


}


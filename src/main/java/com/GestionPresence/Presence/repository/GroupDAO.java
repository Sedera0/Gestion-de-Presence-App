package com.GestionPresence.Presence.repository;

import com.GestionPresence.Presence.config.DatabaseConnection;
import com.GestionPresence.Presence.entity.Group;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GroupDAO {
    public void addGroup(Group group) throws SQLException {
        String query = "INSERT INTO group (group_name) VALUES (?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, group.getGroupName());
            statement.executeUpdate();
        }
    }

    public Group getGroup(int groupId) throws SQLException {
        String query = "SELECT * FROM group WHERE group_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, groupId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Group group = new Group();
                group.setGroupId(resultSet.getInt("group_id"));
                group.setGroupName(resultSet.getString("group_name"));
                return group;
            }
        }
        return null;
    }

    public List<Group> getAllGroup() throws SQLException {
        List<Group> groups = new ArrayList<>();
        String query = "SELECT * FROM group";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Group group = new Group();
                group.setGroupId(resultSet.getInt("group_id"));
                group.setGroupName(resultSet.getString("group_name"));
                groups.add(group);
            }
        }
        return groups;
    }

    public void updateGroup(Group group) throws SQLException {
        String query = "UPDATE group SET group_name = ? WHERE group_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, group.getGroupName());
            statement.setInt(2, group.getGroupId());
            statement.executeUpdate();
        }
    }

    public void deleteGroup(int groupId) throws SQLException {
        String query = "DELETE FROM group WHERE group_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, groupId);
            statement.executeUpdate();
        }
    }
}

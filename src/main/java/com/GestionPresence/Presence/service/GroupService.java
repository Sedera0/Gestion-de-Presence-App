package com.GestionPresence.Presence.service;

import com.GestionPresence.Presence.entity.Group;
import com.GestionPresence.Presence.repository.GroupDAO;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Service
public class GroupService {

    private GroupDAO repository;

    public GroupService(GroupDAO repository) {
        this.repository = repository;
    }

    public void addGroup(Group group) throws SQLException {
        repository.addGroup(group);
    }

    public Group getGroup(int groupId) throws SQLException {
        return repository.getGroup(groupId);
    }

    public List<Group> getAllGroup() throws SQLException {
        return repository.getAllGroup();
    }

    public void updateGroup(Group group) throws SQLException {
        repository.updateGroup(group);
    }

    public void deleteGroup(int groupId) throws SQLException {
        repository.deleteGroup(groupId);
    }
}

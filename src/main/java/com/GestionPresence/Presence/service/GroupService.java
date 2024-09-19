package com.GestionPresence.Presence.service;

import com.GestionPresence.Presence.entity.Group;
import com.GestionPresence.Presence.repository.GroupDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class GroupService {
    private final GroupDAO groupDAO;

    public GroupService(GroupDAO groupDAO) {
        this.groupDAO = groupDAO;
    }

    public void addGroup(Group group) throws SQLException {
        groupDAO.addGroup(group);
    }

    public Group getGroup(int groupId) throws SQLException {
        return groupDAO.getGroup(groupId);
    }

    public List<Group> getAllGroup() throws SQLException {
        return groupDAO.getAllGroup();
    }

    public void updateGroup(Group group) throws SQLException {
        groupDAO.updateGroup(group);
    }

    public void deleteGroup(int groupId) throws SQLException {
        groupDAO.deleteGroup(groupId);
    }
}

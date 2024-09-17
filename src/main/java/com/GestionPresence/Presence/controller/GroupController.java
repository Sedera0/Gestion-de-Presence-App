package com.GestionPresence.Presence.controller;

import com.GestionPresence.Presence.entity.Group;
import com.GestionPresence.Presence.service.GroupService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/group")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    public String addGroup(@RequestBody Group group) throws SQLException {
        groupService.addGroup(group);
        return "Group added successfully!";
    }

    @GetMapping("/{groupId}")
    public Group getGroup(@PathVariable int groupId) throws SQLException {
        return groupService.getGroup(groupId);
    }

    @GetMapping
    public List<Group> getAllGroup() throws SQLException {
        return groupService.getAllGroup();
    }

    @PutMapping("/{groupId}")
    public String updateGroup(@PathVariable int groupId, @RequestBody Group group) throws SQLException {
        group.setGroupId(groupId);
        groupService.updateGroup(group);
        return "Group updated successfully!";
    }

    @DeleteMapping("/{groupId}")
    public String deleteGroup(@PathVariable int groupId) throws SQLException {
        groupService.deleteGroup(groupId);
        return "Group deleted successfully!";
    }
}

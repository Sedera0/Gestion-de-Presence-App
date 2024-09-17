package com.GestionPresence.Presence.controller;

import com.GestionPresence.Presence.entity.Group;
import com.GestionPresence.Presence.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    public ResponseEntity<String> addGroup(@RequestBody Group group) {
        try {
            groupService.addGroup(group);
            return new ResponseEntity<>("Group added successfully!", HttpStatus.CREATED);
        } catch (SQLException e) {
            return new ResponseEntity<>("Failed to add group: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<Group> getGroup(@PathVariable int groupId) {
        try {
            Group group = groupService.getGroup(groupId);
            if (group != null) {
                return new ResponseEntity<>(group, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (SQLException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Group>> getAllGroup() {
        try {
            List<Group> groups = groupService.getAllGroup();
            return new ResponseEntity<>(groups, HttpStatus.OK);
        } catch (SQLException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{groupId}")
    public ResponseEntity<String> updateGroup(@PathVariable int groupId, @RequestBody Group group) {
        try {
            group.setGroupId(groupId);
            groupService.updateGroup(group);
            return new ResponseEntity<>("Group updated successfully!", HttpStatus.OK);
        } catch (SQLException e) {
            return new ResponseEntity<>("Failed to update group: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{groupId}")
    public ResponseEntity<String> deleteGroup(@PathVariable int groupId) {
        try {
            groupService.deleteGroup(groupId);
            return new ResponseEntity<>("Group deleted successfully!", HttpStatus.NO_CONTENT);
        } catch (SQLException e) {
            return new ResponseEntity<>("Failed to delete group: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

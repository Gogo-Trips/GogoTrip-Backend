package com.example.gogotrips.forum.api;

import com.example.gogotrips.forum.domain.service.ForumService;
import com.example.gogotrips.forum.resource.ForumResource;
import com.example.gogotrips.forum.resource.ForumResponseResource;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/forums")
@RequiredArgsConstructor
public class ForumController {
    private final ForumService forumService;

    @PostMapping
    public ResponseEntity<ForumResponseResource> createForum(@Valid @RequestBody ForumResource forumResource) {
        ForumResponseResource forumResponseResource = forumService.createForum(forumResource);
        return new ResponseEntity<>(forumResponseResource, HttpStatus.CREATED);
    }

    @GetMapping("/{forumId}")
    public ResponseEntity<ForumResponseResource> getForumById(@PathVariable Long forumId) {
        ForumResponseResource forumResponseResource = forumService.getForumById(forumId);
        return new ResponseEntity<>(forumResponseResource, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ForumResponseResource>> getAllForums() {
        List<ForumResponseResource> forumResponseResources = forumService.getAllForums();
        return new ResponseEntity<>(forumResponseResources, HttpStatus.OK);
    }

    @PutMapping("/{forumId}")
    public ResponseEntity<ForumResponseResource> updateForum(
            @PathVariable Long forumId,
            @Valid @RequestBody ForumResource forumResource) {
        ForumResponseResource forumResponseResource = forumService.updateForum(forumId, forumResource);
        return new ResponseEntity<>(forumResponseResource, HttpStatus.OK);
    }

    @DeleteMapping("/{forumId}")
    public ResponseEntity<Void> deleteForum(@PathVariable Long forumId) {
        forumService.deleteForum(forumId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

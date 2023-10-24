package com.example.gogotrips.forum.domain.service;

import com.example.gogotrips.forum.resource.ForumResource;
import com.example.gogotrips.forum.resource.ForumResponseResource;

import java.util.List;

public interface ForumService {
    List<ForumResponseResource> getAllForums();
    ForumResponseResource getForumById(Long forumId);
    ForumResponseResource createForum(ForumResource forumResource);
    ForumResponseResource updateForum(Long forumId, ForumResource forumResource);
    void deleteForum(Long travelerId);
}

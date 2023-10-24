package com.example.gogotrips.forum.domain.service;

import com.example.gogotrips.forum.domain.entity.Forum;
import com.example.gogotrips.forum.domain.persistence.ForumRepository;
import com.example.gogotrips.forum.mappers.ForumMapper;
import com.example.gogotrips.forum.resource.ForumResource;
import com.example.gogotrips.forum.resource.ForumResponseResource;
import com.example.gogotrips.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ForumServiceImpl implements ForumService{
    private final ForumRepository forumRepository;

    private final ForumMapper forumMapper;

    @Override
    public List<ForumResponseResource> getAllForums() {
        List<Forum> forums = forumRepository.findAll();
        return forumMapper.entityListToResponseResourceList(forums);
    }

    @Override
    public ForumResponseResource getForumById(Long forumId) {
        Forum forum = forumRepository.findById(forumId)
                .orElseThrow(() -> new ResourceNotFoundException("Forum not found with id: " + forumId));
        return forumMapper.entityToResponseResource(forum);
    }

    @Transactional
    @Override
    public ForumResponseResource createForum(ForumResource forumResource) {
        Forum forum = forumMapper.resourceToEntity(forumResource);
        forum = forumRepository.save(forum);

        return forumMapper.entityToResponseResource(forum);
    }

    @Transactional
    @Override
    public ForumResponseResource updateForum(Long forumId, ForumResource forumResource) {
        Optional<Forum> optionalforum = forumRepository.findById(forumId);

        if (optionalforum.isPresent()) {
            Forum forum = optionalforum.get();

            forum.setTitle(forumResource.getTitle());
            forum.setAmountPhotos(forumResource.getAmountPhotos());
            forum.setAmountPublications(forumResource.getAmountPublications());
            forum.setAmountComments(forumResource.getAmountComments());

            forum = forumRepository.save(forum);
            return forumMapper.entityToResponseResource(forum);
        } else {
            throw new ResourceNotFoundException("Forum not found with id: " + forumId);
        }
    }

    @Override
    @Transactional
    public void deleteForum(Long forumId) {
        if (!forumRepository.existsById(forumId)) {
            throw new ResourceNotFoundException("forum not found with id: " + forumId);
        }

        forumRepository.deleteById(forumId);
    }
}

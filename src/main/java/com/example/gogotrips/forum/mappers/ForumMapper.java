package com.example.gogotrips.forum.mappers;

import com.example.gogotrips.forum.domain.entity.Forum;
import com.example.gogotrips.forum.resource.ForumResource;
import com.example.gogotrips.forum.resource.ForumResponseResource;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ForumMapper {
    private final ModelMapper modelMapper;

    public ForumMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Forum resourceToEntity(ForumResource forumResource) {
        return modelMapper.map(forumResource, Forum.class);
    }

    public ForumResource entityToResource(Forum forum) {
        return modelMapper.map(forum, ForumResource.class);
    }

    public ForumResponseResource entityToResponseResource(Forum forum) {
        return modelMapper.map(forum, ForumResponseResource.class);
    }

    public List<Forum> resourceListToEntityList(List<ForumResource> forumResources) {
        return forumResources
                .stream()
                .map(this::resourceToEntity)
                .toList();
    }

    public List<ForumResource> entityListToResourceList(List<Forum> forums) {
        return forums
                .stream()
                .map(this::entityToResource)
                .toList();
    }

    public List<ForumResponseResource> entityListToResponseResourceList(List<Forum> forums) {
        return forums
                .stream()
                .map(this::entityToResponseResource)
                .toList();
    }
}

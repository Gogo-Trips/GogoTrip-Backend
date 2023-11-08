package service;

import com.example.gogotrips.forum.domain.entity.Forum;
import com.example.gogotrips.forum.domain.persistence.ForumRepository;
import com.example.gogotrips.forum.domain.service.ForumServiceImpl;
import com.example.gogotrips.forum.mappers.ForumMapper;
import com.example.gogotrips.forum.resource.ForumResource;
import com.example.gogotrips.forum.resource.ForumResponseResource;
import com.example.gogotrips.shared.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class ForumServiceTest {
    @InjectMocks
    private ForumServiceImpl forumService;

    @Mock
    private ForumRepository forumRepository;

    @Mock
    private ForumMapper forumMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllForums() {
        // Arrange
        List<Forum> forums = new ArrayList<>();
        List<ForumResponseResource> forumResponseResources = new ArrayList<>();

        when(forumRepository.findAll()).thenReturn(forums);
        when(forumMapper.entityListToResponseResourceList(forums)).thenReturn(forumResponseResources);

        // Act
        List<ForumResponseResource> result = forumService.getAllForums();

        // Assert
        assertEquals(forumResponseResources, result);
    }

    @Test
    public void testGetForumById() {
        // Arrange
        Long commentId = 1L;
        Forum forum = new Forum();
        ForumResponseResource responseDto = new ForumResponseResource();

        when(forumRepository.findById(commentId)).thenReturn(Optional.of(forum));
        when(forumMapper.entityToResponseResource(forum)).thenReturn(responseDto);

        // Act
        ForumResponseResource result = forumService.getForumById(commentId);

        // Assert
        assertEquals(responseDto, result);
    }

    @Test
    public void testGetForumByIdNotFound() {
        // Arrange
        Long nonExistentForumId = 999L;

        when(forumRepository.findById(nonExistentForumId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            forumService.getForumById(nonExistentForumId);
        });
    }

    @Test
    public void testCreateForum() {
        // Arrange
        ForumResource forumResource = new ForumResource();
        forumResource.setTitle("Los lugares más visitados en Perú");
        forumResource.setAmountPhotos(5);
        forumResource.setAmountPublications(10);
        forumResource.setAmountComments(4);

        Forum forum = new Forum();
        forum.setTitle(forum.getTitle());
        forum.setAmountPhotos(forum.getAmountPhotos());
        forum.setAmountPublications(forum.getAmountPublications());
        forum.setAmountComments(forum.getAmountComments());

        ForumResponseResource forumResponseResource = new ForumResponseResource();
        forumResponseResource.setId(1L);
        forumResponseResource.setTitle(forumResponseResource.getTitle());
        forumResponseResource.setAmountPhotos(forumResponseResource.getAmountPhotos());
        forumResponseResource.setAmountComments(forumResponseResource.getAmountComments());
        forumResponseResource.setAmountPublications(forumResponseResource.getAmountPublications());

        when(forumMapper.resourceToEntity(forumResource)).thenReturn(forum);
        when(forumRepository.save(forum)).thenReturn(forum);
        when(forumMapper.entityToResponseResource(forum)).thenReturn(forumResponseResource);

        // Act
        ForumResponseResource result = forumService.createForum(forumResource);

        // Assert
        assertEquals(forumResponseResource, result);
    }


    @Test
    public void testUpdateForum() {//Edit
        // Arrange
        Long forumId = 1L;
        ForumResource commentIdDto = new ForumResource();
        Forum forum = new Forum();
        ForumResponseResource responseDto = new ForumResponseResource();

        forum.setTitle("Los 50 mejores lugares del Perú");
        forum.setAmountPhotos(15);
        forum.setAmountPublications(10);
        forum.setAmountComments(14);

        // Configura el comportamiento esperado de los mocks
        when(forumRepository.findById(forumId)).thenReturn(Optional.of(forum));
        when(forumRepository.save(any())).thenReturn(forum); // Cambia esto si es necesario
        when(forumMapper.entityToResponseResource(forum)).thenReturn(responseDto);

        // Act
        ForumResponseResource result = forumService.updateForum(forumId, commentIdDto);

        // Assert
        assertNotNull(result); // Asegúrate de que result no sea nulo
        assertEquals(responseDto, result); // Asegúrate de que result sea igual a responseDto
    }

    @Test
    public void testUpdateForumNotFound() {
        // Arrange
        Long nonExistentForumId = 999L;
        ForumResource forumDto = new ForumResource();
        forumDto.setTitle("UpdatedTitle");
        forumDto.setAmountPhotos(0);
        forumDto.setAmountPublications(0);
        forumDto.setAmountComments(0);

        when(forumRepository.findById(nonExistentForumId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            forumService.updateForum(nonExistentForumId, forumDto);
        });
    }

    @Test
    public void testDeleteForum() {
        // Arrange
        Long forumId = 1L;

        when(forumRepository.existsById(forumId)).thenReturn(true);

        // Act
        forumService.deleteForum(forumId);

        // Assert
        verify(forumRepository, times(1)).deleteById(forumId);
    }

    @Test
    public void testDeleteForumNotFound() {
        // Arrange
        Long nonExistentForumId = 999L;

        when(forumRepository.existsById(nonExistentForumId)).thenReturn(false);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            forumService.deleteForum(nonExistentForumId);
        });
    }
}

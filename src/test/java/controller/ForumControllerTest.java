package controller;

import com.example.gogotrips.forum.api.ForumController;
import com.example.gogotrips.forum.domain.service.ForumService;
import com.example.gogotrips.forum.resource.ForumResource;
import com.example.gogotrips.forum.resource.ForumResponseResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ForumControllerTest {
    @InjectMocks
    private ForumController forumController;

    @Mock
    private ForumService forumService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateForum() throws Exception {
        ForumResource forumResource = new ForumResource();

        ForumResponseResource forumResponseResource = new ForumResponseResource();

        when(forumService.createForum(forumResource)).thenReturn(forumResponseResource);

        ResponseEntity<ForumResponseResource> responseEntity = forumController.createForum(forumResource);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(forumResponseResource, responseEntity.getBody());
    }

    @Test
    public void testGetForumById() {
        Long forumId = 1L;
        ForumResponseResource forumResponseResource = new ForumResponseResource();

        when(forumService.getForumById(forumId)).thenReturn(forumResponseResource);

        ResponseEntity<ForumResponseResource> responseEntity = forumController.getForumById(forumId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(forumResponseResource, responseEntity.getBody());
    }

    @Test
    public void testGetAllForums() {
        List<ForumResponseResource> responseDtoList = new ArrayList<>();

        when(forumService.getAllForums()).thenReturn(responseDtoList);

        ResponseEntity<List<ForumResponseResource>> responseEntity = forumController.getAllForums();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseDtoList, responseEntity.getBody());
    }

    @Test
    public void testUpdateForum() {
        Long forumId = 1L;
        ForumResource forumResource = new ForumResource();

        ForumResponseResource responseDto = new ForumResponseResource();

        when(forumService.updateForum(forumId, forumResource)).thenReturn(responseDto);

        ResponseEntity<ForumResponseResource> responseEntity = forumController.updateForum(forumId, forumResource);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseDto, responseEntity.getBody());
    }

    @Test
    public void testDeleteForum() {
        Long forumId = 1L;

        ResponseEntity<Void> responseEntity = forumController.deleteForum(forumId);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(forumService, times(1)).deleteForum(forumId);
    }
}

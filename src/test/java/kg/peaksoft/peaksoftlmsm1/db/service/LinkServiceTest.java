package kg.peaksoft.peaksoftlmsm1.db.service;

import kg.peaksoft.peaksoftlmsm1.api.dto.link.LinkRequest;
import kg.peaksoft.peaksoftlmsm1.api.dto.link.LinkResponse;
import kg.peaksoft.peaksoftlmsm1.api.dto.mappers.LinkEditMapper;
import kg.peaksoft.peaksoftlmsm1.api.dto.mappers.LinkViewMapper;
import kg.peaksoft.peaksoftlmsm1.db.entity.Link;
import kg.peaksoft.peaksoftlmsm1.db.repository.LinkRepository;
import kg.peaksoft.peaksoftlmsm1.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.DisplayName.class)
class LinkServiceTest {

    @Autowired
    @InjectMocks
    private LinkService linkService;
    @Mock
    private LinkRepository linkRepository;
    @Mock
    private LinkEditMapper linkEditMapper;
    @Mock
    private LinkViewMapper linkViewMapper;

    private Link link1;

    @BeforeEach
    public void setup() {
        link1 = new Link(1L,"link","nameLink",null);
    }

    @Test
    @Order(1)
    @DisplayName("JUnit test for {1} should save link")
    void create() {
        LinkRequest linkRequest = new LinkRequest("link", "nameLink",null);

        Mockito.when(linkEditMapper.mapToEntity(linkRequest)).thenReturn(link1);
        linkService.create(linkRequest);

        Mockito.verify(linkRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(Link.class));
    }

    @Test
    @Order(2)
    @DisplayName("JUnit test for {2} should update Link by id")
    void update() {
        link1.setLink("Name of link");
        LinkRequest linkRequest = new LinkRequest("link","Name of link",null);

        Mockito.when(linkRepository.findById(1L)).thenReturn(Optional.of(link1));
        Mockito.when(linkEditMapper.mapToUpdate(link1, linkRequest)).thenReturn(link1);

        linkService.update(1L, linkRequest);

        Mockito.verify(linkRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(Link.class));

    }

    @Test
    @Order(3)
    @DisplayName("JUnit test for {3} should find link by id")
    void getById() {
        LinkResponse linkResponse = new LinkResponse(1L, "link", "Name of link");

        Mockito.when(linkRepository.findById(1L)).thenReturn(Optional.of(link1));
        Mockito.when(linkViewMapper.mapToResponse(Mockito.any(Link.class))).thenReturn(linkResponse);

        LinkResponse actualLinkResponse = linkService.getById(1L);

        Assertions.assertThat(actualLinkResponse.getId()).isEqualTo(linkResponse.getId());
        Assertions.assertThat(actualLinkResponse.getLink()).isEqualTo(linkResponse.getLink());
    }

    @Test
    @Order(4)
    @DisplayName("JUnit test for {4} delete link by id")
    void delete() {

        Mockito.when(linkRepository.findById(1L)).thenReturn(Optional.of(link1));
        linkService.delete(link1.getId());
    }

    @Test
    void throwExceptionIfEntityIsNotAvailable() {

        assertThrows(ResourceNotFoundException.class, () -> linkService.delete(link1.getId()));
        assertThrows(ResourceNotFoundException.class, () -> linkService.getById(link1.getId()));
    }

    @AfterEach
    public void tearDown() {
        link1 = null;
    }

}
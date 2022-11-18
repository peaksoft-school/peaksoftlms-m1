package kg.peaksoft.peaksoftlmsm1.db.service;

import kg.peaksoft.peaksoftlmsm1.controller.dto.mappers.PresentationEditMapper;
import kg.peaksoft.peaksoftlmsm1.controller.dto.mappers.PresentationViewMapper;
import kg.peaksoft.peaksoftlmsm1.controller.dto.presentation.PresentationRequest;
import kg.peaksoft.peaksoftlmsm1.controller.dto.presentation.PresentationResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.Presentation;
import kg.peaksoft.peaksoftlmsm1.db.repository.PresentationRepository;
import kg.peaksoft.peaksoftlmsm1.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.DisplayName.class)
class PresentationServiceTest {

    @Autowired
    @InjectMocks
    private PresentationService presentationService;
    @Mock
    private PresentationRepository presentationRepository;
    @Mock
    private PresentationEditMapper presentationEditMapper;
    @Mock
    private PresentationViewMapper presentationViewMapper;

    private Presentation presentation;

    @BeforeEach
    public void setup() {
        presentation = new Presentation(1L,"Java","about Java","file",null);
    }

    @Test
    @Order(1)
    @DisplayName("JUnit test for {1} should save presentation")
    void create() {
        PresentationRequest presentationRequest = new PresentationRequest("Java","about Java","file",null);

        Mockito.when(presentationEditMapper.create(presentationRequest)).thenReturn(presentation);
        presentationService.create(presentationRequest);

        Mockito.verify(presentationRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(Presentation.class));
    }

    @Test
    @Order(2)
    @DisplayName("JUnit test for {2} should update Presentation by id")
    void update() {
        presentation.setName("New presentation");
        PresentationRequest presentationRequest = new PresentationRequest("New presentation",
                "about Java", "file",null);

        Mockito.when(presentationRepository.findById(1L)).thenReturn(Optional.of(presentation));
        Mockito.when(presentationEditMapper.update(presentation,presentationRequest)).thenReturn(presentation);

        presentationService.update(1L, presentationRequest);

        Mockito.verify(presentationRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(Presentation.class));
    }

    @Test
    @Order(3)
    @DisplayName("JUnit test for {3} should find Presentation by id")
    void getById() {
        PresentationResponse presentationResponse = new PresentationResponse(1L,
                "New Presentation", "about Java", "file");

        Mockito.when(presentationRepository.findById(1L)).thenReturn(Optional.of(presentation));
        Mockito.when(presentationViewMapper.mapToResponse(Mockito.any(Presentation.class))).thenReturn(presentationResponse);

        PresentationResponse actualPresentationResponse = presentationService.getById(1L);

        org.assertj.core.api.Assertions.assertThat(actualPresentationResponse.getId()).isEqualTo(presentationResponse.getId());
        org.assertj.core.api.Assertions.assertThat(actualPresentationResponse.getName()).isEqualTo(presentationResponse.getName());

    }

    @Test
    @Order(4)
    @DisplayName("JUnit test for {4} delete presentation by id")
    void delete() {

        Mockito.when(presentationRepository.findById(1L)).thenReturn(Optional.of(presentation));
        presentationService.delete(presentation.getId());
    }

    @Test
    void throwExceptionIfEntityIsNotAvailable() {

        assertThrows(ResourceNotFoundException.class, () -> presentationService.delete(presentation.getId()));
        assertThrows(ResourceNotFoundException.class, () -> presentationService.getById(presentation.getId()));
    }

    @AfterEach
    public void tearDown() {
        presentation = null;
    }

}
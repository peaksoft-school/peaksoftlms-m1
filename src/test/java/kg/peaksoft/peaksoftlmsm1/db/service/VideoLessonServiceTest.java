package kg.peaksoft.peaksoftlmsm1.db.service;

import kg.peaksoft.peaksoftlmsm1.controller.dto.mappers.VideoLessonEditMapper;
import kg.peaksoft.peaksoftlmsm1.controller.dto.mappers.VideoLessonViewMapper;
import kg.peaksoft.peaksoftlmsm1.controller.dto.videoLesson.VideoLessonRequest;
import kg.peaksoft.peaksoftlmsm1.controller.dto.videoLesson.VideoLessonResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.VideoLesson;
import kg.peaksoft.peaksoftlmsm1.db.repository.VideoLessonRepository;
import kg.peaksoft.peaksoftlmsm1.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.DisplayName.class)
class VideoLessonServiceTest {

    @Autowired
    @InjectMocks
    private VideoLessonService videoLessonService;
    @Mock
    private VideoLessonRepository repository;
    @Mock
    private VideoLessonEditMapper editMapper;
    @Mock
    private VideoLessonViewMapper viewMapper;

    private VideoLesson videoLesson;

    @BeforeEach
    public void setup() {
        videoLesson = new VideoLesson(1L,"video","lesson","link",null);
    }

    @Test
    @Order(1)
    @DisplayName("JUnit test for {1} should save VideoLesson")
    void create() {
        VideoLessonRequest videoLessonRequest = new VideoLessonRequest("video","lesson","link");

        Mockito.when(editMapper.create(videoLessonRequest)).thenReturn(videoLesson);
        videoLessonService.create(videoLessonRequest);

        Mockito.verify(repository, Mockito.times(1))
                .save(ArgumentMatchers.any(VideoLesson.class));
    }

    @Test
    @Order(2)
    @DisplayName("JUnit test for {2} should update VideoLesson by id")
    void update() {
        videoLesson.setName("VideoLesson");
        VideoLessonRequest videoLessonRequest = new VideoLessonRequest("VideoLesson","lesson","link");

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(videoLesson));
        Mockito.when(editMapper.update(videoLesson, videoLessonRequest)).thenReturn(videoLesson);

        videoLessonService.update(1L, videoLessonRequest);

        Mockito.verify(repository, Mockito.times(1))
                .save(ArgumentMatchers.any(VideoLesson.class));
    }

    @Test
    @Order(3)
    @DisplayName("JUnit test for {3} should find VideoLesson by id")
    void getById() {
        VideoLessonResponse videoLessonResponse = new VideoLessonResponse(1L,"video","lesson","link");

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(videoLesson));
        Mockito.when(viewMapper.mapperResponse(Mockito.any(VideoLesson.class))).thenReturn(videoLessonResponse);

        VideoLessonResponse actualVideoResponse = videoLessonService.getById(1L);

        Assertions.assertThat(actualVideoResponse.getId()).isEqualTo(videoLessonResponse.getId());
        Assertions.assertThat(actualVideoResponse.getName()).isEqualTo(videoLessonResponse.getName());
    }

    @Test
    @Order(4)
    @DisplayName("JUnit test for {4} delete VideoLesson by id")
    void delete() {

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(videoLesson));
        videoLessonService.delete(videoLesson.getId());
    }

    @Test
    void throwExceptionIfEntityIsNotAvailable() {

        assertThrows(ResourceNotFoundException.class, () -> videoLessonService.delete(videoLesson.getId()));
        assertThrows(ResourceNotFoundException.class, () -> videoLessonService.getById(videoLesson.getId()));
    }

    @AfterEach
    public void tearDown() {
        videoLesson = null;
    }

}
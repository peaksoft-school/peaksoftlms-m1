package kg.peaksoft.peaksoftlmsm1.db.service;

import kg.peaksoft.peaksoftlmsm1.controller.dto.lesson.LessonRequest;
import kg.peaksoft.peaksoftlmsm1.controller.dto.lesson.LessonResponse;
import kg.peaksoft.peaksoftlmsm1.controller.dto.mappers.LessonEditMapper;
import kg.peaksoft.peaksoftlmsm1.controller.dto.mappers.LessonViewMapper;
import kg.peaksoft.peaksoftlmsm1.db.entity.Lesson;
import kg.peaksoft.peaksoftlmsm1.db.repository.LessonRepository;
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
class LessonServiceTest {

    @Autowired
    @InjectMocks
    private LessonService lessonService;
    @Mock
    private LessonRepository lessonRepository;
    @Mock
    private LessonEditMapper lessonEditMapper;
    @Mock
    private LessonViewMapper lessonViewMapper;

    private Lesson lesson1;

    @BeforeEach
    public void setup(){
        lesson1 = new Lesson(1L,"Java lesson",null,
                null,null,null,null,null);
    }

    @Test
    @Order(1)
    @DisplayName("JUnit test for {1} should save lesson")
    void create() {
        LessonRequest lessonRequest = new LessonRequest("Java",null,null,null,null,null,null);

        Mockito.when(lessonEditMapper.mapToEntity(lessonRequest)).thenReturn(lesson1);
        lessonService.create(lessonRequest);

        Mockito.verify(lessonRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(Lesson.class));

    }

    @Test
    @Order(2)
    @DisplayName("JUnit test for {2} should update Lesson by id")
    void update() {
        lesson1.setName("Java lesson new");
        LessonRequest lessonRequest = new LessonRequest("Java lesson new",null,null,null,null,null,null);

        Mockito.when(lessonRepository.findById(1L)).thenReturn(Optional.of(lesson1));
        Mockito.when(lessonEditMapper.mapToUpdate(lesson1,lessonRequest)).thenReturn(lesson1);

        lessonService.update(1L, lessonRequest);

        Mockito.verify(lessonRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(Lesson.class));
    }

    @Test
    @Order(3)
    @DisplayName("JUnit test for {3} should find lesson by id")
    void getById() {
        LessonResponse lessonResponse = new LessonResponse(1L,"Java lesson",null,null,null,null,null);

        Mockito.when(lessonRepository.findById(1L)).thenReturn(Optional.of(lesson1));
        Mockito.when(lessonViewMapper.mapToResponse(Mockito.any(Lesson.class))).thenReturn(lessonResponse);

        LessonResponse actualLessonResponse = lessonService.getById(1L);

        Assertions.assertThat(actualLessonResponse.getId()).isEqualTo(lessonResponse.getId());
        Assertions.assertThat(actualLessonResponse.getName()).isEqualTo(lessonResponse.getName());

    }

    @Test
    @Order(4)
    @DisplayName("JUnit test for {4} delete lesson by id")
    void delete() {

        Mockito.when(lessonRepository.findById(1L)).thenReturn(Optional.of(lesson1));
        lessonService.delete(lesson1.getId());
    }

    @Test
    void throwExceptionIfEntityIsNotAvailable() {

        assertThrows(ResourceNotFoundException.class, () -> lessonService.delete(lesson1.getId()));
        assertThrows(ResourceNotFoundException.class, () -> lessonService.getById(lesson1.getId()));
    }

    @AfterEach
    public void tearDown() {
        lesson1  = null;
    }

}
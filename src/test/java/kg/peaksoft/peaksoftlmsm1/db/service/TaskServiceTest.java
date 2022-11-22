package kg.peaksoft.peaksoftlmsm1.db.service;

import kg.peaksoft.peaksoftlmsm1.controller.mappers.edit.TaskEditMapper;
import kg.peaksoft.peaksoftlmsm1.controller.mappers.view.TaskViewMapper;
import kg.peaksoft.peaksoftlmsm1.controller.dto.task.TaskRequest;
import kg.peaksoft.peaksoftlmsm1.controller.dto.task.TaskResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.Task;
import kg.peaksoft.peaksoftlmsm1.db.repository.TaskRepository;
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
class TaskServiceTest {

    @Autowired
    @InjectMocks
    private TaskService taskService;
    @Mock
    private TaskEditMapper editMapper;
    @Mock
    private TaskViewMapper viewMapper;
    @Mock
    private TaskRepository repository;

    private Task task;

    @BeforeEach
    public void setup() {
        task = new Task(1L,"task","task one",
                "link","image","code",null,null);
    }

    @Test
    @Order(1)
    @DisplayName("JUnit test for {1} should save Task")
    void create() {
        TaskRequest taskRequest = new TaskRequest("task","task one","link",null,"image","code");

        Mockito.when(editMapper.create(taskRequest)).thenReturn(task);
        taskService.create(taskRequest);

        Mockito.verify(repository, Mockito.times(1))
                .save(ArgumentMatchers.any(Task.class));
    }

    @Test
    @Order(2)
    @DisplayName("JUnit test for {2} should update Task by id")
    void update() {
        task.setName("Java task");
        TaskRequest taskRequest = new TaskRequest("Java task", "task one","link",null,"image","code");

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(task));
        Mockito.when(editMapper.update(task, taskRequest)).thenReturn(task);

        taskService.update(1L, taskRequest);

        Mockito.verify(repository, Mockito.times(1))
                .save(ArgumentMatchers.any(Task.class));
    }

    @Test
    @Order(3)
    @DisplayName("JUnit test for {3} should find Task by id")
    void getById() {
        TaskResponse taskResponse = new TaskResponse(1L,"Java task",
                "task one","link",null,"image","code");

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(task));
        Mockito.when(viewMapper.mapToResponse(Mockito.any(Task.class))).thenReturn(taskResponse);

        TaskResponse actualTaskResponse = taskService.getById(1L);

        Assertions.assertThat(actualTaskResponse.getId()).isEqualTo(taskResponse.getId());
        Assertions.assertThat(actualTaskResponse.getName()).isEqualTo(taskResponse.getName());
    }

    @Test
    @Order(4)
    @DisplayName("JUnit test for {4} delete Task by id")
    void delete() {

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(task));
        taskService.delete(task.getId());
    }

    @Test
    void throwExceptionIfEntityIsNotAvailable() {

        assertThrows(ResourceNotFoundException.class, () -> taskService.delete(task.getId()));
        assertThrows(ResourceNotFoundException.class, () -> taskService.getById(task.getId()));
    }

    @AfterEach
    public void tearDown() {
        task = null;
    }

}
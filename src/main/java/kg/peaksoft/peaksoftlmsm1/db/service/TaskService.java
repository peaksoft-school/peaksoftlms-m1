package kg.peaksoft.peaksoftlmsm1.db.service;

import kg.peaksoft.peaksoftlmsm1.api.dto.mappers.TaskEditMapper;
import kg.peaksoft.peaksoftlmsm1.api.dto.mappers.TaskViewMapper;
import kg.peaksoft.peaksoftlmsm1.api.dto.task.TaskRequest;
import kg.peaksoft.peaksoftlmsm1.api.dto.task.TaskResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.Task;
import kg.peaksoft.peaksoftlmsm1.db.repository.TaskRepository;
import kg.peaksoft.peaksoftlmsm1.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskEditMapper editMapper;
    private final TaskViewMapper viewMapper;
    private final TaskRepository repository;

    public TaskResponse create(TaskRequest request){
        Task task = editMapper.create(request);
        repository.save(task);
        log.info("Entity task save: {}", task.getName());
        return viewMapper.mapToResponse(task);
    }

    public TaskResponse update(Long id, TaskRequest request){
        Optional<Task> task =Optional.ofNullable(repository.findById(id).orElseThrow(() -> {
            log.error("Entity task with id = {} does not exists in database", id);
            throw new ResourceNotFoundException("Entity", "id", id);
        }));
        editMapper.update(task.get(), request);
        log.info("Entity task updated: {}", id);
        return viewMapper.mapToResponse(repository.save(task.get()));
    }

    public TaskResponse getById(Long id){
        log.info("Get entity task by id: {}", id);
        return viewMapper.mapToResponse(repository.findById(id).orElseThrow(() -> {
            log.error("Entity task with id = {} does not exists in database", id);
            throw new ResourceNotFoundException("Entity", "id", id);
        }));
    }

    public TaskResponse delete(Long id) {
        Task task = repository.findById(id).orElseThrow(() -> {
            log.error("Entity task with id = {} does not exists in database", id);
            throw new ResourceNotFoundException("Entity", "id", id);
        });
        repository.deleteById(id);
        log.info("Delete entity task by id: {}", id);
        return viewMapper.mapToResponse(task);
    }

}

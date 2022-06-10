package kg.peaksoft.peaksoftlmsm1.db.service;

import kg.peaksoft.peaksoftlmsm1.db.dto.mappers.TaskEditMapper;
import kg.peaksoft.peaksoftlmsm1.db.dto.mappers.TaskViewMapper;
import kg.peaksoft.peaksoftlmsm1.db.dto.task.TaskRequest;
import kg.peaksoft.peaksoftlmsm1.db.dto.task.TaskResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.Task;
import kg.peaksoft.peaksoftlmsm1.db.repository.TaskRepository;
import kg.peaksoft.peaksoftlmsm1.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskEditMapper editMapper;
    private final TaskViewMapper viewMapper;
    private final TaskRepository repository;

    public TaskResponse create(TaskRequest request){
        Task task = editMapper.create(request);
        repository.save(task);
        return viewMapper.mapToResponse(task);
    }

    public TaskResponse update(Long id, TaskRequest request){
        Optional<Task> task =Optional.ofNullable(repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Entity", "id", id)));
        editMapper.update(task.get(), request);
        return viewMapper.mapToResponse(repository.save(task.get()));
    }

    public TaskResponse getById(Long id){
        return viewMapper.mapToResponse(repository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Entity", "id", id)));
    }

    public TaskResponse delete(Long id) {
        Task task = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Entity", "id", id));
        repository.deleteById(id);
        return viewMapper.mapToResponse(task);
    }

}

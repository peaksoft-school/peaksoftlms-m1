package kg.peaksoft.peaksoftlmsm1.controller.mappers;

import kg.peaksoft.peaksoftlmsm1.controller.dto.task.TaskRequest;
import kg.peaksoft.peaksoftlmsm1.db.entity.Task;
import kg.peaksoft.peaksoftlmsm1.db.repository.FilePathRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskEditMapper {

    private final FilePathRepository repository;

    public Task create(TaskRequest request){
        if (request == null) {
            return null;
        }
        Task task = new Task();
        task.setName(request.getName());
        task.setText(request.getText());
        task.setLink(request.getLink());
        task.setFile(repository.findById(request.getFile()).get());
        task.setImage(request.getImage());
        task.setCode(request.getCode());
        return task;
    }

    public Task update(Task task, TaskRequest request){
        task.setName(request.getName());
        task.setText(request.getText());
        task.setLink(request.getLink());
        task.setFile(repository.findById(request.getFile()).get());
        task.setImage(request.getImage());
        task.setCode(request.getCode());
        return task;
    }

}

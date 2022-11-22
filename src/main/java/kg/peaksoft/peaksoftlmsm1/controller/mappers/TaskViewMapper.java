package kg.peaksoft.peaksoftlmsm1.controller.mappers;

import kg.peaksoft.peaksoftlmsm1.controller.dto.task.TaskResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.Task;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskViewMapper {

    public TaskResponse mapToResponse(Task task){
        if (task == null) {
            return null;
        }
        TaskResponse response = new TaskResponse();
        if (task.getId() != null) {
            response.setId(task.getId());
        }
        response.setName(task.getName());
        response.setText(task.getText());
        response.setLink(task.getLink());
        response.setFile(task.getFile());
        response.setImage(task.getImage());
        response.setCode(task.getCode());
        return response;
    }

    public List<TaskResponse> map(List<Task> tasks){
        List<TaskResponse> responses = new ArrayList<>();
        for(Task task : tasks){
            responses.add(mapToResponse(task));
        }
        return responses;
    }

}

package kg.peaksoft.peaksoftlmsm1.mapper;

import kg.peaksoft.peaksoftlmsm1.db.dto.group.GroupRequest;
import kg.peaksoft.peaksoftlmsm1.db.repository.StudentRepository;
import kg.peaksoft.peaksoftlmsm1.model.Course;
import kg.peaksoft.peaksoftlmsm1.model.Group;
import kg.peaksoft.peaksoftlmsm1.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GroupEditMapper {

    private StudentRepository studentRepository;

    public Group createGroup(GroupRequest request) {
        if (request == null) {
            return null;
        }
        Group group = new Group();
        group.setGroupName(request.getGroup_name());
        group.setDateOfStart(request.getDate_of_start());
        group.setDescription(request.getDescription());
        group.setCourse(request.getCourse());
        List<Student> students = new ArrayList<>();
        Student student = studentRepository.findById(request.getStudent()).get();
        students.add(student);
        group.setStudent(students);
        return group;
    }

    public Group updateGroup(Group group, GroupRequest request) {
        group.setGroupName(request.getGroup_name());
        group.setDateOfStart(request.getDate_of_start());
        group.setCourse(request.getCourse());
        List<Student> students = new ArrayList<>();
        Student student = studentRepository.findById(request.getStudent()).get();
        students.add(student);
        group.setStudent(students);
        return group;
    }
}

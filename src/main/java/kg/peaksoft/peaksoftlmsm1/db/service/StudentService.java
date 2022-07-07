package kg.peaksoft.peaksoftlmsm1.db.service;

import kg.peaksoft.peaksoftlmsm1.db.dto.mappers.StudentEditMapper;
import kg.peaksoft.peaksoftlmsm1.db.dto.mappers.StudentViewMapper;
import kg.peaksoft.peaksoftlmsm1.db.dto.student.StudentRequest;
import kg.peaksoft.peaksoftlmsm1.db.dto.student.StudentResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.Role;
import kg.peaksoft.peaksoftlmsm1.db.entity.User;
import kg.peaksoft.peaksoftlmsm1.db.entity.enums.StudyFormat;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.Group;
import kg.peaksoft.peaksoftlmsm1.db.repository.GroupRepository;
import kg.peaksoft.peaksoftlmsm1.db.repository.UserRepository;
import kg.peaksoft.peaksoftlmsm1.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {

    private final UserRepository userRepository;
    private final StudentEditMapper studentEditMapper;
    private final StudentViewMapper studentViewMapper;
    private final GroupRepository groupRepository;

    public StudentResponse create(StudentRequest request){
        User user = studentEditMapper.mapToEntity(request);
        userRepository.save(user);
        log.info("Entity user save: {}", user.getFirstName());
        return studentViewMapper.mapToResponse(user);
    }

    public StudentResponse update(Long id, StudentRequest request){
        Optional<User> user = Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> {
            log.error("Entity user with id = {} does not exists in database", id);
            throw new ResourceNotFoundException("Entity", "id", id);
        }));
        studentEditMapper.mapToUpdate(user.get(), request);
        log.info("Entity user updated: {}", id);
        return studentViewMapper.mapToResponse(userRepository.save(user.get()));
    }

    public StudentResponse getById(Long id){
        log.info("Get entity student by id: {}", id);
        return studentViewMapper.mapToResponse(userRepository.findById(id).orElseThrow(() -> {
            log.error("Entity student with id = {} does not exists in database", id);
            throw new ResourceNotFoundException("Entity", "id", id);
        }));
    }

    public StudentResponse delete(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> {
            log.error("Entity user with id = {} does not exists in database", id);
            throw new ResourceNotFoundException("Entity", "id", id);
        });
        userRepository.deleteById(id);
        log.info("Delete entity user by id: {}", id);
        return studentViewMapper.mapToResponse(user);
    }

    public List<StudentResponse> getAll() {
        log.info("Entity student get all: {}");
        return studentViewMapper.map(userRepository.findAll());
    }

    public List<StudentResponse> importStudentsExcelFile(MultipartFile files, Long groupId) throws IOException {

        Group group = groupRepository.findById(groupId).orElseThrow(() -> {
            throw new ResourceNotFoundException("Entity user with id = {} does not exists in database", groupId);
        });

        List<User> userList = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
        XSSFSheet sheet = workbook.getSheetAt(0);

        DataFormatter formatter = new DataFormatter();

        for (int index = 0; index < sheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0) {
                User user = new User();
                XSSFRow row = sheet.getRow(index);

                user.setFirstName(formatter.formatCellValue(row.getCell(0)));
                user.setLastName(formatter.formatCellValue(row.getCell(1)));
                user.setStudyFormat(StudyFormat.valueOf(formatter.formatCellValue(row.getCell(2))));
                user.setPhoneNumber((formatter.formatCellValue(row.getCell(3))));
                user.setEmail(formatter.formatCellValue(row.getCell(4)));
                user.setPassword(formatter.formatCellValue(row.getCell(5)));
                user.setCreated(LocalDateTime.now());
                userList.add(user);
            }

        }

        Role role = new Role("ROLE_STUDENT");
        for (User user : userList) {
            user.setRoles(role);
            user.setGroups(group);
            userRepository.save(user);
        }

        List<StudentResponse> studentResponses = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            studentResponses.add(studentViewMapper.mapToResponse(user));
        }
        return studentResponses;
    }














}

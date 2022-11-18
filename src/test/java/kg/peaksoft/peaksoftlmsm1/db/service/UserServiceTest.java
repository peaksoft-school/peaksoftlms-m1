package kg.peaksoft.peaksoftlmsm1.db.service;

import kg.peaksoft.peaksoftlmsm1.controller.dto.mappers.UserEditMapper;
import kg.peaksoft.peaksoftlmsm1.controller.dto.mappers.UserViewMapper;
import kg.peaksoft.peaksoftlmsm1.controller.dto.request.UserRequest;
import kg.peaksoft.peaksoftlmsm1.controller.dto.response.UserResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.User;
import kg.peaksoft.peaksoftlmsm1.db.repository.UserRepository;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.DisplayName.class)
class UserServiceTest {

    @Autowired
    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Mock
    private UserEditMapper userEditMapper;
    @Mock
    private UserViewMapper userViewMapper;

    private User user1;
    private User user2;
    List<User> userList = new ArrayList<>();

    @BeforeEach
    public void setup() {
        userList = new ArrayList<>();
        user1 = User.builder()
                .id(1L)
                .firstName("admin")
                .lastName("lastName")
                .password("password")
                .phoneNumber("89996786767")
                .email("admin@gmail.com")
                .build();
        user2 = User.builder()
                .id(1L)
                .firstName("admin2")
                .lastName("lastName22")
                .password("password")
                .phoneNumber("89996786767")
                .email("admin2@gmail.com")
                .build();
        userList.add(user1);
        userList.add(user2);
    }

    @Test
    @Order(1)
    @DisplayName("JUnit test for {1} should load User by email")
    void loadUserByUsername() {
        Mockito.when(userRepository.findByEmail("admin@gmail.com")).thenReturn(Optional.of(user1));
        userService.loadUserByUsername(user1.getUsername());
    }

    @Test
    @Order(2)
    @DisplayName("JUnit test for {2} should save User")
    void create() {
        UserRequest userRequest = new UserRequest("admin","lastName","admin@gmail.com","password");

        Mockito.when(userEditMapper.mapToEntity(userRequest)).thenReturn(user1);
        Mockito.when(bCryptPasswordEncoder.encode(userRequest.getPassword())).thenReturn("admin");
        userService.create(userRequest);

        Mockito.verify(userRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(User.class));
    }

    @Test
    @Order(3)
    @DisplayName("JUnit test for {3} should update User by id")
    void update() {
        user1.setFirstName("First admin");
        UserRequest userRequest = new UserRequest("First admin","lastName","admin@gmail.com","password");

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        Mockito.when(userEditMapper.mapToUpdate(user1,userRequest)).thenReturn(user1);

        userService.update(1L, userRequest);

        Mockito.verify(userRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(User.class));
    }

    @Test
    @Order(4)
    @DisplayName("JUnit test for {4} should find User by id")
    void getById() {
        UserResponse userResponse = new UserResponse(1L,"First admin","lastName",
                "admin@gmail.com",false,null);

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        Mockito.when(userViewMapper.mapToResponse(Mockito.any(User.class))).thenReturn(userResponse);

        UserResponse actualUserResponse = userService.getById(1L);

        Assertions.assertThat(actualUserResponse.getId()).isEqualTo(userResponse.getId());
        Assertions.assertThat(actualUserResponse.getFirstName()).isEqualTo(userResponse.getFirstName());
    }

    @Test
    @Order(5)
    @DisplayName("JUnit test for {5} should get all Users")
    void getAll() {

        Mockito.when(userService.getAll()).thenReturn(userList);
        userList = userService.getAll();

        assertEquals(userList, userList);
        Mockito.verify(userRepository, Mockito.times(1)).findAll();
    }

    @Test
    @Order(6)
    @DisplayName("JUnit test for {6} delete User by id")
    void delete() {

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        userService.delete(user1.getId());
    }

    @Test
    void throwExceptionIfEntityIsNotAvailable() {

        assertThrows(ResourceNotFoundException.class, () -> userService.delete(user1.getId()));
        assertThrows(ResourceNotFoundException.class, () -> userService.getById(user1.getId()));
    }

    @AfterEach
    public void tearDown() {
        user1 = user2 = null;
        userList = null;
    }
}
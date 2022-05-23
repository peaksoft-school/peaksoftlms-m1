package kg.peaksoft.peaksoftlmsm1.api;

import kg.peaksoft.peaksoftlmsm1.db.entity.Role;
import kg.peaksoft.peaksoftlmsm1.db.entity.User;
import kg.peaksoft.peaksoftlmsm1.db.repository.RoleRepository;
import kg.peaksoft.peaksoftlmsm1.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class DemoData implements ApplicationListener<ContextRefreshedEvent> {


    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DemoData(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Role adminRole = new Role("ROLE_ADMIN");
        User user1 = new User();
        user1.setFirstName("admin");
        user1.setLastName("admin");
        user1.setPassword(passwordEncoder.encode("admin"));
        user1.setEmail("admin@gmail.com");
        user1.setRoles(Arrays.asList(adminRole));
        user1.setCreated(LocalDateTime.now());
        userRepository.save(user1);

        Role instructorRole = new Role("ROLE_INSTRUCTOR");
        User user2 = new User();
        user2.setFirstName("instructor");
        user2.setLastName("instructor");
        user2.setPassword(passwordEncoder.encode("instructor"));
        user2.setEmail("instructor@gmail.com");
        user2.setRoles(Arrays.asList(instructorRole));
        user2.setCreated(LocalDateTime.now());
        userRepository.save(user2);

        Role studentRole = new Role("ROLE_STUDENT");
        User user3 = new User();
        user3.setFirstName("student");
        user3.setLastName("student");
        user3.setPassword(passwordEncoder.encode("student"));
        user3.setEmail("student@gmail.com");
        user3.setRoles(Arrays.asList(studentRole));
        user3.setCreated(LocalDateTime.now());
        userRepository.save(user3);

    }
}
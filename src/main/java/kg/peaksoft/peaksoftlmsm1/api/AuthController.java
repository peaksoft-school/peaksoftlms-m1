package kg.peaksoft.peaksoftlmsm1.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsm1.config.JwtTokenUtil;
import kg.peaksoft.peaksoftlmsm1.db.dto.request.AuthRequest;
import kg.peaksoft.peaksoftlmsm1.db.dto.request.UserRequest;
import kg.peaksoft.peaksoftlmsm1.db.dto.response.AuthMapper;
import kg.peaksoft.peaksoftlmsm1.db.dto.response.AuthResponse;
import kg.peaksoft.peaksoftlmsm1.db.dto.response.UserResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.User;
import kg.peaksoft.peaksoftlmsm1.exception.ExceptionType;
import kg.peaksoft.peaksoftlmsm1.db.repository.UserRepository;
import kg.peaksoft.peaksoftlmsm1.db.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*",maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/public")
@Tag(name = "Authentication", description = "User with role ADMIN, INSTRUCTOR, STUDENT can authenticate")
public class AuthController {

    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository repository;
    private final AuthMapper authMapper;

    @PostMapping("login")
    @Operation(summary = "sign in method", description = "Login Admin, Instructor, Student")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
            User user = repository.findByEmail(authenticationToken.getName()).get();
            log.info("inside AuthController login method");
            return ResponseEntity.ok()
                    .body(authMapper.view(jwtTokenUtil.generateToken(user), ExceptionType.Successfully, user));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authMapper.view("", ExceptionType.LOGIN_FAILED, null));
        }
    }

    @PostMapping("registration")
    @Operation(summary = "Admin can registration Student and Instructor", description = "Admin can registration Student and Instructor")
    public UserResponse create(@RequestBody UserRequest userRequest) {
        log.info("inside AuthController create method");
        return userService.create(userRequest);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "Admin can update Student and Instructor", description = "Admin can update Student and Instructor")
    public UserResponse update(@PathVariable Long id, @RequestBody UserRequest userRequest){
        log.info("inside AuthController update method");
        return userService.update(id,userRequest);
    }

    @DeleteMapping ("{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "Admin can delete Student and Instructor", description = "Admin can delete Student and Instructor")
    public UserResponse delete(@PathVariable Long id){
        log.info("inside AuthController delete method");
        return userService.delete(id);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "Admin can get by ID Student and Instructor", description = "Admin can get by ID Student and Instructor")
    public UserResponse getById(@PathVariable Long id){
        log.info("inside AuthController get by id method");
        return userService.getById(id);
    }

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @Operation(summary = "Admin can get all Student and Instructor", description = "Admin can get all Student and Instructor")
    public List<User> getAll(){
        log.info("inside AuthController get all method");
        return userService.getAll();
    }

}
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            return ResponseEntity.ok()
                    .body(authMapper.view(jwtTokenUtil.generateToken(user), ExceptionType.Successfully, user));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authMapper.view("", ExceptionType.LOGIN_FAILED, null));
        }
    }

    @PostMapping("registration")
    @Operation(summary = "method create", description = "Admin can registration Student and Instructor")
    public UserResponse create(@RequestBody UserRequest userRequest) {
        return userService.create(userRequest);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "method update", description = "Admin can update Student and Instructor")
    public UserResponse update(@PathVariable Long id, @RequestBody UserRequest userRequest){
        return userService.update(id,userRequest);
    }

    @DeleteMapping ("{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "method delete", description = "Admin can delete Student and Instructor")
    public UserResponse delete(@PathVariable Long id){
        return userService.delete(id);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "method getById", description = "Admin can get by ID Student and Instructor")
    public UserResponse getById(@PathVariable Long id){
        return userService.getById(id);
    }

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @Operation(summary = "method get all", description = "Admin can get all Student and Instructor")
    public List<User> getAll(){
        return userService.getAll();
    }

}
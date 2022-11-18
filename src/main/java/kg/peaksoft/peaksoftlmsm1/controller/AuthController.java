package kg.peaksoft.peaksoftlmsm1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.peaksoft.peaksoftlmsm1.config.security.JwtTokenUtil;
import kg.peaksoft.peaksoftlmsm1.controller.dto.request.AuthRequest;
import kg.peaksoft.peaksoftlmsm1.controller.dto.response.AuthMapper;
import kg.peaksoft.peaksoftlmsm1.controller.dto.response.AuthResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.User;
import kg.peaksoft.peaksoftlmsm1.exception.ExceptionType;
import kg.peaksoft.peaksoftlmsm1.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*",maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("api/public")
@Tag(name = "Authentication", description = "User with role ADMIN, INSTRUCTOR, STUDENT can authenticate")
public class AuthController {

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

}
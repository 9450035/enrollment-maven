package ir.ac.fum.enrollment.profile.controller;

import ir.ac.fum.enrollment.profile.dto.LoginRequest;
import ir.ac.fum.enrollment.profile.dto.LoginResponse;
import ir.ac.fum.enrollment.profile.entities.StudentEntity;
import ir.ac.fum.enrollment.profile.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<StudentEntity> register(@RequestBody StudentEntity studentEntity) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(studentEntity));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(loginRequest));
    }

}

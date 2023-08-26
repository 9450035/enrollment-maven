package ir.ac.fum.enrollment.profile.service;

import ir.ac.fum.enrollment.profile.dto.LoginRequest;
import ir.ac.fum.enrollment.profile.dto.LoginResponse;
import ir.ac.fum.enrollment.profile.entities.StudentEntity;
import ir.ac.fum.enrollment.profile.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public StudentEntity register(StudentEntity studentEntity) throws Exception {
        var dbStudent = studentRepository.findByStudentId(studentEntity.getStudentId());
        if (dbStudent.isPresent()) {
            throw new Exception("");
        }

        studentEntity.setPassword(passwordEncoder.encode(studentEntity.getPassword()));
        studentEntity.setRole("student");
        return studentRepository.save(studentEntity);

    }

    public LoginResponse login(LoginRequest loginRequest) {
        var user = studentRepository.findByStudentId(loginRequest.username());
        if(user.isEmpty()){
            throw new RuntimeException();
        }
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new LoginResponse(tokenService.generateVerifyToken(user.get()));
    }
}

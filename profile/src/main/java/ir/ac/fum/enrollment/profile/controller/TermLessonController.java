package ir.ac.fum.enrollment.profile.controller;

import ir.ac.fum.enrollment.profile.dto.TermLessonDTO;
import ir.ac.fum.enrollment.profile.dto.TermLessonOut;
import ir.ac.fum.enrollment.profile.service.TermLessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TermLessonController {

    private final TermLessonService termLessonService;

    @PostMapping("/create-terms")
    public ResponseEntity<Void> createTerm(@RequestBody TermLessonDTO termLessonDTO) {
        termLessonService.chooseTerm(termLessonDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/term-statistic")
    public ResponseEntity<List<TermLessonOut>> statistic(@RequestParam Integer year, @RequestParam Integer term) {
        return ResponseEntity.ok(termLessonService.getStatastic(year, term));
    }

}

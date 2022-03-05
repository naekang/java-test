package dev.naekang.javatest.study;

import dev.naekang.javatest.domain.Study;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class StudyController {

    private final StudyRepository studyRepository;

    @GetMapping("/study/{id}")
    public Study getStudy(@PathVariable Long id) {
        return studyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Study not found for '" + id + "'"));
    }

    @PostMapping("/study")
    public Study createStudy(@RequestBody Study study) {
        return studyRepository.save(study);
    }

}

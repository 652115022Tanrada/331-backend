package se331.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import se331.rest.service.OrganizerService;
import se331.rest.util.LabMapper;
import se331.rest.service.ParticipantService;

@RestController
@RequiredArgsConstructor
public class ParticipantController {
    final ParticipantService participantService;
    @GetMapping("/participants")
    ResponseEntity<?> getParticipants() {
        return ResponseEntity.ok(LabMapper.INSTANCE.getParticipantDTO(participantService.getAllParticipants()));
    }
}

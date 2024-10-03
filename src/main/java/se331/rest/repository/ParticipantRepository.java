package se331.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se331.rest.entity.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}
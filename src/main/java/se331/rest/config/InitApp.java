package se331.rest.config;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import se331.rest.entity.Event;
import se331.rest.entity.Organizer;
import se331.rest.entity.Participant;
import se331.rest.repository.EventRepository;
import se331.rest.repository.OrganizerRepository;
import se331.rest.repository.ParticipantRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    EventRepository eventRepository;

    final OrganizerRepository organizerRepository;
    final ParticipantRepository participantRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        // Organizer
        Organizer org1, org2, org3;
        org1 = organizerRepository.save(Organizer.builder()
                .name("CAMT").build());
        org2 = organizerRepository.save(Organizer.builder()
                .name("CMU").build());
        org3 = organizerRepository.save(Organizer.builder()
                .name("ChiangMai").build());

        // Participant
        Participant p1, p2, p3, p4, p5;
        p1 = participantRepository.save(Participant.builder()
                .name("Patty")
                .telNo("0961112222")
                .build());
        p2 = participantRepository.save(Participant.builder()
                .name("John")
                .telNo("0953334444")
                .build());
        p3 = participantRepository.save(Participant.builder()
                .name("Alice")
                .telNo("0925556666")
                .build());
        p4 = participantRepository.save(Participant.builder()
                .name("Bob")
                .telNo("0937778888")
                .build());
        p5 = participantRepository.save(Participant.builder()
                .name("Eve")
                .telNo("0919990000")
                .build());

        Event tempEvent;

        // Event 1: Midterm Exam (CAMT, Patty, John, Alice)
        tempEvent = eventRepository.save(Event.builder()
                .category("Academic")
                .title("Midterm Exam")
                .description("A time for taking the exam")
                .location("CAMT Building")
                .date("3rd Sept")
                .time("3.00-4.00 pm.")
                .petAllowed(false)
                .build());
        tempEvent.setOrganizer(org1);
        org1.getOwnEvents().add(tempEvent);
        // Update participants' event history (owning side)
        tempEvent.getParticipants().addAll(List.of(p1,p2,p3));
        p1.getEventHistory().add(tempEvent);  // Patty
        p2.getEventHistory().add(tempEvent);  // John
        p3.getEventHistory().add(tempEvent);  // Alice

        // Event 2: Commencement Day (CAMT, John, Alice, Bob)
        tempEvent = eventRepository.save(Event.builder()
                .category("Academic")
                .title("Commencement Day")
                .description("A time for celebration")
                .location("CMU Convention hall")
                .date("21th Jan")
                .time("8.00am-4.00 pm.")
                .petAllowed(false)
                .build());
        tempEvent.setOrganizer(org1);
        org1.getOwnEvents().add(tempEvent);
        p2.getEventHistory().add(tempEvent);  // John
        p3.getEventHistory().add(tempEvent);  // Alice
        p4.getEventHistory().add(tempEvent);  // Bob

        // Event 3: Loy Krathong (CMU, Patty, Bob, Eve)
        tempEvent = eventRepository.save(Event.builder()
                .category("Cultural")
                .title("Loy Krathong")
                .description("A time for Krathong")
                .location("Ping River")
                .date("21th Nov")
                .time("8.00am-10.00 pm.")
                .petAllowed(false)
                .build());
        tempEvent.setOrganizer(org2);
        org2.getOwnEvents().add(tempEvent);
        tempEvent.getParticipants().addAll(List.of(p1,p2,p3));
        p1.getEventHistory().add(tempEvent);  // Patty
        p4.getEventHistory().add(tempEvent);  // Bob
        p5.getEventHistory().add(tempEvent);  // Eve

        // Event 4: Songkran (ChiangMai, Alice, Bob, Eve)
        tempEvent = eventRepository.save(Event.builder()
                .category("Cultural")
                .title("Songkran")
                .description("Let's Play Water")
                .location("Chiang Mai Moat")
                .date("13th April")
                .time("10.00am-6.00 pm.")
                .petAllowed(true)
                .build());
        tempEvent.setOrganizer(org3);
        org3.getOwnEvents().add(tempEvent);
        tempEvent.getParticipants().addAll(List.of(p1,p2,p3));
        p3.getEventHistory().add(tempEvent);  // Alice
        p4.getEventHistory().add(tempEvent);  // Bob
        p5.getEventHistory().add(tempEvent);  // Eve

        // Save updated participants to persist the changes
        participantRepository.save(p1);
        participantRepository.save(p2);
        participantRepository.save(p3);
        participantRepository.save(p4);
        participantRepository.save(p5);
    }
}

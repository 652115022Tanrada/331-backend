package se331.rest.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import se331.rest.entity.Organizer;
import se331.rest.entity.Participant;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    String category;
    String title;
    String description;
    String location;
    String date;
    String time;
    Boolean petAllowed;
    @ManyToOne
    Organizer organizer;
    @JsonManagedReference // Prevents infinite recursion
    @ManyToMany(mappedBy = "eventHistory")
    List<Participant> participants;
}

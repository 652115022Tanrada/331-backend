package se331.rest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import se331.rest.entity.Event;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    String name;
    String telNo;
    @ManyToMany
    List<Event> eventHistory;
}

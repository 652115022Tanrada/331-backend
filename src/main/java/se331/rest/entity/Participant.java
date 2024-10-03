package se331.rest.entity;

import ch.qos.logback.classic.spi.LoggingEventVO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
    @JsonManagedReference // Prevents infinite recursion
    @ManyToMany
    @Builder.Default
    List<Event> eventHistory = new ArrayList<>();
}

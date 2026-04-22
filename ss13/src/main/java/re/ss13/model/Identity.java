package re.ss13.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "identity")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Identity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cccd;

    // quan hệ has-a
    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;

}

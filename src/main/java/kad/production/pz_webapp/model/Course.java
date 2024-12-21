package kad.production.pz_webapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Course {
    private int id;
    private String name;
    private String description;
    private LocalDate startTime;
    private LocalDate endTime;
    private User teacher;
}

package kad.production.pz_webapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class Lesson {
    private int id;
    private Course course;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
}

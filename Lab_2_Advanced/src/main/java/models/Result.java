package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Result {
    private Double x;
    private Double y;
    private Double R;
    private boolean hit;
    private Double calTime;
    private LocalDateTime releaseTime;
}

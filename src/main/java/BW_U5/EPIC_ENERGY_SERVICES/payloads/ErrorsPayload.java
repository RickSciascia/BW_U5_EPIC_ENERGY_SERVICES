package BW_U5.EPIC_ENERGY_SERVICES.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ErrorsPayload {
    private String message;
    private LocalDateTime timestamp;
}

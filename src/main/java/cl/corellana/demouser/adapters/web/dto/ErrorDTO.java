package cl.corellana.demouser.adapters.web.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorDTO {

    private LocalDateTime timestamp;

    private int codigo;

    private String detail;
}

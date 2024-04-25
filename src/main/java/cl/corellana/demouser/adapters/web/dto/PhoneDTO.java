package cl.corellana.demouser.adapters.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PhoneDTO {
    private long number;

    @JsonProperty("citycode")
    private int cityCode;

    @JsonProperty("contrycode")
    private String contryCode;
}

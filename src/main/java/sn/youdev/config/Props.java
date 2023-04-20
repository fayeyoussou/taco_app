package sn.youdev.config;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@ConfigurationProperties(prefix="taco.orders")
@Getter
@Setter
@Validated
public class Props {
    @Min(value=5, message="must be between 5 and 25") @Max(value=25, message="must be between 5 and 25")
    private int pageSize = 20;
}

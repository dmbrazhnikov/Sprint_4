package dmbrazhnikov.edu.test.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
public class AboutRentParams {
    private final String rentalPeriodLiteral, color;
    @Setter
    private String commentary;
}

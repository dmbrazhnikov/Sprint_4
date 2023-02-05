package dmbrazhnikov.edu.test.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
public class ClientInfoParams {

    private final String name, lastName, address, phoneNum;
    @Setter
    private String subwayStationName;
}

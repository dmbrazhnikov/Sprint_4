package dmbrazhnikov.edu.test.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
public class ClientInfo {

    private final String firstName, lastName, address, phoneNum;
    @Setter
    private String subwayStationName;
}

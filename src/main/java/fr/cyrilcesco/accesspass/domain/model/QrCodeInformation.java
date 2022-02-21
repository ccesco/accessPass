package fr.cyrilcesco.accesspass.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class QrCodeInformation {

    private String username;
    private String firstName;
    private String birthDate;
    private Boolean isVIP;
}

package fr.cyrilcesco.accesspass.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
@AllArgsConstructor
public class QrCodeInformation {

    private String username;
    private String firstName;
    private String birthDate;
    private Boolean isVIP;

    public static QrCodeInformation generate() {
        return QrCodeInformation.builder().firstName(generateString()).username(generateString()).birthDate("10-01-1990").isVIP(false).build();
    }

    private static String generateString() {
        return UUID.randomUUID().toString();
    }
}

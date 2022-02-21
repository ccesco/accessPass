package fr.cyrilcesco.accesspass.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class AccessCodeGenerated {
    private String value;
}

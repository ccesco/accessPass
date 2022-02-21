package fr.cyrilcesco.accesspass.domain.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class AccessCodeGeneratedList {
    private List<AccessCodeGenerated> accessCodeGenerateds;

    public AccessCodeGeneratedList() {
        this.accessCodeGenerateds = new ArrayList<>();
    }

    public boolean addElementToBuffer(AccessCodeGenerated qrCodeInformation) {
        return accessCodeGenerateds.add(qrCodeInformation);
    }
}

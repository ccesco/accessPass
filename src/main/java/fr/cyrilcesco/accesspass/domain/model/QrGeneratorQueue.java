package fr.cyrilcesco.accesspass.domain.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class QrGeneratorQueue {
    private List<QrCodeInformation> buffer;

    public QrGeneratorQueue() {
        this.buffer = new ArrayList<>();
    }

    public boolean addElementToBuffer(QrCodeInformation qrCodeInformation) {
        return buffer.add(qrCodeInformation);
    }

    public boolean addElementsToBuffer(List<QrCodeInformation> qrCodeInformations) {
        return buffer.addAll(qrCodeInformations);
    }

    public boolean removeElementToBuffer(QrCodeInformation qrCodeInformation) {
        return buffer.remove(qrCodeInformation);
    }
}

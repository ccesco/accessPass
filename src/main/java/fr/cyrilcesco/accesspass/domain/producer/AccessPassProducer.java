package fr.cyrilcesco.accesspass.domain.producer;

import fr.cyrilcesco.accesspass.domain.model.QrCodeInformation;
import fr.cyrilcesco.accesspass.domain.model.QrGeneratorQueue;
import org.springframework.stereotype.Service;

@Service
public class AccessPassProducer implements Runnable {

    private QrGeneratorQueue buffer;
    private QrCodeInformation valueToAddToBuffer = null;

    public AccessPassProducer(QrGeneratorQueue buffer) {
        this.buffer = buffer;
    }

    public boolean addValueToBuffer(QrCodeInformation valueToAddToBuffer) {
        return this.buffer.getBuffer().add(valueToAddToBuffer);
    }

    @Override
    public void run() {
        if (valueToAddToBuffer != null) {
            buffer.addElementToBuffer(valueToAddToBuffer);
        }
    }
}

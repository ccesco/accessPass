package fr.cyrilcesco.accesspass.domain.consumer;

import fr.cyrilcesco.accesspass.domain.model.AccessCodeGenerated;
import fr.cyrilcesco.accesspass.domain.model.AccessCodeGeneratedList;
import fr.cyrilcesco.accesspass.domain.model.QrCodeInformation;
import fr.cyrilcesco.accesspass.domain.model.QrGeneratorQueue;
import org.springframework.stereotype.Service;

@Service
public class QrCodeGeneratorConsumer implements Runnable {

    private QrGeneratorQueue buffer;
    private AccessCodeGeneratedList result;

    public QrCodeGeneratorConsumer(QrGeneratorQueue buffer, AccessCodeGeneratedList result) {
        this.buffer = buffer;
        this.result = result;
    }

    @Override
    public void run() {

        while (true) {
            if (buffer.getBuffer().isEmpty()) {
                continue;
            } else {
                QrCodeInformation removed = buffer.getBuffer().remove(0);
                AccessCodeGenerated accessCodeGenerated = AccessCodeGenerated.builder().value(removed.getFirstName() + " " + removed.getUsername()).build();
                result.addElementToBuffer(accessCodeGenerated);
                System.out.println(Thread.currentThread().getName() + " generate " + accessCodeGenerated.getValue());
            }
        }
    }
}

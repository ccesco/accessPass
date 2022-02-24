package fr.cyrilcesco.accesspass.domain;

import fr.cyrilcesco.accesspass.domain.consumer.QrCodeGeneratorConsumer;
import fr.cyrilcesco.accesspass.domain.model.AccessCodeGeneratedList;
import fr.cyrilcesco.accesspass.domain.model.QrCodeInformation;
import fr.cyrilcesco.accesspass.domain.model.QrGeneratorBlockingQueue;
import fr.cyrilcesco.accesspass.domain.producer.AccessPassProducer;
import fr.cyrilcesco.accesspass.model.RequestDto;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class AccessCodeManager {

    private QrGeneratorBlockingQueue buffer;
    private AccessCodeGeneratedList result;

    public AccessCodeManager(QrGeneratorBlockingQueue buffer, AccessCodeGeneratedList result) {
        this.buffer = buffer;
        this.result = result;
    }

    @SneakyThrows
    public AccessCodeGeneratedList run(RequestDto requestDto) {

        for (int i = 0; i < 10; i++) {
            Thread producerThread = new Thread(new AccessPassProducer(buffer));
            producerThread.setName("producerThread" + i);
            producerThread.start();
        }

        for (int i = 0; i < 3; i++) {
            AccessCodeGeneratedList partialResult = new AccessCodeGeneratedList();
            Thread consumerThread = new Thread(new QrCodeGeneratorConsumer(buffer, partialResult));
            consumerThread.setName("consumerThread" + i);
            consumerThread.start();
            consumerThread.join();
            result.addElementsToBuffer(partialResult.getAccessCodeGenerateds());
        }

        return result;
    }
}

package fr.cyrilcesco.accesspass.domain;

import fr.cyrilcesco.accesspass.domain.consumer.QrCodeGeneratorConsumer;
import fr.cyrilcesco.accesspass.domain.model.AccessCodeGeneratedList;
import fr.cyrilcesco.accesspass.domain.model.QrCodeInformation;
import fr.cyrilcesco.accesspass.domain.model.QrGeneratorQueue;
import fr.cyrilcesco.accesspass.domain.producer.AccessPassProducer;
import fr.cyrilcesco.accesspass.model.RequestDto;
import org.springframework.stereotype.Component;

@Component
public class AccessCodeManager {

    private QrGeneratorQueue buffer;
    private AccessCodeGeneratedList result;

    public AccessCodeManager(QrGeneratorQueue buffer, AccessCodeGeneratedList result) {
        this.buffer = buffer;
        this.result = result;
    }

    public AccessCodeGeneratedList run(RequestDto requestDto) {

        Thread producerThread = new Thread(new AccessPassProducer(buffer));
        producerThread.setName("producerThread");

        buffer.addElementToBuffer(getQrCodeInformation(requestDto));

        Thread consumerThread1 = new Thread(new QrCodeGeneratorConsumer(buffer, result));
        consumerThread1.setName("consumerThread1");

        Thread consumerThread2 = new Thread(new QrCodeGeneratorConsumer(buffer, result));
        consumerThread2.setName("consumerThread2");

        producerThread.start();
        consumerThread1.start();
        consumerThread2.start();

        return result;
    }

    private QrCodeInformation getQrCodeInformation(RequestDto requestDto) {
        return QrCodeInformation.builder().birthDate(requestDto.getBasicInfo().getBirthDate()).firstName(requestDto.getBasicInfo().getFirstName()).username(requestDto.getBasicInfo().getUsername()).build();
    }
}

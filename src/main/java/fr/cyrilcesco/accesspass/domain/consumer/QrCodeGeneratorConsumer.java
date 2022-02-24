package fr.cyrilcesco.accesspass.domain.consumer;

import fr.cyrilcesco.accesspass.domain.model.AccessCodeGenerated;
import fr.cyrilcesco.accesspass.domain.model.AccessCodeGeneratedList;
import fr.cyrilcesco.accesspass.domain.model.QrCodeInformation;
import fr.cyrilcesco.accesspass.domain.model.QrGeneratorBlockingQueue;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class QrCodeGeneratorConsumer implements Runnable {

    private QrGeneratorBlockingQueue buffer;
    private AccessCodeGeneratedList result;

    public QrCodeGeneratorConsumer(QrGeneratorBlockingQueue buffer, AccessCodeGeneratedList result) {
        this.buffer = buffer;
        this.result = result;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (result.getAccessCodeGenerateds().size() <= 200) {
            consume();
        }
    }

    private void consume() throws InterruptedException {

        QrCodeInformation value;
            value = buffer.getBuffer().take();
        System.out.println(Thread.currentThread().getName() + " taille buffer " + buffer.getBuffer().size());
        AccessCodeGenerated accessCodeGenerated = AccessCodeGenerated.builder().value(value.getFirstName() + " " + value.getUsername()).build();
        result.addElementToBuffer(accessCodeGenerated);
        System.out.println(Thread.currentThread().getName() + " generate " + accessCodeGenerated.getValue());
        System.out.println(Thread.currentThread().getName() + " result taille buffer " + result.getAccessCodeGenerateds().size());
    }
}

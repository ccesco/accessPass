package fr.cyrilcesco.accesspass.domain.producer;

import fr.cyrilcesco.accesspass.domain.model.QrCodeInformation;
import fr.cyrilcesco.accesspass.domain.model.QrGeneratorBlockingQueue;
import org.springframework.stereotype.Service;

@Service
public class AccessPassProducer implements Runnable {

    private QrGeneratorBlockingQueue buffer;

    public AccessPassProducer(QrGeneratorBlockingQueue buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        produce();
    }

    private void produce() {
        while (true) {
            QrCodeInformation value = QrCodeInformation.generate();
            try {
                buffer.getBuffer().put(value);
            } catch (InterruptedException e) {
                break;
            }
            System.out.println(Thread.currentThread().getName() + " taille buffer " + buffer.getBuffer().size());
            System.out.println(Thread.currentThread().getName() + " produce " + value.getUsername() + " " + value.getFirstName());
        }
    }
}

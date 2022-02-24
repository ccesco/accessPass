package fr.cyrilcesco.accesspass.domain.model;

import lombok.Getter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

@Getter
public class QrGeneratorBlockingQueue {
    private BlockingQueue<QrCodeInformation> buffer;
    private int maxSize;

    public QrGeneratorBlockingQueue(int maxSize) {
        this.maxSize = maxSize;
        this.buffer = new LinkedBlockingDeque<>(maxSize);
    }
}

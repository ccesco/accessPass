package fr.cyrilcesco.accesspass.config;

import fr.cyrilcesco.accesspass.domain.model.AccessCodeGeneratedList;
import fr.cyrilcesco.accesspass.domain.model.QrGeneratorBlockingQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccessPassConfig {

    @Bean
    public QrGeneratorBlockingQueue qrCodeGenertorQueue() {
        return new QrGeneratorBlockingQueue(10);
    }

    @Bean
    public AccessCodeGeneratedList accessCodeGeneratedList() {
        return new AccessCodeGeneratedList();
    }
}

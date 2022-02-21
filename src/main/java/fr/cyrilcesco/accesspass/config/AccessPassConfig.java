package fr.cyrilcesco.accesspass.config;

import fr.cyrilcesco.accesspass.domain.model.AccessCodeGeneratedList;
import fr.cyrilcesco.accesspass.domain.model.QrGeneratorQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccessPassConfig {

    @Bean
    public QrGeneratorQueue qrCodeGenertorQueue() {
        return new QrGeneratorQueue();
    }

    @Bean
    public AccessCodeGeneratedList accessCodeGeneratedList() {
        return new AccessCodeGeneratedList();
    }
}

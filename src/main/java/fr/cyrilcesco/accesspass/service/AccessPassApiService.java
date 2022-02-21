package fr.cyrilcesco.accesspass.service;

import fr.cyrilcesco.accesspass.api.AccessPassApiDelegate;
import fr.cyrilcesco.accesspass.model.AccessPass;
import fr.cyrilcesco.accesspass.model.RequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccessPassApiService implements AccessPassApiDelegate {

    @Override
    public ResponseEntity<AccessPass> postAccessPassRequest(RequestDto requestDto) {
        return ResponseEntity.ok(new AccessPass());
    }
}

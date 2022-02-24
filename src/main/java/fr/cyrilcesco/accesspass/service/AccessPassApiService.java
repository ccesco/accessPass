package fr.cyrilcesco.accesspass.service;

import fr.cyrilcesco.accesspass.api.AccessPassApiDelegate;
import fr.cyrilcesco.accesspass.domain.AccessCodeManager;
import fr.cyrilcesco.accesspass.domain.model.AccessCodeGenerated;
import fr.cyrilcesco.accesspass.domain.model.AccessCodeGeneratedList;
import fr.cyrilcesco.accesspass.model.AccessPass;
import fr.cyrilcesco.accesspass.model.RequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccessPassApiService implements AccessPassApiDelegate {

    private AccessCodeManager accessCodeManager;

    public AccessPassApiService(AccessCodeManager accessCodeManager) {
        this.accessCodeManager = accessCodeManager;
    }

    @Override
    public ResponseEntity<List<AccessPass>> postAccessPassRequest(RequestDto requestDto) {
        AccessCodeGeneratedList result = accessCodeManager.run(requestDto);
        return ResponseEntity.ok(result.getAccessCodeGenerateds().stream().map((accessCodeGenerated) -> mapToAccessPass(requestDto, accessCodeGenerated)).collect(Collectors.toList()));
    }

    private AccessPass mapToAccessPass(RequestDto requestDto, AccessCodeGenerated accessCodeGenerated) {
        AccessPass accessPass = new AccessPass();
        accessPass.setBasicInfo(requestDto.getBasicInfo());
        accessPass.setDateDemande(LocalDate.now().toString());
        accessPass.setDateGeneration(LocalDate.now().plusDays(1).toString());
        accessPass.setCodeValue(accessCodeGenerated.getValue());
        return accessPass;
    }
}

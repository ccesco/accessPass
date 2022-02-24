package fr.cyrilcesco.accesspass.domain.model;

import lombok.Getter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

@Getter
public class AccessCodeGeneratedList {
    private BlockingQueue<AccessCodeGenerated> accessCodeGenerateds;

    public AccessCodeGeneratedList() {
        this.accessCodeGenerateds = new LinkedBlockingDeque<>();
    }

    public boolean addElementsToBuffer(BlockingQueue<AccessCodeGenerated> partialAccessCodeGenerateds) {
        return accessCodeGenerateds.addAll(partialAccessCodeGenerateds);
    }

    public boolean addElementToBuffer(AccessCodeGenerated partialAccessCodeGenerateds) {
        return accessCodeGenerateds.add(partialAccessCodeGenerateds);
    }
}

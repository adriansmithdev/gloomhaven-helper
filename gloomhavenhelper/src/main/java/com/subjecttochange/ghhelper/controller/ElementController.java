package com.subjecttochange.ghhelper.controller;

import com.subjecttochange.ghhelper.exception.BadRequestException;
import com.subjecttochange.ghhelper.exception.Errors;
import com.subjecttochange.ghhelper.exception.ResourceNotFoundException;
import com.subjecttochange.ghhelper.persistence.model.orm.Element;
import com.subjecttochange.ghhelper.persistence.model.orm.Room;
import com.subjecttochange.ghhelper.persistence.model.orm.Stat;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.MonsterInstance;
import com.subjecttochange.ghhelper.persistence.repository.ElementRepository;
import com.subjecttochange.ghhelper.persistence.repository.RoomRepository;
import com.subjecttochange.ghhelper.persistence.repository.StatRepository;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Set;

@RestController
@ToString
public class ElementController {

    private RoomRepository roomRepository;
    private ElementRepository elementRepository;

    @Autowired
    public ElementController(RoomRepository roomRepository, ElementRepository elementRepository) {
        this.roomRepository = roomRepository;
        this.elementRepository = elementRepository;
    }

    @GetMapping("/elements")
    @ResponseBody
    public Page<Element> getElement(@RequestParam(value = "hash") String hash,
                                 @RequestParam(value = "id", required = false) Long id,
                                 Pageable pageable) {
        if (id == null) {
            return elementRepository.findByRoomHash(hash, pageable);
        } else {
            Element element = elementRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(Errors.NO_ID_ELEMENT + id));
            Element.checkHashMatchesGiven(element, hash, id);
            return new PageImpl<>(Collections.singletonList(element));
        }
    }

    @PutMapping("/elements")
    @ResponseBody
    public Element updateElement(@RequestParam(value = "hash") String hash,
                              @RequestParam(value = "id") Long id,
                              @Valid @RequestBody(required = false) Element request) {
        Element element = elementRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(Errors.NO_ID_ELEMENT + id));
        element = element.updateElement(element);
        return elementRepository.save(element);
    }

    public void createElements(Room room) {
        Element.ElementType[] elementTypes = Element.ElementType.values();

        for (int i = 0; i < elementTypes.length; i++) {
            elementRepository.save(Element.createElement(elementTypes[i], 0, room));
        }
    }

    public void decrementElements(Room room) {
        Set<Element> elementSet = room.getElements();
        for (Element element : elementSet) {
            element.setStrength(Math.max(element.getStrength() - 1, 0));
        }
    }
}

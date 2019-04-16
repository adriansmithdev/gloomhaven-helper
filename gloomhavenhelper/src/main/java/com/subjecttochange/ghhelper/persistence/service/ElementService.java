package com.subjecttochange.ghhelper.persistence.service;

import com.subjecttochange.ghhelper.exception.Errors;
import com.subjecttochange.ghhelper.exception.ResourceNotFoundException;
import com.subjecttochange.ghhelper.persistence.model.orm.Element;
import com.subjecttochange.ghhelper.persistence.repository.ElementRepository;
import com.subjecttochange.ghhelper.persistence.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class ElementService {

    private final RoomRepository roomRepository;
    private final ElementRepository elementRepository;

    @Autowired
    public ElementService(RoomRepository roomRepository, ElementRepository elementRepository) {
        this.roomRepository = roomRepository;
        this.elementRepository = elementRepository;
    }

    @Transactional
    public Page<Element> getElement(String hash, Long id, Pageable pageable) {
        if (id == null) {
            return elementRepository.findByRoomHash(hash, pageable);
        } else {
            Element element = elementRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(Errors.NO_ID_ELEMENT + id));
            Element.checkHashMatchesGiven(element, hash, id);
            return new PageImpl<>(Collections.singletonList(element));
        }
    }

    @Transactional
    public Element updateElement(String hash, Long id, Element request) {
        Element element = elementRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(Errors.NO_ID_ELEMENT + id));
        Element.checkHashMatchesGiven(element, hash, id);
        element = element.updateElement(request);
        return elementRepository.save(element);
    }

}

package com.subjecttochange.ghhelper.controller;

import com.subjecttochange.ghhelper.persistence.model.EventType;
import com.subjecttochange.ghhelper.persistence.model.orm.Element;
import com.subjecttochange.ghhelper.persistence.service.ElementService;
import com.subjecttochange.ghhelper.persistence.service.StreamService;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@ToString
public class ElementController {

    private final ElementService elementService;
    private final StreamService streamService;

    @Autowired
    public ElementController(ElementService elementService, StreamService streamService) {
        this.elementService = elementService;
        this.streamService = streamService;
    }

    @GetMapping("/elements")
    @ResponseBody
    public Page<Element> getElement(@RequestParam(value = "hash") String hash,
                                    @RequestParam(value = "id", required = false) Long id,
                                    Pageable pageable) {
        return elementService.getElement(hash, id, pageable);
    }

    @PutMapping("/elements")
    @ResponseBody
    public Element updateElement(@RequestParam(value = "hash") String hash,
                                 @RequestParam(value = "id") Long id,
                                 @Valid @RequestBody(required = false) Element request) {
        Element element = elementService.updateElement(hash, id, request);
        streamService.newEvent(EventType.PUT_ELEMENT, hash, element);
        return element;
    }

}

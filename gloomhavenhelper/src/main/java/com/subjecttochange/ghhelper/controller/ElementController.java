package com.subjecttochange.ghhelper.controller;

import com.subjecttochange.ghhelper.exception.Errors;
import com.subjecttochange.ghhelper.exception.ResourceNotFoundException;
import com.subjecttochange.ghhelper.persistence.model.orm.Element;
import com.subjecttochange.ghhelper.persistence.repository.ElementRepository;
import com.subjecttochange.ghhelper.persistence.repository.RoomRepository;
import com.subjecttochange.ghhelper.persistence.service.ElementService;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;

@RestController
@ToString
public class ElementController {

    private final ElementService elementService;

    @Autowired
    public ElementController(ElementService elementService) {
        this.elementService = elementService;
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
        return elementService.updateElement(hash, id, request);
    }

}

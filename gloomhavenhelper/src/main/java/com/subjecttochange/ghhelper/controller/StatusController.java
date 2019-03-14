package com.subjecttochange.ghhelper.controller;

import com.subjecttochange.ghhelper.exception.BadRequestException;
import com.subjecttochange.ghhelper.exception.Errors;
import com.subjecttochange.ghhelper.exception.ResourceNotFoundException;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.Status;
import com.subjecttochange.ghhelper.persistence.repository.StatusRepository;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;

@RestController
@ToString
public class StatusController {

    private StatusRepository statusRepository;

    @Autowired
    public StatusController(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @GetMapping("/statuses")
    @ResponseBody
    public Page<Status> getStatus(@RequestParam(value = "id", required = false) Long id, Pageable pageable) {
        if (id == null) {
            return statusRepository.findAll(pageable);
        } else {
            return new PageImpl<>(Collections.singletonList(statusRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(Errors.NO_ID_STATUS + id))));
        }
    }

    @PostMapping("/statuses")
    @ResponseBody
    public Status createStatus(@Valid @RequestBody Status statusRequest) {
        if (statusRequest.getName() == null || statusRequest.getTooltip() == null) {
            throw new BadRequestException("Name and tooltip are both required!");
        }
        Status status = Status.create(statusRequest.getName(), statusRequest.getTooltip());
        return statusRepository.save(status);
    }

    @PutMapping("/statuses")
    @ResponseBody
    public Status updateStatus(@RequestParam(value = "id") Long id, @Valid @RequestBody Status statusRequest) {
        Status statusResult = statusRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(Errors.NO_ID_STATUS + id));
        statusResult = statusResult.updateStatus(statusRequest);
        return statusRepository.save(statusResult);
    }

    @DeleteMapping("/statuses")
    public ResponseEntity<?> deleteStatus(@RequestParam(value = "id") Long id) {
        statusRepository.delete(statusRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(Errors.NO_ID_STATUS + id)));
        return ResponseEntity.ok().build();
    }
}


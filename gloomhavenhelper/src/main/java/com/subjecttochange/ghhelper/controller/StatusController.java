package com.subjecttochange.ghhelper.controller;

import com.subjecttochange.ghhelper.persistence.model.orm.monster.Status;
import com.subjecttochange.ghhelper.persistence.service.StatusService;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@ToString
public class StatusController {

    private final StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping("/statuses")
    @ResponseBody
    public Page<Status> getStatus(@RequestParam(value = "id", required = false) Long id, Pageable pageable) {
        return statusService.getStatus(id, pageable);
    }

    @PostMapping("/statuses")
    @ResponseBody
    public Status createStatus(@Valid @RequestBody Status statusRequest) {
        return statusService.createStatus(statusRequest);
    }

    @PutMapping("/statuses")
    @ResponseBody
    public Status updateStatus(@RequestParam(value = "id") Long id, @Valid @RequestBody Status statusRequest) {
        return statusService.updateStatus(id, statusRequest);
    }

    @DeleteMapping("/statuses")
    public ResponseEntity<?> deleteStatus(@RequestParam(value = "id") Long id) {
        return statusService.deleteStatus(id);
    }
}


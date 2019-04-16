package com.subjecttochange.ghhelper.persistence.service;

import com.subjecttochange.ghhelper.exception.BadRequestException;
import com.subjecttochange.ghhelper.exception.Errors;
import com.subjecttochange.ghhelper.exception.ResourceNotFoundException;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.Status;
import com.subjecttochange.ghhelper.persistence.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;

@Service
public class StatusService {

    private final StatusRepository statusRepository;

    @Autowired
    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Transactional
    public Page<Status> getStatus(Long id, Pageable pageable) {
        if (id == null) {
            return statusRepository.findAll(pageable);
        } else {
            return new PageImpl<>(Collections.singletonList(statusRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(Errors.NO_ID_STATUS + id))));
        }
    }

    @Transactional
    public Status createStatus(Status statusRequest) {
        if (statusRequest.getName() == null || statusRequest.getTooltip() == null) {
            throw new BadRequestException("Name and tooltip are both required!");
        }
        Status status = Status.create(statusRequest.getName(), statusRequest.getTooltip());
        return statusRepository.save(status);
    }

    @Transactional
    public Status updateStatus(Long id, Status statusRequest) {
        Status statusResult = statusRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(Errors.NO_ID_STATUS + id));
        statusResult = statusResult.updateStatus(statusRequest);
        return statusRepository.save(statusResult);
    }

    @Transactional
    public ResponseEntity<?> deleteStatus(Long id) {
        statusRepository.delete(statusRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(Errors.NO_ID_STATUS + id)));
        return ResponseEntity.ok().build();
    }
}

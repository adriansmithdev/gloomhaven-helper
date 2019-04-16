package com.subjecttochange.ghhelper.persistence.service;

import com.subjecttochange.ghhelper.exception.BadRequestException;
import com.subjecttochange.ghhelper.exception.Errors;
import com.subjecttochange.ghhelper.exception.ResourceNotFoundException;
import com.subjecttochange.ghhelper.persistence.model.orm.Stat;
import com.subjecttochange.ghhelper.persistence.repository.StatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class StatService {

    private final StatRepository statRepository;

    @Autowired
    public StatService(StatRepository statRepository) {
        this.statRepository = statRepository;
    }

    @Transactional
    public Page<Stat> getStat(Long id, Pageable pageable) {
        if (id == null) {
            return statRepository.findAll(pageable);
        } else {
            return new PageImpl<>(Collections.singletonList(statRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(Errors.NO_ID_STAT + id))));
        }
    }

    @Transactional
    public Stat createStat(Stat statRequest) {
        if (statRequest.getName() == null || statRequest.getTooltip() == null) {
            throw new BadRequestException("Name and tooltip are both required!");
        }
        Stat stat = Stat.create(statRequest.getName(), statRequest.getTooltip());
        return statRepository.save(stat);
    }

    @Transactional
    public Stat updateStat(Long id, Stat statRequest) {
        Stat statResult = statRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(Errors.NO_ID_STAT + id));
        statResult = statResult.updateStat(statRequest);
        return statRepository.save(statResult);
    }

    @Transactional
    public ResponseEntity<?> deleteStat(Long id) {
        statRepository.delete(statRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(Errors.NO_ID_STAT + id)));
        return ResponseEntity.ok().build();
    }
}

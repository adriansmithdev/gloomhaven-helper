package com.subjecttochange.ghhelper.controller;

import com.subjecttochange.ghhelper.exception.BadRequestException;
import com.subjecttochange.ghhelper.exception.Errors;
import com.subjecttochange.ghhelper.exception.ResourceNotFoundException;
import com.subjecttochange.ghhelper.persistence.model.orm.Stat;
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

@RestController
@ToString
public class StatController {

    private StatRepository statRepository;

    @Autowired
    public StatController(StatRepository statRepository) {
        this.statRepository = statRepository;
    }

    @GetMapping("/stats")
    @ResponseBody
    public Page<Stat> getStat(@RequestParam(value = "id", required = false) Long id, Pageable pageable) {
        if (id == null) {
            return statRepository.findAll(pageable);
        } else {
            return new PageImpl<>(Collections.singletonList(
                    statRepository.findById(id).orElseThrow(() ->
                            new ResourceNotFoundException(Errors.NO_ID_STAT + id))));
        }
    }

    @PostMapping("/stats")
    @ResponseBody
    public Stat createStat(@Valid @RequestBody Stat statRequest) {
        if (statRequest.getName() == null || statRequest.getTooltip() == null) {
            throw new BadRequestException("Name and tooltip are both required!");
        }
        Stat stat = Stat.create(statRequest.getName(), statRequest.getTooltip());
        return statRepository.save(stat);
    }

    @PutMapping("/stats")
    @ResponseBody
    public Stat updateStat(@RequestParam(value = "id") Long id, @Valid @RequestBody Stat statRequest) {
        Stat statResult = statRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(Errors.NO_ID_STAT + id));
        statResult = statResult.updateStat(statRequest);
        statResult = statRepository.save(statResult);
        return statResult;
    }

    @DeleteMapping("/stats")
    public ResponseEntity<?> deleteStat(@RequestParam(value = "id") Long id) {
        statRepository.delete(statRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(Errors.NO_ID_STAT + id)));
        return ResponseEntity.ok().build();
    }
}
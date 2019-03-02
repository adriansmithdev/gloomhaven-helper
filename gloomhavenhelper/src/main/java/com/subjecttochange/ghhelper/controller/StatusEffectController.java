package com.subjecttochange.ghhelper.controller;

import com.subjecttochange.ghhelper.exception.BadRequestException;
import com.subjecttochange.ghhelper.exception.ResourceNotFoundException;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.MonsterCondition;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.MonsterConditionKey;
import com.subjecttochange.ghhelper.persistence.model.orm.monster.MonsterInstance;
import com.subjecttochange.ghhelper.persistence.repository.ConditionRepository;
import com.subjecttochange.ghhelper.persistence.repository.MonsterInstanceRepository;
import com.subjecttochange.ghhelper.persistence.repository.StatusEffectRepository;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@ToString
public class StatusEffectController {

    private StatusEffectRepository statusEffectRepository;
    private MonsterInstanceRepository monsterInstanceRepository;
    private ConditionRepository conditionRepository;
    private static final String NOT_FOUND_STATUS_EFFECT = "Status not found with id ";
    private static final String NOT_FOUND_CONDITION = "Condition not found with id ";
    private static final String NOT_FOUND_INSTANCE = "Monster instance not found with id ";
    private static final String BAD_REQUEST = "Expected monsterInstanceId or id";

    @Autowired
    public StatusEffectController(StatusEffectRepository statusEffectRepository,
                                  MonsterInstanceRepository monsterInstanceRepository,
                                  ConditionRepository conditionRepository) {
        this.statusEffectRepository = statusEffectRepository;
        this.monsterInstanceRepository = monsterInstanceRepository;
        this.conditionRepository = conditionRepository;
    }

    @GetMapping("/statuseffects")
    @ResponseBody
    public Page<MonsterCondition> getStatusEffects(@RequestParam(value = "hash") String hash,
                                           @RequestParam(value = "monsterInstanceId", required = false) Long monsterInstanceId,
                                           @RequestParam(value = "id", required = false) Long id) {
        if (id == null && monsterInstanceId == null) {
            throw new BadRequestException(BAD_REQUEST);
        }

        if (id == null) {
            MonsterInstance instance = monsterInstanceRepository.findById(monsterInstanceId)
                    .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND_INSTANCE));
            MonsterInstance.checkHashMatchesGiven(instance, hash, monsterInstanceId);
            return new PageImpl<>(new ArrayList<>(instance.getStatuses()));
        } else {
            MonsterCondition monsterCondition = statusEffectRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND_STATUS_EFFECT));
            MonsterInstance.checkHashMatchesGiven(monsterCondition.getInstance(), hash, id);
            return new PageImpl<>(Collections.singletonList(monsterCondition));
        }
    }

    @PostMapping("/statuseffects")
    @ResponseBody
    public MonsterCondition createStatusEffect(@RequestParam(value = "hash") String hash,
                                       @Valid @RequestBody MonsterCondition condition) {
        MonsterInstance monsterInstance = monsterInstanceRepository.findById(condition.getMonsterInstanceId())
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND_INSTANCE + condition.getMonsterInstanceId()));

        MonsterInstance.checkHashMatchesGiven(monsterInstance, hash, monsterInstance.getId());

        MonsterConditionKey monsterConditionKey = new MonsterConditionKey();
        monsterConditionKey.setConditionId(condition.getConditionId());
        monsterConditionKey.setMonsterInstanceId(condition.getMonsterInstanceId());

        MonsterCondition monsterCondition = new MonsterCondition();
        monsterCondition.setId(monsterConditionKey);
        monsterCondition.setCondition(conditionRepository.findById(condition.getConditionId())
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND_CONDITION + condition.getConditionId())));
        monsterCondition.setInstance(monsterInstance);

        return statusEffectRepository.save(monsterCondition);
    }

//    @PutMapping("/status")
//    @ResponseBody
//    public Condition updateRoom(@RequestParam(value = "id") Long id, @Valid @RequestBody Condition condition) {
//        Condition conditionResult = statusEffectRepository.findById(id).orElseThrow(() ->
//                new ResourceNotFoundException(NOT_FOUND + id));
//        conditionResult.setName(condition.getName());
//        conditionResult.setTooltip(condition.getTooltip());
//        conditionResult = statusEffectRepository.save(conditionResult);
//        return conditionResult;
//    }
//
//    @DeleteMapping("/status")
//    public ResponseEntity<?> deleteRoom(@RequestParam(value = "id") Long id) {
//        statusEffectRepository.delete(statusEffectRepository.findById(id).orElseThrow(() ->
//                new ResourceNotFoundException(NOT_FOUND + id)));
//        return ResponseEntity.ok().build();
//    }
}

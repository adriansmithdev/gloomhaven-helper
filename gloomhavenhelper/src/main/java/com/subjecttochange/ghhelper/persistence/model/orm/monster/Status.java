package com.subjecttochange.ghhelper.persistence.model.orm.monster;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.subjecttochange.ghhelper.persistence.model.orm.BaseModel;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Status extends BaseModel {
    @Id
    @GeneratedValue(generator = "status_generator")
    @SequenceGenerator(
            name = "status_generator",
            sequenceName = "status_sequence",
            initialValue = 1
    )
    private Long id;
    private String name;
    private String tooltip;

    public Status() {
        super();
    }

    public static Status create(String name, String tooltip) {
        Status status = new Status();
        status.setName(name);
        status.setTooltip(tooltip);
        return status;
    }

    public Status updateStatus(Status statusRequest){
        if (statusRequest.getName() != null) {
            setName(statusRequest.getName());
        }
        if (statusRequest.getTooltip() != null) {
            setTooltip(statusRequest.getTooltip());
        }
        return this;
    }
}

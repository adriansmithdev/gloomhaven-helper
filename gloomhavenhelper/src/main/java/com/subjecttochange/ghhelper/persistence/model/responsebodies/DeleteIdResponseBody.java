package com.subjecttochange.ghhelper.persistence.model.responsebodies;

import lombok.Data;
import lombok.NonNull;

@Data
public class DeleteIdResponseBody {
    @NonNull
    private Long id;

    public static DeleteIdResponseBody create(Long id) {
        return new DeleteIdResponseBody(id);
    }
}

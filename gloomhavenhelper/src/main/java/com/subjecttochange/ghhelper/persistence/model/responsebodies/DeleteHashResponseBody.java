package com.subjecttochange.ghhelper.persistence.model.responsebodies;

import lombok.Data;
import lombok.NonNull;

@Data
public class DeleteHashResponseBody {
    @NonNull
    private String hash;

    public static DeleteHashResponseBody create(String hash) {
        return new DeleteHashResponseBody(hash);
    }
}

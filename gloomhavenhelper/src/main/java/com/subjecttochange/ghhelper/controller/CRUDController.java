package com.subjecttochange.ghhelper.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public abstract class CRUDController<T> {
    abstract Page<T> find();
    abstract T create();
    abstract T update();
    abstract ResponseEntity<?> delete();
}

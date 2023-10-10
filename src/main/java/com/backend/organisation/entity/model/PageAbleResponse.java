package com.backend.organisation.entity.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;

@Data
@AllArgsConstructor
public class PageAbleResponse<T>{
    public Collection<T> data;
    public long count;
}

package com.datu.logistics.order.service.dto;

import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class PageDTO<T> {

    private List<T> content = Collections.emptyList();

    public static <T> PageDTO<T> by(List<T> content) {
        PageDTO<T> pageDTO = new PageDTO<>();
        pageDTO.setContent(content);
        return pageDTO;
    }
}

package com.codky.book.springboot2webservice2.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor // 선언된 모든 final 필드가 포함된 생성자를 생성해준다, final 이 없는 필드는 생성자에 포함되지 않는다.
public class HelloResponseDto {

    private final String name;
    private final int amount;
}

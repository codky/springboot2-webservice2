package com.codky.book.springboot2webservice2.web.dto;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    /**
     * 롬복의 @Getter 로 get 메소드가, @RequiredArgsConstructor 로 생성자가 자동으로 생성되는지 증명
     */
    @Test
    public void 롬복_기능_테스트() {
        // given
        String name = "test";
        int amount = 1000;

        // when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        // then
        assertThat(dto.getName()).isEqualTo(name); // assertThat 은 assertj 라는 테스트 검증 라이브러리의 검증 메소드, 메소드 체이닝으로 메소드를 이어서 사용 가능
        assertThat(dto.getAmount()).isEqualTo(amount);
    }

    // https://youngsubee.tistory.com/entry/Spring-boot-JsonPathResultMatchers%EC%97%90%EB%9F%AC
    // https://tube-life.tistory.com/14
}
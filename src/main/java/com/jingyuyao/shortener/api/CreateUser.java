package com.jingyuyao.shortener.api;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateUser {
    private int externalId;
    private String externalSource;
    private String email;
    private String name;
}

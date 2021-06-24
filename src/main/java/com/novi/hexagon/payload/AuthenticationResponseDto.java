package com.novi.hexagon.payload;

import com.novi.hexagon.model.Dto;

public class AuthenticationResponseDto {

final Dto dto;

    public AuthenticationResponseDto(Dto dto) {
        this.dto = dto;
    }

    public Dto getDto() {
        return dto;
    }

}
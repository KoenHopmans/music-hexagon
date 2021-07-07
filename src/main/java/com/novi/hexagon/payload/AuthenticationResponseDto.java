package com.novi.hexagon.payload;

public class AuthenticationResponseDto {

final AuthorityDto dto;

    public AuthenticationResponseDto(AuthorityDto dto) {
        this.dto = dto;
    }

    public AuthorityDto getDto() {
        return dto;
    }

}
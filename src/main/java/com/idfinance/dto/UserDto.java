package com.idfinance.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class UserDto {
    @NonNull private String userName;
    @NonNull private String coinSymbol;
}

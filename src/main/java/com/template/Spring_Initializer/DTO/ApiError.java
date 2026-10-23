package com.template.Spring_Initializer.DTO;

import java.util.List;

public record ApiError(
int status,
    String  error,
    List<String> messages){
}

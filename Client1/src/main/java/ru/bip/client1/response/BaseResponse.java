package ru.bip.client1.response;


import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class BaseResponse {
    public boolean success;
    public String message;
}

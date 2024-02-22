package com.HotelManagement.Dto;

import lombok.*;

//@Getter
//@Setter
@AllArgsConstructor
@NoArgsConstructor
//@ToString
@Data
public class JwtRequest {

    private  String username;
    private  String password;


}

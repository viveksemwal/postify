package com.vivek.postify.modal;

import lombok.*;

import javax.persistence.Entity;

@Data
public class User {
    private String username;
    private String displayname;
    private String password;

}

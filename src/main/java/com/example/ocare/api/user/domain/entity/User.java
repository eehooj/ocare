package com.example.ocare.api.user.domain.entity;

import com.example.ocare.global.common.entity.EntityExtension;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "USER")
@Getter
public class User extends EntityExtension {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long userId;
}

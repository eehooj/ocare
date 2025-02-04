package com.example.ocare.global.common.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
@MappedSuperclass
public class EntityExtension {

  protected LocalDateTime createDt;

}

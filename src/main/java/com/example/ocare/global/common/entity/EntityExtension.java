package com.example.ocare.global.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;


@Getter
@MappedSuperclass
public class EntityExtension {

  /**
   * 등록일시
   */
  @ColumnDefault("CURRENT_TIMESTAMP")
  protected LocalDateTime createDatetime;

}

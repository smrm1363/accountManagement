package com.mohammadreza.mirali.accountManagement.domain.moneyTransfer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Map;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class RateDto {
  private Map<String,Double> rates;
}

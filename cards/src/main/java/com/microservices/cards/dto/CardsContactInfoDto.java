package com.microservices.cards.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix ="cards")
@Getter
@Setter

public class CardsContactInfoDto {
    private String message;
    private Map<String, String> contactDetails;
    private List<String> onCallSupport;
}

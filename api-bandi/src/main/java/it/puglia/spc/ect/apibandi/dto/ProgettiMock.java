package it.puglia.spc.ect.apibandi.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "mock.progetti")
public class ProgettiMock extends Progetti {

}

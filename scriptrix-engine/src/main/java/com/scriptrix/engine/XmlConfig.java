package com.scriptrix.engine;

import com.scriptrix.engine.project.Project;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import java.util.Map;

@Configuration
public class XmlConfig {

  @Bean
  public Jaxb2Marshaller jaxb2Marshaller() {
    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
    marshaller.setClassesToBeBound(Project.class);
    marshaller.setMarshallerProperties(Map.of("jaxb.formatted.output", true));
    return marshaller;
  }

  @Bean
  public Marshaller marshaller() {
    return jaxb2Marshaller();
  }

  @Bean
  public Unmarshaller unmarshaller() {
    return jaxb2Marshaller();
  }
}
package com.everbestlab.sms_services;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

@Testcontainers
class DockerComposeTest {

  @Container
  static DockerComposeContainer<?> environment =
    new DockerComposeContainer<>(new File("docker-compose.yml"))
      .withExposedService("smsServicedb_1", 5432, Wait.forListeningPort());

  @Test
  void dockerComposeTest() {
    System.out.println(environment.getServicePort("smsServicedb_1", 5432));
  }
}
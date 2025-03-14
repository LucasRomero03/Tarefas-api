package com.lrtech.toDoList.config.healthCheck;

import java.net.InetAddress;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class HealthCheck implements HealthIndicator {

  @Override
  public Health health() {
    try {
      InetAddress address = InetAddress.getByName("localhost");
      if (address.isReachable(1000)) {
        return Health.up().build();
      }
    } catch (Exception e) {
      return Health.down().withDetail("motivo", e.getMessage()).build();
    }
    return Health.down().withDetail("motivo", "motivo desconhecido").build();
  }

}

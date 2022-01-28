package com.kyc.catalogs.config;

import org.springframework.boot.actuate.autoconfigure.health.ConditionalOnEnabledHealthIndicator;
import org.springframework.boot.actuate.autoconfigure.health.HealthContributorAutoConfiguration;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnEnabledHealthIndicator("external-services")
@AutoConfigureBefore(HealthContributorAutoConfiguration.class)
public class ExternalServicesHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        return Health.unknown().build();
    }
}

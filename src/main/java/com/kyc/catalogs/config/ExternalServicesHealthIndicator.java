package com.kyc.catalogs.config;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.health.autoconfigure.contributor.ConditionalOnEnabledHealthIndicator;
import org.springframework.boot.health.autoconfigure.contributor.HealthContributorAutoConfiguration;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

;

@Component
@ConditionalOnEnabledHealthIndicator("external-services")
@AutoConfigureBefore(HealthContributorAutoConfiguration.class)
public class ExternalServicesHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        return Health.unknown().build();
    }
}

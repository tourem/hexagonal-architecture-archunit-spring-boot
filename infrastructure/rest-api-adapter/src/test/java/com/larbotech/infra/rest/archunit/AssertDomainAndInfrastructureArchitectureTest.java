package com.larbotech.infra.rest.archunit;



import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;
import org.junit.jupiter.api.Test;

public class AssertDomainAndInfrastructureArchitectureTest extends AbstractArchitectureTest {

    private static final Architectures.LayeredArchitecture domainAndInfrastructureArchitecture = Architectures.layeredArchitecture()
            .layer(DOMAIN_LAYER)
            .definedBy(DOMAIN_BASE_PACKAGE)
            .layer(INFRASTRUCTURE_PERSISTENCE_LAYER)
            .definedBy(INFRASTRUCTURE_PERSISTENCE_PACKAGE)
            .layer(INFRASTRUCTURE_REST_LAYER)
            .definedBy(INFRASTRUCTURE_REST_PACKAGE);


    @Test
    public void domainLayerShouldOnlyBeAccessedByInfrastructurePersistenceAndRestLayers() {
        ArchRule rule = domainAndInfrastructureArchitecture.whereLayer(DOMAIN_LAYER)
                .mayOnlyBeAccessedByLayers(INFRASTRUCTURE_PERSISTENCE_LAYER, INFRASTRUCTURE_REST_LAYER);
        rule.check(classes);
    }

    @Test
    public void infrastructurePersistenceLayerMayOnlyBeAccessedByInfrastructureRestLayer() {
        ArchRule rule = domainAndInfrastructureArchitecture
                .whereLayer(INFRASTRUCTURE_PERSISTENCE_LAYER)
                .mayOnlyBeAccessedByLayers(INFRASTRUCTURE_REST_LAYER);
        rule.check(classes);
    }

    @Test
    public void restLayerShouldNotBeAccessedByAnyLayer() {
        ArchRule rule = domainAndInfrastructureArchitecture.whereLayer(INFRASTRUCTURE_REST_LAYER)
                .mayNotBeAccessedByAnyLayer();
        rule.check(classes);
    }

}
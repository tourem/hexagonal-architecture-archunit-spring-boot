package com.larbotech.infra.rest.archunit;


import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = AbstractArchitectureTest.BASE_PACKAGE)
public class LayeredArchitectureTest extends AbstractArchitectureTest {
    @ArchTest
    static final ArchRule layer_dependencies_are_respected = layeredArchitecture()

            .layer("Infrastructure").definedBy("com.larbotech.infra..")
            .layer("Rest").definedBy("com.larbotech.infra.rest..")
            .layer("Persistence").definedBy("com.larbotech.infra.persistence..")
            .layer("Domain").definedBy("com.larbotech.domain..")


            .whereLayer("Rest").mayNotBeAccessedByAnyLayer()
            .whereLayer("Persistence").mayOnlyBeAccessedByLayers("Rest")
            .whereLayer("Domain").mayOnlyBeAccessedByLayers("Persistence", "Rest");

}
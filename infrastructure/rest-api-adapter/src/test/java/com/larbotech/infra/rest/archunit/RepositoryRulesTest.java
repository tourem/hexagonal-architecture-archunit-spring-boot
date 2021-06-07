package com.larbotech.infra.rest.archunit;


import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import javax.persistence.Entity;
import javax.persistence.EntityManager;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = AbstractArchitectureTest.INFRASTRUCTURE_PERSISTENCE_PACKAGE)
public class RepositoryRulesTest extends AbstractArchitectureTest {
    @ArchTest
    static final ArchRule Repositories_must_reside_in_a_dao_package =
            classes().that().haveNameMatching(".*Repository").should().resideInAPackage("..repository..")
                    .as("Repositories should reside in a package '..repository..'");

    @ArchTest
    static final ArchRule entities_must_reside_in_a_domain_package =
            classes().that().areAnnotatedWith(Entity.class).should().resideInAPackage("..entity..")
                    .as("Entities should reside in a package '..domain..'");

    @ArchTest
    static final ArchRule only_Repositories_may_use_the_EntityManager =
            noClasses().that().resideOutsideOfPackage("..repository..")
                    .should().accessClassesThat().areAssignableTo(EntityManager.class)
                    .as("Only Repositories may use the " + EntityManager.class.getSimpleName());

}

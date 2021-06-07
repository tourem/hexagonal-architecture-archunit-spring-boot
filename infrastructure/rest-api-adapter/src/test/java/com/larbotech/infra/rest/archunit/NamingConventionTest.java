package com.larbotech.infra.rest.archunit;

import com.larbotech.infra.rest.controller.AbstractController;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = {AbstractArchitectureTest.BASE_PACKAGE})
public class NamingConventionTest extends AbstractArchitectureTest {

    @ArchTest
    static ArchRule services_should_be_prefixed =
            classes()
                    .that().resideInAPackage("..service..")
                    .and().areAnnotatedWith(Service.class)
                    .should().haveSimpleNameEndingWith("Service");

    @ArchTest
    static ArchRule controllers_should_not_have_Gui_in_name =
            classes()
                    .that().resideInAPackage("..controller..")
                    .should().haveSimpleNameNotContaining("Gui");

    @ArchTest
    static ArchRule controllers_should_be_suffixed =
            classes()
                    .that().resideInAPackage("..controller..")
                    .or().areAnnotatedWith(Controller.class)
                    .or().areAssignableTo(AbstractController.class)
                    .should().haveSimpleNameEndingWith("Controller");

    @ArchTest
    static ArchRule classes_named_controller_should_be_in_a_controller_package =
            classes()
                    .that().haveSimpleNameContaining("Controller")
                    .should().resideInAPackage("..controller..");

    @ArchTest
    private final ArchRule allConfigurationClassesShouldBeInConfigurationPackage = classes()
            .that().areAnnotatedWith(Configuration.class)
            .should().resideInAPackage("..configuration..");
}

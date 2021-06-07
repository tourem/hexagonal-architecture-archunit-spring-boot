package com.larbotech.infra.rest.archunit;

import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

public class RestAdaptersComponentsTest extends AbstractArchitectureTest {

    @Test
    public void noClassesWithControllerOrRestControllerAnnotationShouldResideOutsideOfRestAdaptersPackages() {
        ArchRule rule = ArchRuleDefinition.noClasses()
                .that().areAnnotatedWith(Controller.class)
                .or().areAnnotatedWith(RestController.class)
                .should().resideOutsideOfPackage(INFRASTRUCTURE_REST_PACKAGE);
        rule.check(classes);
    }

    @Test
    public void publicControllerMethodsShouldBeAnnotatedWithARequestMapping() {
        ArchRule rule = ArchRuleDefinition.methods()
                .that().arePublic()
                .and().areDeclaredInClassesThat().resideInAPackage("..infra.rest.controller..")
                .and().areDeclaredInClassesThat().haveSimpleNameEndingWith("Controller")
                .and().areDeclaredInClassesThat().areAnnotatedWith(Controller.class)
                .or().areDeclaredInClassesThat().areAnnotatedWith(RestController.class)
                .should().beAnnotatedWith(RequestMapping.class)
                .orShould().beAnnotatedWith(GetMapping.class)
                .orShould().beAnnotatedWith(PostMapping.class)
                .orShould().beAnnotatedWith(PatchMapping.class)
                .orShould().beAnnotatedWith(DeleteMapping.class);
        rule.check(classes);
    }
}

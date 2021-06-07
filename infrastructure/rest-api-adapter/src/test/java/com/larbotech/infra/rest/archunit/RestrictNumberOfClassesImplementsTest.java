package com.larbotech.infra.rest.archunit;


import com.larbotech.domain.api.SendMoneyUseCase;
import com.larbotech.domain.api.service.SendMoneyService;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.base.DescribedPredicate.lessThanOrEqualTo;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = AbstractArchitectureTest.DOMAIN_BASE_PACKAGE)
public class RestrictNumberOfClassesImplementsTest extends AbstractArchitectureTest {

    @ArchTest
    static final ArchRule no_new_classes_should_implement_SendMoneyUseCase =
            classes().that().implement(SendMoneyUseCase.class)
                    .should().containNumberOfElements(lessThanOrEqualTo(1))
                    .because("from now on new classes should implement " + SendMoneyService.class.getName());

}

package com.larbotech.infra.rest.archunit;


import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.library.GeneralCodingRules;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;
import static com.tngtech.archunit.library.GeneralCodingRules.*;

@AnalyzeClasses(packages = AbstractArchitectureTest.BASE_PACKAGE)
public class CodingRulesTest extends AbstractArchitectureTest {

    @ArchTest
    private final ArchRule no_generic_exceptions = NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS;

    @ArchTest
    private final ArchRule no_jodatime = NO_CLASSES_SHOULD_USE_JODATIME;

    @ArchTest
    private final ArchRule no_field_injection = NO_CLASSES_SHOULD_USE_FIELD_INJECTION;


    @Test
    public void noClassesShouldThrowGenericExceptions() {
        ArchRule rule = noClasses()
                .should(GeneralCodingRules.THROW_GENERIC_EXCEPTIONS);
        rule.check(classes);
    }

    @Test
    public void noClassesShouldUseStandardLogging() {
        ArchRule rule = noClasses()
                .should(GeneralCodingRules.USE_JAVA_UTIL_LOGGING);
        rule.check(classes);
    }

    /** impl The class below cannot be interface */
    @ArchTest
    public static final ArchRule interfaces_must_not_be_placed_in_implementation_packages = noClasses()
            .that()
            .resideInAPackage("..impl..")
            .should()
            .beInterfaces();

    @ArchTest
    public static final ArchRule NO_DEPRECATED_CLASSES_IN_COMP1_PACKAGE = noClasses().that()
            .areAnnotatedWith(Deprecated.class).should()
            .resideInAnyPackage("com.larbotech..")
            .because("deprecated classes should not be allowed in package com.larbotech");
}

package com.larbotech.infra.rest.archunit;


import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import org.junit.jupiter.api.BeforeAll;

@AnalyzeClasses(packages = AbstractArchitectureTest.BASE_PACKAGE)
public abstract class AbstractArchitectureTest {
    public static final String BASE_PACKAGE = "com.larbotech..";
    public static final String DOMAIN_BASE_PACKAGE = "com.larbotech.domain..";
    public static final String INFRASTRUCTURE_PERSISTENCE_PACKAGE = "com.larbotech.infra.persistence..";
    public static final String INFRASTRUCTURE_REST_PACKAGE = "com.larbotech.infra.rest..";
    // Port & Adapters
    public static final String DOMAIN_LAYER = "Domain layer";
    public static final String INFRASTRUCTURE_LAYER = "Infrastructure layer";
    public static final String INFRASTRUCTURE_PERSISTENCE_LAYER = "Infrastructure persistence layer";
    public static final String INFRASTRUCTURE_REST_LAYER = "Infrastructure api rest layer";


    static JavaClasses classes;

    @BeforeAll
    public static void setUp() {
        classes = new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                //.withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_ARCHIVES)
                .importPackages(BASE_PACKAGE);
    }
}
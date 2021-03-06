/*
 * Copyright 2021 Kato Shinya.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package org.thinkit.generator.entity.engine.formatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.thinkit.generator.entity.engine.catalog.EnvaliAnnotation;
import org.thinkit.generator.entity.engine.catalog.EnvaliErrorType;
import org.thinkit.generator.entity.engine.catalog.EnvaliMetaType;
import org.thinkit.generator.entity.engine.catalog.EnvaliNumericDataType;
import org.thinkit.generator.entity.engine.catalog.EnvaliNumericRangeType;
import org.thinkit.generator.entity.engine.catalog.EnvaliRegexMethod;
import org.thinkit.generator.entity.engine.catalog.EnvaliRegexModifier;
import org.thinkit.generator.entity.engine.catalog.EnvaliRegexPreset;
import org.thinkit.generator.entity.engine.dto.EntityCreator;
import org.thinkit.generator.entity.engine.dto.EntityDefinition;
import org.thinkit.generator.entity.engine.dto.EntityEnvaliDefinition;
import org.thinkit.generator.entity.engine.dto.EntityField;
import org.thinkit.generator.entity.engine.dto.EntityMatrix;
import org.thinkit.generator.entity.engine.dto.EntityMeta;
import org.thinkit.generator.entity.engine.dto.EntityResource;
import org.thinkit.generator.entity.engine.dto.EntityResourceGroup;
import org.thinkit.generator.entity.engine.dto.EnvaliLiteralMeta;
import org.thinkit.generator.entity.engine.dto.EnvaliMeta;
import org.thinkit.generator.entity.engine.dto.EnvaliNumericMeta;
import org.thinkit.generator.entity.engine.dto.EnvaliRegexMeta;

/**
 * {@link EntityResourceFormatter} をのテストケースを管理するクラスです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
public final class EntityResourceFormatterTest {

    @Test
    void testBaseCase() {

        final EntityMatrix entityMatrix = EntityMatrix.builder()
                .entityCreator(EntityCreator.builder().creator("Kato Shinya").build())
                .entityDefinitions(List.of(this.getEntityDefinitionOfBaseCase())).build();

        final EntityResourceGroup entityResourceGroup = EntityResourceFormatter.newInstance().format(entityMatrix);

        assertNotNull(entityResourceGroup);

        final EntityResource entityResource = entityResourceGroup.get(0);

        assertEquals("org.thinkit.test.generator.entity", entityResource.getPackageName());
        assertEquals("TestEntity", entityResource.getResourceName());
        assertEquals("java", entityResource.getExtension());
        assertEquals(EXPECTED_ENTITY_DEFAULT, entityResource.getResource());
    }

    @Test
    void testWhenFieldHasDefaultValue() {

        final EntityMatrix entityMatrix = EntityMatrix.builder()
                .entityCreator(EntityCreator.builder().creator("Kato Shinya").build())
                .entityDefinitions(List.of(this.getEntityDefinitionWhenFieldHasDefaultValue())).build();

        final EntityResourceGroup entityResourceGroup = EntityResourceFormatter.newInstance().format(entityMatrix);

        assertNotNull(entityResourceGroup);

        final EntityResource entityResource = entityResourceGroup.get(0);

        assertEquals("org.thinkit.test.generator.entity", entityResource.getPackageName());
        assertEquals("TestEntity", entityResource.getResourceName());
        assertEquals("java", entityResource.getExtension());
        assertEquals(EXPECTED_ENTITY_WHEN_FIELD_HAS_INITIAL_VALUE, entityResource.getResource());
    }

    @Test
    void testWhenFieldHasEnvaliAnnotation() {

        final EntityMatrix entityMatrix = EntityMatrix.builder()
                .entityCreator(EntityCreator.builder().creator("Kato Shinya").build())
                .entityDefinitions(List.of(this.getEntityDefinitionWhenFieldHasEnvaliAnnotation())).build();

        final EntityResourceGroup entityResourceGroup = EntityResourceFormatter.newInstance().format(entityMatrix);

        assertNotNull(entityResourceGroup);

        final EntityResource entityResource = entityResourceGroup.get(0);

        assertEquals("org.thinkit.test.generator.entity", entityResource.getPackageName());
        assertEquals("TestEntity", entityResource.getResourceName());
        assertEquals("java", entityResource.getExtension());
        assertEquals(EXPECTED_ENTITY_WHEN_FIELD_HAS_ENVALI_ANNOTATION, entityResource.getResource());
    }

    @Test
    void testWhenFieldHasEnvaliAnnotationWithOptions() {

        final EntityMatrix entityMatrix = EntityMatrix.builder()
                .entityCreator(EntityCreator.builder().creator("Kato Shinya").build())
                .entityDefinitions(List.of(this.getEntityDefinitionWhenFieldHasEnvaliAnnotationWithOptions())).build();

        final EntityResourceGroup entityResourceGroup = EntityResourceFormatter.newInstance().format(entityMatrix);

        assertNotNull(entityResourceGroup);

        final EntityResource entityResource = entityResourceGroup.get(0);

        assertEquals("org.thinkit.test.generator.entity", entityResource.getPackageName());
        assertEquals("TestEntity", entityResource.getResourceName());
        assertEquals("java", entityResource.getExtension());
        assertEquals(EXPECTED_ENTITY_WHEN_FIELD_HAS_ENVALI_ANNOTATION_WITH_OPTIONS, entityResource.getResource());
    }

    @Test
    void testWhenFieldHasLiteralEnvaliAnnotationWithOptions() {

        final EntityMatrix entityMatrix = EntityMatrix.builder()
                .entityCreator(EntityCreator.builder().creator("Kato Shinya").build())
                .entityDefinitions(List.of(this.getEntityDefinitionWhenFieldHasLiteralEnvaliAnnotationWithOptions()))
                .build();

        final EntityResourceGroup entityResourceGroup = EntityResourceFormatter.newInstance().format(entityMatrix);

        assertNotNull(entityResourceGroup);

        final EntityResource entityResource = entityResourceGroup.get(0);

        assertEquals("org.thinkit.test.generator.entity", entityResource.getPackageName());
        assertEquals("TestEntity", entityResource.getResourceName());
        assertEquals("java", entityResource.getExtension());
        assertEquals(EXPECTED_ENTITY_WHEN_FIELD_HAS_LITERAL_ENVALI_ANNOTATION_WITH_OPTIONS,
                entityResource.getResource());
    }

    @Test
    void testWhenFieldHasNumericEnvaliAnnotationWithOptions() {

        final EntityMatrix entityMatrix = EntityMatrix.builder()
                .entityCreator(EntityCreator.builder().creator("Kato Shinya").build())
                .entityDefinitions(List.of(this.getEntityDefinitionWhenFieldHasNumericEnvaliAnnotationWithOptions()))
                .build();

        final EntityResourceGroup entityResourceGroup = EntityResourceFormatter.newInstance().format(entityMatrix);

        assertNotNull(entityResourceGroup);

        final EntityResource entityResource = entityResourceGroup.get(0);

        assertEquals("org.thinkit.test.generator.entity", entityResource.getPackageName());
        assertEquals("TestEntity", entityResource.getResourceName());
        assertEquals("java", entityResource.getExtension());
        assertEquals(EXPECTED_ENTITY_WHEN_FIELD_HAS_NUMERIC_ENVALI_ANNOTATION_WITH_OPTIONS,
                entityResource.getResource());
    }

    @Test
    void testWhenMultiplePattern() {

        final EntityMatrix entityMatrix = EntityMatrix.builder()
                .entityCreator(EntityCreator.builder().creator("Kato Shinya").build())
                .entityDefinitions(List.of(this.getEntityDefinitionOfBaseCase(),
                        this.getEntityDefinitionWhenFieldHasDefaultValue(),
                        this.getEntityDefinitionWhenFieldHasEnvaliAnnotation(),
                        this.getEntityDefinitionWhenFieldHasEnvaliAnnotationWithOptions(),
                        this.getEntityDefinitionWhenFieldHasLiteralEnvaliAnnotationWithOptions(),
                        this.getEntityDefinitionWhenFieldHasNumericEnvaliAnnotationWithOptions()))
                .build();

        final EntityResourceGroup entityResourceGroup = EntityResourceFormatter.newInstance().format(entityMatrix);

        assertNotNull(entityResourceGroup);

        final List<String> expectedTemplates = List.of(EXPECTED_ENTITY_DEFAULT,
                EXPECTED_ENTITY_WHEN_FIELD_HAS_INITIAL_VALUE, EXPECTED_ENTITY_WHEN_FIELD_HAS_ENVALI_ANNOTATION,
                EXPECTED_ENTITY_WHEN_FIELD_HAS_ENVALI_ANNOTATION_WITH_OPTIONS,
                EXPECTED_ENTITY_WHEN_FIELD_HAS_LITERAL_ENVALI_ANNOTATION_WITH_OPTIONS,
                EXPECTED_ENTITY_WHEN_FIELD_HAS_NUMERIC_ENVALI_ANNOTATION_WITH_OPTIONS);

        for (int i = 0, size = entityResourceGroup.size(); i < size; i++) {
            final EntityResource entityResource = entityResourceGroup.get(i);
            assertEquals("org.thinkit.test.generator.entity", entityResource.getPackageName());
            assertEquals("TestEntity", entityResource.getResourceName());
            assertEquals("java", entityResource.getExtension());
            assertEquals(expectedTemplates.get(i), entityResource.getResource());
        }
    }

    @Test
    void testWhenFieldHasChildEntityDefinition() {

        final EntityMatrix entityMatrix = EntityMatrix.builder()
                .entityCreator(EntityCreator.builder().creator("Kato Shinya").build())
                .entityDefinitions(List.of(this.getEntityDefinitionWhenFieldHasChildEntityDefinition())).build();

        final EntityResourceGroup entityResourceGroup = EntityResourceFormatter.newInstance().format(entityMatrix);

        assertNotNull(entityResourceGroup);
        assertTrue(entityResourceGroup.size() == 2);

        final List<String> expectedTemplates = List.of(EXPECTED_CHILD_ENTITY_WHEN_FIELD_HAS_CHILD_DEFINITION,
                EXPECTED_PARENT_ENTITY_WHEN_FIELD_HAS_CHILD_DEFINITION);

        for (int i = 0, size = entityResourceGroup.size(); i < size; i++) {
            final EntityResource entityResource = entityResourceGroup.get(i);
            assertEquals("org.thinkit.test.generator.entity", entityResource.getPackageName());
            assertEquals("TestEntity", entityResource.getResourceName());
            assertEquals("java", entityResource.getExtension());
            assertEquals(expectedTemplates.get(i), entityResource.getResource());
        }
    }

    private EntityDefinition getEntityDefinitionOfBaseCase() {

        final EntityMeta entityMeta = EntityMeta.builder().version("1.0.0")
                .dependentPackages(List.of("java.util.ArrayList", "java.util.List")).build();

        final EntityField entityField = EntityField.builder().description("This is the test field.").dataType("String")
                .variableName("test").build();

        final EntityDefinition entityDefinition = EntityDefinition.builder().entityMeta(entityMeta)
                .packageName("org.thinkit.test.generator.entity").className("TestEntity")
                .entityFields(List.of(entityField)).build();

        return entityDefinition;
    }

    private EntityDefinition getEntityDefinitionWhenFieldHasDefaultValue() {

        final EntityMeta entityMeta = EntityMeta.builder().version("1.0.0").build();

        final EntityField entityField = EntityField.builder().description("This is the test field.").dataType("String")
                .variableName("test").initialValue("test").build();

        final EntityDefinition entityDefinition = EntityDefinition.builder().entityMeta(entityMeta)
                .packageName("org.thinkit.test.generator.entity").className("TestEntity")
                .entityFields(List.of(entityField)).build();

        return entityDefinition;
    }

    private EntityDefinition getEntityDefinitionWhenFieldHasEnvaliAnnotation() {

        final EntityMeta entityMeta = EntityMeta.builder().version("1.0.0").appliedEnvali(true).build();

        final EntityField entityField1 = EntityField.builder().description("This is the test field.").dataType("String")
                .variableName("test").initialValue("test")
                .entityEnvaliDefinitions(List.of(
                        EntityEnvaliDefinition.builder().envaliAnnotation(EnvaliAnnotation.REQUIRE_NON_NULL).build(),
                        EntityEnvaliDefinition.builder().envaliAnnotation(EnvaliAnnotation.REQUIRE_NON_EMPTY).build()))
                .build();

        final EntityField entityField2 = EntityField.builder().description("This is the test field2.").dataType("int")
                .variableName("test2").initialValue("0")
                .entityEnvaliDefinitions(List.of(
                        EntityEnvaliDefinition.builder().envaliAnnotation(EnvaliAnnotation.REQUIRE_POSITIVE).build()))
                .build();

        final EntityDefinition entityDefinition = EntityDefinition.builder().entityMeta(entityMeta)
                .packageName("org.thinkit.test.generator.entity").className("TestEntity")
                .entityFields(List.of(entityField1, entityField2)).build();

        return entityDefinition;
    }

    private EntityDefinition getEntityDefinitionWhenFieldHasEnvaliAnnotationWithOptions() {

        final EntityMeta entityMeta = EntityMeta.builder().version("1.0.0").appliedEnvali(true).build();

        final EntityField entityField1 = EntityField.builder().description("This is the test field.").dataType("String")
                .variableName("test").initialValue("test")
                .entityEnvaliDefinitions(List.of(
                        EntityEnvaliDefinition.builder().envaliAnnotation(EnvaliAnnotation.REQUIRE_NON_NULL)
                                .envaliErrorType(EnvaliErrorType.RECOVERABLE).message("testMessage").build(),
                        EntityEnvaliDefinition.builder().envaliAnnotation(EnvaliAnnotation.REQUIRE_NON_EMPTY).build()))
                .build();

        final EntityField entityField2 = EntityField.builder().description("This is the test field2.").dataType("int")
                .variableName("test2").initialValue("0")
                .entityEnvaliDefinitions(List.of(
                        EntityEnvaliDefinition.builder().envaliAnnotation(EnvaliAnnotation.REQUIRE_POSITIVE).build()))
                .build();

        final EntityField entityField3 = EntityField.builder().description("This is the test field3.")
                .dataType("String").variableName("test3").initialValue("testRegex")
                .entityEnvaliDefinitions(List.of(EntityEnvaliDefinition.builder()
                        .envaliAnnotation(EnvaliAnnotation.REQUIRE_MATCH)
                        .envaliMeta(EnvaliMeta.builder().envaliMetaType(EnvaliMetaType.REGEX)
                                .envaliRegexMeta(EnvaliRegexMeta.builder().expression("regex")
                                        .envaliRegexModifiers(
                                                Set.of(EnvaliRegexModifier.UNIX_LINES, EnvaliRegexModifier.DOTALL))
                                        .envaliRegexMethod(EnvaliRegexMethod.MATCHES).build())
                                .build())
                        .build()))
                .build();

        final EntityField entityField4 = EntityField.builder().description("This is the test field4.")
                .dataType("String").variableName("test4").initialValue("testRegex2")
                .entityEnvaliDefinitions(List.of(EntityEnvaliDefinition.builder()
                        .envaliAnnotation(EnvaliAnnotation.REQUIRE_MATCH).envaliErrorType(EnvaliErrorType.UNRECOVERABLE)
                        .message("message")
                        .envaliMeta(EnvaliMeta.builder().envaliMetaType(EnvaliMetaType.REGEX)
                                .envaliRegexMeta(EnvaliRegexMeta.builder().envaliRegexPreset(EnvaliRegexPreset.WEB_URL)
                                        .envaliRegexModifiers(Set.of(EnvaliRegexModifier.DOTALL))
                                        .envaliRegexMethod(EnvaliRegexMethod.FIND).build())
                                .build())
                        .build()))
                .build();

        final EntityDefinition entityDefinition = EntityDefinition.builder().entityMeta(entityMeta)
                .packageName("org.thinkit.test.generator.entity").className("TestEntity")
                .entityFields(List.of(entityField1, entityField2, entityField3, entityField4)).build();

        return entityDefinition;
    }

    private EntityDefinition getEntityDefinitionWhenFieldHasLiteralEnvaliAnnotationWithOptions() {

        final EntityMeta entityMeta = EntityMeta.builder().version("1.0.0").appliedEnvali(true).build();

        final EntityField entityField1 = EntityField.builder().description("This is the test field.").dataType("String")
                .variableName("test").initialValue("test")
                .entityEnvaliDefinitions(List.of(
                        EntityEnvaliDefinition.builder().envaliAnnotation(EnvaliAnnotation.REQUIRE_NON_NULL)
                                .envaliErrorType(EnvaliErrorType.RECOVERABLE).message("testMessage").build(),
                        EntityEnvaliDefinition.builder().envaliAnnotation(EnvaliAnnotation.REQUIRE_NON_EMPTY).build()))
                .build();

        final EntityField entityField2 = EntityField.builder().description("This is the test field2.").dataType("int")
                .variableName("test2").initialValue("0")
                .entityEnvaliDefinitions(List.of(
                        EntityEnvaliDefinition.builder().envaliAnnotation(EnvaliAnnotation.REQUIRE_POSITIVE).build()))
                .build();

        final EntityField entityField3 = EntityField.builder().description("This is the test field3.")
                .dataType("String").variableName("test3")
                .entityEnvaliDefinitions(List.of(EntityEnvaliDefinition.builder()
                        .envaliAnnotation(EnvaliAnnotation.REQUIRE_START_WITH)
                        .envaliErrorType(EnvaliErrorType.RECOVERABLE).message("message for start with annotation")
                        .envaliMeta(EnvaliMeta.builder().envaliMetaType(EnvaliMetaType.LITERAL)
                                .envaliLiteralMeta(EnvaliLiteralMeta.builder().prefix("start with something").build())
                                .build())
                        .build()))
                .build();

        final EntityField entityField4 = EntityField.builder().description("This is the test field4.")
                .dataType("String").variableName("test4")
                .entityEnvaliDefinitions(List.of(EntityEnvaliDefinition.builder()
                        .envaliAnnotation(EnvaliAnnotation.REQUIRE_END_WITH)
                        .envaliMeta(EnvaliMeta.builder().envaliMetaType(EnvaliMetaType.LITERAL)
                                .envaliLiteralMeta(EnvaliLiteralMeta.builder().suffix("end with something").build())
                                .build())
                        .build()))
                .build();

        final EntityField entityField5 = EntityField.builder().description("This is the test field5.")
                .dataType("String").variableName("test5")
                .entityEnvaliDefinitions(List.of(EntityEnvaliDefinition.builder()
                        .envaliAnnotation(EnvaliAnnotation.REQUIRE_START_WITH)
                        .envaliMeta(EnvaliMeta.builder().envaliMetaType(EnvaliMetaType.LITERAL)
                                .envaliLiteralMeta(EnvaliLiteralMeta.builder().prefix("start with something").build())
                                .build())
                        .build(),
                        EntityEnvaliDefinition.builder().envaliAnnotation(EnvaliAnnotation.REQUIRE_END_WITH)
                                .envaliMeta(EnvaliMeta.builder().envaliMetaType(EnvaliMetaType.LITERAL)
                                        .envaliLiteralMeta(
                                                EnvaliLiteralMeta.builder().suffix("end with something").build())
                                        .build())
                                .build()))
                .build();

        final EntityDefinition entityDefinition = EntityDefinition.builder().entityMeta(entityMeta)
                .packageName("org.thinkit.test.generator.entity").className("TestEntity")
                .entityFields(List.of(entityField1, entityField2, entityField3, entityField4, entityField5)).build();

        return entityDefinition;
    }

    private EntityDefinition getEntityDefinitionWhenFieldHasNumericEnvaliAnnotationWithOptions() {

        final EntityMeta entityMeta = EntityMeta.builder().version("1.0.0").appliedEnvali(true).build();

        final EntityField entityField1 = EntityField.builder().description("This is the test field.").dataType("String")
                .variableName("test").initialValue("test")
                .entityEnvaliDefinitions(List.of(
                        EntityEnvaliDefinition.builder().envaliAnnotation(EnvaliAnnotation.REQUIRE_NON_NULL)
                                .envaliErrorType(EnvaliErrorType.RECOVERABLE).message("testMessage").build(),
                        EntityEnvaliDefinition.builder().envaliAnnotation(EnvaliAnnotation.REQUIRE_NON_EMPTY).build()))
                .build();

        final EntityField entityField2 = EntityField.builder().description("This is the test field2.").dataType("int")
                .variableName("test2").initialValue("0")
                .entityEnvaliDefinitions(List.of(
                        EntityEnvaliDefinition.builder().envaliAnnotation(EnvaliAnnotation.REQUIRE_POSITIVE).build()))
                .build();

        final EntityField entityField3 = EntityField.builder().description("This is the test field3.").dataType("int")
                .variableName("test3")
                .entityEnvaliDefinitions(
                        List.of(EntityEnvaliDefinition.builder().envaliAnnotation(EnvaliAnnotation.REQUIRE_RANGE_FROM)
                                .envaliErrorType(EnvaliErrorType.RECOVERABLE).message("message for int")
                                .envaliMeta(EnvaliMeta.builder().envaliMetaType(EnvaliMetaType.NUMERIC)
                                        .envaliNumericMeta(EnvaliNumericMeta.builder()
                                                .envaliNumericDataType(EnvaliNumericDataType.INT)
                                                .envaliNumericRangeType(EnvaliNumericRangeType.FROM).from("10").build())
                                        .build())
                                .build()))
                .build();

        final EntityField entityField4 = EntityField.builder().description("This is the test field4.")
                .dataType("double").variableName("test4")
                .entityEnvaliDefinitions(
                        List.of(EntityEnvaliDefinition.builder()
                                .envaliAnnotation(EnvaliAnnotation.REQUIRE_RANGE_FROM_TO).message("message for double")
                                .envaliMeta(EnvaliMeta.builder().envaliMetaType(EnvaliMetaType.NUMERIC)
                                        .envaliNumericMeta(EnvaliNumericMeta.builder()
                                                .envaliNumericDataType(EnvaliNumericDataType.DOUBLE)
                                                .envaliNumericRangeType(EnvaliNumericRangeType.FROM_TO).from("10.0")
                                                .to("50.1").build())
                                        .build())
                                .build()))
                .build();

        final EntityField entityField5 = EntityField.builder().description("This is the test field5.").dataType("byte")
                .variableName("test5")
                .entityEnvaliDefinitions(
                        List.of(EntityEnvaliDefinition.builder().envaliAnnotation(EnvaliAnnotation.REQUIRE_RANGE_TO)
                                .message("message for byte")
                                .envaliMeta(EnvaliMeta.builder().envaliMetaType(EnvaliMetaType.NUMERIC)
                                        .envaliNumericMeta(EnvaliNumericMeta.builder()
                                                .envaliNumericDataType(EnvaliNumericDataType.BYTE)
                                                .envaliNumericRangeType(EnvaliNumericRangeType.TO).to("1").build())
                                        .build())
                                .build()))
                .build();

        final EntityDefinition entityDefinition = EntityDefinition.builder().entityMeta(entityMeta)
                .packageName("org.thinkit.test.generator.entity").className("TestEntity")
                .entityFields(List.of(entityField1, entityField2, entityField3, entityField4, entityField5)).build();

        return entityDefinition;
    }

    private EntityDefinition getEntityDefinitionWhenFieldHasChildEntityDefinition() {

        final EntityMeta entityMeta = EntityMeta.builder().version("1.0.0").appliedEnvali(true).build();

        final EntityField entityField2 = EntityField.builder().description("This is the test field2.").dataType("int")
                .variableName("test2").initialValue("0")
                .entityEnvaliDefinitions(List.of(
                        EntityEnvaliDefinition.builder().envaliAnnotation(EnvaliAnnotation.REQUIRE_POSITIVE).build()))
                .build();

        final EntityField entityField1 = EntityField.builder().description("This is the test field.").dataType("String")
                .variableName("test").initialValue("test")
                .entityEnvaliDefinitions(List.of(
                        EntityEnvaliDefinition.builder().envaliAnnotation(EnvaliAnnotation.REQUIRE_NON_NULL)
                                .envaliErrorType(EnvaliErrorType.RECOVERABLE).message("testMessage").build(),
                        EntityEnvaliDefinition.builder().envaliAnnotation(EnvaliAnnotation.REQUIRE_NON_EMPTY).build()))
                .childEntityDefinitions(List.of(EntityDefinition.builder().entityMeta(entityMeta)
                        .packageName("org.thinkit.test.generator.entity").className("TestEntity")
                        .entityFields(List.of(entityField2)).build()))
                .build();

        final EntityDefinition entityDefinition = EntityDefinition.builder().entityMeta(entityMeta)
                .packageName("org.thinkit.test.generator.entity").className("TestEntity")
                .entityFields(List.of(entityField1)).build();

        return entityDefinition;
    }

    /**
     * エンティティの期待値
     */
    private static final String EXPECTED_ENTITY_DEFAULT = """
            /*
             * Copyright %s Kato Shinya.
             *
             * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
             * in compliance with the License. You may obtain a copy of the License at
             *
             *     http://www.apache.org/licenses/LICENSE-2.0
             *
             * Unless required by applicable law or agreed to in writing, software distributed under the License
             * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
             * or implied. See the License for the specific language governing permissions and limitations under
             * the License.
             */

            package org.thinkit.test.generator.entity;

            import java.util.ArrayList;
            import java.util.List;
            import java.io.Serializable;
            import lombok.AccessLevel;
            import lombok.AllArgsConstructor;
            import lombok.Builder;
            import lombok.EqualsAndHashCode;
            import lombok.Getter;
            import lombok.NoArgsConstructor;
            import lombok.ToString;

            /**
             * This entity class was created by Entity Generator.
             *
             * @author Kato Shinya
             * @since 1.0.0
             */
            @ToString
            @EqualsAndHashCode
            @Builder(toBuilder = true)
            @NoArgsConstructor(access = AccessLevel.PRIVATE)
            @AllArgsConstructor(access = AccessLevel.PRIVATE)
            public final class TestEntity implements Serializable {

                /** The serial version UID */
                private static final long serialVersionUID = 1L;

                /** This is the test field. */
                @Getter private String test;
            }
            """.formatted(String.valueOf(LocalDate.now().getYear()));

    /**
     * フィールドが初期値を持つ場合のエンティティの期待値
     */
    private static final String EXPECTED_ENTITY_WHEN_FIELD_HAS_INITIAL_VALUE = """
            /*
             * Copyright %s Kato Shinya.
             *
             * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
             * in compliance with the License. You may obtain a copy of the License at
             *
             *     http://www.apache.org/licenses/LICENSE-2.0
             *
             * Unless required by applicable law or agreed to in writing, software distributed under the License
             * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
             * or implied. See the License for the specific language governing permissions and limitations under
             * the License.
             */

            package org.thinkit.test.generator.entity;

            import java.io.Serializable;
            import lombok.AccessLevel;
            import lombok.AllArgsConstructor;
            import lombok.Builder;
            import lombok.EqualsAndHashCode;
            import lombok.Getter;
            import lombok.NoArgsConstructor;
            import lombok.ToString;

            /**
             * This entity class was created by Entity Generator.
             *
             * @author Kato Shinya
             * @since 1.0.0
             */
            @ToString
            @EqualsAndHashCode
            @Builder(toBuilder = true)
            @NoArgsConstructor(access = AccessLevel.PRIVATE)
            @AllArgsConstructor(access = AccessLevel.PRIVATE)
            public final class TestEntity implements Serializable {

                /** The serial version UID */
                private static final long serialVersionUID = 1L;

                /** This is the test field. */
                @Getter @Builder.Default private String test = "test";
            }
            """.formatted(String.valueOf(LocalDate.now().getYear()));

    /**
     * フィールドがEnvaliアノテーションを持つ場合のエンティティの期待値
     */
    private static final String EXPECTED_ENTITY_WHEN_FIELD_HAS_ENVALI_ANNOTATION = """
            /*
             * Copyright %s Kato Shinya.
             *
             * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
             * in compliance with the License. You may obtain a copy of the License at
             *
             *     http://www.apache.org/licenses/LICENSE-2.0
             *
             * Unless required by applicable law or agreed to in writing, software distributed under the License
             * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
             * or implied. See the License for the specific language governing permissions and limitations under
             * the License.
             */

            package org.thinkit.test.generator.entity;

            import org.thinkit.framework.envali.annotation.RequireNonNull;
            import org.thinkit.framework.envali.annotation.RequireNonEmpty;
            import org.thinkit.framework.envali.annotation.RequirePositive;
            import org.thinkit.framework.envali.entity.ValidatableEntity;
            import java.io.Serializable;
            import lombok.AccessLevel;
            import lombok.AllArgsConstructor;
            import lombok.Builder;
            import lombok.EqualsAndHashCode;
            import lombok.Getter;
            import lombok.NoArgsConstructor;
            import lombok.ToString;

            /**
             * This entity class was created by Entity Generator.
             *
             * @author Kato Shinya
             * @since 1.0.0
             */
            @ToString
            @EqualsAndHashCode
            @Builder(toBuilder = true)
            @NoArgsConstructor(access = AccessLevel.PRIVATE)
            @AllArgsConstructor(access = AccessLevel.PRIVATE)
            public final class TestEntity implements ValidatableEntity, Serializable {

                /** The serial version UID */
                private static final long serialVersionUID = 1L;

                /** This is the test field. */
                @Getter @Builder.Default @RequireNonNull @RequireNonEmpty private String test = "test";

                /** This is the test field2. */
                @Getter @Builder.Default @RequirePositive private int test2 = 0;
            }
            """.formatted(String.valueOf(LocalDate.now().getYear()));

    /**
     * フィールドがオプション付きEnvaliアノテーションを持つ場合のエンティティの期待値
     */
    private static final String EXPECTED_ENTITY_WHEN_FIELD_HAS_ENVALI_ANNOTATION_WITH_OPTIONS = """
            /*
             * Copyright %s Kato Shinya.
             *
             * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
             * in compliance with the License. You may obtain a copy of the License at
             *
             *     http://www.apache.org/licenses/LICENSE-2.0
             *
             * Unless required by applicable law or agreed to in writing, software distributed under the License
             * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
             * or implied. See the License for the specific language governing permissions and limitations under
             * the License.
             */

            package org.thinkit.test.generator.entity;

            import org.thinkit.framework.envali.annotation.RequireNonNull;
            import org.thinkit.framework.envali.annotation.RequireNonEmpty;
            import org.thinkit.framework.envali.annotation.RequirePositive;
            import org.thinkit.framework.envali.annotation.RequireMatch;
            import org.thinkit.framework.envali.entity.ValidatableEntity;
            import org.thinkit.framework.envali.catalog.ErrorType;
            import org.thinkit.framework.envali.catalog.RegexPreset;
            import org.thinkit.framework.envali.catalog.RegexModifier;
            import org.thinkit.framework.envali.catalog.RegexMethod;
            import java.io.Serializable;
            import lombok.AccessLevel;
            import lombok.AllArgsConstructor;
            import lombok.Builder;
            import lombok.EqualsAndHashCode;
            import lombok.Getter;
            import lombok.NoArgsConstructor;
            import lombok.ToString;

            /**
             * This entity class was created by Entity Generator.
             *
             * @author Kato Shinya
             * @since 1.0.0
             */
            @ToString
            @EqualsAndHashCode
            @Builder(toBuilder = true)
            @NoArgsConstructor(access = AccessLevel.PRIVATE)
            @AllArgsConstructor(access = AccessLevel.PRIVATE)
            public final class TestEntity implements ValidatableEntity, Serializable {

                /** The serial version UID */
                private static final long serialVersionUID = 1L;

                /** This is the test field. */
                @Getter
                @Builder.Default
                @RequireNonNull(errorType = ErrorType.RECOVERABLE, message = "testMessage")
                @RequireNonEmpty
                private String test = "test";

                /** This is the test field2. */
                @Getter @Builder.Default @RequirePositive private int test2 = 0;

                /** This is the test field3. */
                @Getter
                @Builder.Default
                @RequireMatch(
                        expression = "regex",
                        modifiers = {RegexModifier.UNIX_LINES, RegexModifier.DOTALL},
                        method = RegexMethod.MATCHES)
                private String test3 = "testRegex";

                /** This is the test field4. */
                @Getter
                @Builder.Default
                @RequireMatch(
                        errorType = ErrorType.UNRECOVERABLE,
                        message = "message",
                        presetExpression = RegexPreset.WEB_URL,
                        modifiers = {RegexModifier.DOTALL})
                private String test4 = "testRegex2";
            }
            """.formatted(String.valueOf(LocalDate.now().getYear()));

    /**
     * フィールドが数値を扱うオプション付きEnvaliアノテーションを持つ場合のエンティティの期待値
     */
    private static final String EXPECTED_ENTITY_WHEN_FIELD_HAS_NUMERIC_ENVALI_ANNOTATION_WITH_OPTIONS = """
            /*
             * Copyright %s Kato Shinya.
             *
             * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
             * in compliance with the License. You may obtain a copy of the License at
             *
             *     http://www.apache.org/licenses/LICENSE-2.0
             *
             * Unless required by applicable law or agreed to in writing, software distributed under the License
             * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
             * or implied. See the License for the specific language governing permissions and limitations under
             * the License.
             */

            package org.thinkit.test.generator.entity;

            import org.thinkit.framework.envali.annotation.RequireNonNull;
            import org.thinkit.framework.envali.annotation.RequireNonEmpty;
            import org.thinkit.framework.envali.annotation.RequirePositive;
            import org.thinkit.framework.envali.annotation.RequireRangeFrom;
            import org.thinkit.framework.envali.annotation.RequireRangeFromTo;
            import org.thinkit.framework.envali.annotation.RequireRangeTo;
            import org.thinkit.framework.envali.entity.ValidatableEntity;
            import org.thinkit.framework.envali.catalog.ErrorType;
            import java.io.Serializable;
            import lombok.AccessLevel;
            import lombok.AllArgsConstructor;
            import lombok.Builder;
            import lombok.EqualsAndHashCode;
            import lombok.Getter;
            import lombok.NoArgsConstructor;
            import lombok.ToString;

            /**
             * This entity class was created by Entity Generator.
             *
             * @author Kato Shinya
             * @since 1.0.0
             */
            @ToString
            @EqualsAndHashCode
            @Builder(toBuilder = true)
            @NoArgsConstructor(access = AccessLevel.PRIVATE)
            @AllArgsConstructor(access = AccessLevel.PRIVATE)
            public final class TestEntity implements ValidatableEntity, Serializable {

                /** The serial version UID */
                private static final long serialVersionUID = 1L;

                /** This is the test field. */
                @Getter
                @Builder.Default
                @RequireNonNull(errorType = ErrorType.RECOVERABLE, message = "testMessage")
                @RequireNonEmpty
                private String test = "test";

                /** This is the test field2. */
                @Getter @Builder.Default @RequirePositive private int test2 = 0;

                /** This is the test field3. */
                @Getter
                @RequireRangeFrom(errorType = ErrorType.RECOVERABLE, message = "message for int", intFrom = 10)
                private int test3;

                /** This is the test field4. */
                @Getter
                @RequireRangeFromTo(message = "message for double", doubleFrom = 10.0, doubleTo = 50.1)
                private double test4;

                /** This is the test field5. */
                @Getter
                @RequireRangeTo(message = "message for byte", byteTo = 1)
                private byte test5;
            }
            """.formatted(String.valueOf(LocalDate.now().getYear()));

    /**
     * フィールドが文字列を扱うオプション付きEnvaliアノテーションを持つ場合のエンティティの期待値
     */
    private static final String EXPECTED_ENTITY_WHEN_FIELD_HAS_LITERAL_ENVALI_ANNOTATION_WITH_OPTIONS = """
            /*
             * Copyright %s Kato Shinya.
             *
             * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
             * in compliance with the License. You may obtain a copy of the License at
             *
             *     http://www.apache.org/licenses/LICENSE-2.0
             *
             * Unless required by applicable law or agreed to in writing, software distributed under the License
             * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
             * or implied. See the License for the specific language governing permissions and limitations under
             * the License.
             */

            package org.thinkit.test.generator.entity;

            import org.thinkit.framework.envali.annotation.RequireNonNull;
            import org.thinkit.framework.envali.annotation.RequireNonEmpty;
            import org.thinkit.framework.envali.annotation.RequirePositive;
            import org.thinkit.framework.envali.annotation.RequireStartWith;
            import org.thinkit.framework.envali.annotation.RequireEndWith;
            import org.thinkit.framework.envali.entity.ValidatableEntity;
            import org.thinkit.framework.envali.catalog.ErrorType;
            import java.io.Serializable;
            import lombok.AccessLevel;
            import lombok.AllArgsConstructor;
            import lombok.Builder;
            import lombok.EqualsAndHashCode;
            import lombok.Getter;
            import lombok.NoArgsConstructor;
            import lombok.ToString;

            /**
             * This entity class was created by Entity Generator.
             *
             * @author Kato Shinya
             * @since 1.0.0
             */
            @ToString
            @EqualsAndHashCode
            @Builder(toBuilder = true)
            @NoArgsConstructor(access = AccessLevel.PRIVATE)
            @AllArgsConstructor(access = AccessLevel.PRIVATE)
            public final class TestEntity implements ValidatableEntity, Serializable {

                /** The serial version UID */
                private static final long serialVersionUID = 1L;

                /** This is the test field. */
                @Getter
                @Builder.Default
                @RequireNonNull(errorType = ErrorType.RECOVERABLE, message = "testMessage")
                @RequireNonEmpty
                private String test = "test";

                /** This is the test field2. */
                @Getter @Builder.Default @RequirePositive private int test2 = 0;

                /** This is the test field3. */
                @Getter
                @RequireStartWith(
                        errorType = ErrorType.RECOVERABLE,
                        message = "message for start with annotation",
                        prefix = "start with something")
                private String test3;

                /** This is the test field4. */
                @Getter
                @RequireEndWith(suffix = "end with something")
                private String test4;

                /** This is the test field5. */
                @Getter
                @RequireStartWith(prefix = "start with something")
                @RequireEndWith(suffix = "end with something")
                private String test5;
            }
            """.formatted(String.valueOf(LocalDate.now().getYear()));

    /**
     * フィールドが子定義を持つ場合の親エンティティの期待値
     */
    private static final String EXPECTED_PARENT_ENTITY_WHEN_FIELD_HAS_CHILD_DEFINITION = """
            /*
             * Copyright 2021 Kato Shinya.
             *
             * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
             * in compliance with the License. You may obtain a copy of the License at
             *
             *     http://www.apache.org/licenses/LICENSE-2.0
             *
             * Unless required by applicable law or agreed to in writing, software distributed under the License
             * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
             * or implied. See the License for the specific language governing permissions and limitations under
             * the License.
             */

            package org.thinkit.test.generator.entity;

            import org.thinkit.framework.envali.annotation.RequireNonNull;
            import org.thinkit.framework.envali.annotation.RequireNonEmpty;
            import org.thinkit.framework.envali.entity.ValidatableEntity;
            import org.thinkit.framework.envali.catalog.ErrorType;
            import java.io.Serializable;
            import lombok.AccessLevel;
            import lombok.AllArgsConstructor;
            import lombok.Builder;
            import lombok.EqualsAndHashCode;
            import lombok.Getter;
            import lombok.NoArgsConstructor;
            import lombok.ToString;

            /**
             * This entity class was created by Entity Generator.
             *
             * @author Kato Shinya
             * @since 1.0.0
             */
            @ToString
            @EqualsAndHashCode
            @Builder(toBuilder = true)
            @NoArgsConstructor(access = AccessLevel.PRIVATE)
            @AllArgsConstructor(access = AccessLevel.PRIVATE)
            public final class TestEntity implements ValidatableEntity, Serializable {

                /** The serial version UID */
                private static final long serialVersionUID = 1L;

                /** This is the test field. */
                @Getter
                @Builder.Default
                @RequireNonNull(errorType = ErrorType.RECOVERABLE, message = "testMessage")
                @RequireNonEmpty
                private String test = "test";
            }
            """.formatted(String.valueOf(LocalDate.now().getYear()));

    /**
     * フィールドが子定義を持つ場合の子エンティティの期待値
     */
    private static final String EXPECTED_CHILD_ENTITY_WHEN_FIELD_HAS_CHILD_DEFINITION = """
            /*
             * Copyright %s Kato Shinya.
             *
             * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
             * in compliance with the License. You may obtain a copy of the License at
             *
             *     http://www.apache.org/licenses/LICENSE-2.0
             *
             * Unless required by applicable law or agreed to in writing, software distributed under the License
             * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
             * or implied. See the License for the specific language governing permissions and limitations under
             * the License.
             */

            package org.thinkit.test.generator.entity;

            import org.thinkit.framework.envali.annotation.RequirePositive;
            import org.thinkit.framework.envali.entity.ValidatableEntity;
            import java.io.Serializable;
            import lombok.AccessLevel;
            import lombok.AllArgsConstructor;
            import lombok.Builder;
            import lombok.EqualsAndHashCode;
            import lombok.Getter;
            import lombok.NoArgsConstructor;
            import lombok.ToString;

            /**
             * This entity class was created by Entity Generator.
             *
             * @author Kato Shinya
             * @since 1.0.0
             */
            @ToString
            @EqualsAndHashCode
            @Builder(toBuilder = true)
            @NoArgsConstructor(access = AccessLevel.PRIVATE)
            @AllArgsConstructor(access = AccessLevel.PRIVATE)
            public final class TestEntity implements ValidatableEntity, Serializable {

                /** The serial version UID */
                private static final long serialVersionUID = 1L;

                /** This is the test field2. */
                @Getter @Builder.Default @RequirePositive private int test2 = 0;
            }
            """.formatted(String.valueOf(LocalDate.now().getYear()));
}

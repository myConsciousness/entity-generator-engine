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

import java.util.List;

import org.junit.jupiter.api.Test;
import org.thinkit.generator.entity.engine.catalog.EnvaliAnnotation;
import org.thinkit.generator.entity.engine.dto.EntityCreator;
import org.thinkit.generator.entity.engine.dto.EntityDefinition;
import org.thinkit.generator.entity.engine.dto.EntityEnvaliDefinition;
import org.thinkit.generator.entity.engine.dto.EntityField;
import org.thinkit.generator.entity.engine.dto.EntityMatrix;
import org.thinkit.generator.entity.engine.dto.EntityMeta;
import org.thinkit.generator.entity.engine.dto.EntityResourceGroup;

/**
 * {@link EntityResourceFormatter} をのテストケースを管理するクラスです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
public final class EntityResourceFormatterTest {

    @Test
    void testBaseCase() {

        final EntityMeta entityMeta = EntityMeta.builder().version("1.0.0")
                .dependentPackages(List.of("java.util.ArrayList", "java.util.List")).build();

        final EntityField entityField = EntityField.builder().description("This is the test field.").dataType("String")
                .variableName("test").build();

        final EntityDefinition entityDefinition = EntityDefinition.builder().entityMeta(entityMeta)
                .packageName("org.thinkit.test.generator.entity").className("TestEntity")
                .entityFields(List.of(entityField)).build();

        final EntityMatrix entityMatrix = EntityMatrix.builder()
                .entityCreator(EntityCreator.builder().creator("Kato Shinya").build())
                .entityDefinitions(List.of(entityDefinition)).build();

        final EntityResourceGroup entityResourceGroup = EntityResourceFormatter.newInstance().format(entityMatrix);

        assertNotNull(entityResourceGroup);
        assertEquals(EXPECTED_ENTITY_DEFAULT, entityResourceGroup.get(0).getResource());
    }

    @Test
    void testWhenFieldHasDefaultValue() {

        final EntityMeta entityMeta = EntityMeta.builder().version("1.0.0").build();

        final EntityField entityField = EntityField.builder().description("This is the test field.").dataType("String")
                .variableName("test").initialValue("test").build();

        final EntityDefinition entityDefinition = EntityDefinition.builder().entityMeta(entityMeta)
                .packageName("org.thinkit.test.generator.entity").className("TestEntity")
                .entityFields(List.of(entityField)).build();

        final EntityMatrix entityMatrix = EntityMatrix.builder()
                .entityCreator(EntityCreator.builder().creator("Kato Shinya").build())
                .entityDefinitions(List.of(entityDefinition)).build();

        final EntityResourceGroup entityResourceGroup = EntityResourceFormatter.newInstance().format(entityMatrix);

        assertNotNull(entityResourceGroup);
        assertEquals(EXPECTED_ENTITY_WHEN_FIELD_HAS_INITIAL_VALUE, entityResourceGroup.get(0).getResource());
    }

    @Test
    void testWhenFieldHasEnvaliAnnotation() {

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

        final EntityMatrix entityMatrix = EntityMatrix.builder()
                .entityCreator(EntityCreator.builder().creator("Kato Shinya").build())
                .entityDefinitions(List.of(entityDefinition)).build();

        final EntityResourceGroup entityResourceGroup = EntityResourceFormatter.newInstance().format(entityMatrix);

        assertNotNull(entityResourceGroup);
        assertEquals(EXPECTED_ENTITY_WHEN_FIELD_HAS_ENVALI_ANNOTATION, entityResourceGroup.get(0).getResource());
    }

    /**
     * エンティティの期待値
     */
    private static final String EXPECTED_ENTITY_DEFAULT = """
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
            """;

    /**
     * フィールドが初期値を保つ場合のエンティティの期待値
     */
    private static final String EXPECTED_ENTITY_WHEN_FIELD_HAS_INITIAL_VALUE = """
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
            """;

    /**
     * フィールドがEnvaliアノテーションを保つ場合のエンティティの期待値
     */
    private static final String EXPECTED_ENTITY_WHEN_FIELD_HAS_ENVALI_ANNOTATION = """
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
            public final class TestEntity implements Serializable, ValidatableEntity {

                /** The serial version UID */
                private static final long serialVersionUID = 1L;

                /** This is the test field. */
                @Getter @Builder.Default @RequireNonNull @RequireNonEmpty private String test = "test";

                /** This is the test field2. */
                @Getter @Builder.Default @RequirePositive private int test2 = 0;
            }
            """;

}

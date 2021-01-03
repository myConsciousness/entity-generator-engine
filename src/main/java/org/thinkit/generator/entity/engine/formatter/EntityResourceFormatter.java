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

import java.util.List;

import org.thinkit.generator.common.duke.catalog.AnnotationPattern;
import org.thinkit.generator.common.duke.factory.Annotation;
import org.thinkit.generator.common.duke.factory.ClassBody;
import org.thinkit.generator.common.duke.factory.ClassDescription;
import org.thinkit.generator.common.duke.factory.Copyright;
import org.thinkit.generator.common.duke.factory.Description;
import org.thinkit.generator.common.duke.factory.Field;
import org.thinkit.generator.common.duke.factory.FieldDefinition;
import org.thinkit.generator.common.duke.factory.Package;
import org.thinkit.generator.common.duke.factory.Resource;
import org.thinkit.generator.common.duke.factory.ResourceFactory;
import org.thinkit.generator.common.duke.formatter.JavaResourceFormatter;
import org.thinkit.generator.entity.engine.dto.EntityCreator;
import org.thinkit.generator.entity.engine.dto.EntityDefinition;
import org.thinkit.generator.entity.engine.dto.EntityField;
import org.thinkit.generator.entity.engine.dto.EntityMatrix;
import org.thinkit.generator.entity.engine.dto.EntityResource;
import org.thinkit.generator.entity.engine.dto.EntityResourceGroup;
import org.thinkit.generator.entity.engine.factory.EntityResourceFactory;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

/**
 * {@link EntityMatrix} クラスに格納されたリソース情報を基にエンティティリソースを生成する処理を定義したフォーマッタークラスです。
 * <p>
 * {@link #newInstance()} メソッドを使用することで {@link EntityResourceFormatter}
 * クラスの新しいインスタンスを生成することができます。 {@link EntityResourceFormatter}
 * クラスの新しいインスタンスを生成した後は {@link #format(EntityMatrix)}
 * メソッドを呼び出し整形処理を行ってください。整形処理が正常終了した場合は生成されたリソースが格納された
 * {@link EntityResourceGroup} が返却されます。
 *
 * <pre>
 * 操作例:
 * <code>
 * EntityResourceFormatter.newInstance().format(entityMatrix).foreach(entityResource -> {
 *      * // do something like
 *      entityResource.getPackageName();
 *      entityResource.getClassName();
 *      entityResource.getResource();
 * });
 * </code>
 * </pre>
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode
@NoArgsConstructor(staticName = "newInstance")
public final class EntityResourceFormatter implements JavaResourceFormatter<EntityMatrix, EntityResourceGroup> {

    @Override
    public EntityResourceGroup format(@NonNull EntityMatrix entityMatrix) {

        final EntityResourceGroup entityResourceGroup = EntityResourceGroup.newInstance();
        this.createResourceRecursively(entityMatrix.getEntityCreator(), entityMatrix.getEntityDefinitions(),
                entityResourceGroup);

        return entityResourceGroup;
    }

    private void createResourceRecursively(@NonNull final EntityCreator entityCreator,
            @NonNull final List<EntityDefinition> entityDefinitions,
            @NonNull final EntityResourceGroup entityResourceGroup) {

        entityDefinitions.forEach(entityDefinition -> {
            final String className = entityDefinition.getClassName();
            final Resource resource = EntityResourceFactory.getInstance().createResource(
                    this.createCopyright(entityCreator), this.createPackage(entityDefinition),
                    this.createClassBody(className, entityCreator, entityDefinition, entityResourceGroup));

            entityResourceGroup.add(EntityResource.builder().packageName(entityDefinition.getPackageName())
                    .resourceName(className).resource(resource.createResource()).build());
        });
    }

    private ClassBody createClassBody(@NonNull final String className, @NonNull final EntityCreator entityCreator,
            @NonNull final EntityDefinition entityDefinition, @NonNull final EntityResourceGroup entityResourceGroup) {

        final ResourceFactory factory = EntityResourceFactory.getInstance();
        final ClassDescription classDescription = factory.createClassDescription(entityCreator.getCreator(),
                entityDefinition.getEntityMeta().getVersion());

        final ClassBody classBody = factory.createClassBody(classDescription, className);
        this.addClassAnnotation(classBody);

        entityDefinition.getEntityFields().forEach(entityField -> {
            classBody.add(this.createField(entityField));

            final List<EntityDefinition> childEntityDefinition = entityField.getChildEntityDefinitions();

            if (!childEntityDefinition.isEmpty()) {
                this.createResourceRecursively(entityCreator, childEntityDefinition, entityResourceGroup);
            }
        });

        return classBody;
    }

    private void addClassAnnotation(@NonNull ClassBody classBody) {

        final ResourceFactory factory = EntityResourceFactory.getInstance();

        classBody.add(factory.createAnnotation(AnnotationPattern.LOMBOK_TO_STRING));
        classBody.add(factory.createAnnotation(AnnotationPattern.LOMBOK_EQUALS_AND_HASH_CODE));

        final Annotation builderAnnotation = factory.createAnnotation(AnnotationPattern.LOMBOK_BUILDER)
                .add(factory.createAnnotationParameter("toBuilder").add(true));
        classBody.add(builderAnnotation);
    }

    private Copyright createCopyright(@NonNull EntityCreator entityCreator) {
        return EntityResourceFactory.getInstance().createCopyright(entityCreator.getCreator());
    }

    private Package createPackage(@NonNull EntityDefinition entityDefinition) {
        return EntityResourceFactory.getInstance().createPackage(entityDefinition.getPackageName());
    }

    private Field createField(@NonNull EntityField entityField) {

        final ResourceFactory factory = EntityResourceFactory.getInstance();
        final Description description = factory.createDescription(entityField.getDescription());
        final FieldDefinition fieldDefinition = factory.createFieldDefinition(entityField.getDataType(),
                entityField.getVariableName(), entityField.getInitialValue());

        return factory.createField(fieldDefinition, description);
    }
}

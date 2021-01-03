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

import org.thinkit.generator.common.duke.factory.Resource;
import org.thinkit.generator.common.duke.factory.ResourceFactory;
import org.thinkit.generator.common.duke.formatter.JavaResourceFormatter;
import org.thinkit.generator.entity.engine.dto.EntityCreator;
import org.thinkit.generator.entity.engine.dto.EntityDefinition;
import org.thinkit.generator.entity.engine.dto.EntityField;
import org.thinkit.generator.entity.engine.dto.EntityMatrix;
import org.thinkit.generator.entity.engine.dto.EntityMeta;
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

        if (!this.formatEntityResourceRecursively(entityMatrix.getEntityCreator(), entityMatrix.getEntityDefinitions(),
                entityResourceGroup)) {
            return null;
        }

        return entityResourceGroup;
    }

    private boolean formatEntityResourceRecursively(@NonNull final EntityCreator entityCreator,
            @NonNull final List<EntityDefinition> entityDefinitions,
            @NonNull final EntityResourceGroup entityResourceGroup) {

        for (final EntityDefinition entityDefinition : entityDefinitions) {
            final String className = entityDefinition.getClassName();
            final Resource resource = this.formatResource(className, entityDefinition.getEntityFields(),
                    entityDefinition.getEntityMeta(), entityCreator, entityResourceGroup);

            if (resource == null) {
                return false;
            }

            entityResourceGroup.add(EntityResource.builder().packageName(entityDefinition.getPackageName())
                    .resourceName(className).resource(resource.createResource()).build());
        }

        return true;
    }

    private Resource formatResource(@NonNull final String className, @NonNull final List<EntityField> entityFields,
            @NonNull final EntityMeta entityMeta, @NonNull final EntityCreator entityCreator,
            @NonNull final EntityResourceGroup entityResourceGroup) {

        final ResourceFactory resourceFactory = EntityResourceFactory.getInstance();
        final Resource resource = null;

        for (final EntityField entityField : entityFields) {
            final String dataType = entityField.getDataType();
            final String variableName = entityField.getVariableName();

            resourceFactory.createFieldDefinition(dataType, variableName, entityField.getInitialValue());

            final List<EntityDefinition> child = entityField.getChildEntityDefinitions();

            if (!child.isEmpty()) {

                if (!this.formatEntityResourceRecursively(entityCreator, child, entityResourceGroup)) {
                    return null;
                }
            }
        }

        return resource;
    }
}

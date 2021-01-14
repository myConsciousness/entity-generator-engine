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

package org.thinkit.generator.entity.engine.content;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.thinkit.framework.content.Attribute;
import org.thinkit.framework.content.Condition;
import org.thinkit.framework.content.Content;
import org.thinkit.framework.content.annotation.ContentMapping;
import org.thinkit.generator.entity.engine.catalog.EntityDependentPackage;
import org.thinkit.generator.entity.engine.content.entity.EntityPackage;
import org.thinkit.generator.entity.engine.content.entity.EntityPackageGroup;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * コンテンツ「EntityPackage」をロードするクラスです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
@ContentMapping(content = "org/thinkit/generator/entity/engine/EntityPackage")
public class EntityPackageLoader implements Content<EntityPackageGroup> {

    /**
     * エンティティが依存するパッケージ定数の集合
     */
    private Set<EntityDependentPackage> entityDependentPackages;

    /**
     * コンテンツ要素定数
     */
    @RequiredArgsConstructor
    private enum ContentAttribute implements Attribute {

        /**
         * エンティティパッケージ
         */
        ENTITY_PACKAGE(Key.entityPackage);

        /**
         * 検索キー
         */
        private final Key key;

        @Override
        public String getString() {
            return this.key.name();
        }

        /**
         * キー要素
         */
        private enum Key {
            entityPackage;
        }
    }

    /**
     * コンテンツ条件定数
     */
    @RequiredArgsConstructor
    private enum ContentCondition implements Condition {

        /**
         * エンティティが依存するパッケージコード
         */
        PACKAGE_CODE(Key.packageCode);

        /**
         * 条件キー
         */
        private final Key key;

        @Override
        public String getString() {
            return this.key.name();
        }

        /**
         * キー要素
         */
        private enum Key {
            packageCode;
        }
    }

    @Override
    public EntityPackageGroup execute() {

        final EntityPackageGroup entityPackageGroup = EntityPackageGroup.of(0);

        this.loadContent(this).forEach(content -> {
            entityPackageGroup.add(EntityPackage.builder()
                    .packageName(content.get(ContentAttribute.ENTITY_PACKAGE.getString())).build());
        });

        return entityPackageGroup;
    }

    @Override
    public Set<Attribute> getAttributes() {
        return Set.of(ContentAttribute.ENTITY_PACKAGE);
    }

    @Override
    public List<Map<Condition, String>> getConditions() {

        final List<Map<Condition, String>> conditions = new ArrayList<>(0);

        this.entityDependentPackages.forEach(entityDependentPackage -> {
            conditions.add(Map.of(ContentCondition.PACKAGE_CODE, String.valueOf(entityDependentPackage.getCode())));
        });

        return conditions;
    }
}

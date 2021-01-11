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
import org.thinkit.generator.entity.engine.catalog.EntityInterface;
import org.thinkit.generator.entity.engine.content.entity.EntityInterfaceName;
import org.thinkit.generator.entity.engine.content.entity.EntityInterfaceNameGroup;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * コンテンツ「EntityInterfaceName」をロードするクラスです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
@ContentMapping(content = "org/thinkit/generator/entity/engine/EntityInterfaceName")
public final class EntityInterfaceNameLoader implements Content<EntityInterfaceNameGroup> {

    /**
     * エンティティのインターフェース集合
     */
    private Set<EntityInterface> entityInterfaces;

    /**
     * コンテンツ要素定数
     */
    @RequiredArgsConstructor
    private enum ContentAttribute implements Attribute {

        /**
         * エンティティのインターフェース名
         */
        INTERFACE_NAME(Key.interfaceName);

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
            interfaceName;
        }
    }

    /**
     * コンテンツ条件定数
     */
    @RequiredArgsConstructor
    private enum ContentCondition implements Condition {

        /**
         * エンティティのインターフェースコード
         */
        INTERFACE_CODE(Key.interfaceCode);

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
            interfaceCode;
        }
    }

    @Override
    public EntityInterfaceNameGroup execute() {

        final EntityInterfaceNameGroup entityInterfaceNameGroup = EntityInterfaceNameGroup.newInstance();

        this.loadContent(this).forEach(content -> {
            entityInterfaceNameGroup.add(EntityInterfaceName.builder()
                    .interfaceName(content.get(ContentAttribute.INTERFACE_NAME.getString())).build());
        });

        return entityInterfaceNameGroup;
    }

    @Override
    public Set<Attribute> getAttributes() {
        return Set.of(ContentAttribute.INTERFACE_NAME);
    }

    @Override
    public List<Map<Condition, String>> getConditions() {

        final List<Map<Condition, String>> conditions = new ArrayList<>(0);

        this.entityInterfaces.forEach(entityInterface -> {
            conditions.add(Map.of(ContentCondition.INTERFACE_CODE, String.valueOf(entityInterface.getCode())));
        });

        return conditions;
    }
}

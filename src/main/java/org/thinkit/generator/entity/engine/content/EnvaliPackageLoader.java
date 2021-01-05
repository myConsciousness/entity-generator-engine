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

import java.util.Map;
import java.util.Set;

import org.thinkit.framework.content.Attribute;
import org.thinkit.framework.content.Condition;
import org.thinkit.framework.content.Content;
import org.thinkit.framework.content.annotation.ContentMapping;
import org.thinkit.generator.entity.engine.content.entity.EnvaliPackage;
import org.thinkit.generator.entity.engine.content.entity.EnvaliPackageGroup;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * コンテンツ「EnvaliPackage」をロードするクラスです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode
@NoArgsConstructor(staticName = "newInstance")
@ContentMapping(content = "org/thinkit/generator/catalog/entity/EnvaliPackage")
public class EnvaliPackageLoader implements Content<EnvaliPackageGroup> {

    /**
     * コンテンツ要素定数
     */
    @RequiredArgsConstructor
    private enum ContentAttribute implements Attribute {

        /**
         * カタログパッケージ
         */
        ENVALI_PACKAGE(Key.envaliPackage);

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
            envaliPackage;
        }
    }

    @Override
    public EnvaliPackageGroup execute() {

        final EnvaliPackageGroup envaliPackageGroup = EnvaliPackageGroup.of(0);

        this.loadContent(this).forEach(content -> {
            envaliPackageGroup.add(EnvaliPackage.builder()
                    .packageName(content.get(ContentAttribute.ENVALI_PACKAGE.getString())).build());
        });

        return envaliPackageGroup;
    }

    @Override
    public Set<Attribute> getAttributes() {
        return Set.of(ContentAttribute.ENVALI_PACKAGE);
    }

    @Override
    public Map<Condition, String> getConditions() {
        return Map.of();
    }
}

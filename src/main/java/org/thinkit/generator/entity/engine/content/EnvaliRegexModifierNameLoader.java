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
import org.thinkit.generator.entity.engine.catalog.EnvaliRegexModifier;
import org.thinkit.generator.entity.engine.content.entity.EnvaliRegexModifierName;
import org.thinkit.generator.entity.engine.content.entity.EnvaliRegexModifierNameGroup;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * コンテンツ「EnvaliRegexModifierName」をロードするクラスです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
@ContentMapping(content = "org/thinkit/generator/entity/engine/EnvaliRegexModifierName")
public final class EnvaliRegexModifierNameLoader implements Content<EnvaliRegexModifierNameGroup> {

    /**
     * Envaliの正規表現解析フラグ集合
     */
    private Set<EnvaliRegexModifier> envaliRegexModifiers;

    /**
     * コンテンツ要素定数
     */
    @RequiredArgsConstructor
    private enum ContentAttribute implements Attribute {

        /**
         * 正規表現解析フラグ名
         */
        REGEX_MODIFIER_NAME(Key.regexModifierName);

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
            regexModifierName;
        }
    }

    /**
     * コンテンツ条件定数
     */
    @RequiredArgsConstructor
    private enum ContentCondition implements Condition {

        /**
         * 正規表現解析フラグコード
         */
        REGEX_MODIFIER_CODE(Key.regexModifierCode);

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
            regexModifierCode;
        }
    }

    @Override
    public EnvaliRegexModifierNameGroup execute() {

        final EnvaliRegexModifierNameGroup envaliRegexModifierNameGroup = EnvaliRegexModifierNameGroup.newInstance();

        this.loadContent(this).forEach(content -> {
            envaliRegexModifierNameGroup.add(EnvaliRegexModifierName.builder()
                    .regexModifierName(content.get(ContentAttribute.REGEX_MODIFIER_NAME.getString())).build());
        });

        return envaliRegexModifierNameGroup;
    }

    @Override
    public Set<Attribute> getAttributes() {
        return Set.of(ContentAttribute.REGEX_MODIFIER_NAME);
    }

    @Override
    public List<Map<Condition, String>> getConditions() {

        final List<Map<Condition, String>> conditions = new ArrayList<>(this.envaliRegexModifiers.size());

        this.envaliRegexModifiers.forEach(envaliRegexModifier -> {
            conditions.add(Map.of(ContentCondition.REGEX_MODIFIER_CODE, String.valueOf(envaliRegexModifier.getCode())));
        });

        return conditions;
    }
}

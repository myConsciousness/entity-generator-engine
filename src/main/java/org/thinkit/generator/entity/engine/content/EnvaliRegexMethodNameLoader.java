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

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.thinkit.framework.content.Attribute;
import org.thinkit.framework.content.Condition;
import org.thinkit.framework.content.Content;
import org.thinkit.framework.content.annotation.ContentMapping;
import org.thinkit.generator.entity.engine.catalog.EnvaliRegexMethod;
import org.thinkit.generator.entity.engine.content.entity.EnvaliRegexMethodName;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * コンテンツ「EnvaliRegexMethodName」をロードするクラスです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
@ContentMapping(content = "org/thinkit/generator/entity/engine/EnvaliRegexMethodName")
public final class EnvaliRegexMethodNameLoader implements Content<EnvaliRegexMethodName> {

    /**
     * Envaliの正規表現解析メソッド
     */
    private EnvaliRegexMethod envaliRegexMethod;

    /**
     * コンテンツ要素定数
     */
    @RequiredArgsConstructor
    private enum ContentAttribute implements Attribute {

        /**
         * 正規表現解析メソッド名
         */
        REGEX_METHOD_NAME(Key.regexMethodName);

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
            regexMethodName;
        }
    }

    /**
     * コンテンツ条件定数
     */
    @RequiredArgsConstructor
    private enum ContentCondition implements Condition {

        /**
         * 正規表現解析メソッドコード
         */
        REGEX_METHOD_CODE(Key.regexMethodCode);

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
            regexMethodCode;
        }
    }

    @Override
    public EnvaliRegexMethodName execute() {
        return EnvaliRegexMethodName.builder()
                .regexMethodName(this.loadContent(this).get(0).get(ContentAttribute.REGEX_METHOD_NAME.getString()))
                .build();
    }

    @Override
    public Set<Attribute> getAttributes() {
        return Set.of(ContentAttribute.REGEX_METHOD_NAME);
    }

    @Override
    public List<Map<Condition, String>> getConditions() {
        return List.of(Map.of(ContentCondition.REGEX_METHOD_CODE, String.valueOf(this.envaliRegexMethod.getCode())));
    }
}

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
import org.thinkit.generator.entity.engine.catalog.EnvaliErrorType;
import org.thinkit.generator.entity.engine.content.entity.EnvaliErrorTypeName;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * コンテンツ「EnvaliErrorTypeName」をロードするクラスです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
@ContentMapping(content = "org/thinkit/generator/entity/engine/EnvaliErrorTypeName")
public final class EnvaliErrorTypeNameLoader implements Content<EnvaliErrorTypeName> {

    /**
     * Envaliエラー種別
     */
    private EnvaliErrorType envaliErrorType;

    /**
     * コンテンツ要素定数
     */
    @RequiredArgsConstructor
    private enum ContentAttribute implements Attribute {

        /**
         * エラー種別名
         */
        ERROR_TYPE_NAME(Key.errorTypeName);

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
            errorTypeName;
        }
    }

    /**
     * コンテンツ条件定数
     */
    @RequiredArgsConstructor
    private enum ContentCondition implements Condition {

        /**
         * エラー種別コード
         */
        ERROR_TYPE_CODE(Key.errorTypeCode);

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
            errorTypeCode;
        }
    }

    @Override
    public EnvaliErrorTypeName execute() {
        return EnvaliErrorTypeName.builder()
                .errorTypeName(this.loadContent(this).get(0).get(ContentAttribute.ERROR_TYPE_NAME.getString())).build();
    }

    @Override
    public Set<Attribute> getAttributes() {
        return Set.of(ContentAttribute.ERROR_TYPE_NAME);
    }

    @Override
    public List<Map<Condition, String>> getConditions() {
        return List.of(Map.of(ContentCondition.ERROR_TYPE_CODE, String.valueOf(this.envaliErrorType.getCode())));
    }
}

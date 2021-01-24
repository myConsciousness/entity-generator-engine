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
import org.thinkit.generator.entity.engine.catalog.EnvaliNumericDataType;
import org.thinkit.generator.entity.engine.content.entity.EnvaliNumericRangeOption;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * コンテンツ「EnvaliNumericRangeOption」をロードするクラスです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
@ContentMapping(content = "org/thinkit/generator/entity/engine/EnvaliNumericRangeOption")
public final class EnvaliNumericRangeOptionLoader implements Content<EnvaliNumericRangeOption> {

    /**
     * Envaliがサポートする数値のデータ型
     */
    private EnvaliNumericDataType envaliNumericDataType;

    /**
     * コンテンツ要素定数
     */
    @RequiredArgsConstructor
    private enum ContentAttribute implements Attribute {

        /**
         * 開始オプション
         */
        FROM_OPTION(Key.fromOption),

        /**
         * 終了オプション
         */
        TO_OPTION(Key.toOption);

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
            fromOption, toOption;
        }
    }

    /**
     * コンテンツ条件定数
     */
    @RequiredArgsConstructor
    private enum ContentCondition implements Condition {

        /**
         * 数値のデータ型コード
         */
        NUMERIC_DATA_TYPE_CODE(Key.numericDataTypeCode);

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
            numericDataTypeCode;
        }
    }

    @Override
    public EnvaliNumericRangeOption execute() {
        final Map<String, String> content = this.loadContent(this).get(0);
        return EnvaliNumericRangeOption.builder().fromOption(content.get(ContentAttribute.FROM_OPTION.getString()))
                .toOption(content.get(ContentAttribute.TO_OPTION.getString())).build();
    }

    @Override
    public Set<Attribute> getAttributes() {
        return Set.of(ContentAttribute.FROM_OPTION, ContentAttribute.TO_OPTION);
    }

    @Override
    public List<Map<Condition, String>> getConditions() {
        return List.of(
                Map.of(ContentCondition.NUMERIC_DATA_TYPE_CODE, String.valueOf(this.envaliNumericDataType.getCode())));
    }
}

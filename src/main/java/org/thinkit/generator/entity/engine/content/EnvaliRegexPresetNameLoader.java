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
import org.thinkit.generator.entity.engine.catalog.EnvaliRegexPreset;
import org.thinkit.generator.entity.engine.content.entity.EnvaliRegexPresetName;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * コンテンツ「EnvaliRegexPresetName」をロードするクラスです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
@ContentMapping(content = "org/thinkit/generator/entity/engine/EnvaliRegexPresetName")
public final class EnvaliRegexPresetNameLoader implements Content<EnvaliRegexPresetName> {

    /**
     * Envaliの正規表現プリセット
     */
    private EnvaliRegexPreset envaliRegexPreset;

    /**
     * コンテンツ要素定数
     */
    @RequiredArgsConstructor
    private enum ContentAttribute implements Attribute {

        /**
         * 正規表現プリセット名
         */
        REGEX_PRESET_NAME(Key.regexPresetName);

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
            regexPresetName;
        }
    }

    /**
     * コンテンツ条件定数
     */
    @RequiredArgsConstructor
    private enum ContentCondition implements Condition {

        /**
         * 正規表現プリセットコード
         */
        REGEX_PRESET_CODE(Key.regexPresetCode);

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
            regexPresetCode;
        }
    }

    @Override
    public EnvaliRegexPresetName execute() {
        return EnvaliRegexPresetName.builder()
                .regexPresetName(this.loadContent(this).get(0).get(ContentAttribute.REGEX_PRESET_NAME.getString()))
                .build();
    }

    @Override
    public Set<Attribute> getAttributes() {
        return Set.of(ContentAttribute.REGEX_PRESET_NAME);
    }

    @Override
    public List<Map<Condition, String>> getConditions() {
        return List.of(Map.of(ContentCondition.REGEX_PRESET_CODE, String.valueOf(this.envaliRegexPreset.getCode())));
    }
}

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

package org.thinkit.generator.entity.engine.catalog;

import org.thinkit.api.catalog.Catalog;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Envaliの正規表現解析メソッドを管理するカタログです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
@RequiredArgsConstructor
public enum EnvaliRegexMethod implements Catalog<EnvaliRegexMethod> {

    /**
     * パターンに一致する入力シーケンスの次の部分シーケンスを検索
     */
    FIND(0),

    /**
     * 領域の先頭から始まる入力シーケンスとパターンとの一致する部分を検索
     */
    LOOKING_AT(1),

    /**
     * 領域全体をパターンに一致する部分を検索
     */
    MATCHES(2);

    /**
     * コード値
     */
    @Getter
    private final int code;
}

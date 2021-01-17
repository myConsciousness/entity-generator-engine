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
 * Envaliの正規表現解析フラグを管理するカタログです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
@RequiredArgsConstructor
public enum EnvaliRegexModifier implements Catalog<EnvaliRegexModifier> {

    /**
     * Unix行
     */
    UNIX_LINES(0),

    /**
     * 大文字小文字を区別しない
     */
    CASE_INSENSITIVE(1),

    /**
     * パターン内の空白とコメントを許可
     */
    COMMENTS(2),

    /**
     * 複数行
     */
    MULTILINE(3),

    /**
     * パターンのリテラル解析を有効化
     */
    LITERAL(4),

    /**
     * ドットオールモード
     */
    DOTALL(5),

    /**
     * ユニコードを考慮したケース折り畳みを有効化
     */
    UNICODE_CASE(6),

    /**
     * 正準等価を有効化
     */
    CANON_EQ(7),

    /**
     * 定義済み文字クラスとPOSIX文字クラスのUnicodeバージョンを有効化
     */
    UNICODE_CHARACTER_CLASS(8);

    /**
     * コード値
     */
    @Getter
    private final int code;
}

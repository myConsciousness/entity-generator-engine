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
 * Envaliの正規表現プリセットを管理するカタログです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
@RequiredArgsConstructor
public enum EnvaliRegexPreset implements Catalog<EnvaliRegexPreset> {

    /**
     * なし
     */
    NONE(0),

    /**
     * メールアドレス
     */
    EMAIL_ADDRESS(1),

    /**
     * ドメイン名
     */
    DOMAIN_NAME(2),

    /**
     * Web URL (HTTP / HTTPS)
     */
    WEB_URL(3),

    /**
     * ユーザーID
     */
    USER_ID(4),

    /**
     * 固定電話番号 (Japan)
     */
    FIXED_LINE_PHONE_JP(5),

    /**
     * ハイフン付き固定電話番号 (Japan)
     */
    FIXED_LINE_PHONE_WITH_HYPHEN_JP(6),

    /**
     * 携帯電話番号 (Japan)
     */
    CELL_PHONE_JP(7),

    /**
     * ハイフン付き携帯電話番号 (Japan)
     */
    CELL_PHONE_WITH_HYPHEN_JP(8),

    /**
     * パスワード
     * <p>
     * 少なくとも1つの大文字、1つの小文字、1つの数値または特殊文字、そして8-32の長さであることを保証します。
     */
    PASSWORD(9),

    /**
     * 日付 (yyyyMMdd format)
     */
    DATE(10),

    /**
     * ハイフン付き日付 (yyyy-MM-dd format)
     */
    DATE_WITH_HYPHEN(11),

    /**
     * スラッシュ付き日付 (yyyyMMdd format)
     */
    DATE_WITH_SLASH(12),

    /**
     * 郵便番号 (Japan)
     */
    POST_CODE_JP(13),

    /**
     * XML file
     */
    XML_FILE(14),

    /**
     * IPアドレス (IPv4)
     */
    IP_ADDRESS(15),

    /**
     * ポート付きIPアドレス (IPv4)
     */
    IP_ADDRESS_WITH_PORT(16),

    /**
     * 数値
     */
    NUMERIC(17),

    /**
     * 英数字
     */
    ALPHANUMERIC(18),

    /**
     * 英字
     */
    ALPHABET(19),

    /**
     * 英字 (大文字)
     */
    ALPHABET_UPPER_CASE(20),

    /**
     * 英字 (小文字)
     */
    ALPHABET_LOWER_CASE(21),

    /**
     * FTP URL
     */
    FTP_URL(22),

    /**
     * Java file
     */
    JAVA_FILE(23),

    /**
     * Text file
     */
    TEXT_FILE(24),

    /**
     * JSON file
     */
    JSON_FILE(25),

    /**
     * 漢字
     */
    JAPANESE_KANJI(26),

    /**
     * ひらがな
     */
    HIRAGANA(27),

    /**
     * ひらばな (大文字)
     */
    HIRAGANA_UPPER_CASE(28),

    /**
     * ひらがな (小文字)
     */
    HIRAGANA_LOWER_CASE(29),

    /**
     * カタカナ
     */
    KATAKANA(30),

    /**
     * カタカナ (大文字)
     */
    KATAKANA_UPPER_CASE(31),

    /**
     * カタカナ (小文字)
     */
    KATAKANA_LOWER_CASE(32),

    /**
     * 和字
     */
    JAPANESE_ALPHABET(33),

    /**
     * 和数字
     */
    JAPANESE_ALPHANUMERIC(34);

    /**
     * コード値
     */
    @Getter
    private final int code;
}

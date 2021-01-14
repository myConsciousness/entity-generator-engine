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

package org.thinkit.generator.entity.engine.factory;

import org.apache.commons.lang3.StringUtils;
import org.thinkit.generator.common.duke.factory.FieldDefinition;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * エンティティクラスのフィールド定義を生成する処理を定義したファクトリークラスです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class EntityFieldDefinition extends FieldDefinition {

    /**
     * シリアルバージョンUIDのフィールド名
     */
    private static final String SERIAL_VERSION_UID = "serialVersionUID";

    /**
     * 引数として渡された情報を基に {@link EntityFieldDefinition} クラスの新しいインスタンスを生成します。
     *
     * @param dataType     データ型
     * @param variableName 変数名
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private EntityFieldDefinition(@NonNull String dataType, @NonNull String variableName) {
        super(dataType, variableName);
    }

    /**
     * 引数として渡された情報を基に {@link EntityFieldDefinition} クラスの新しいインスタンスを生成します。
     *
     * @param dataType     データ型
     * @param variableName 変数名
     * @param initialValue 初期値
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private EntityFieldDefinition(@NonNull String dataType, @NonNull String variableName,
            @NonNull String initialValue) {
        super(dataType, variableName, initialValue);
    }

    /**
     * 引数として渡された情報を基に {@link EntityFieldDefinition} クラスの新しいインスタンスを生成し返却します。
     *
     * @param dataType     データ型
     * @param variableName 変数名
     * @return {@link EntityFieldDefinition} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    protected static FieldDefinition of(@NonNull String dataType, @NonNull String variableName) {
        return new EntityFieldDefinition(dataType, variableName);
    }

    /**
     * 引数として渡された情報を基に {@link EntityFieldDefinition} クラスの新しいインスタンスを生成し返却します。
     *
     * @param dataType     データ型
     * @param variableName 変数名
     * @param initialValue 初期値
     * @return {@link EntityFieldDefinition} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    protected static FieldDefinition of(@NonNull String dataType, @NonNull String variableName,
            @NonNull String initialValue) {
        return new EntityFieldDefinition(dataType, variableName, initialValue);
    }

    @Override
    public String createResource() {

        if (SERIAL_VERSION_UID.equals(super.getVariableName())) {
            return """
                    private static final long serialVersionUID = 1L;
                    """;
        }

        final String initialValue = super.getInitialValue();

        if (StringUtils.isEmpty(initialValue)) {
            return """
                    private %s %s;
                    """.formatted(super.getDataType(), super.getVariableName());
        }

        return """
                private %s %s = %s;
                """.formatted(super.getDataType(), super.getVariableName(), this.appendQuotesOrDefault(initialValue));
    }

    /**
     * データ型が文字列型または文字型の場合はそれぞれ対応するクォーテーションを初期値に付与し返却します。
     *
     * @param initialValue クォーテーション付与対象の初期値
     * @return データ型が文字列型または文字型の場合はそれぞれ対応するクォーテーションを付与された初期値
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private String appendQuotesOrDefault(@NonNull String initialValue) {
        return switch (super.getDataType()) {
            case "String" -> String.format("\"%s\"", initialValue);
            case "char", "Character" -> String.format("'%s'", initialValue);
            default -> initialValue;
        };
    }
}

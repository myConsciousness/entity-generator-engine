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

package org.thinkit.generator.entity.engine.helper;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.thinkit.generator.entity.engine.catalog.EnvaliErrorType;
import org.thinkit.generator.entity.engine.dto.EntityEnvaliDefinition;
import org.thinkit.generator.entity.engine.dto.EntityField;
import org.thinkit.generator.entity.engine.dto.EnvaliMeta;
import org.thinkit.generator.entity.engine.dto.EnvaliRegexMeta;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Envaliアノテーションで付加するパラメータの適用可否を解析するクラスです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public final class EnvaliAnnotationAnalyzer implements Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = -2834373379172574941L;

    /**
     * Envaliエラー種別の適用可否
     */
    @Getter
    private boolean appliedEnvaliErrorType;

    /**
     * Envali正規表現プリセットの適用可否
     */
    @Getter
    private boolean appliedEnvaliRegexPreset;

    /**
     * Envali正規表現解析フラグの適用可否
     */
    @Getter
    private boolean appliedEnvaliRegexModifier;

    /**
     * Envali正規表現解析メソッドの適用可否
     */
    @Getter
    private boolean appliedEnvaliRegexMethod;

    /**
     * {@link EnvaliAnnotationAnalyzer} の新しいインスタンスを生成する際に引数として渡された
     * {@code entityFields} を解析し、Envaliアノテーションの各適用可否を導出します。
     * <p>
     * この解析時間は引数として渡された {@code entityFields}
     * のデータ量に依存し、最小はエラー種別、正規表現プリセット、正規表現解析フラグおよび正規表現解析メソッドのオプションを全て検知した場合で、最大は
     * {@code entityFields} に格納されたエンティティのフィールド定義の量分になります。
     *
     * @param entityFields エンティティのフィール定義
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private EnvaliAnnotationAnalyzer(@NonNull List<EntityField> entityFields) {

        for (final EntityField entityField : entityFields) {
            final List<EntityEnvaliDefinition> entityEnvaliDefinitions = entityField.getEntityEnvaliDefinitions();

            if (entityEnvaliDefinitions.isEmpty()) {
                continue;
            }

            for (final EntityEnvaliDefinition entityEnvaliDefinition : entityEnvaliDefinitions) {

                if (!this.appliedEnvaliErrorType
                        && entityEnvaliDefinition.getEnvaliErrorType() != EnvaliErrorType.RUNTIME) {
                    this.appliedEnvaliErrorType = true;
                }

                final EnvaliMeta envaliMeta = entityEnvaliDefinition.getEnvaliMeta();

                if (envaliMeta != null && envaliMeta.getEnvaliRegexMeta() != null) {
                    final EnvaliRegexMeta envaliRegexMeta = envaliMeta.getEnvaliRegexMeta();

                    if (StringUtils.isEmpty(envaliRegexMeta.getExpression())) {
                        if (!this.isAppliedEnvaliRegexPreset() && envaliRegexMeta.getEnvaliRegexPreset() != null) {
                            this.appliedEnvaliRegexPreset = true;
                        }
                    }

                    if (!this.appliedEnvaliRegexModifier && !envaliRegexMeta.getEnvaliRegexModifiers().isEmpty()) {
                        this.appliedEnvaliRegexModifier = true;
                    }

                    if (!this.appliedEnvaliRegexMethod && envaliRegexMeta.getEnvaliRegexMethod() != null) {
                        this.appliedEnvaliRegexMethod = true;
                    }
                }
            }

            if (this.isAppliedEnvaliErrorType() && this.isAppliedEnvaliRegexPreset()
                    && this.isAppliedEnvaliRegexModifier() && this.isAppliedEnvaliRegexMethod()) {
                return;
            }
        }
    }

    /**
     * {@link EnvaliAnnotationAnalyzer} の新しいインスタンスを生成する際に引数として渡された
     * {@code entityFields} を解析し、Envaliアノテーションの各適用可否を導出します。
     * <p>
     * この解析時間は引数として渡された {@code entityFields}
     * のデータ量に依存し、最小はエラー種別、正規表現プリセット、正規表現解析フラグおよび正規表現解析メソッドのオプションを全て検知した場合で、最大は
     * {@code entityFields} に格納されたエンティティのフィールド定義の量分になります。
     *
     * @param entityFields エンティティのフィール定義
     * @return {@link EnvaliAnnotationAnalyzer} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static EnvaliAnnotationAnalyzer forField(@NonNull List<EntityField> entityFields) {
        return new EnvaliAnnotationAnalyzer(entityFields);
    }
}

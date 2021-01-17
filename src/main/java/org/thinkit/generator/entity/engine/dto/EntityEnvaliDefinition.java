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

package org.thinkit.generator.entity.engine.dto;

import java.io.Serializable;

import org.thinkit.framework.envali.annotation.RequireNonNull;
import org.thinkit.framework.envali.entity.ValidatableEntity;
import org.thinkit.generator.entity.engine.catalog.EnvaliAnnotation;
import org.thinkit.generator.entity.engine.catalog.EnvaliErrorType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * エンティティのEnvali定義データを管理するデータクラスです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntityEnvaliDefinition implements ValidatableEntity, Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 4279830525658504915L;

    /**
     * Envaliアノテーション
     */
    @Getter
    private EnvaliAnnotation envaliAnnotation;

    /**
     * Envaliエラー種別
     */
    @Getter
    @Builder.Default
    @RequireNonNull
    private EnvaliErrorType envaliErrorType = EnvaliErrorType.RUNTIME;

    /**
     * エラー文言
     */
    @Getter
    @RequireNonNull
    private String message;

    /**
     * Envaliのメタデータ
     */
    @Getter
    private EnvaliMeta envaliMeta;
}

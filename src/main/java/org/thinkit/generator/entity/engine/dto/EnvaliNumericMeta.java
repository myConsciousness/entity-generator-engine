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

import org.thinkit.framework.envali.entity.ValidatableEntity;
import org.thinkit.generator.entity.engine.catalog.EnvaliNumericDataType;
import org.thinkit.generator.entity.engine.catalog.EnvaliNumericRangeType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

/**
 * Envaliの数値メタデータを管理するデータクラスです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EnvaliNumericMeta implements ValidatableEntity, Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = -3189701314070991047L;

    /**
     * 基準となる数値データ型
     */
    @Getter
    @NonNull
    private EnvaliNumericDataType envaliNumericDataType;

    /**
     * 基準となる範囲種別
     */
    @Getter
    @NonNull
    private EnvaliNumericRangeType envaliNumericRangeType;

    /**
     * 開始範囲（int）
     */
    @Getter
    private int intFrom;

    /**
     * 開始範囲（long）
     */
    @Getter
    private long longFrom;

    /**
     * 開始範囲（float）
     */
    @Getter
    private float floatFrom;

    /**
     * 開始範囲（double）
     */
    @Getter
    private double doubleFrom;

    /**
     * 開始範囲（short）
     */
    @Getter
    private short shortFrom;

    /**
     * 開始範囲（byte）
     */
    @Getter
    private byte byteFrom;

    /**
     * 終了範囲（int）
     */
    @Getter
    private int intTo;

    /**
     * 終了範囲（long）
     */
    @Getter
    private long longTo;

    /**
     * 終了範囲（float）
     */
    @Getter
    private float floatTo;

    /**
     * 終了範囲（double）
     */
    @Getter
    private double doubleTo;

    /**
     * 終了範囲（short）
     */
    @Getter
    private short shortTo;

    /**
     * 終了範囲（byte）
     */
    @Getter
    private byte byteTo;
}

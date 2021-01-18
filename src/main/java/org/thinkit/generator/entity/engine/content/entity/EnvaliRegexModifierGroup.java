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

package org.thinkit.generator.entity.engine.content.entity;

import java.util.ArrayList;
import java.util.Collection;

import org.thinkit.framework.content.entity.ContentEntity;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * コンテンツ「EnvaliRegexModifierName」の集合情報を管理するリストです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class EnvaliRegexModifierGroup extends ArrayList<EnvaliRegexModifierName> implements ContentEntity {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 4403868292626626927L;

    /**
     * 初期容量10の空のリストを作成します。
     */
    private EnvaliRegexModifierGroup() {
        super();
    }

    /**
     * 指定された初期容量で空のリストを作成します。
     *
     * @param initialCapacity 初期容量
     *
     * @exception IllegalArgumentException 初期容量が負数の場合
     */
    private EnvaliRegexModifierGroup(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * 指定したコレクションの要素を含むリストを、コレクションのイテレータによって返された順に作成します。
     *
     * @param collection 複製元のコレクション
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private EnvaliRegexModifierGroup(Collection<? extends EnvaliRegexModifierName> collection) {
        super(collection);
    }

    /**
     * 初期容量10の空の {@link EnvaliRegexModifierGroup} を作成します。
     *
     * @return 初期容量10を持つ空の {@link EnvaliRegexModifierGroup}
     */
    public static EnvaliRegexModifierGroup newInstance() {
        return new EnvaliRegexModifierGroup();
    }

    /**
     * 指定された初期容量で空の {@link EnvaliRegexModifierGroup} を作成します。
     *
     * @param initialCapacity 初期容量
     * @return 指定された初期容量を持つ空の {@link EnvaliRegexModifierGroup}
     *
     * @exception IllegalArgumentException 初期容量が負数の場合
     */
    public static EnvaliRegexModifierGroup of(int initialCapacity) {
        return new EnvaliRegexModifierGroup(initialCapacity);
    }

    /**
     * 指定したコレクションの要素を含む {@link EnvaliRegexModifierGroup}
     * を、コレクションのイテレータによって返された順に作成します。
     *
     * @param collection 複製元のコレクション
     * @return 指定したコレクションの要素を含む {@link EnvaliRegexModifierGroup}
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static EnvaliRegexModifierGroup of(Collection<? extends EnvaliRegexModifierName> collection) {
        return new EnvaliRegexModifierGroup(collection);
    }
}

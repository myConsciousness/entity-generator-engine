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

import org.thinkit.generator.common.duke.factory.Generics;
import org.thinkit.generator.common.duke.factory.Inheritance;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * エンティティクラスの継承を生成する処理を定義したファクトリークラスです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class EntityInheritance extends Inheritance {

    /**
     * 引数として渡された情報を基に {@link EntityInheritance} クラスの新しいインスタンスを生成します。
     *
     * @param literal 継承名
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private EntityInheritance(@NonNull String literal) {
        super(literal);
    }

    /**
     * 引数として渡された情報を基に {@link EntityInheritance} クラスの新しいインスタンスを生成します。
     *
     * @param literal  継承名
     * @param generics 総称型
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private EntityInheritance(@NonNull String literal, @NonNull Generics generics) {
        super(literal, generics);
    }

    /**
     * 引数として渡された情報を基に {@link EntityInheritance} クラスの新しいインスタンスを生成し返却します。
     *
     * @param literal 継承名
     * @return {@link EntityInheritance} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    protected static Inheritance of(@NonNull String literal) {
        return new EntityInheritance(literal);
    }

    /**
     * 引数として渡された情報を基に {@link EntityInheritance} クラスの新しいインスタンスを生成し返却します。
     *
     * @param literal  継承名
     * @param generics 総称型
     * @return {@link EntityInheritance} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    protected static Inheritance of(@NonNull String literal, @NonNull Generics generics) {
        return new EntityInheritance(literal, generics);
    }

    @Override
    public String createResource() {

        final StringBuilder inheritance = new StringBuilder(super.getLiteral());
        final Generics generics = super.getGenerics();

        if (!generics.isEmpty()) {
            inheritance.append(generics.createResource());
        }

        return inheritance.toString();
    }
}

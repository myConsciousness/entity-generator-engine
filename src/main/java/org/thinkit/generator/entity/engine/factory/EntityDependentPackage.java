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

import org.thinkit.generator.common.duke.factory.DependentPackage;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * エンティティクラスの依存パッケージを生成する処理を定義したファクトリークラスです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class EntityDependentPackage extends DependentPackage {

    /**
     * コンストラクタ
     *
     * @param dependentPackage 依存パッケージ
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private EntityDependentPackage(@NonNull String dependentPackage) {
        super(dependentPackage);
    }

    /**
     * 引数として渡された情報を基に {@link DependentPackage} クラスの新しいインスタンスを生成し返却します。
     *
     * @param dependentPackage 依存パッケージ
     * @return {@link DependentPackage} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static DependentPackage of(@NonNull String dependentPackage) {
        return new EntityDependentPackage(dependentPackage);
    }

    @Override
    public String createResource() {
        return String.format("import %s;", super.getDependentPackage());
    }
}

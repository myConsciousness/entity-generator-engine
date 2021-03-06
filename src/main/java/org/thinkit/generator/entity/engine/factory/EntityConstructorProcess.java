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

import org.thinkit.generator.common.duke.factory.ConstructorProcess;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * エンティティクラスのコンストラクタ処理を生成する処理を定義したファクトリークラスです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class EntityConstructorProcess extends ConstructorProcess {

    /**
     * 引数として渡された情報を基に {@link EntityConstructorProcess} クラスの新しいインスタンスを生成し返却します。
     *
     * @param variableName 変数名
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private EntityConstructorProcess(@NonNull String variableName) {
        super(variableName);
    }

    /**
     * 引数として渡された情報を基に {@link EntityConstructorProcess} クラスの新しいインスタンスを生成し返却します。
     *
     * @param variableName 変数名
     * @return {@link EntityConstructorProcess} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    protected static ConstructorProcess of(@NonNull String variableName) {
        return new EntityConstructorProcess(variableName);
    }

    @Override
    public String createResource() {

        final String process = super.getProcess();

        return """
                this.%s = %s;""".formatted(process, process);
    }
}

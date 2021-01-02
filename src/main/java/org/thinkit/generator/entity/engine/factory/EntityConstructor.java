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
import org.thinkit.generator.common.duke.factory.Constructor;
import org.thinkit.generator.common.duke.factory.FunctionDescription;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * エンティティクラスのコンストラクタを生成する処理を定義したファクトリークラスです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class EntityConstructor extends Constructor {

    /**
     * 引数として渡された {@link EntityConstructor} クラスの新しいインスタンスを生成し返却します。
     *
     * @param functionName        コンストラクタ名
     * @param functionDescription コンストラクタのJavadoc
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private EntityConstructor(@NonNull String functionName, @NonNull FunctionDescription functionDescription) {
        super(functionName, functionDescription);
    }

    /**
     * 引数として渡された {@link EntityConstructor} クラスの新しいインスタンスを生成し返却します。
     *
     * @param functionName        コンストラクタ名
     * @param functionDescription コンストラクタのJavadoc
     * @return {@link EntityConstructor} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    protected static Constructor of(@NonNull String functionName, @NonNull FunctionDescription functionDescription) {
        return new EntityConstructor(functionName, functionDescription);
    }

    @Override
    public String createResource() {

        final String process = super.getProcess();

        if (StringUtils.isEmpty(process)) {
            return """
                    %s
                    private %s() {
                    }
                    """.formatted(super.getFunctionDescription().createResource(), super.getFunctionName());
        }

        return """
                %s
                private %s(%s) {
                    %s
                }
                """.formatted(super.getFunctionDescription().createResource(), super.getFunctionName(),
                super.getParameter(), super.getProcess());
    }
}

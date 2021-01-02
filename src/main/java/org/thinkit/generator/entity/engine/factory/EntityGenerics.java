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

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * エンティティクラスのジェネリクスを生成する処理を定義したファクトリークラスです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(staticName = "of")
public final class EntityGenerics extends Generics {

    /**
     * 総称型の区切り文字
     */
    private static final String GENERICS_DELIMITER = ", ";

    @Override
    public String createResource() {
        return """
                <%s>""".formatted(String.join(GENERICS_DELIMITER, super.getGenerics()));
    }
}

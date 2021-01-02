package org.thinkit.generator.entity.engine.factory;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.thinkit.generator.common.duke.factory.Copyright;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntityCopyright extends Copyright {

    /**
     * 引数として与えられた情報を基に {@link EntityCopyright} クラスの新しいインスタンスを生成します。
     *
     * @param creator 作成者
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private EntityCopyright(@NonNull String creator) {
        super(creator);
    }

    /**
     * 引数として渡された情報を基に {@link EntityCopyright} クラスの新しいインスタンスを生成し返却します。
     *
     * @param creator 作成者
     * @return {@link EntityCopyright} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    protected static Copyright of(@NonNull String creator) {
        return new EntityCopyright(creator);
    }

    @Override
    public String createResource() {
        return """
                /*
                 * Copyright %s %s.
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
                """.formatted(super.getCreationYear(), super.getCreator());
    }
}

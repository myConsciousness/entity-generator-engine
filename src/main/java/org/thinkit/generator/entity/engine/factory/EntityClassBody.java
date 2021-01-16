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

import org.thinkit.common.catalog.Brace;
import org.thinkit.common.catalog.Delimiter;
import org.thinkit.common.catalog.Indentation;
import org.thinkit.generator.common.duke.factory.ClassBody;
import org.thinkit.generator.common.duke.factory.ClassDescription;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * エンティティクラスのクラスボディ部を生成する処理を定義したファクトリークラスです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class EntityClassBody extends ClassBody {

    /**
     * 改行
     */
    private static final String RETURN = Indentation.RETURN.getTag();

    /**
     * コンストラクタ
     *
     * @param classDescription クラスの説明
     * @param resourceName     リソース名
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private EntityClassBody(@NonNull ClassDescription classDescription, @NonNull String resourceName) {
        super(classDescription, resourceName);
    }

    /**
     * 引数として渡された情報を基に {@link EntityClassBody} クラスの新しいインスタンスを生成し返却します。
     *
     * @param classDescription クラスの説明
     * @param resourceName     リソース名
     * @return {@link EntityClassBody} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    protected static ClassBody of(@NonNull ClassDescription classDescription, @NonNull String resourceName) {
        return new EntityClassBody(classDescription, resourceName);
    }

    @Override
    public String createResource() {

        final StringBuilder classBody = new StringBuilder();

        this.createClassDescription(classBody);
        this.createClassBody(classBody);

        return classBody.toString();
    }

    /**
     * クラスの説明を表現する文字列リソースを生成しエンティティのクラスボディへ追加します。
     *
     * @param classBody エンティティのクラスボディ
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private void createClassDescription(@NonNull StringBuilder classBody) {
        classBody.append(super.getClassDescription().createResource());
        classBody.append(RETURN);
    }

    /**
     * クラスのボディ部を表現する文字列リソースを生成しエンティティのクラスボディへ追加します。
     *
     * @param classBody エンティティのクラスボディ
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private void createClassBody(@NonNull StringBuilder classBody) {

        super.getAnnotations().forEach(annotation -> {
            classBody.append(annotation.createResource());
            classBody.append(RETURN);
        });

        classBody.append(String.format("public final class %s implements %s {", super.getResourceName(),
                this.createInterface()));
        classBody.append(RETURN).append(RETURN);

        this.createField(classBody);

        classBody.append(Brace.END.getTag());
        classBody.append(RETURN);
    }

    /**
     * 設定されたインターフェース定義からインターフェースを表現する文字列を生成し返却します。複数のインターフェースが存在する場合はカンマ区切りで返却します。
     *
     * @return インターフェースを表現する文字列
     */
    private String createInterface() {

        final StringBuilder __interface = new StringBuilder();
        final String delimiter = Delimiter.COMMA.getTag();

        super.getInterfaces().forEach(_interface -> {
            __interface.append(_interface.createResource());
            __interface.append(delimiter);
        });

        __interface.setLength(__interface.length() - delimiter.length());

        return __interface.toString();
    }

    /**
     * フィールドを表現する文字列リソースを生成しエンティティのクラスボディへ追加します。
     *
     * @param classBody エンティティのクラスボディ
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private void createField(@NonNull StringBuilder classBody) {

        super.getFields().forEach(field -> {
            classBody.append(field.createResource());
            classBody.append(RETURN).append(RETURN);
        });

        classBody.setLength(classBody.length() - RETURN.length());
    }
}

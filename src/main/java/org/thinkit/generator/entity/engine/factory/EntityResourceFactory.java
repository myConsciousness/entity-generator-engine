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

import org.thinkit.generator.common.duke.catalog.AnnotationPattern;
import org.thinkit.generator.common.duke.catalog.Modifier;
import org.thinkit.generator.common.duke.factory.Annotation;
import org.thinkit.generator.common.duke.factory.AnnotationParameter;
import org.thinkit.generator.common.duke.factory.ClassBody;
import org.thinkit.generator.common.duke.factory.ClassDescription;
import org.thinkit.generator.common.duke.factory.Constructor;
import org.thinkit.generator.common.duke.factory.ConstructorProcess;
import org.thinkit.generator.common.duke.factory.Copyright;
import org.thinkit.generator.common.duke.factory.DependentPackage;
import org.thinkit.generator.common.duke.factory.Description;
import org.thinkit.generator.common.duke.factory.DescriptionTag;
import org.thinkit.generator.common.duke.factory.EnumDefinition;
import org.thinkit.generator.common.duke.factory.Enumeration;
import org.thinkit.generator.common.duke.factory.Field;
import org.thinkit.generator.common.duke.factory.FieldDefinition;
import org.thinkit.generator.common.duke.factory.FunctionDescription;
import org.thinkit.generator.common.duke.factory.Generics;
import org.thinkit.generator.common.duke.factory.Inheritance;
import org.thinkit.generator.common.duke.factory.Interface;
import org.thinkit.generator.common.duke.factory.Method;
import org.thinkit.generator.common.duke.factory.MethodProcess;
import org.thinkit.generator.common.duke.factory.Package;
import org.thinkit.generator.common.duke.factory.Parameter;
import org.thinkit.generator.common.duke.factory.Resource;
import org.thinkit.generator.common.duke.factory.ResourceFactory;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

/**
 * エンティティクラスの各コンポーネントクラスを生成するファクトリークラスです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntityResourceFactory extends ResourceFactory {

    /**
     * {@link EntityResourceFactory} のシングルトンインスタンスを返却します。
     *
     * @return {@link EntityResourceFactory} のシングルトンインスタンス
     */
    public static ResourceFactory getInstance() {
        return InstanceHolder.INSTANCE;
    }

    /**
     * {@link EntityResourceFactory} のシングルトンインスタンスを保持するインナークラスです。<br>
     * {@link EntityResourceFactory} シングルトンインスタンスは初回参照時にメモリに読み込まれます。
     */
    private static class InstanceHolder {

        /**
         * シングルトンインスタンス
         */
        private static final ResourceFactory INSTANCE = new EntityResourceFactory();
    }

    @Override
    public Annotation createAnnotation(@NonNull AnnotationPattern arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public AnnotationParameter createAnnotationParameter(@NonNull String arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ClassBody createClassBody(@NonNull ClassDescription arg0, @NonNull String arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ClassDescription createClassDescription(@NonNull String arg0, @NonNull String arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ClassDescription createClassDescription(@NonNull String arg0, @NonNull String arg1, @NonNull String arg2) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Constructor createConstructor(@NonNull String arg0, @NonNull FunctionDescription arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ConstructorProcess createConstructorProcess(String arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ConstructorProcess createConstructorProcess(@NonNull String arg0, @NonNull String arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Copyright createCopyright(@NonNull String arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Copyright createCopyright(@NonNull String arg0, @NonNull String arg1, @NonNull String arg2,
            @NonNull String arg3, @NonNull String arg4) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DependentPackage createDependentPackage(@NonNull String arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Description createDescription(@NonNull String arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DescriptionTag createDescriptionTag(@NonNull String arg0, @NonNull String arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DescriptionTag createDescriptionTag(@NonNull String arg0, @NonNull String arg1,
            @NonNull AnnotationPattern arg2) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EnumDefinition createEnumDefinition(@NonNull String arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Enumeration createEnumeration(@NonNull EnumDefinition arg0, @NonNull Description arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Field createField(@NonNull FieldDefinition arg0, @NonNull Description arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public FieldDefinition createFieldDefinition(@NonNull String arg0, @NonNull String arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public FieldDefinition createFieldDefinition(@NonNull String arg0, @NonNull String arg1, @NonNull String arg2) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public FunctionDescription createFunctionDescription(@NonNull String arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Generics createGenerics() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Inheritance createInheritance(@NonNull String arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Inheritance createInheritance(@NonNull String arg0, @NonNull Generics arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Interface createInterface(@NonNull String arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Interface createInterface(@NonNull String arg0, @NonNull Generics arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Method createMethod(@NonNull Modifier arg0, @NonNull String arg1, @NonNull String arg2,
            @NonNull FunctionDescription arg3) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MethodProcess createMethodProcess(@NonNull String arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Package createPackage(@NonNull String arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Parameter createParameter(@NonNull String arg0, @NonNull String arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Resource createResource(@NonNull Copyright arg0, @NonNull Package arg1, @NonNull ClassBody arg2) {
        // TODO Auto-generated method stub
        return null;
    }
}

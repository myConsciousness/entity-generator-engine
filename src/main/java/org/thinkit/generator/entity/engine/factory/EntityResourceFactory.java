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
     * サポート外メッセージ
     */
    private static final String UNSUPPORTED_MESSAGE = "This method is not supposed to be called when Entity class is created";

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
    public Copyright createCopyright(@NonNull String creator) {
        return EntityCopyright.of(creator);
    }

    @Override
    @Deprecated
    public Copyright createCopyright(@NonNull String projectName, @NonNull String fileName, @NonNull String encoding,
            @NonNull String creator, @NonNull String creationDate) {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }

    @Override
    public ClassDescription createClassDescription(@NonNull String creator, @NonNull String version) {
        return EntityClassDescription.of(creator, version);
    }

    @Override
    @Deprecated
    public ClassDescription createClassDescription(@NonNull String description, @NonNull String creator,
            @NonNull String version) {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }

    @Override
    public Description createDescription(@NonNull String description) {
        return EntityDescription.of(description);
    }

    @Override
    public Inheritance createInheritance(@NonNull String literal) {
        return EntityInheritance.of(literal);
    }

    @Override
    public Inheritance createInheritance(@NonNull String literal, @NonNull Generics generics) {
        return EntityInheritance.of(literal, generics);
    }

    @Override
    public Interface createInterface(@NonNull String literal) {
        return EntityInterface.of(literal);
    }

    @Override
    public Interface createInterface(@NonNull String literal, @NonNull Generics generics) {
        return EntityInterface.of(literal, generics);
    }

    @Override
    public Generics createGenerics() {
        return EntityGenerics.of();
    }

    @Override
    public EnumDefinition createEnumDefinition(@NonNull String literal) {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }

    @Override
    public Enumeration createEnumeration(@NonNull EnumDefinition enumDefinition, @NonNull Description description) {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }

    @Override
    public FieldDefinition createFieldDefinition(@NonNull String dataType, @NonNull String variableName) {
        return EntityFieldDefinition.of(dataType, variableName);
    }

    @Override
    @Deprecated
    public FieldDefinition createFieldDefinition(@NonNull String dataType, @NonNull String variableName,
            @NonNull String initialValue) {
        return EntityFieldDefinition.of(dataType, variableName, initialValue);
    }

    @Override
    public Field createField(@NonNull FieldDefinition fieldDefinition, @NonNull Description description) {
        return EntityField.of(fieldDefinition, description);
    }

    @Override
    public FunctionDescription createFunctionDescription(@NonNull String description) {
        return EntityMethodDescription.of(description);
    }

    @Override
    @Deprecated
    public DescriptionTag createDescriptionTag(@NonNull String variableName, @NonNull String description) {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }

    @Override
    public DescriptionTag createDescriptionTag(@NonNull String variableName, @NonNull String description,
            @NonNull AnnotationPattern annotationPattern) {
        return EntityDescriptionTag.of(variableName, description, annotationPattern);
    }

    @Override
    public Constructor createConstructor(@NonNull String functionName,
            @NonNull FunctionDescription functionDescription) {
        return EntityConstructor.of(functionName, functionDescription);
    }

    @Override
    public Parameter createParameter(@NonNull String dataType, @NonNull String variableName) {
        return EntityParameter.of(dataType, variableName);
    }

    @Override
    public ConstructorProcess createConstructorProcess(String variableName) {
        return EntityConstructorProcess.of(variableName);
    }

    @Override
    @Deprecated
    public ConstructorProcess createConstructorProcess(@NonNull String variableName, @NonNull String getterName) {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }

    @Override
    public Method createMethod(@NonNull Modifier modifier, @NonNull String returnType, @NonNull String methodName,
            @NonNull FunctionDescription methodDescription) {
        return EntityMethod.of(modifier, returnType, methodName, methodDescription);
    }

    @Override
    public MethodProcess createMethodProcess(@NonNull String variableName) {
        return EntityMethodProcess.of(variableName);
    }

    @Override
    public DependentPackage createDependentPackage(@NonNull String dependentPackage) {
        return EntityDependentPackage.of(dependentPackage);
    }

    @Override
    public Package createPackage(@NonNull String packageName) {
        return EntityPackage.of(packageName);
    }

    @Override
    public Annotation createAnnotation(@NonNull AnnotationPattern annotationPattern) {
        return EntityAnnotation.of(annotationPattern);
    }

    @Override
    public AnnotationParameter createAnnotationParameter(@NonNull String fieldName) {
        return EntityAnnotationParameter.of(fieldName);
    }

    @Override
    public ClassBody createClassBody(@NonNull ClassDescription classDescription, @NonNull String resourceName) {
        return EntityClassBody.of(classDescription, resourceName);
    }

    @Override
    public Resource createResource(@NonNull Copyright copyright, @NonNull Package packageName,
            @NonNull ClassBody classBody) {
        return EntityResource.of(copyright, packageName, classBody);
    }
}

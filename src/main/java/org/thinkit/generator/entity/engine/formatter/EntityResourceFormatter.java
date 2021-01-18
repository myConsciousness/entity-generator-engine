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

package org.thinkit.generator.entity.engine.formatter;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.thinkit.framework.content.ContentInvoker;
import org.thinkit.framework.envali.Envali;
import org.thinkit.generator.common.duke.catalog.AnnotationPattern;
import org.thinkit.generator.common.duke.catalog.ParameterDataType;
import org.thinkit.generator.common.duke.factory.Annotation;
import org.thinkit.generator.common.duke.factory.ClassBody;
import org.thinkit.generator.common.duke.factory.ClassDescription;
import org.thinkit.generator.common.duke.factory.Copyright;
import org.thinkit.generator.common.duke.factory.DependentPackage;
import org.thinkit.generator.common.duke.factory.Description;
import org.thinkit.generator.common.duke.factory.Field;
import org.thinkit.generator.common.duke.factory.FieldDefinition;
import org.thinkit.generator.common.duke.factory.Package;
import org.thinkit.generator.common.duke.factory.Resource;
import org.thinkit.generator.common.duke.factory.ResourceFactory;
import org.thinkit.generator.common.duke.formatter.JavaResourceFormatter;
import org.thinkit.generator.entity.engine.catalog.EntityDependentPackage;
import org.thinkit.generator.entity.engine.catalog.EntityInterface;
import org.thinkit.generator.entity.engine.catalog.EnvaliErrorType;
import org.thinkit.generator.entity.engine.content.EntityInterfaceNameLoader;
import org.thinkit.generator.entity.engine.content.EntityPackageLoader;
import org.thinkit.generator.entity.engine.content.EnvaliAnnotationPackageLoader;
import org.thinkit.generator.entity.engine.content.EnvaliErrorTypeNameLoader;
import org.thinkit.generator.entity.engine.content.LombokPackageLoader;
import org.thinkit.generator.entity.engine.content.entity.EnvaliErrorTypeName;
import org.thinkit.generator.entity.engine.dto.EntityCreator;
import org.thinkit.generator.entity.engine.dto.EntityDefinition;
import org.thinkit.generator.entity.engine.dto.EntityEnvaliDefinition;
import org.thinkit.generator.entity.engine.dto.EntityField;
import org.thinkit.generator.entity.engine.dto.EntityMatrix;
import org.thinkit.generator.entity.engine.dto.EntityMeta;
import org.thinkit.generator.entity.engine.dto.EntityResource;
import org.thinkit.generator.entity.engine.dto.EntityResourceGroup;
import org.thinkit.generator.entity.engine.dto.EnvaliLiteralMeta;
import org.thinkit.generator.entity.engine.dto.EnvaliMeta;
import org.thinkit.generator.entity.engine.dto.EnvaliNumericMeta;
import org.thinkit.generator.entity.engine.dto.EnvaliRegexMeta;
import org.thinkit.generator.entity.engine.factory.EntityResourceFactory;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

/**
 * {@link EntityMatrix} クラスに格納されたリソース情報を基にエンティティリソースを生成する処理を定義したフォーマッタークラスです。
 * <p>
 * {@link #newInstance()} メソッドを使用することで {@link EntityResourceFormatter}
 * クラスの新しいインスタンスを生成することができます。 {@link EntityResourceFormatter}
 * クラスの新しいインスタンスを生成した後は {@link #format(EntityMatrix)}
 * メソッドを呼び出し整形処理を行ってください。整形処理が正常終了した場合は生成されたリソースが格納された
 * {@link EntityResourceGroup} が返却されます。
 *
 * <pre>
 * 操作例:
 * <code>
 * EntityResourceFormatter.newInstance().format(entityMatrix).foreach(entityResource -> {
 *      * // do something like
 *      entityResource.getPackageName();
 *      entityResource.getClassName();
 *      entityResource.getResource();
 * });
 * </code>
 * </pre>
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode
@NoArgsConstructor(staticName = "newInstance")
public final class EntityResourceFormatter implements JavaResourceFormatter<EntityMatrix, EntityResourceGroup> {

    @Override
    public EntityResourceGroup format(@NonNull EntityMatrix entityMatrix) {
        Envali.validate(entityMatrix);

        final EntityResourceGroup entityResourceGroup = EntityResourceGroup.newInstance();
        this.createResourceRecursively(entityMatrix.getEntityCreator(), entityMatrix.getEntityDefinitions(),
                entityResourceGroup);

        return entityResourceGroup;
    }

    /**
     * エンティティリソースを再帰的に生成します。
     *
     * @param entityCreator       エンティティ作成者
     * @param entityDefinitions   エンティティ定義リスト
     * @param entityResourceGroup エンティティリソースグループ
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private void createResourceRecursively(@NonNull final EntityCreator entityCreator,
            @NonNull final List<EntityDefinition> entityDefinitions,
            @NonNull final EntityResourceGroup entityResourceGroup) {

        entityDefinitions.forEach(entityDefinition -> {
            final String className = entityDefinition.getClassName();
            final Resource resource = EntityResourceFactory.getInstance().createResource(
                    this.createCopyright(entityCreator), this.createPackage(entityDefinition),
                    this.createClassBody(className, entityCreator, entityDefinition, entityResourceGroup));

            this.addDependentPackage(resource, entityDefinition.getEntityMeta(), entityDefinition.getEntityFields());

            entityResourceGroup.add(EntityResource.builder().packageName(entityDefinition.getPackageName())
                    .resourceName(className).resource(resource.createResource()).build());
        });
    }

    /**
     * リソースへ依存パッケージを追加します。
     *
     * @param resource     エンティティリソース
     * @param entityMeta   エンティティのメタデータ
     * @param entityFields エンティティのフィールドデータ
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private void addDependentPackage(@NonNull Resource resource, @NonNull EntityMeta entityMeta,
            @NonNull List<EntityField> entityFields) {

        entityMeta.getDependentPackages().forEach(dependentPackage -> {
            resource.add(this.createDependentPackage(dependentPackage));
        });

        entityFields.forEach(entityField -> {
            entityField.getEntityEnvaliDefinitions().forEach(entityEnvaliDefinition -> {
                resource.add(this.createDependentPackage(ContentInvoker
                        .of(EnvaliAnnotationPackageLoader.of(entityEnvaliDefinition.getEnvaliAnnotation())).invoke()
                        .getPackageName()));
            });
        });

        ContentInvoker.of(EntityPackageLoader.of(this.getEntityDependentPackages(entityMeta))).invoke()
                .forEach(envaliPackage -> {
                    resource.add(this.createDependentPackage(envaliPackage.getPackageName()));
                });

        ContentInvoker.of(LombokPackageLoader.newInstance()).invoke().forEach(lombokPackage -> {
            resource.add(this.createDependentPackage(lombokPackage.getPackageName()));
        });
    }

    /**
     * エンティティの依存パッケージを生成し返却します。
     *
     * @param dependentPackage 依存パッケージ
     * @return エンティティの依存パッケージ
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private DependentPackage createDependentPackage(@NonNull String dependentPackage) {
        return EntityResourceFactory.getInstance().createDependentPackage(dependentPackage);
    }

    /**
     * エンティティのクラスボディ部を生成し返却します。
     *
     * @param className           クラス名
     * @param entityCreator       エンティティ作成者
     * @param entityDefinition    エンティティ定義
     * @param entityResourceGroup エンティティリソースグループ
     * @return エンティティのクラスボディ部
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private ClassBody createClassBody(@NonNull final String className, @NonNull final EntityCreator entityCreator,
            @NonNull final EntityDefinition entityDefinition, @NonNull final EntityResourceGroup entityResourceGroup) {

        final EntityMeta entityMeta = entityDefinition.getEntityMeta();

        final ResourceFactory factory = EntityResourceFactory.getInstance();
        final ClassDescription classDescription = factory.createClassDescription(entityCreator.getCreator(),
                entityMeta.getVersion());

        final ClassBody classBody = factory.createClassBody(classDescription, className);
        this.addClassAnnotation(classBody);

        ContentInvoker.of(EntityInterfaceNameLoader.of(this.getEntityInterfaces(entityMeta))).invoke()
                .forEach(entityInterfaceName -> {
                    classBody.add(factory.createInterface(entityInterfaceName.getInterfaceName()));
                });

        classBody.add(this.createFieldSerialVersionUID());

        entityDefinition.getEntityFields().forEach(entityField -> {
            classBody.add(this.createField(entityField));

            final List<EntityDefinition> childEntityDefinition = entityField.getChildEntityDefinitions();

            if (!childEntityDefinition.isEmpty()) {
                this.createResourceRecursively(entityCreator, childEntityDefinition, entityResourceGroup);
            }
        });

        return classBody;
    }

    /**
     * クラスボディにクラスアノテーションを追加します。
     *
     * @param classBody クラスボディ部
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private void addClassAnnotation(@NonNull ClassBody classBody) {

        final ResourceFactory factory = EntityResourceFactory.getInstance();

        final Annotation builderAnnotation = factory.createAnnotation(AnnotationPattern.LOMBOK_BUILDER)
                .add(factory.createAnnotationParameter("toBuilder").put(ParameterDataType.DEFAULT, true));
        final Annotation noArgsConstructorAnnotation = factory
                .createAnnotation(AnnotationPattern.LOMBOK_NO_ARGS_CONSTRUCTOR)
                .add(factory.createAnnotationParameter("access").put(ParameterDataType.DEFAULT, "AccessLevel.PRIVATE"));
        final Annotation allArgsConstructorAnnotation = factory
                .createAnnotation(AnnotationPattern.LOMBOK_ALL_ARGS_CONSTRUCTOR)
                .add(factory.createAnnotationParameter("access").put(ParameterDataType.DEFAULT, "AccessLevel.PRIVATE"));

        classBody.add(factory.createAnnotation(AnnotationPattern.LOMBOK_TO_STRING));
        classBody.add(factory.createAnnotation(AnnotationPattern.LOMBOK_EQUALS_AND_HASH_CODE));
        classBody.add(builderAnnotation);
        classBody.add(noArgsConstructorAnnotation);
        classBody.add(allArgsConstructorAnnotation);
    }

    /**
     * Envaliの適用可否に基づいてエンティティが依存するパッケージ名を返却します。
     *
     * @param entityMeta エンティティのメタデータ
     * @return エンティティが依存するパッケージ名の集合
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private Set<EntityDependentPackage> getEntityDependentPackages(@NonNull EntityMeta entityMeta) {

        if (entityMeta.isAppliedEnvali()) {
            return EnumSet.allOf(EntityDependentPackage.class);
        }

        return EnumSet.of(EntityDependentPackage.SERIALIZABLE);
    }

    /**
     * Envaliの適用可否に基づいてエンティティが実装するインターフェース名を返却します。
     *
     * @param entityMeta エンティティのメタデータ
     * @return エンティティが実装するインターフェス名の集合
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private Set<EntityInterface> getEntityInterfaces(@NonNull EntityMeta entityMeta) {

        if (entityMeta.isAppliedEnvali()) {
            return EnumSet.allOf(EntityInterface.class);
        }

        return EnumSet.of(EntityInterface.SERIALIZABLE);
    }

    /**
     * エンティティの著作権を生成し返却します。
     *
     * @param entityCreator エンティティ作成者
     * @return エンティティの著作権
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private Copyright createCopyright(@NonNull EntityCreator entityCreator) {
        return EntityResourceFactory.getInstance().createCopyright(entityCreator.getCreator());
    }

    /**
     * エンティティのパッケージを生成し返却します。
     *
     * @param entityDefinition エンティティ定義
     * @return エンティティのパッケージ
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private Package createPackage(@NonNull EntityDefinition entityDefinition) {
        return EntityResourceFactory.getInstance().createPackage(entityDefinition.getPackageName());
    }

    /**
     * シリアルバージョンUIDを表現するフィールドを生成し返却します。
     *
     * @return シリアルバージョンUIDを表現するフィールド
     */
    private Field createFieldSerialVersionUID() {

        final ResourceFactory factory = EntityResourceFactory.getInstance();

        final Description description = factory.createDescription("The serial version UID");
        final FieldDefinition fieldDefinition = factory.createFieldDefinition("", "serialVersionUID");

        return factory.createField(fieldDefinition, description);
    }

    /**
     * エンティティのフィールドを生成し返却します。
     *
     * @param entityField エンティティフィールド定義
     * @return エンティティのフィールド
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private Field createField(@NonNull EntityField entityField) {

        final ResourceFactory factory = EntityResourceFactory.getInstance();
        final String initialValue = entityField.getInitialValue();

        final Description description = factory.createDescription(entityField.getDescription());
        final Field field = factory.createField(StringUtils.isEmpty(initialValue)
                ? factory.createFieldDefinition(entityField.getDataType(), entityField.getVariableName())
                : factory.createFieldDefinition(entityField.getDataType(), entityField.getVariableName(), initialValue),
                description);

        field.add(factory.createAnnotation(AnnotationPattern.LOMBOK_GETTER));

        if (!StringUtils.isEmpty(initialValue)) {
            field.add(factory.createAnnotation(AnnotationPattern.LOMBOK_BUILDER_DEFAULT));
        }

        this.addEnvaliAnnotations(field, entityField);

        return field;
    }

    private void addEnvaliAnnotations(@NonNull Field field, @NonNull EntityField entityField) {

        entityField.getEntityEnvaliDefinitions().forEach(entityEnvaliDefinition -> {

            final Annotation annotation = this.createEnvaliAnnotation(entityEnvaliDefinition);
            final EnvaliMeta envaliMeta = entityEnvaliDefinition.getEnvaliMeta();

            if (envaliMeta != null) {
                switch (envaliMeta.getEnvaliMetaType()) {
                    case LITERAL -> this.addEnvaliLiteralParameter(annotation, entityEnvaliDefinition,
                            envaliMeta.getEnvaliLiteralMeta());
                    case NUMERIC -> this.addEnvaliNumericParameter(annotation, entityEnvaliDefinition,
                            envaliMeta.getEnvaliNumericMeta());
                    case REGEX -> this.addEnvaliRegexParameter(annotation, entityEnvaliDefinition,
                            envaliMeta.getEnvaliRegexMeta());
                }
            }

            field.add(annotation);
        });
    }

    private Annotation createEnvaliAnnotation(@NonNull EntityEnvaliDefinition entityEnvaliDefinition) {

        final ResourceFactory factory = EntityResourceFactory.getInstance();

        final Annotation annotation = factory.createAnnotation(entityEnvaliDefinition.getEnvaliAnnotation().getTag());
        final EnvaliErrorType envaliErrorType = entityEnvaliDefinition.getEnvaliErrorType();
        final String message = entityEnvaliDefinition.getMessage();

        if (envaliErrorType != EnvaliErrorType.RUNTIME) {
            final EnvaliErrorTypeName envaliErrorTypeName = ContentInvoker
                    .of(EnvaliErrorTypeNameLoader.of(envaliErrorType)).invoke();
            annotation.add(factory.createAnnotationParameter("errorType").put(ParameterDataType.DEFAULT,
                    envaliErrorTypeName.getErrorTypeName()));
        }

        if (!StringUtils.isEmpty(message)) {
            annotation.add(factory.createAnnotationParameter("message").put(ParameterDataType.STRING,
                    entityEnvaliDefinition.getMessage()));
        }

        return annotation;
    }

    private void addEnvaliLiteralParameter(@NonNull Annotation annotation,
            @NonNull EntityEnvaliDefinition entityEnvaliDefinition, @NonNull EnvaliLiteralMeta envaliLiteralMeta) {

        final ResourceFactory factory = EntityResourceFactory.getInstance();

        final String prefix = envaliLiteralMeta.getPrefix();
        final String suffix = envaliLiteralMeta.getSuffix();

        if (!StringUtils.isEmpty(prefix) && !StringUtils.isEmpty(suffix)) {
            annotation.add(factory.createAnnotationParameter("prefix").put(ParameterDataType.STRING, prefix))
                    .add(factory.createAnnotationParameter("suffix").put(ParameterDataType.STRING, suffix));
        } else if (!StringUtils.isEmpty(prefix)) {
            annotation.add(factory.createAnnotationParameter("prefix").put(ParameterDataType.STRING, prefix));
        } else if (!StringUtils.isEmpty(suffix)) {
            annotation.add(factory.createAnnotationParameter("suffix").put(ParameterDataType.STRING, suffix));
        } else {
            throw new IllegalStateException(
                    "Detected the literal meta object for Envali, but no parameters have been set.");
        }
    }

    private void addEnvaliNumericParameter(@NonNull Annotation annotation,
            @NonNull EntityEnvaliDefinition entityEnvaliDefinition, @NonNull EnvaliNumericMeta envaliNumericMeta) {

        final ResourceFactory factory = EntityResourceFactory.getInstance();

        switch (envaliNumericMeta.getEnvaliNumericDataType()) {
            case INT -> {
                switch (envaliNumericMeta.getEnvaliNumericRangeType()) {
                    case FROM -> annotation.add(factory.createAnnotationParameter("intFrom")
                            .put(ParameterDataType.DEFAULT, envaliNumericMeta.getIntFrom()));
                    case TO -> annotation.add(factory.createAnnotationParameter("intTo").put(ParameterDataType.DEFAULT,
                            envaliNumericMeta.getIntTo()));
                    case FROM_TO -> annotation
                            .add(factory.createAnnotationParameter("intFrom").put(ParameterDataType.DEFAULT,
                                    envaliNumericMeta.getIntFrom()))
                            .add(factory.createAnnotationParameter("intTo").put(ParameterDataType.DEFAULT,
                                    envaliNumericMeta.getIntTo()));
                }
            }

            case LONG -> {
                switch (envaliNumericMeta.getEnvaliNumericRangeType()) {
                    case FROM -> annotation.add(factory.createAnnotationParameter("longFrom")
                            .put(ParameterDataType.DEFAULT, envaliNumericMeta.getLongFrom()));
                    case TO -> annotation.add(factory.createAnnotationParameter("longTo").put(ParameterDataType.DEFAULT,
                            envaliNumericMeta.getLongTo()));
                    case FROM_TO -> annotation
                            .add(factory.createAnnotationParameter("longFrom").put(ParameterDataType.DEFAULT,
                                    envaliNumericMeta.getLongFrom()))
                            .add(factory.createAnnotationParameter("longTo").put(ParameterDataType.DEFAULT,
                                    envaliNumericMeta.getLongTo()));
                }
            }

            case FLOAT -> {
                switch (envaliNumericMeta.getEnvaliNumericRangeType()) {
                    case FROM -> annotation.add(factory.createAnnotationParameter("floatFrom")
                            .put(ParameterDataType.DEFAULT, envaliNumericMeta.getFloatFrom()));
                    case TO -> annotation.add(factory.createAnnotationParameter("floatTo")
                            .put(ParameterDataType.DEFAULT, envaliNumericMeta.getFloatTo()));
                    case FROM_TO -> annotation
                            .add(factory.createAnnotationParameter("floatFrom").put(ParameterDataType.DEFAULT,
                                    envaliNumericMeta.getFloatFrom()))
                            .add(factory.createAnnotationParameter("floatTo").put(ParameterDataType.DEFAULT,
                                    envaliNumericMeta.getFloatTo()));
                }
            }

            case DOUBLE -> {
                switch (envaliNumericMeta.getEnvaliNumericRangeType()) {
                    case FROM -> annotation.add(factory.createAnnotationParameter("doubleFrom")
                            .put(ParameterDataType.DEFAULT, envaliNumericMeta.getDoubleFrom()));
                    case TO -> annotation.add(factory.createAnnotationParameter("doubleTo")
                            .put(ParameterDataType.DEFAULT, envaliNumericMeta.getDoubleTo()));
                    case FROM_TO -> annotation
                            .add(factory.createAnnotationParameter("doubleFrom").put(ParameterDataType.DEFAULT,
                                    envaliNumericMeta.getDoubleFrom()))
                            .add(factory.createAnnotationParameter("doubleTo").put(ParameterDataType.DEFAULT,
                                    envaliNumericMeta.getDoubleTo()));
                }
            }

            case SHORT -> {
                switch (envaliNumericMeta.getEnvaliNumericRangeType()) {
                    case FROM -> annotation.add(factory.createAnnotationParameter("shortFrom")
                            .put(ParameterDataType.DEFAULT, envaliNumericMeta.getShortFrom()));
                    case TO -> annotation.add(factory.createAnnotationParameter("shortTo")
                            .put(ParameterDataType.DEFAULT, envaliNumericMeta.getShortTo()));
                    case FROM_TO -> annotation
                            .add(factory.createAnnotationParameter("shortFrom").put(ParameterDataType.DEFAULT,
                                    envaliNumericMeta.getShortFrom()))
                            .add(factory.createAnnotationParameter("shortTo").put(ParameterDataType.DEFAULT,
                                    envaliNumericMeta.getShortTo()));
                }
            }

            case BYTE -> {
                switch (envaliNumericMeta.getEnvaliNumericRangeType()) {
                    case FROM -> annotation.add(factory.createAnnotationParameter("byteFrom")
                            .put(ParameterDataType.DEFAULT, envaliNumericMeta.getByteFrom()));
                    case TO -> annotation.add(factory.createAnnotationParameter("byteTo").put(ParameterDataType.DEFAULT,
                            envaliNumericMeta.getByteTo()));
                    case FROM_TO -> annotation
                            .add(factory.createAnnotationParameter("byteFrom").put(ParameterDataType.DEFAULT,
                                    envaliNumericMeta.getByteFrom()))
                            .add(factory.createAnnotationParameter("byteTo").put(ParameterDataType.DEFAULT,
                                    envaliNumericMeta.getByteTo()));
                }
            }
        }
    }

    private void addEnvaliRegexParameter(@NonNull Annotation annotation,
            @NonNull EntityEnvaliDefinition entityEnvaliDefinition, @NonNull EnvaliRegexMeta envaliRegexMeta) {

        final ResourceFactory factory = EntityResourceFactory.getInstance();
    }
}

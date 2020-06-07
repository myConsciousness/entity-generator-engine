/**
 * Project Name : Generator<br>
 * File Name : ClassNameDefinition.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/04/23<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.dtogenerator;

import java.util.Objects;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * クラス名の定義情報を管理するデータクラスです。 当該クラスはイミュータブルです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@Getter
@ToString
@EqualsAndHashCode
public final class ClassNameDefinition {

    /**
     * バージョン
     */
    @NonNull
    private String version = "";

    /**
     * プロジェクト名
     */
    @NonNull
    private String projectName = "";

    /**
     * パッケージ名
     */
    @NonNull
    private String packageName = "";

    /**
     * クラスの物理名
     */
    @NonNull
    private String physicalName = "";

    /**
     * クラスの論理名
     */
    @NonNull
    private String logicalName = "";

    /**
     * クラスの補足
     */
    @NonNull
    private String description = "";

    /**
     * デフォルトコンストラクタ
     */
    @SuppressWarnings("unused")
    private ClassNameDefinition() {
    }

    /**
     * コンストラクタ
     *
     * @param version      バージョン
     * @param projectName  プロジェクト名
     * @param packageName  パッケージ名
     * @param physicalName 物理名
     * @param logicalName  論理名
     * @param description  補足
     */
    public ClassNameDefinition(final String version, final String projectName, final String packageName,
            String physicalName, String logicalName, String description) {
        this.version = version;
        this.projectName = projectName;
        this.packageName = packageName;
        this.logicalName = logicalName;
        this.physicalName = physicalName;
        this.description = description;
    }

    /**
     * コピーコンストラクタ
     *
     * @param classNameDefinition クラス名定義情報
     */
    public ClassNameDefinition(@NonNull ClassNameDefinition classNameDefinition) {
        this.version = classNameDefinition.getVersion();
        this.projectName = classNameDefinition.getProjectName();
        this.packageName = classNameDefinition.getPackageName();
        this.logicalName = classNameDefinition.getLogicalName();
        this.physicalName = classNameDefinition.getPhysicalName();
        this.description = classNameDefinition.getDescription();
    }
}

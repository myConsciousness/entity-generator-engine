package org.thinkit.generator.entity.engine.factory;

import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.FormatterException;
import com.google.googlejavaformat.java.JavaFormatterOptions;
import com.google.googlejavaformat.java.JavaFormatterOptions.Style;

import org.thinkit.common.catalog.Indentation;
import org.thinkit.generator.common.duke.factory.ClassBody;
import org.thinkit.generator.common.duke.factory.Copyright;
import org.thinkit.generator.common.duke.factory.Package;
import org.thinkit.generator.common.duke.factory.Resource;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * エンティティクラスのリソースを生成する処理を定義したファクトリークラスです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class EntityResource extends Resource {

    /**
     * 改行コード
     */
    private static final String RETURN_CODE = Indentation.RETURN.getTag();

    /**
     * コンストラクタ
     *
     * @param copyright   著作権定義
     * @param packageName パッケージ定義
     * @param classBody   クラスボディ部
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private EntityResource(@NonNull Copyright copyright, @NonNull Package packageName, @NonNull ClassBody classBody) {
        super(copyright, packageName, classBody);
    }

    /**
     * 引数として渡された情報を基に {@link EntityResource} クラスの新しいインスタンスを生成し返却します。
     *
     * @param copyright   著作権定義
     * @param packageName パッケージ定義
     * @param classBody   クラスボディ部
     * @return {@link EntityResource} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    protected static Resource of(@NonNull Copyright copyright, @NonNull Package packageName,
            @NonNull ClassBody classBody) {
        return new EntityResource(copyright, packageName, classBody);
    }

    @Override
    public String createResource() {

        final StringBuilder resource = new StringBuilder();

        this.createCopyright(resource);
        this.createPackage(resource);
        this.createDependentPackage(resource);
        this.createClassBody(resource);

        return this.format(resource);
    }

    /**
     * 著作権を表現する文字列リソースを生成しカタログリソースへ追加します。
     *
     * @param resource カタログリソース
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private void createCopyright(@NonNull StringBuilder resource) {
        resource.append(super.getCopyright().createResource());
        resource.append(RETURN_CODE);
    }

    /**
     * パッケージを表現する文字列リソースを生成しカタログリソースへ追加します。
     *
     * @param resource カタログリソース
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private void createPackage(@NonNull StringBuilder resource) {
        resource.append(super.getPackageName().createResource());
        resource.append(RETURN_CODE).append(RETURN_CODE);
    }

    /**
     * 依存パッケージを表現する文字列リソースを生成しカタログリソースへ追加します。
     *
     * @param resource カタログリソース
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private void createDependentPackage(@NonNull StringBuilder resource) {
        super.getDependentPackages().forEach(dependentPackage -> {
            resource.append(dependentPackage.createResource());
            resource.append(RETURN_CODE);
        });

        resource.append(RETURN_CODE);
    }

    /**
     * クラスボディを表現する文字列リソースを生成しカタログリソースへ追加します。
     *
     * @param resource カタログリソース
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private void createClassBody(@NonNull StringBuilder resource) {
        resource.append(super.getClassBody().createResource());
    }

    /**
     * 引数として渡されたカタログリソースを整形した結果を返却します。
     * <p>
     * 生成されたカタログリソースに文法エラーが存在する場合は実行時に {@link IllegalStateException} が発生します。
     *
     * @param resource カタログリソース
     * @return 整形されたカタログリソース
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private String format(@NonNull StringBuilder resource) {
        try {
            return new Formatter(JavaFormatterOptions.builder().style(Style.AOSP).build())
                    .formatSource(resource.toString());
        } catch (FormatterException e) {
            throw new IllegalStateException(e);
        }
    }
}

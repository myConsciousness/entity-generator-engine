/**
 * エンティティ定義クラスに格納されたリソース情報を基にエンティティリソースを生成する処理を定義したクラスを管理するパッケージです。
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
 */
package org.thinkit.generator.entity.engine.formatter;

/**
 * Project Name : Business Tool<br>
 * File Name : Main.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/05/16<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */
package org.thinkit.generator;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.thinkit.common.catalog.Catalog;
import org.thinkit.generator.catalog.GeneratorDivision;
import org.thinkit.generator.rule.StartGeneratorManager;

import com.google.common.flogger.FluentLogger;

/**
 * Generatorプロジェクトのエントリーポイントクラスです。<br>
 * コマンドライン引数として渡された各データから業務に応じた生成器を判断して起動します。<br>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public final class Main {

    /**
     * ログ出力オブジェクト
     */
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    /**
     * Generatorプロジェクトで使用する各生成器をコマンドライン引数から判定し起動します。<br>
     * <br>
     * 各生成器が処理を開始するために以下のコマンドライン引数が必要になります。<br>
     * 1. ファイルパス ・・・ 各生成器が解析する対象の情報が記述されたファイルへのパスです。<br>
     * 2. 生成器区分 ・・・ 起動する対象の生成器区分です。<br>
     * <br>
     * 各生成器の実行に必要なコマンドライン引数が渡されなかった場合は当メイン処理の実行時に必ず失敗します。
     *
     * @param args コマンドライン引数
     * @exception IllegalArgumentException 各生成器の実行に必要な引数が渡されなかった場合
     */
    public static void main(String[] args) {
        logger.atInfo().log("START");

        if (args.length < 2) {
            logger.atSevere().log("It is necessary to pass command line arguments in order to execute the process.");
            throw new IllegalArgumentException(String.format(
                            "wrong parameter was given. 2 parameter was expected but %s parameters were given.",
                            args.length));
        }

        final String filePath = args[0];
        final int generatorCategoryCode = Integer.parseInt(args[1]);

        if (!Catalog.hasCode(GeneratorDivision.class, generatorCategoryCode)) {
            logger.atSevere().log("An incorrect number was passed as a code value for GeneratorCategory.");
            throw new IllegalArgumentException(
                            "The code values passed as command line arguments are not defined in GeneratorCategory.");
        }

        final GeneratorDivision category = Catalog.getEnum(GeneratorDivision.class, generatorCategoryCode);

        logger.atInfo().log("The file path passed as command line argument = (%s)", filePath);
        logger.atInfo().log("The generator category passed as command line argument = (%s)", category);

        final StartGeneratorManager startGeneratorManager = new StartGeneratorManager(category);
        if (!startGeneratorManager.execute()) {
            logger.atSevere().log("The process of retrieving the startup package name was terminated abnormally.");
            return;
        }

        try {
            @SuppressWarnings("unchecked")
            final Class<? extends Generator> generatorClass
                            = (Class<? extends Generator>) Class.forName(startGeneratorManager.getPackageName());

            final Constructor<? extends Generator> constructor = generatorClass.getDeclaredConstructor(String.class);

            final Generator generator = constructor.newInstance(filePath);

            if (!generator.execute()) {
                logger.atSevere()
                                .log("An unexpected error has occurred. Please confirm the information you have entered.");
                return;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        logger.atInfo().log("END");
    }
}

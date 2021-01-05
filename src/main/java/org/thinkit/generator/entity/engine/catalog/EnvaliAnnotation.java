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

package org.thinkit.generator.entity.engine.catalog;

import org.thinkit.api.catalog.BiCatalog;
import org.thinkit.framework.envali.annotation.NestedEntity;
import org.thinkit.framework.envali.annotation.RequireEndWith;
import org.thinkit.framework.envali.annotation.RequireMatch;
import org.thinkit.framework.envali.annotation.RequireNegative;
import org.thinkit.framework.envali.annotation.RequireNonBlank;
import org.thinkit.framework.envali.annotation.RequireNonEmpty;
import org.thinkit.framework.envali.annotation.RequireNonNull;
import org.thinkit.framework.envali.annotation.RequirePositive;
import org.thinkit.framework.envali.annotation.RequireRangeFrom;
import org.thinkit.framework.envali.annotation.RequireRangeFromTo;
import org.thinkit.framework.envali.annotation.RequireRangeTo;
import org.thinkit.framework.envali.annotation.RequireStartWith;
import org.thinkit.generator.common.duke.catalog.AnnotationPattern;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Envaliフレームワークのアノテーションを管理するカタログです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
@RequiredArgsConstructor
public enum EnvaliAnnotation implements BiCatalog<EnvaliAnnotation, AnnotationPattern> {

    /**
     * The pattern of {@link RequireNonNull}
     */
    REQUIRE_NON_NULL(0, AnnotationPattern.ENVALI_REQUIRE_NON_NULL),

    /**
     * The pattern of {@link RequireNonBlank}
     */
    REQUIRE_NON_BLANK(1, AnnotationPattern.ENVALI_REQUIRE_NON_BLANK),

    /**
     * The pattern of {@link RequirePositive}
     */
    REQUIRE_POSITIVE(2, AnnotationPattern.ENVALI_REQUIRE_POSITIVE),

    /**
     * The pattern of {@link RequireNegative}
     */
    REQUIRE_NEGATIVE(3, AnnotationPattern.ENVALI_REQUIRE_NEGATIVE),

    /**
     * The pattern of {@link RequireRangeFrom}
     */
    REQUIRE_RANGE_FROM(4, AnnotationPattern.ENVALI_REQUIRE_RANGE_FROM),

    /**
     * The pattern of {@link RequireRangeTo}
     */
    REQUIRE_RANGE_TO(5, AnnotationPattern.ENVALI_REQUIRE_RANGE_TO),

    /**
     * The pattern of {@link RequireRangeFromTo}
     */
    REQUIRE_RANGE_FROM_TO(6, AnnotationPattern.ENVALI_REQUIRE_RANGE_FROM_TO),

    /**
     * The pattern of {@link RequireStartWith}
     */
    REQUIRE_START_WITH(7, AnnotationPattern.ENVALI_REQUIRE_START_WITH),

    /**
     * The pattern of {@link RequireEndWith}
     */
    REQUIRE_END_WITH(8, AnnotationPattern.ENVALI_REQUIRE_END_WITH),

    /**
     * The pattern of {@link RequireNonEmpty}
     */
    REQUIRE_NON_EMPTY(9, AnnotationPattern.ENVALI_REQUIRE_NON_EMPTY),

    /**
     * The pattern of {@link NestedEntity}
     */
    NESTED_ENTITY(10, AnnotationPattern.ENVALI_NESTED_ENTITY),

    /**
     * The pattern of {@link RequireMatch}
     */
    REQUIRE_MATCH(11, AnnotationPattern.ENVALI_REQUIRE_MATCH);

    /**
     * コード値
     */
    @Getter
    private final int code;

    /**
     * タグ
     */
    @Getter
    private final AnnotationPattern tag;
}

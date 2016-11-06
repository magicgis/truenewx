package org.truenewx.core.region;

import java.util.Locale;

import javax.annotation.Nullable;

/**
 * 具体国家的行政区划选项来源
 *
 * @author jianglei
 * @since JDK 1.8
 */
public interface NationalRegionOptionSource {
    /**
     * 获取国家代号
     *
     * @return 国家代号
     */
    String getNation();

    /**
     * 获取当前国家的区划选项，其显示名为指定显示区域下的文本
     *
     * @param locale
     *            显示区域
     *
     * @return 当前国家的区划选项
     */
    RegionOption getNationalRegionOption(@Nullable
    Locale locale);

    /**
     * 获取指定行政区划代号对应的行政区划选项
     *
     * @param code
     *            行政区划代号
     * @param locale
     *            显示区域
     *
     * @return 行政区划选项
     */
    @Nullable
    RegionOption getSubRegionOption(String code, @Nullable
    Locale locale);

    /**
     * 获取指定行政区划名称对应的行政区划选项
     *
     * @param provinceCaption
     *            省份名称
     * @param cityCaption
     *            市名称
     * @param countyCaption
     *            县名称
     * @param locale
     *            显示区域
     * @return 行政区划选项
     */
    @Nullable
    RegionOption getSubRegionOption(String provinceCaption, @Nullable
    String cityCaption, @Nullable
    String countyCaption, @Nullable
    Locale locale);
}

package org.truenewx.core.spring.beans.factory;

import org.springframework.beans.factory.FactoryBean;
import org.truenewx.core.util.StringUtil;

/**
 * 格式化的随机字符串工厂Bean
 * 
 * @author jianglei
 * @since JDK 1.8
 */
public class FormattedRandomStringFactoryBean implements FactoryBean<String> {
    /**
     * 格式
     */
    private String format;
    /**
     * 用于格式化的随机字符串长度
     */
    private int length;
    /**
     * 大小写转换方向
     */
    private String toCase;

    public void setFormat(final String format) {
        this.format = format;
    }

    public void setLength(final int length) {
        this.length = length;
    }

    /**
     * @param toCase
     *            大小写转换方向，支持：upper和lower
     */
    public void setToCase(final String toCase) {
        this.toCase = toCase;
    }

    @Override
    public String getObject() throws Exception {
        String s = StringUtil.randomMixeds(this.length, null);
        if ("upper".equals(this.toCase)) {
            s = s.toUpperCase();
        } else if ("lower".equals(this.toCase)) {
            s = s.toLowerCase();
        }
        return String.format(this.format, s);
    }

    @Override
    public Class<?> getObjectType() {
        return String.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}

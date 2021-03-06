package org.truenewx.core.spring.beans.factory.config;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.springframework.util.PropertyPlaceholderHelper;
import org.truenewx.core.spring.util.PlaceholderResolver;

/**
 * 可对外提供转换占位符字符串功能的属性占位符配置器
 *
 * @author jianglei
 * @since JDK 1.8
 */
public class PropertyPlaceholderConfigurer
        extends org.springframework.beans.factory.config.PropertyPlaceholderConfigurer
        implements PlaceholderResolver {
    private Properties props;
    private PropertyPlaceholderHelper helper;
    private Object propertiesProvider;

    public void setPropertiesProvider(final Object propertiesProvider) {
        this.propertiesProvider = propertiesProvider;
    }

    @Override
    protected void loadProperties(final Properties props) throws IOException {
        if (this.propertiesProvider != null) { // 首先尝试加载属性集提供者提供的属性集
            Object result = null;
            try {
                // 属性集提供者必须具有getProperties()方法
                final Method method = this.propertiesProvider.getClass().getMethod("getProperties");
                result = method.invoke(this.propertiesProvider);
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException
                    | IllegalArgumentException | InvocationTargetException e) {
                // 忽略所有可预期异常
            }
            if (result instanceof Map) { // getProperties()方法必须访问Map结果
                @SuppressWarnings("unchecked")
                final Map<Object, Object> properties = (Map<Object, Object>) result;
                for (final Entry<Object, Object> entry : properties.entrySet()) {
                    final Object value = entry.getValue();
                    if (value != null) {
                        props.put(entry.getKey().toString(), value.toString());
                    }
                }
            }
        }
        super.loadProperties(props);
        this.props = props;
    }

    @Override
    public String resolveStringValue(String strVal) {
        if (strVal == null) {
            return null;
        }
        if (strVal.indexOf(this.placeholderPrefix) >= 0
                && strVal.indexOf(this.placeholderSuffix) >= 0) {
            if (this.helper == null) {
                this.helper = new PropertyPlaceholderHelper(this.placeholderPrefix,
                        this.placeholderSuffix, this.valueSeparator,
                        this.ignoreUnresolvablePlaceholders);
            }
            strVal = this.helper.replacePlaceholders(strVal, this);
        }
        return (strVal.equals(this.nullValue) ? null : strVal);
    }

    @Override
    public String resolvePlaceholder(final String placeholderKey) {
        final String value = this.props.getProperty(placeholderKey);
        return resolveStringValue(value);
    }

    @Override
    public Iterable<String> getPlaceholderKeys() {
        final List<String> placeholders = new ArrayList<>(); // Properties的keys已经是Set，key不会重复，故此处用List以提高性能
        for (final Object key : this.props.keySet()) {
            if (key instanceof String) {
                placeholders.add((String) key);
            }
        }
        return placeholders;
    }

}

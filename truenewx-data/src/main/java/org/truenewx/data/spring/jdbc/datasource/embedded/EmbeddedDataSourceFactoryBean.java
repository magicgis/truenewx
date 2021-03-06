package org.truenewx.data.spring.jdbc.datasource.embedded;

import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactoryBean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

/**
 * 内嵌数据源工厂Bean
 *
 * @author jianglei
 * @since JDK 1.8
 */
public class EmbeddedDataSourceFactoryBean extends EmbeddedDatabaseFactoryBean {

    /**
     * @param type
     *            字符串型数据库类型
     */
    public void setType(final String type) {
        setDatabaseType(EmbeddedDatabaseType.valueOf(type.toUpperCase()));
    }

    /**
     * @param scripts
     *            初始化脚本
     */
    public void setScripts(final Resource[] scripts) {
        setDatabasePopulator(new ResourceDatabasePopulator(scripts));
    }

}

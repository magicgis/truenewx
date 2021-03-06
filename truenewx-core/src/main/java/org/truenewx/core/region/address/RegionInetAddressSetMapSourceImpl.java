package org.truenewx.core.region.address;

import java.io.InputStream;
import java.util.Locale;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.truenewx.core.Strings;
import org.truenewx.core.net.InetAddressSet;

/**
 * 抽象的区划-网络地址集合 来源实现
 *
 * @author jianglei
 * @since JDK 1.8
 */
public class RegionInetAddressSetMapSourceImpl
        implements RegionInetAddressSetMapSource, InitializingBean {
    private Resource location;
    private Locale locale = Locale.getDefault();
    private String encoding = Strings.DEFAULT_ENCODING;
    private boolean init;
    private RegionInetAddressSetMapParser parser;
    private Map<String, InetAddressSet> map;

    public void setLocation(final Resource location) {
        this.location = location;
    }

    public void setLocale(final Locale locale) {
        this.locale = locale;
    }

    public void setEncoding(final String encoding) {
        this.encoding = encoding;
    }

    public void setParser(final RegionInetAddressSetMapParser parser) {
        this.parser = parser;
    }

    public void setInit(final boolean init) {
        this.init = init;
    }

    @Override
    public Map<String, InetAddressSet> getMap() {
        if (this.map == null) {
            init();
        }
        return this.map;
    }

    private void init() {
        if (this.location != null) { // 不检查文件是否存在，不存在时抛出异常以便于定位错误
            try {
                // 初始化时解析并缓存
                final InputStream in = this.location.getInputStream();
                this.map = this.parser.parse(in, this.locale, this.encoding);
                in.close();
            } catch (final Exception e) {
                LoggerFactory.getLogger(getClass()).error(e.getMessage(), e);
            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (this.init) {
            init();
        }
    }
}

package org.truenewx.web.tag;

import java.io.IOException;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.truenewx.core.Strings;
import org.truenewx.web.tagext.ItemTagSupport;

/**
 * 复选框标签
 *
 * @author jianglei
 * @since JDK 1.8
 */
public class CheckBoxTag extends ItemTagSupport {

    public void setValue(final String[] value) {
        super.setValue(value);
    }

    @Override
    protected boolean isCurrentValue(final String value) {
        final boolean result = super.isCurrentValue(value);
        if (!result) {
            String[] values;
            if (this.value instanceof String) {
                values = ((String) this.value).split(Strings.COMMA);
            } else if (this.value instanceof String[]) {
                values = (String[]) this.value;
            } else {
                return false;
            }
            return ArrayUtils.contains(values, value);
        }
        return result;
    }

    @Override
    protected void resolveItem(final String value, final String text) throws IOException {
        print("<input type=\"checkbox\"");
        final String id = getId();
        if (StringUtils.isNotBlank(id)) {
            print(Strings.SPACE, "id=\"", id, Strings.UNDERLINE, value, "\"");
        }
        print(Strings.SPACE, "value=\"", value, "\"");
        print(joinAttributes("id", "value"));
        if (isCurrentValue(value)) {
            print(" checked=\"checked\"");
        }
        print("/> ", text, Strings.ENTER);
    }

}

package org.truenewx.web.rpc.server.meta;

import org.apache.commons.lang3.StringUtils;
import org.truenewx.core.Strings;

/**
 * 重名RPC方法异常
 *
 * @author jianglei
 * @since JDK 1.8
 */
public class DuplicatedRpcMethodException extends ReflectiveOperationException {

    private static final long serialVersionUID = -8714547397267622760L;

    public DuplicatedRpcMethodException(final Class<?> clazz, final String methodName,
                    final Integer argCount) {
        super(StringUtils.join(clazz.getSimpleName(), Strings.DOT, methodName, "(",
                        getArgExpression(argCount), ") method, maybe wrong number of arguments"));
    }

    private static String getArgExpression(final Integer argCount) {
        final StringBuffer result = new StringBuffer();
        if (argCount != null) {
            for (int i = 0; i < argCount; i++) {
                result.append(Strings.ASTERISK).append(Strings.COMMA).append(Strings.SPACE);
            }
            if (result.length() > 0) {
                result.delete(result.length() - 2, result.length());
            }
        }
        return result.toString();
    }

}

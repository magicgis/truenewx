package org.truenewx.data.validation.rule.builder;

import java.lang.annotation.Annotation;

import org.springframework.stereotype.Component;
import org.truenewx.data.validation.constraint.TagLimit;
import org.truenewx.data.validation.rule.TagLimitRule;

/**
 * 标签限定规则构建器
 *
 * @author jianglei
 * @since JDK 1.8
 */
@Component
public class TagLimitRuleBuilder implements ValidationRuleBuilder<TagLimitRule> {

    @Override
    public Class<?>[] getConstraintTypes() {
        return new Class<?>[] { TagLimit.class };
    }

    @Override
    public void update(final Annotation annotation, final TagLimitRule rule) {
        if (annotation instanceof TagLimit) {
            final TagLimit tagLimit = (TagLimit) annotation;
            rule.addAllowed(tagLimit.allowed());
            rule.addForbidden(tagLimit.forbidden());
        }
    }

    @Override
    public TagLimitRule create(final Annotation annotation) {
        if (annotation instanceof TagLimit) {
            final TagLimit tagLimit = (TagLimit) annotation;
            final TagLimitRule rule = new TagLimitRule();
            rule.addAllowed(tagLimit.allowed());
            rule.addForbidden(tagLimit.forbidden());
            return rule;
        }
        return null;
    }

}

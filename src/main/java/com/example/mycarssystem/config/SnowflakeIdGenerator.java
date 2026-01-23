package com.example.mycarssystem.config;

import cn.hutool.core.util.IdUtil;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.generator.BeforeExecutionGenerator;
import org.hibernate.generator.EventType;
import org.hibernate.generator.EventTypeSets;

import java.util.EnumSet;

public class SnowflakeIdGenerator implements BeforeExecutionGenerator {
    @Override
    public Object generate(SharedSessionContractImplementor session, Object owner, Object value, EventType eventType) {
        // 同样使用 Hutool 生成 ID
        return IdUtil.getSnowflake(1, 1).nextId();
    }

    @Override
    public EnumSet<EventType> getEventTypes() {
        // 指定在插入（INSERT）时生成
        return EventTypeSets.INSERT_ONLY;
    }
}

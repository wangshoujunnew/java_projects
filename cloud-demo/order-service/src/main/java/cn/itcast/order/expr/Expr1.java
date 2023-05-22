package cn.itcast.order.expr;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;



@RefreshScope // 实时刷新配置信息
@Component
@Data
public class Expr1 {
    @Value(value = "${switchFlag:}")
    private Boolean switchFlag;

    @Value(value = "${distanceScala:}")
    private Double distanceScala;

    @Value(value = "${person.name:}")
    private String userName;
}

package rpc.test.order.spring;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import rpc.client.RpcClient;
import rpc.test.order.iface.OrderInfoInterface;


@Configuration
@Lazy
@Order(Ordered.LOWEST_PRECEDENCE)
public class OrderServiceProxyAutoConfig {
    private static final String SERVICE = "cmc.lucky.test";

    /**
     * 订单服务
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public OrderInfoInterface orderServiceProxy() {
        return RpcClient.get(SERVICE, OrderInfoInterface.class);
    }

}

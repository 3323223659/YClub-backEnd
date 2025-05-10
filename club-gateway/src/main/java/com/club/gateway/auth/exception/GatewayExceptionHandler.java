package com.club.gateway.auth.exception;
import cn.dev33.satoken.exception.SaTokenException;
import com.club.gateway.auth.entity.Result;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: yang
 * @Date: 2025/05/09/21:13
 * @Description: 全局网关异常处理器
 * 这个类实现了Spring WebFlux的ErrorWebExceptionHandler接口，
 * 用于捕获和处理在网关层面发生的所有异常，返回统一的错误响应格式。
 * 主要功能：
 * 1. 捕获各种类型的异常
 * 2. 根据异常类型设置合适的HTTP状态码
 * 3. 返回标准化的JSON错误响应
 * 4. 保证响应格式的一致性
 */
@Component
public class GatewayExceptionHandler implements ErrorWebExceptionHandler {

    // Jackson库的ObjectMapper，用于对象与JSON之间的转换,线程安全，可以重复使用
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) {
        // 从交换上下文中获取请求和响应对象,这里的请求和响应是响应式(Reactive)的ServerHttpRequest/Response
        ServerHttpRequest request = serverWebExchange.getRequest();
        ServerHttpResponse response = serverWebExchange.getResponse();
        Integer code = 200;
        // 这里使用instanceof判断异常的具体类型
        if (throwable instanceof SaTokenException) {
            //如果是Sa-Token认证/授权异常
            code = 401;
        } else {
            code = 500;
        }
        Result result = Result.fail(code);
        // 设置响应头 这样前端就知道返回的是JSON格式数据
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        // 构建并返回响应使用响应式编程的方式构建响应
        return response.writeWith(Mono.fromSupplier(() -> {
            // 获取数据缓冲区工厂用于创建数据缓冲区(DataBuffer)
            DataBufferFactory dataBufferFactory = response.bufferFactory();
            // 初始化字节数组，用于存储JSON数据
            byte[] bytes = null;
            try {
                // 将Result对象序列化为JSON字节数组使用ObjectMapper进行序列化
                bytes = objectMapper.writeValueAsBytes(result);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            // 将字节数组包装为数据缓冲区并返回WebFlux要求的响应格式
            return dataBufferFactory.wrap(bytes);
        }));
    }
}


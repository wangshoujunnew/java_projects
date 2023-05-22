package cn.itcast.order.utils;
//限流
// 令牌桶限流方法
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Limiter {
    private int capacity; // 桶容量
    private long rate; // 放令牌速率
    private AtomicInteger tokens; // 当前令牌数量
    private long lastRefillTimestamp; // 上次放令牌时间戳

    public Limiter(int capacity, long rate) {
        this.capacity = capacity;
        this.rate = rate;
        this.tokens = new AtomicInteger(capacity); // 初始化为满令牌桶
        this.lastRefillTimestamp = System.nanoTime(); // 初始化上次放令牌时间为当前时间
    }

    // 尝试获取令牌，获取成功返回 true，否则返回 false
    public synchronized boolean tryAcquire() {
        refill(); // 先补充令牌
        if (tokens.get() > 0) { // 如果当前有令牌，则减少令牌数并返回 true
            tokens.decrementAndGet();
            return true;
        }
        return false; // 否则返回 false
    }

    // 补充令牌
    private void refill() {
        long timeElapsed = System.nanoTime() - lastRefillTimestamp; // 计算距上次放令牌经过的时间
        long newTokens = timeElapsed * rate / TimeUnit.SECONDS.toNanos(1); // 计算应该新增的令牌数
        if (newTokens > 0) {
            tokens.set(Math.min(capacity, tokens.get() + (int) newTokens)); // 如果新增的令牌数大于 0，将新增后的令牌数设置为当前令牌数量
            lastRefillTimestamp = System.nanoTime(); // 更新上次放令牌时间戳
        }
    }
}


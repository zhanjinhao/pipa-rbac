package cn.addenda.piparbac.config;

import cn.addenda.businesseasy.lock.DistributeLockService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RedissonLockService implements DistributeLockService {

    private final Map<String, RLock> redissonLockMap = new ConcurrentHashMap<>();

    private final RedissonClient redissonClient;

    public RedissonLockService(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public boolean tryLock(String lockName) {
        return redissonLockMap.computeIfAbsent(lockName, s -> redissonClient.getLock(lockName)).tryLock();
    }

    @Override
    public void unlock(String lockName) {
        redissonLockMap.computeIfAbsent(lockName, s -> redissonClient.getLock(lockName)).unlock();
    }

    @Override
    public void forceUnlock(String lockName) {
        redissonLockMap.computeIfAbsent(lockName, s -> redissonClient.getLock(lockName)).forceUnlock();
    }
}

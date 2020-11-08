package com.grow.self.partaker;

import com.grow.common.exception.BaseRuntimeException;
import com.grow.common.result.ResponseResultUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/10/26 10:53
 */
@Component
public class PartakerBuild {

    private static final String PREFIX_PB = "PB";

    private static final String PREFIX_P = "P";

    private final RedisTemplate<String, Object> redisTemplate;

    public PartakerBuild(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /*发起*/
    public void sponsor(final String userId) {
        checkPartaker(userId);

        final String partakerNo = PartakerPartyGenContext.generatePartakerPartyNo(PREFIX_PB);
        final PartakerParty partakerParty = new PartakerParty(
                userId,
                partakerNo,
                PartakerPartyGenContext.generatePartakerPartyNo(PREFIX_P),
                "",
                new ArrayList<>()
        );

        final ConcurrentMap<Integer, Map<Integer, PartakerParty>> index = new ConcurrentHashMap<>();
        final Map<Integer, PartakerParty> item = new HashMap<>();
        item.put(0, partakerParty);
        index.put(0, item);

        redisTemplate.opsForSet().add(partakerNo, index);
    }

    /*加入*/
    public synchronized void join(final String userId, final String partakerNo/*那个*/, final String partyNo/*具体那个*/) {
        checkPartaker(userId);

        final ConcurrentMap<Integer, Map<Integer, PartakerParty>> index = getIndex(partakerNo);

        final String partakerPartyNo = PartakerPartyGenContext.generatePartakerPartyNo(PREFIX_P);

        final List<PartakerPartyJoin> indexList = index
                .keySet()
                .stream()
                .map(x -> {
                    final Map<Integer, PartakerParty> partakerPartyMap = index.get(x);

                    /*找出所选择的PartakerParty的index*/
                    final Set<Integer> partakerPartySet = getPositionJoin(partakerPartyMap, partakerPartyNo, partyNo);

                    /*返回PartakerPartyJoin对象*/
                    if (!CollectionUtils.isEmpty(partakerPartySet)) {
                        return new PartakerPartyJoin(
                                x,
                                partakerPartyMap.get(
                                        partakerPartySet
                                                .stream()
                                                .max(Integer::compareTo)
                                                .orElse(0)/*可抛异常*/
                                )
                        );
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        indexList.forEach(x/*index*/ -> {
            final Integer x1 = x.getIndex() + 1;
            final PartakerParty partakerParty = new PartakerParty(
                    userId,
                    partakerNo,
                    partakerPartyNo,
                    x.getPartakerParty().getPartyNo(),
                    new ArrayList<>()
            );

            if (index.containsKey(x1)) {
                Map<Integer, PartakerParty> partakerPartyMap = index.get(x1);
                Integer max = partakerPartyMap.keySet().stream().max(Integer::compareTo).orElse(0);
                partakerPartyMap.put(max + 1, partakerParty);
            } else {
                Map<Integer, PartakerParty> partakerPartyMap = new HashMap<>();
                partakerPartyMap.put(0, partakerParty);
                index.put(x1, partakerPartyMap);
            }
        });

        redisTemplate.opsForSet().add(partakerNo, index);
    }

    /*移出*/
    public synchronized void move(final String partakerNo/*那个*/, final String partyNo/*具体那个*/) {
        final ConcurrentMap<Integer, Map<Integer, PartakerParty>> index = getIndex(partakerNo);

        index
                .keySet()
                .stream()
                .peek(x -> {
                    final Map<Integer, PartakerParty> partakerPartyMap = index.get(x);

                    /*找出所选择的PartakerParty的index*/
                    final Set<Integer> partakerPartySet = getPositionJoin(partakerPartyMap, null, partyNo);

                    if (!CollectionUtils.isEmpty(partakerPartySet)) {
                        Integer maxIndex = partakerPartySet
                                .stream()
                                .max(Integer::compareTo)
                                .orElse(0);


                    }



                })
                .collect(Collectors.toList());
    }

    private Set<Integer> getPositionJoin(final Map<Integer, PartakerParty> partakerPartyMap, final String partakerPartyNo, final String partyNo) {
        return partakerPartyMap
                .keySet()
                .stream()
                .filter(y -> {
                    final PartakerParty partakerParty = partakerPartyMap.get(y);

                    /*确定在那个下面,返回二级key*/
                    if (partakerParty.getPartyNo().equals(partyNo)) {
                        if (!StringUtils.isEmpty(partakerPartyNo)) {
                            partakerParty.getChildList().add(partakerPartyNo);
                            partakerPartyMap.put(y, partakerParty);
                        }
                        return true;
                    }
                    return false;

                })
                .collect(Collectors.toSet());
    }

    @SuppressWarnings("unchecked")
    private ConcurrentMap<Integer, Map<Integer, PartakerParty>> getIndex(final String partakerNo) {
        final ConcurrentMap<Integer, Map<Integer, PartakerParty>> index = (ConcurrentMap<Integer, Map<Integer, PartakerParty>>) redisTemplate
                .opsForSet()
                .members(partakerNo);
        if (CollectionUtils.isEmpty(index)) {
            throw new BaseRuntimeException(ResponseResultUtils.getResponseResultF("不存在"));
        }
        return index;
    }

    private void checkPartaker(final String userId) {
        /*入库检查是否存在该userId且status为0*/
    }
}

package com.grow.self.partaker;

import java.io.Serializable;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/10/26 17:03
 */
public class PartakerPartyJoin implements Serializable {

    /*一级key索引*/
    private final Integer index;

    /*所选择的PartakerParty对象*/
    private final PartakerParty partakerParty;

    public PartakerPartyJoin(Integer index, PartakerParty partakerParty) {
        this.index = index;
        this.partakerParty = partakerParty;
    }

    public Integer getIndex() {
        return index;
    }

    public PartakerParty getPartakerParty() {
        return partakerParty;
    }
}

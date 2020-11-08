package com.grow.self.partaker;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @author https://github.com/XiFYuW
 * @date 2020/10/26 10:38
 */
public final class PartakerParty implements Serializable {

    private final String userId;

    private final String partakerNo;

    private final String partyNo;

    private final String father;

    private final List<String> childList;

    public PartakerParty(
            String userId,
            String partakerNo,
            String partyNo,
            String father,
            List<String> childList
    ) {
        this.userId = userId;
        this.partakerNo = partakerNo;
        this.partyNo = partyNo;
        this.father = father;
        this.childList = childList;
    }

    public String getUserId() {
        return userId;
    }

    public String getPartakerNo() {
        return partakerNo;
    }

    public String getFather() {
        return father;
    }

    public List<String> getChildList() {
        return childList;
    }

    public String getPartyNo() {
        return partyNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PartakerParty)) return false;
        PartakerParty that = (PartakerParty) o;
        return getUserId().equals(that.getUserId()) &&
                getPartakerNo().equals(that.getPartakerNo()) &&
                getPartyNo().equals(that.getPartyNo()) &&
                getFather().equals(that.getFather()) &&
                getChildList().equals(that.getChildList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getPartakerNo(), getPartyNo(), getFather(), getChildList());
    }

    @Override
    public String toString() {
        return "{" + "\"userId\":\"" +
                userId + '\"' +
                ",\"partakerNo\":\"" +
                partakerNo + '\"' +
                ",\"partyNo\":\"" +
                partyNo + '\"' +
                ",\"father\":\"" +
                father + '\"' +
                ",\"childList\":" +
                childList +
                '}';
    }
}

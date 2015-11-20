package com.thinknowa.botin.sdk.model;

import java.util.Date;

/**
 * Created by ppedregal on 18/11/15.
 */
public class AccessToken {

    private String id;
    private String ttl;
    private Date created;
    private String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

	@Override
	public String toString() {
		return "AccessToken [id=" + id + ", ttl=" + ttl + ", created=" + created + ", userId=" + userId + "]";
	}

}

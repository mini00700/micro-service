package com.tcl.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tvsetting")
public class TvSetting implements Serializable{
	private static final long serialVersionUID = -7174928478446736960L;

	@Id
	@NotNull
    @Size(min = 1, max = 100)
    @Column(length = 255, unique = true, nullable = false)
    private String username;

    @Size(min = 1, max = 2000)
    @Column(length = 2000, unique = false, nullable = true)
    private String setting;


	@Size(min=1,max=20)
	@Column(name = "user_type", length = 20, unique = false, nullable = true)
    private String userType;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSetting() {
		return setting;
	}

	public void setSetting(String setting) {
		this.setting = setting;
	}


    public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TvSetting tvSetting = (TvSetting) o;
        if(tvSetting.username == null || username == null) {
            return false;
        }
        return Objects.equals(username, tvSetting.username);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(username);
    }

    @Override
    public String toString() {
        return "TvSetting{" +
            "username=" + username +
            ", usertype=" + userType +
            ", setting='" + setting + "'" +
            '}';
    }
}

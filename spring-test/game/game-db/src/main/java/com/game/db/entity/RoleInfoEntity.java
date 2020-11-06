package com.game.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.game.core.hibernate.orm.BaseEntity;

@Entity
@Table(name = "t_role_info")
public class RoleInfoEntity extends BaseEntity<Long> {

	/** 个性签名 */
	private String personSign = "";
	/** 最爱好友 */
	private long favouriteFriend = 0;
	/** 最爱英雄 */
	private int favouriteHero = 0;
	/** 最爱宠物 */
	private int favouritePet = 0;

	@Transient
	@Override
	public Long getId() {
		return uid;
	}

	@Id
	@Override
	public long getUid() {
		return uid;
	}

	@Override
	public void setId(Long id) {
		this.uid = id;
	}

	@Column(columnDefinition = "TEXT")
	public String getPersonSign() {
		return personSign;
	}

	@Column(columnDefinition = "bigint(20) default 0")
	public long getFavouriteFriend() {
		return favouriteFriend;
	}

	@Column(columnDefinition = " int default 0")
	public int getFavouriteHero() {
		return favouriteHero;
	}

	@Column(columnDefinition = " int default 0")
	public int getFavouritePet() {
		return favouritePet;
	}

	public void setPersonSign(String personSign) {
		this.personSign = personSign;
	}

	public void setFavouriteFriend(long favouriteFriend) {
		this.favouriteFriend = favouriteFriend;
	}

	public void setFavouriteHero(int favouriteHero) {
		this.favouriteHero = favouriteHero;
	}

	public void setFavouritePet(int favouritePet) {
		this.favouritePet = favouritePet;
	}

}

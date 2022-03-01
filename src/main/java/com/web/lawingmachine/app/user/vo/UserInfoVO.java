package com.web.lawingmachine.app.user.vo;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.web.lawingmachine.app.common.vo.BaseVO;

import lombok.Data;

@Data
public class UserInfoVO extends BaseVO implements UserDetails {

	private String userInfoSeq;
	private String userId;
	private String userNm;
	private String nickname;
	private String email;
	private String mobile;
	private int loginCnt;
	private String membershipCd;
	private String membershipNm;
	private String examTicket;
	private String examTicketPath;
	private String examTicketNo;
	private String examTicketYn;
	private String role;

	private String password;
	private int takeRev;

	// 사용자의 권한을 콜렉션 형태로 반환
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> roles = new HashSet<>();
		for (String auth : role.split(",")) {
			roles.add(new SimpleGrantedAuthority(auth));
		}
		return roles;
	}

	// 사용자의 id를 반환 (unique한 값)
	@Override
	public String getUsername() {
		return userId;
	}

	// 계정 만료 여부 반환
	@Override
	public boolean isAccountNonExpired() {
		// 만료되었는지 확인하는 로직
		return true;// true -> 만료되지 않았음
	}

	// 계정 잠금 여부 반환
	@Override
	public boolean isAccountNonLocked() {
		// 계정 잠금되었는지 확인하는 로직
		return true; // true -> 잠금되지 않았음
	}

	// 패스워드의 만료 여부 반환
	@Override
	public boolean isCredentialsNonExpired() {
		// 패스워드가 만료되었는지 확인하는 로직
		return true; // true -> 만료되지 않았음
	}

	// 계정 사용 가능 여부 반환
	@Override
	public boolean isEnabled() {
		// 계정이 사용 가능한지 확인하는 로직
		return true; // true -> 사용 가능
	}
}

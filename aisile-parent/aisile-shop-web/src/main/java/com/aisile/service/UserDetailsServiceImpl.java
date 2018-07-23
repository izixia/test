package com.aisile.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.aisile.pojo.TbSeller;
import com.aisile.sellergoods.service.SellerService;
/**
 * @author admin
 *
 */
public class UserDetailsServiceImpl implements UserDetailsService{

	private SellerService sellerService;


	public void setSellerService(SellerService sellerService) {
		this.sellerService = sellerService;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 构建角色列表
		List<GrantedAuthority> grantedAuths=new ArrayList<GrantedAuthority>();
		grantedAuths.add(new SimpleGrantedAuthority("ROLE_SELLER"));
		TbSeller seller=sellerService.findOne(username);
		if(seller!=null){
			//账号没问题,并且权限是已审核状态
			if(seller.getStatus().equals("1")){
				return new User(username, seller.getPassword(), grantedAuths);
			}else{
				return null;
			}
		}else{
			return null;
		}
	}


}

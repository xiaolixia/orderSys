/**
 *  Copyright 2015 ChinaSoft International Ltd. All rights reserved.
 */
package com.chinasofti.ordersys.service.admin;

import java.util.ArrayList;

import com.chinasofti.ordersys.vo.UserInfo;
import com.chinasofti.util.jdbc.template.JDBCTemplateWithDS;
import com.chinasofti.util.sec.Passport;

/**
 * <p>
 * Title: UserService
 * </p>
 * <p>
 * Description: 锟斤拷品锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: ChinaSoft International Ltd.
 * </p>
 * 
 * @author etc
 * @version 1.0
 */
public class UserService {


	
	/**
	 * 锟斤拷莶锟狡稩D值删锟斤拷锟狡凤拷锟较拷姆锟斤拷锟�
	 * 
	 * @param UserId
	 *            要删锟斤拷牟锟狡稩d
	 * */
	public void deleteUserById(Integer UserId) {
		// 锟斤拷取锟斤拷锟斤拷锟斤拷锟接池碉拷锟斤拷菘锟侥ｏ拷锟斤拷锟斤拷锟斤拷锟竭讹拷锟斤拷
		JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
		// 执锟斤拷删锟斤拷锟斤拷锟�
		helper.executePreparedUpdate("delete from userinfo where UserId=?",
				new Object[] { UserId });
		
	}

	/**
	 * 锟斤拷硬锟狡凤拷姆锟斤拷锟�
	 * 
	 * @param info
	 *            锟斤拷要锟斤拷拥牟锟狡凤拷锟较�
	 * */
	public void addUser(UserInfo info) {
		// 锟斤拷取锟斤拷锟斤拷锟斤拷锟接池碉拷锟斤拷菘锟侥ｏ拷锟斤拷锟斤拷锟斤拷锟竭讹拷锟斤拷
		JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
		// 执锟斤拷锟斤拷硬锟斤拷锟�
		helper.executePreparedUpdate(
				"insert into Userinfo(userAccount,userPass,role,locked,faceimg) values(?,?,?,?,?)",
				new Object[] {  info.getUserAccount(),
						info.getUserPass(), new Integer(info.getRoleId()),
						new Integer(info.getLocked()),
						info.getFaceimg() });
		
		
		System.out.println(info.getUserAccount()+" "+
				info.getUserPass()+" "+ new Integer(info.getRoleId())+" "+
				new Integer(info.getLocked())+ " "+
				info.getFaceimg() );

	}

	/**
	 * 锟斤拷锟絛ishesId锟斤拷取锟斤拷品锟斤拷细锟斤拷息锟侥凤拷锟斤拷
	 * 
	 * @param UserId
	 *            要锟斤拷取锟斤拷息锟斤拷锟截讹拷锟斤拷品Id
	 * @return 锟斤拷锟截革拷id锟侥诧拷品锟斤拷细锟斤拷息
	 * */
	public UserInfo getUserById(Integer UserId) {
		// 锟斤拷取锟斤拷锟斤拷锟斤拷锟接池碉拷锟斤拷菘锟侥ｏ拷锟斤拷锟斤拷锟斤拷锟竭讹拷锟斤拷
		JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
		// 锟斤拷询锟斤拷id锟侥诧拷品锟斤拷细锟斤拷息
		ArrayList<UserInfo> list = helper.preparedQueryForList(
				"select * from Userinfo where UserId=?",
				new Object[] { UserId }, UserInfo.class);
		// 锟斤拷锟截诧拷询锟斤拷锟�
		return list.get(0);
	}

	/**
	 * 锟睫改诧拷品锟斤拷息锟侥凤拷锟斤拷
	 * 
	 * @param Info
	 *            要锟睫改的诧拷品锟斤拷息锟斤拷锟斤拷锟斤拷UserId为锟睫革拷锟斤拷锟捷ｏ拷锟斤拷锟斤拷锟斤拷息为锟睫改碉拷目锟斤拷值
	 * */
	public void modifyUser(UserInfo info) {
		// 锟斤拷取锟斤拷锟斤拷锟斤拷锟接池碉拷锟斤拷菘锟侥ｏ拷锟斤拷锟斤拷锟斤拷锟竭讹拷锟斤拷
		JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
		// 执锟斤拷锟睫改诧拷锟斤拷
		System.out.println(info.getUserAccount()+info.getUserPass());
		helper.executePreparedUpdate(
				"update Userinfo set userAccount=?,userPass=?,locked=?,faceimg=? where userId=?",
				new Object[] { info.getUserAccount(),
						info.getUserPass(), 
						new Integer(info.getLocked()),
						info.getFaceimg(),  new Integer(info.getUserId())});
System.out.println("ui");
	}

	/**
	 * 锟斤拷取头4锟斤拷锟狡硷拷锟斤拷品锟斤拷锟斤拷息
	 * 
	 * @return 头4锟斤拷锟狡硷拷锟斤拷品锟叫憋拷
	 * */
	public ArrayList<UserInfo> getSomeRecommendUser(int page) {
		// 锟斤拷取锟斤拷锟斤拷锟斤拷锟接池碉拷锟斤拷菘锟侥ｏ拷锟斤拷锟斤拷锟斤拷锟竭讹拷锟斤拷
		JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
		// 锟斤拷询头4锟斤拷锟狡硷拷锟侥诧拷品锟斤拷息
		ArrayList<UserInfo> list = helper.preparedForPageList(
				"select * from userinfo order by userId",
				new Object[] {}, page, 4, UserInfo.class);

		// 锟斤拷锟截斤拷锟�
		//System.out.println(list.get(0).getUserAccount());
		return list;

	}
	public int getFinalPage() {
		// 锟斤拷取锟斤拷锟斤拷锟斤拷锟接池碉拷锟斤拷菘锟侥ｏ拷锟斤拷锟斤拷锟斤拷锟竭讹拷锟斤拷
		JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
		// 锟斤拷询头4锟斤拷锟狡硷拷锟侥诧拷品锟斤拷息
		ArrayList<UserInfo> list = helper.queryForList("select * from userinfo",UserInfo.class);
		// 锟斤拷锟截斤拷锟�
		//System.out.println(list.get(0).getUserAccount());
		int t=list.size();
		int page = (t/4)+(t%4==0?0:1);
		System.out.println(t+" "+page);
		return page;

	}
	
	public ArrayList<UserInfo> getTop4RecommendUser() {
		// 锟斤拷取锟斤拷锟斤拷锟斤拷锟接池碉拷锟斤拷菘锟侥ｏ拷锟斤拷锟斤拷锟斤拷锟竭讹拷锟斤拷
		JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
		// 锟斤拷询头4锟斤拷锟狡硷拷锟侥诧拷品锟斤拷息
		ArrayList<UserInfo> list = helper.preparedForPageList(
				"select * from Userinfo where recommend=1 order by UserId",
				new Object[] {}, 1, 4, UserInfo.class);

		// 锟斤拷锟截斤拷锟�
		return list;

	}

	public String checkUserPass(Integer userId,String userPass){
		// 获取带有连接池的数据库模版操作工具对象
		JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
		Passport passport = new Passport();
			
			// 查询给定id的员工详细信息
			ArrayList<UserInfo> list = helper.preparedQueryForList(
					"select * from userinfo where userId=?",
					new Object[] { userId }, UserInfo.class);
			UserInfo info = list.get(0);
			if(info.getUserPass().equals(passport.md5(userPass)) == true)
				return "OK";
			else
				return "FAIL";
			
	}

	
	public void modifyUserPasswd(UserInfo info) {
		// 锟斤拷取锟斤拷锟斤拷锟斤拷锟接池碉拷锟斤拷菘锟侥ｏ拷锟斤拷锟斤拷锟斤拷锟竭讹拷锟斤拷
		JDBCTemplateWithDS helper = JDBCTemplateWithDS.getJDBCHelper();
		// 执锟斤拷锟睫改诧拷锟斤拷
		helper.executePreparedUpdate(
				"update Userinfo set userPass=? where userId=?",
				new Object[] {info.getUserPass(),new Integer(info.getUserId())});

	}



}

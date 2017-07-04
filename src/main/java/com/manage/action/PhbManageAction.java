package com.manage.action;

import java.io.File;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import redis.clients.jedis.Jedis;

import com.alibaba.fastjson.JSONObject;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import com.manage.domain.PhbCondition;
import com.manage.model.Channel_Paihangbang;
import com.manage.model.Channel_Paihangbang_Qd;
import com.manage.service.PhbService;
import com.manage.service.QuDongService;
import com.manage.util.RedisUtil;
import com.manage.util.SFTPUtil;

@Controller
@RequestMapping("/phb")
public class PhbManageAction extends AbstractAction{
	@Resource
	private PhbService phbService;
	@Resource
	private QuDongService qudongService;
	
	SFTPUtil sftpUtil = new SFTPUtil();
	
	public static final String REMOTE_PATH = "/data/web/zwsc.ikanshu.cn/paihangImage/";
	/**
	 * 根据条件查询排行榜主榜单信息 zhangzw
	 * @param condition
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 * 
	 */
	@RequestMapping("/listPhb")
	public String listPhb(PhbCondition condition,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		int dqPage = request.getParameter("dqPage")== null? 1 : Integer.parseInt(request.getParameter("dqPage"));
		int totalRow = 0;
		//查询总共条数
		totalRow = this.phbService.findCountByCondition(condition);
		//封装condition
		condition = (PhbCondition)setPageInfo(condition,dqPage,totalRow,20);
		logger.info("condition：{}", condition.toString());
		List<Channel_Paihangbang> list = phbService.findPhbByCondition(condition);
		for (Channel_Paihangbang channel_Paihangbang : list) {
			String pxid = phbService.getPxidByid(channel_Paihangbang.getId());
			channel_Paihangbang.setPxId(pxid);
		}
		obj.put("list", list);
		obj.put("condition", condition);
		return "phb/phb_list";
	}
	
	/**
	 * 根据条件查询排行榜子榜单信息 zhangzw
	 * @param condition
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 * 
	 */
	@RequestMapping("/listPhbSon")
	public String listPhbSon(String id,PhbCondition condition,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		int dqPage = request.getParameter("dqPage")== null? 1 : Integer.parseInt(request.getParameter("dqPage"));
		int totalRow = 0;
		//查询总共条数
		totalRow = this.phbService.findCountByConditionz(id,condition);
		//封装condition
		condition = (PhbCondition)setPageInfo(condition,dqPage,totalRow,20);
		logger.info("condition：{}", condition.toString());
		List<Channel_Paihangbang> list = phbService.findzPhbByCondition(id,condition);
		obj.put("list", list);
		obj.put("id", id);
		obj.put("condition", condition);
		return "phb/phb_list_son";
	}
	
	
	/**
	 * To添加排行榜 zhangzw
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/toAddPhb")
	public String toAddPhb(HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		//List<Channel_Paihangbang_Qudong> qdList = phbService.findQdList();
		List<Channel_Paihangbang_Qd> qdList = qudongService.findQudongList();
		obj.put("qdList", qdList);
		return "phb/phb_add";
	}
	
	/**
	 * 添加排行榜 zhangzw
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/addPhb")
	public String addPhb(Channel_Paihangbang phb,@RequestParam MultipartFile[] icon1, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj,MultipartFile iconn){
		RedisUtil redisUtil = new RedisUtil();
		Jedis jedis = redisUtil.getJedis();

        try {
        //如果存在小图排行榜,则优先创建小图排行榜
        if(icon1.length>0){
        	String[] name1s = request.getParameterValues("name1");//获得小图名称
        	String[] qdId1s = request.getParameterValues("qdId1");//获得小图驱动规则id
        	for (int i = 0; i < icon1.length; i++) {
    			MultipartFile  myfile = icon1[i];
    			if(myfile.isEmpty()){  
                    System.out.println("文件未上传");  
                }else{  
                	// 文件保存路径  
                	String path = request.getSession().getServletContext().getRealPath("/") + "paihangImage/";
                	//截取后缀名
                    String ext = myfile.getOriginalFilename().substring(myfile.getOriginalFilename().lastIndexOf("."));
                    String replace = UUID.randomUUID().toString().replace("-", "");
                    replace+=ext;
                    String filePath =path+ replace;
                    System.out.println(filePath);
                    // 转存文件  
                    myfile.transferTo(new File(filePath));
                    Session session = getSession();
            		ChannelSftp channelSftp = sftpUtil.getChannelSftp(session);
                    sftpUtil.uploadFile(channelSftp,filePath, REMOTE_PATH);
            		sftpUtil.closeConnection(channelSftp, session);
    					
    					Channel_Paihangbang xtPhb = new Channel_Paihangbang();
    					xtPhb.setIcon(replace);
    					xtPhb.setName(name1s[i]);
    					xtPhb.setQdId(qdId1s[i]);
    					xtPhb.setCreateDate(new Date());
    					xtPhb.setUpdateDate(new Date());
    					xtPhb.setCss(3);
    					xtPhb.setFlag(1);
    					xtPhb.setKeyword("");
    					xtPhb.setIntro("");
    					xtPhb.setBdIds("");
    					xtPhb.setDataType(2);
    					xtPhb.setCc(phb.getCc());
    					String key = "phqd_"+xtPhb.getQdId();
    	        		List<String> string = jedis.lrange(key,0,phb.getCc());
    	        		for (String string2 : string) {
    	        			JSONObject jsonObject = JSONObject.parseObject(string2);
    	        			 String bid = (String)jsonObject.get("bid");
    	        			 if(xtPhb.getBids()==null){
    	        				 xtPhb.setBids(bid);
    	        			 }else{
    	        				 xtPhb.setBids(xtPhb.getBids()+","+bid);
    	        			 }
    					}
    					phbService.savePhb(xtPhb);//创建小图榜单
    					String lastPhbId = phbService.getLastPhbId();//获得小图榜单id
    					if(phb.getBdIds()!=null&&phb.getBdIds()!=""){//添加子榜单id
    						phb.setBdIds(phb.getBdIds()+","+lastPhbId);
    					}else{
    						phb.setBdIds(lastPhbId);
    					}
    					
    				
                	 
                }  
    		}	
        }
		
        //如果存在子榜单，则创建子榜单
        if(!request.getParameterValues("name2").equals("")&&request.getParameterValues("name2").length>0&& !"".equals(request.getParameterValues("name2")[0])){
        	String[] name2s = request.getParameterValues("name2");//获得子榜单名称
        	String[] qdId2s = request.getParameterValues("qdId2");//获得子榜单驱动规则id
        	
        	for (int i = 0; i < name2s.length; i++) {
        		Channel_Paihangbang zPhb = new Channel_Paihangbang();
        		zPhb.setName(name2s[i]);
        		zPhb.setQdId(qdId2s[i]);
        		zPhb.setCreateDate(new Date());
        		zPhb.setUpdateDate(new Date());
        		zPhb.setCss(2);
        		zPhb.setFlag(1);
        		zPhb.setIcon("");
        		zPhb.setKeyword("");
        		zPhb.setIntro("");
        		zPhb.setBdIds("");
        		zPhb.setDataType(2);
        		zPhb.setCc(phb.getCc());
        		String key = "phqd_"+zPhb.getQdId();
        		List<String> string = jedis.lrange(key,0,phb.getCc());
        		for (String string2 : string) {
        			JSONObject jsonObject = JSONObject.parseObject(string2);
        			 String bid = (String)jsonObject.get("bid");
        			 if(zPhb.getBids()==null){
        				 zPhb.setBids(bid);
        			 }else{
        				 zPhb.setBids(zPhb.getBids()+","+bid);
        			 }
				}
        		if(zPhb.getBids()==null){
   				 zPhb.setBids("");
   			 }
        		phbService.savePhb(zPhb);//创建子榜单
        		String lastPhbId = phbService.getLastPhbId();//获得子榜单id
				if(phb.getBdIds()!=null&&phb.getBdIds()!=""){//添加子榜单id
					phb.setBdIds(phb.getBdIds()+","+lastPhbId);
				}else{
					phb.setBdIds(lastPhbId);
				}
			}
        }
        //如果存在指数榜单，则创建指数榜单
        if(phb.getCss()==4){
        	String[] name3s = request.getParameterValues("name3");//获得子榜单名称
        	String[] keywords = request.getParameterValues("keyword");//获得指数关键字
        	String[] qdId3s = request.getParameterValues("qdId3");//获得相对应的驱动规则id
        	for (int i = 0; i < keywords.length; i++) {
        		Channel_Paihangbang zsPhb = new Channel_Paihangbang();
        		zsPhb.setName(name3s[i]);
        		zsPhb.setQdId(qdId3s[i]);
        		zsPhb.setKeyword(keywords[i]);
        		zsPhb.setCreateDate(new Date());
        		zsPhb.setUpdateDate(new Date());
        		zsPhb.setCss(4);
        		zsPhb.setFlag(1);
        		zsPhb.setIcon("");
        		zsPhb.setIntro("");
        		zsPhb.setBdIds("");
        		zsPhb.setDataType(2);
        		zsPhb.setCc(phb.getCc());
        		String key = "phqd_"+zsPhb.getQdId();
        		List<String> string = jedis.lrange(key,0,phb.getCc());
        		for (String string2 : string) {
        			JSONObject jsonObject = JSONObject.parseObject(string2);
        			 String bid = (String)jsonObject.get("bid");
        			 if(zsPhb.getBids()==null){
        				 zsPhb.setBids(bid);
        			 }else{
        				 zsPhb.setBids(zsPhb.getBids()+","+bid);
        			 }
				}
        		if(zsPhb.getBids()==null){
   				 zsPhb.setBids("");
   			 }
        		phbService.savePhb(zsPhb);//创建指数榜单
        		String lastPhbId = phbService.getLastPhbId();//获得子榜单id
				if(phb.getBdIds()!=null&&phb.getBdIds()!=""){//添加子榜单id
					phb.setBdIds(phb.getBdIds()+","+lastPhbId);
				}else{
					phb.setBdIds(lastPhbId);
				}
			}
        }
        
        String path = request.getSession().getServletContext().getRealPath("/") + "paihangImage/";
        String ext = iconn.getOriginalFilename().substring(iconn.getOriginalFilename().lastIndexOf("."));//截取后缀名
        String replace = UUID.randomUUID().toString().replace("-", "");
        replace+=ext;
        String filePath =path+ replace;
        System.out.println(filePath);
        iconn.transferTo(new File(filePath));
        
        Session session = getSession();
		ChannelSftp channelSftp = sftpUtil.getChannelSftp(session);
        sftpUtil.uploadFile(channelSftp,filePath, REMOTE_PATH);
		sftpUtil.closeConnection(channelSftp, session);
        
        
        
        
        phb.setIcon(replace);
        phb.setCreateDate(new Date());
		phb.setUpdateDate(new Date());
		phb.setFlag(0);//主榜单
		
		if(phb.getBdIds()==null){
			phb.setBdIds("");
		}
		
		if(phb.getDataType()!=1){
			String key = "phqd_"+phb.getQdId();
			List<String> string = jedis.lrange(key,0,phb.getCc());
//			List<String> string = jedis.lrange("phqd_2",0,phb.getCc());
			for (String string2 : string) {
				JSONObject jsonObject = JSONObject.parseObject(string2);
				 String bid = (String)jsonObject.get("bid");
				 if(phb.getBids()==null){
					 phb.setBids(bid);
				 }else{
					 phb.setBids(phb.getBids()+","+bid);
				 }
			}
		}
		redisUtil.closeJedis(jedis);
		if(phb.getBids()==null){
			phb.setBids("");
		}
        //创建主榜单
		this.phbService.savePhb(phb);
		
		
        } catch (Exception e) {
			e.printStackTrace();
		} 
        
		return "redirect:listPhb";
		
		
	}
	/**
	 * 删除排行榜 zhangzw
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/deletePhb")
	public String deletePhb(String id, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		Channel_Paihangbang phb = this.phbService.getPhbById(id);
		String bdIds = phb.getBdIds();
		String[] split = bdIds.split(",");
		for (String string : split) {
			Channel_Paihangbang zphb=null;
			zphb= this.phbService.getPhbById(string);
			if(zphb!=null){
				this.phbService.deletePhb(string);
			}
		}
		this.phbService.deletePhb(id);
		
		
		return "redirect:listPhb";
	}
	
	/**
	 * To修改排行榜 zhangzw
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/toUpdatePhb")
	public String toUpdatePhb(String id,HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		Channel_Paihangbang phb = this.phbService.getPhbById(id);
		String bdIds = phb.getBdIds();
		List<Channel_Paihangbang> zPhbList = new LinkedList<Channel_Paihangbang>();
		if(bdIds!=null&&!(bdIds.equals(""))){
			String[] split = bdIds.split(",");
			for (String string : split) {
				if(!"".equals(string)){
				Channel_Paihangbang phb2 = this.phbService.getPhbById(string);
				if(phb2!=null){
					zPhbList.add(phb2);
				}
				}
			}
		}
		
		//List<Channel_Paihangbang_Qudong> qdList = phbService.findQdList();
		List<Channel_Paihangbang_Qd> qdList = qudongService.findQudongList();
		obj.put("qdList", qdList);
		obj.put("zPhbList", zPhbList);
		obj.put("phb", phb);
		return "phb/phb_update";
	}
	
	
	/**
	 * 修改排行榜 zhangzw
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/updatePhb")
	public String updatePhb(Channel_Paihangbang phb,String newQdId, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj,MultipartFile iconn){
		RedisUtil redisUtil = new RedisUtil();
		Jedis jedis = redisUtil.getJedis();
		try {
			Channel_Paihangbang phb2 = this.phbService.getPhbById(phb.getId());
			
			if(!iconn.isEmpty()){
				String path = request.getSession().getServletContext().getRealPath("/") + "paihangImage/";
		        String ext = iconn.getOriginalFilename().substring(iconn.getOriginalFilename().lastIndexOf("."));//截取后缀名
		        String replace = UUID.randomUUID().toString().replace("-", "");
		        replace+=ext;
		        String filePath =path+ replace;
		        System.out.println(filePath);
			
				iconn.transferTo(new File(filePath));
				  Session session = getSession();
					ChannelSftp channelSftp = sftpUtil.getChannelSftp(session);
			        sftpUtil.uploadFile(channelSftp,filePath, REMOTE_PATH);
					sftpUtil.closeConnection(channelSftp, session);
			        
			        phb2.setIcon(replace);
			}
			if(phb.getDataType()!=1){
				phb2.setDataType(2);
				if(!(newQdId.equals(phb2.getQdId()))){
					phb2.setQdId(newQdId);
					phb2.setBids("");
					String key = "phqd_"+newQdId;
					List<String> string = jedis.lrange(key,0,phb2.getCc());
//					List<String> string = jedis.lrange("phqd_2",0,phb.getCc());
					for (String string2 : string) {
						JSONObject jsonObject = JSONObject.parseObject(string2);
						 String bid = (String)jsonObject.get("bid");
						 if(phb2.getBids().equals("")){
							 phb2.setBids(bid);
						 }else{
							 phb2.setBids(phb2.getBids()+","+bid);
						 }
					}
				
				}
			}else{
				phb2.setDataType(1);
				phb2.setQdId("");
				phb2.setBids("");
			}
			
			redisUtil.closeJedis(jedis);
			phb2.setName(phb.getName());
			phb2.setUpdateDate(new Date());
			phb2.setCc(phb.getCc());
			phb2.setIntro(phb.getIntro());
			phb2.setIdx(phb.getIdx());
			phb2.setCss(phb.getCss());
			this.phbService.updatePhb(phb2);	
				
			} catch (Exception e) {
				e.printStackTrace();
			}	
		
		
		
		return "redirect:listPhb";
	}
	
	/**
	 * 删除书籍 zhangzw
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/updatePhbBids")
	public String updatePhbBids(String bid,Channel_Paihangbang phb, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		
		phb = this.phbService.getPhbById(phb.getId());
		String bids = phb.getBids();
		String newBids ="";
		if(bids!=null&&bids!=""){
			String[] split = bids.split(",");
			 for (int i = 0; i < split.length; i++) {
					 if(!split[i].equals(bid)){
						 if(newBids!=""){
							 newBids+=","+split[i];
						 }else{
							 newBids=split[i];
						 }
					}
			}	
		}
		phb.setBids(newBids);
		phb.setUpdateDate(new Date());
		this.phbService.updatePhbBook(phb);
		return "redirect:listPhb";
	}
	
	/**
	 * 添加书籍 zhangzw
	 * @param request
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping("/addPhbBids")
	public String addPhbBids(String ids,Channel_Paihangbang phb, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		
		phb = this.phbService.getPhbById(phb.getId());
		String bids = phb.getBids();
		if(bids.equals("")||bids==null){
			bids=ids;
		}else{
			bids +=","+ids;
		}
		phb.setBids(bids);
		phb.setUpdateDate(new Date());
		this.phbService.updatePhbBook(phb);
		return "redirect:listPhb";
	}
	
	@RequestMapping("/toUpdateZphb")
	public String  toUpdateZphb(String id,String fid, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		Channel_Paihangbang phb = this.phbService.getPhbById(id);
		//List<Channel_Paihangbang_Qudong> qdList = phbService.findQdList();
		List<Channel_Paihangbang_Qd> qdList = qudongService.findQudongList();
		obj.put("qdList", qdList);
		obj.put("phb", phb);
		obj.put("fid", fid);
		return "phb/phb_son_update";
	}
	
	@RequestMapping("/updateZphb")
	public String  updateZphb(Channel_Paihangbang  phb,String newQdId,String fid,MultipartFile iconn, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		RedisUtil redisUtil = new RedisUtil();
		Jedis jedis = redisUtil.getJedis();
		try {
			Channel_Paihangbang phb2 = this.phbService.getPhbById(phb.getId());
			
			if(iconn!=null && iconn.getSize()>0){
				String path = request.getSession().getServletContext().getRealPath("/") + "paihangImage/";
		        String ext = iconn.getOriginalFilename().substring(iconn.getOriginalFilename().lastIndexOf("."));//截取后缀名
		        String replace = UUID.randomUUID().toString().replace("-", "");
		        replace+=ext;
		        String filePath =path+ replace;
		        System.out.println(filePath);
			
				iconn.transferTo(new File(filePath));
				  Session session = getSession();
					ChannelSftp channelSftp = sftpUtil.getChannelSftp(session);
			        sftpUtil.uploadFile(channelSftp,filePath, REMOTE_PATH);
					sftpUtil.closeConnection(channelSftp, session);
			        
			        phb2.setIcon(replace);
			}
			if(!(newQdId.equals(phb.getQdId()))){
				phb2.setQdId(newQdId);
				phb2.setBids("");
				String key = "phqd_"+newQdId;
				List<String> string = jedis.lrange(key,0,phb2.getCc());
//				List<String> string = jedis.lrange("phqd_2",0,phb.getCc());
				for (String string2 : string) {
					JSONObject jsonObject = JSONObject.parseObject(string2);
					 String bid = (String)jsonObject.get("bid");
					 if(phb2.getBids().equals("")){
						 phb2.setBids(bid);
					 }else{
						 phb2.setBids(phb2.getBids()+","+bid);
					 }
				}
			
			}
			redisUtil.closeJedis(jedis);
			phb2.setName(phb.getName());
			if(phb.getKeyword()!=null&&!(phb.getKeyword().equals(""))){
				phb2.setKeyword(phb.getKeyword());
			}
			phb2.setUpdateDate(new Date());
			this.phbService.updatePhb(phb2);	
				
			} catch (Exception e) {
				e.printStackTrace();
			}	
		
		
		return "redirect:toUpdatePhb?id="+fid;
	}
	
	@RequestMapping("/deletezPhb")
	public String deletezPhb(String id,String fid, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		Channel_Paihangbang phb = this.phbService.getPhbById(fid);
		String bdIds = phb.getBdIds();
		String[] split = bdIds.split(",");
		String bdisd2 ="";
		for (String string : split) {
			if(!(string.equals(id))){
				if(bdisd2.equals("")){
					bdisd2=string;
				}else{
					bdisd2 +=","+string;
				}
			}
		}
		phb.setBdIds(bdisd2);
		
		this.phbService.updatePhb(phb);
		this.phbService.deletePhb(id);
		
		
		return "redirect:toUpdatePhb?id="+fid;
	}
	@RequestMapping("/toAddZphb")
	public String  toAddZphb(String fid, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		//List<Channel_Paihangbang_Qudong> qdList = phbService.findQdList();
		List<Channel_Paihangbang_Qd> qdList = qudongService.findQudongList();
		obj.put("qdList", qdList);
		obj.put("fid", fid);
		return "phb/phb_son_add";
	}
	@RequestMapping("/addZphb")
	public String  addZphb(Channel_Paihangbang  phb,String fid,MultipartFile iconn, HttpServletRequest request, HttpServletResponse response, Map<String, Object> obj){
		RedisUtil redisUtil = new RedisUtil();
		Jedis jedis = redisUtil.getJedis();
		try {
			Channel_Paihangbang fphb = this.phbService.getPhbById(fid);
				
		        if(phb.getCss()==3){
		        	String path = request.getSession().getServletContext().getRealPath("/") + "paihangImage/";
			        String ext = iconn.getOriginalFilename().substring(iconn.getOriginalFilename().lastIndexOf("."));//截取后缀名
			        String replace = UUID.randomUUID().toString().replace("-", "");
			        replace+=ext;
			        String filePath =path+ replace;
			        System.out.println(filePath);	
			        iconn.transferTo(new File(filePath));
					  Session session = getSession();
						ChannelSftp channelSftp = sftpUtil.getChannelSftp(session);
				        sftpUtil.uploadFile(channelSftp,filePath, REMOTE_PATH);
						sftpUtil.closeConnection(channelSftp, session);
				        
				        phb.setIcon(replace);
		        }else{
		        	phb.setIcon("");
		        }
				
				String key = "phqd_"+phb.getQdId();
				List<String> string = jedis.lrange(key,0,fphb.getCc());
//				List<String> string = jedis.lrange("phqd_2",0,phb.getCc());
				for (String string2 : string) {
					JSONObject jsonObject = JSONObject.parseObject(string2);
					 String bid = (String)jsonObject.get("bid");
					 if(phb.getBids()==null){
						 phb.setBids(bid);
					 }else{
						 phb.setBids(phb.getBids()+","+bid);
					 }
				}
			
			redisUtil.closeJedis(jedis);
			
			
			
			if(phb.getKeyword()==null){
				phb.setKeyword("");
			}
			phb.setCc(fphb.getCc());
			phb.setDataType(2);
			phb.setBdIds("");
			phb.setIntro("");
			phb.setFlag(1);
			phb.setCreateDate(new Date());
			phb.setUpdateDate(new Date());
			this.phbService.savePhb(phb);	//创建子榜单
			String lastPhbId = phbService.getLastPhbId();
			String bdIds = fphb.getBdIds();
			if(bdIds!=null&&!(bdIds.equals(""))){
				bdIds+=","+lastPhbId;
			}else{
				bdIds=lastPhbId;
			}
			fphb.setBdIds(bdIds);
			this.phbService.updatePhb(fphb);
			
			} catch (Exception e) {
				e.printStackTrace();
			}	
		
		
		return "redirect:toUpdatePhb?id="+fid;
	}
	
	
	
	
	
	public Session getSession() {
		//正式
		Session session = sftpUtil.getConnection("60.29.240.243", 10022, "iwanvi", "ChineseAll*()@iwanvi");
		//测试
		//Session session = sftpUtil.getConnection("60.28.209.235", 10022, "root", "zwsctest@2015");
		return session;
	}
	
}

package com.javalec.spring_pjt_board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.javalec.spring_pjt_board.dto.BDto;

public class BDao {
	
	DataSource dataSource;
	
	public BDto contentView(String strID) {
		
		upHit(strID);
		
		BDto dto = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		
		try {
			con = dataSource.getConnection();
			
			String query = "select * from mvc_board where bId = ?";
			pst = con.prepareStatement(query);
			pst.setInt(1, Integer.parseInt(strID));
			rst = pst.executeQuery();
			
			if(rst.next()) {
				int bId  = rst.getInt("bId");
				String bName = rst.getString("bName");
				String bTitle = rst.getString("bTitle");
				String bContent = rst.getString("bContent");
				Timestamp bDate = rst.getTimestamp("bDate");
				int bHit = rst.getInt("bHit");
				int bGroup = rst.getInt("bGroup");
				int bStep = rst.getInt("bStep");
				int bIndent = rst.getInt("bIndent");

				dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				
				if(rst != null) rst.close();
				if(pst != null) pst.close();
				if(con != null) con.close();
				
				
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return dto;
	}
	
	public void write(String bName, String bTitle, String bContent){
		
		Connection con = null;
		PreparedStatement pst = null;
		
		try {
			
			con = dataSource.getConnection();
			String query = "insert into mvc_board (bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) values (mvc_board_seq.nextval,?,?,?,0, mvc_board_seq.currval, 0, 0)";
			pst = con.prepareStatement(query);
			pst.setString(1, bName);
			pst.setString(2, bTitle);
			pst.setString(3, bContent);

			int rn = pst.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
		}
		
	}
	
	public BDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<BDto> list(){
		
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		
		try {
			con = dataSource.getConnection();
			String query = "select bId , bName, bTitle, bContent, bDate , bHit, bGroup ,bStep, bIndent from mvc_board order by bGroup desc, bStep asc";
			pst = con.prepareStatement(query);                          // bGroup desc 원글의 내림차순 , bStep asc 원글의 작성 등록순 
			rst = pst.executeQuery();
			
			while(rst.next()){
				int bId = rst.getInt("bId");
				String bName = rst.getString("bName");
				String bTitle = rst.getString("bTitle");
				String bContent = rst.getString("bContent");
				Timestamp bDate = rst.getTimestamp("bDate");
				int bHit = rst.getInt("bHit");
				int bGroup = rst.getInt("bGroup");
				int bStep = rst.getInt("bStep");
				int bIndent = rst.getInt("bIndent");
				
				BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
				dtos.add(dto);
				
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rst != null) rst.close();
				if(pst != null) pst.close();
				if(con != null) con.close();
			}catch(Exception e2) {
			}
		}
		
		return dtos;
	}
	
	private void upHit(String bId) {
		Connection con = null;
		PreparedStatement pst = null;
		
		try {
			
			con = dataSource.getConnection();
			String query = "update mvc_board set bHit = bHit + 1 where bId = ?";
			pst = con.prepareStatement(query);
			pst.setString(1, bId);
			
			int rn = pst.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if(pst != null) pst.close();
				if(con != null) con.close();
				
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
}
